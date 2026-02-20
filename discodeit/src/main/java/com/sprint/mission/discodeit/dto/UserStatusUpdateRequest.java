package com.sprint.mission.discodeit.dto;

import java.time.Instant;

// record는 필드 뒤에 세미콜론(;)을 찍지 않습니다!
public record UserStatusUpdateRequest(
    Instant newLastActiveAt,
    Boolean online
) {

}