package com.sprint.mission.discodeit.event;

import com.sprint.mission.discodeit.dto.data.MessageDto;

public record MessageCreatedEvent(
    MessageDto message  // 생성된 메시지 전체를 담아요
) {
}