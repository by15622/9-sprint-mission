package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.dto.UserStatusCreateRequest;
import com.sprint.mission.discodeit.dto.UserStatusResponse;
import com.sprint.mission.discodeit.dto.UserStatusUpdateRequest;
import java.util.List;

public interface UserStatusService {
    String create(UserStatusCreateRequest request);
    UserStatusResponse find(String id);
    List<UserStatusResponse> findAll();
    void update(String id, UserStatusUpdateRequest request);
    void updateByUserId(String userId, UserStatusUpdateRequest request);
    void delete(String id);
}