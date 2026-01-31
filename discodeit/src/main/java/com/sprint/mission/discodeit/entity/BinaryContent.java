package com.sprint.mission.discodeit.entity;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class BinaryContent {
    private final UUID id;
    private final Instant createdAt;
    private String fileName;
    private String contentType;
    private Long size;
    private UUID userId;
    private UUID messageId;

    public BinaryContent(String fileName, String contentType, Long size) {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        this.fileName = fileName;
        this.contentType = contentType;
        this.size = size;
//      this.userId = userId;
    }
}