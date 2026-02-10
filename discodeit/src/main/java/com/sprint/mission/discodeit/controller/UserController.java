package com.sprint.mission.discodeit.controller;

import com.sprint.mission.discodeit.dto.*;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(
            path = "create",
            method = RequestMethod.POST,
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}
    )
    public ResponseEntity<User> create(
            @RequestPart("userCreateRequest") UserCreateRequest userCreateRequest,
            @RequestPart(value = "profile", required = false) MultipartFile profile
    ) {
        Optional<BinaryContentCreateRequest> profileRequest = Optional.ofNullable(profile)
                .flatMap(this::resolveProfileRequest);

        User createdUser = userService.create(userCreateRequest, profileRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdUser);
    }

    private Optional<BinaryContentCreateRequest> resolveProfileRequest(MultipartFile profile) {
        if (profile == null || profile.isEmpty()) return Optional.empty(); // null 체크 추가
        try {
            return Optional.of(new BinaryContentCreateRequest(
                    profile.getOriginalFilename(),
                    profile.getContentType(),
                    profile.getBytes()
            ));
        } catch (IOException e) {
            throw new RuntimeException("프로필 파일 처리 중 오류 발생", e);
        }
    }

    @RequestMapping(
            path = "/{userId}", // 방 번호 대신 {userId}라는 변수 칸을 만듭니다.
            method = RequestMethod.PATCH
    )
    public ResponseEntity<User> update(
            @PathVariable UUID userId, // 주소에 담긴 ID를 꺼냅니다.
            @RequestBody UserUpdateRequest request // 몸통에 담긴 수정 정보를 꺼냅니다.
    ) {
        // 서비스(요리사)에게 일을 시킵니다.
        User updatedUser = userService.update(userId, request);

        // 200 OK 상태와 함께 결과물을 보냅니다.
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedUser);
    }



}