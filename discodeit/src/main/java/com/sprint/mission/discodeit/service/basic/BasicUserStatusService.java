package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.dto.UserStatusCreateRequest;
import com.sprint.mission.discodeit.dto.UserStatusResponse;
import com.sprint.mission.discodeit.dto.UserStatusUpdateRequest;
import com.sprint.mission.discodeit.service.UserStatusService;
import com.sprint.mission.discodeit.repository.UserStatusRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BasicUserStatusService implements UserStatusService {

    private final UserStatusRepository userStatusRepository;
    private final UserRepository userRepository;

    @Override
    public String create(UserStatusCreateRequest request) {
        if (!userRepository.existsById(request.userId())) {
            throw new RuntimeException("해당 유저를 찾을 수 없습니다.");
        }
        if (userStatusRepository.existsByUserId(request.userId())) {
            throw new RuntimeException("이미 상태 정보가 존재하는 유저입니다.");
        }
        return null;
    }

    @Override
    public UserStatusResponse find(String id) {
        return null;
    }

    @Override
    public List<UserStatusResponse> findAll() {
        return null;
    }

    @Override
    public void update(String id, UserStatusUpdateRequest request) {
    }

    @Override
    public void updateByUserId(String userId, UserStatusUpdateRequest request) {
    }

    @Override
    public void delete(String id) {
    }
}