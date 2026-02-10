package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.dto.BinaryContentCreateRequest;
import com.sprint.mission.discodeit.entity.BinaryContent;

public interface BinaryContentService {
    BinaryContent create(BinaryContentCreateRequest request);
}