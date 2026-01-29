package com.sprint.mission.discodeit.dto;

import java.util.UUID;

public record FileInfoResponse(
        UUID fileId,
        String fileName,
        String fileUrl
) {}