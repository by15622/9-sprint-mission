package com.sprint.mission.discodeit.dto;

import com.sprint.mission.discodeit.entity.UserStatus;

public record UserStatusResponse(
        String nickname,
        boolean isOnline,
        String lastActive
) {
    public UserStatusResponse(UserStatus userStatus) {
        this(
                userStatus.getUserId().toString(),
                userStatus.isOnline(),
                userStatus.getUpdatedAt().toString()
        );
    }
}