package com.sprint.mission.discodeit.dto.data;

import com.sprint.mission.discodeit.entity.User;
import java.time.Instant;
import java.util.UUID;

public record UserDto(
    UUID id,
    String username,
    Boolean online,
    Instant lastActive
) {

  public static UserDto from(User user) {
    return new UserDto(
        user.getId(),
        user.getUsername(),
        false,
        Instant.now()
    );
  }
}