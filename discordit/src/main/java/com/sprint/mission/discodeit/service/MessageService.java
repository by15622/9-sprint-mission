package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.Message;
import java.util.List;
import java.util.UUID;    //필요한 함수를 사용하기위해 외부의 라이브러리를 가져온다


public interface MessageService {
    // 1. 메시지 생성 (강사님 코드의 create에 해당)
    Message create(String content, UUID channelId, UUID authorId);

    // 2. 단건 조회
    Message find(UUID id);

    // 3. 전체 조회
    List<Message> findAll();

    // 4. 내용 수정 (강사님 코드의 update에 해당)
    Message update(UUID id, String content);

    // 5. 삭제
    boolean delete(UUID id);
}