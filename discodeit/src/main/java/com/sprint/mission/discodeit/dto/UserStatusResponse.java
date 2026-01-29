package com.sprint.mission.discodeit.dto;

public record UserStatusResponse(
        String nickname,
        boolean isOnline,
        String lastActive
) {}