package com.sprint.mission.discodeit.controller;

import com.sprint.mission.discodeit.dto.UserRoleUpdateRequest;
import com.sprint.mission.discodeit.dto.data.JwtDto;
import com.sprint.mission.discodeit.dto.data.UserDto;
import com.sprint.mission.discodeit.exception.ErrorResponse;
import com.sprint.mission.discodeit.security.DiscodeitUserDetails;
import com.sprint.mission.discodeit.security.JwtTokenProvider;
import com.sprint.mission.discodeit.service.UserService;
import com.sprint.mission.discodeit.service.basic.DiscodeitUserDetailsService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final UserService userService;
  private final JwtTokenProvider jwtTokenProvider;
  private final DiscodeitUserDetailsService userDetailsService;

  @GetMapping("csrf-token")
  public ResponseEntity<Void> getCsrfToken(CsrfToken csrfToken) {
    log.debug("CSRF 토큰 요청: {}", csrfToken.getToken());
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  // GET /auth/me 삭제 (프론트가 브라우저 메모리에서 관리)

  @PutMapping
  public ResponseEntity<UserDto> updateRole(@RequestBody UserRoleUpdateRequest request) {
    UserDto userDto = userService.updateRole(request);
    return ResponseEntity.ok(userDto);
  }

  @PostMapping("refresh")
  public ResponseEntity<?> refresh(HttpServletRequest request, HttpServletResponse response) {
    // 1. 쿠키에서 REFRESH_TOKEN 꺼내기
    String refreshToken = null;
    if (request.getCookies() != null) {
      refreshToken = Arrays.stream(request.getCookies())
          .filter(c -> "REFRESH_TOKEN".equals(c.getName()))
          .map(Cookie::getValue)
          .findFirst()
          .orElse(null);
    }

    // 2. 리프레시 토큰 없거나 유효하지 않으면 401 반환
    if (refreshToken == null || !jwtTokenProvider.validateToken(refreshToken)) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body(new ErrorResponse(
              Instant.now(),
              "INVALID_REFRESH_TOKEN",
              "유효하지 않은 리프레시 토큰입니다.",
              Map.of(),
              "TokenException",
              HttpStatus.UNAUTHORIZED.value()
          ));
    }

    // 3. 토큰에서 유저 정보 꺼내기
    String username = jwtTokenProvider.getUsername(refreshToken);
    UUID userId = jwtTokenProvider.getUserId(refreshToken);
    String role = jwtTokenProvider.getRole(refreshToken);

    // 4. 새 Access Token 발급
    String newAccessToken = jwtTokenProvider.generateAccessToken(userId, username, role);

    // 5. Refresh Token Rotation: 새 Refresh Token도 발급해서 쿠키 교체 (보안 강화)
    String newRefreshToken = jwtTokenProvider.generateRefreshToken(userId, username, role);
    Cookie newRefreshCookie = new Cookie("REFRESH_TOKEN", newRefreshToken);
    newRefreshCookie.setHttpOnly(true);
    newRefreshCookie.setPath("/");
    response.addCookie(newRefreshCookie);

    // 6. UserDto 조회 후 JwtDto로 응답
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    DiscodeitUserDetails discodeitUserDetails = (DiscodeitUserDetails) userDetails;
    UserDto userDto = discodeitUserDetails.getUserDto();

    return ResponseEntity.ok(new JwtDto(userDto, newAccessToken));
  }
}
