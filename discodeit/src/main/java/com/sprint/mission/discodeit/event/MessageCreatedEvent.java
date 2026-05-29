package com.sprint.mission.discodeit.event;

import com.sprint.mission.discodeit.entity.Message;

public record MessageCreatedEvent(
    Message message  // 생성된 메시지 전체를 담아요
) {
}