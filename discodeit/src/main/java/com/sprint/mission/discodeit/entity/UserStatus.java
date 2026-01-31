package com.sprint.mission.discodeit.entity;

import lombok.Getter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class UserStatus {
    private final UUID id;
    private final Instant createdAt;
    private Instant updatedAt;
    private UUID userId;
    private String type;

    public UserStatus(UUID userId, String type) {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
        this.userId = userId;
        this.type = type;
    }

    public UserStatus(UUID userId) {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
        this.userId = userId;
        this.type = "OFFLINE";           //유저 상태구별
    }

    public void update(String type) {
        this.type = type;
        this.updatedAt = Instant.now();
    }

    public boolean isOnline() {
        return updatedAt != null && updatedAt.isAfter(Instant.now().minusSeconds(300));
    }
}