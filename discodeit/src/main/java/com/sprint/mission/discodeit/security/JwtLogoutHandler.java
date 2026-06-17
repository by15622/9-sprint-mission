package com.sprint.mission.discodeit.security;

import com.sprint.mission.discodeit.dto.data.UserDto;
import com.sprint.mission.discodeit.service.SseService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtLogoutHandler implements LogoutHandler {

  private final SseService sseService;

  @Override
  public void logout(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) {

    Cookie deleteCookie = new Cookie("REFRESH_TOKEN", null);
    deleteCookie.setHttpOnly(true);
    deleteCookie.setPath("/");
    deleteCookie.setMaxAge(0);
    response.addCookie(deleteCookie);

    if (authentication != null && authentication.getPrincipal() instanceof DiscodeitUserDetails userDetails) {
      UserDto userDto = userDetails.getUserDto();
      sseService.broadcast("users.updated", userDto);
    }
  }
}