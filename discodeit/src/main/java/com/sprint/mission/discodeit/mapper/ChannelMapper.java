package com.sprint.mission.discodeit.mapper;

import com.sprint.mission.discodeit.dto.data.ChannelDto;
import com.sprint.mission.discodeit.dto.data.UserDto;
import com.sprint.mission.discodeit.entity.Channel;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChannelMapper {

  private final UserMapper userMapper;

  public ChannelDto toDto(Channel channel) {
    if (channel == null) {
      return null;
    }

    // 참여자(Entity) 리스트를 UserDto 리스트로 변환합니다.
    List<UserDto> participants = channel.getParticipants().stream()
        .map(userMapper::toDto)
        .toList();

    return new ChannelDto(
        channel.getId(),
        channel.getType(),
        channel.getName(),
        channel.getDescription(),
        participants, // 수정했던 List<UserDto> 타입에 맞게 들어갑니다.
        channel.getLastMessageAt()
    );
  }
}