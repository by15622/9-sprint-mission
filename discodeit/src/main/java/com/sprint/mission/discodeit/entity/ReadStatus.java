package com.sprint.mission.discodeit.entity;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class ReadStatus {
    private final UUID id;
    private final Instant createdAt;
    private Instant updatedAt;
    private UUID userId;
    private UUID channelId;
    private UUID lastReadMessageId;

    public ReadStatus(UUID userId, UUID channelId, UUID lastReadMessageId) {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
        this.userId = userId;
        this.channelId = channelId;
        this.lastReadMessageId = lastReadMessageId;
    }
    public void update(UUID lastReadMessageId) {
        this.lastReadMessageId = lastReadMessageId;
        this.updatedAt = Instant.now();
    }
}