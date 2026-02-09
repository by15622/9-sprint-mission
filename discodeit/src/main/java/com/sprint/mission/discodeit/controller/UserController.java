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
                    profile.getSize(),
                    profile.getInputStream()
            ));
        } catch (IOException e) {
            throw new RuntimeException("프로필 파일 처리 중 오류 발생", e);
        }
    }

    @RequestMapping(method = RequestMethod.GET) // GET /api/user
    public List<UserStatusResponse> findAll() {
        return userService.findAll();
    }

    @RequestMapping(path = "{userId}", method = RequestMethod.PATCH) // PATCH /api/user/{userId}
    public User update(
            @PathVariable UUID userId,
            @RequestBody UserUpdateRequest request
    ) {
        return userService.update(userId, request);
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.GET) // 주소 뒤에 ID를 붙인 GET 요청
    public UserStatusResponse find(@PathVariable UUID userId) {
        return userService.find(userId);
    }

    @RequestMapping(path = "", method = RequestMethod.POST) // 주소: /api/user (POST 방식)
    public User create(@RequestBody UserCreateRequest request) {
        return userService.create(request, Optional.empty());
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable UUID userId) {
        userService.delete(userId);
    }

    @RequestMapping(path = "/{userId}/status", method = RequestMethod.PATCH)
    public void updateStatus(@PathVariable UUID userId, @RequestParam boolean online) {
        userService.updateStatus(userId, online);
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public User login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }
}