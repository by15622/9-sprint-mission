package com.sprint.mission.discodeit.dto.data;

import java.time.Instant;
import java.util.UUID;

public record UserDto(
    UUID id,
    String username,
    Boolean online,
    Instant lastActive
) {

}