package com.sprint.mission.discodeit.entity;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class UserStatus {
    private final UUID id;
    private final Instant createdAt;
    private Instant updatedAt;
    private UUID userId;

    public UserStatus(UUID userId) {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        this.userId = userId;
    }

    // 5분 이내 접속 여부 확인 메서드
    public boolean isOnline() {
        return updatedAt != null && updatedAt.isAfter(Instant.now().minusSeconds(300));
    }
}