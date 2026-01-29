package com.sprint.mission.discodeit.dto;

import java.util.UUID;

public record UserCreateRequest(
        String username,
        String email,
        String password,
        UUID profileImageId // 프로필 이미지는 선택사항이라 UUID로 받아요
) {}