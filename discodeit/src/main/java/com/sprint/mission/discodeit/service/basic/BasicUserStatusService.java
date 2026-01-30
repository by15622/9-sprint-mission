package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.dto.UserStatusCreateRequest;
import com.sprint.mission.discodeit.dto.UserStatusResponse;
import com.sprint.mission.discodeit.dto.UserStatusUpdateRequest;
import com.sprint.mission.discodeit.entity.UserStatus;
import com.sprint.mission.discodeit.service.UserStatusService;
import com.sprint.mission.discodeit.repository.UserStatusRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
        UserStatus userStatus = new UserStatus(request.userId(), request.type());
        UserStatus savedStatus = userStatusRepository.save(userStatus);

        return savedStatus.getId();
    }

    @Override
    public UserStatusResponse find(String id) {
        UserStatus userStatus = userStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 상태 정보를 찾을 수 없습니다."));

        return new UserStatusResponse(userStatus);
    }

    @Override
    public List<UserStatusResponse> findAll() {
        return userStatusRepository.findAll().stream()
                .map(UserStatusResponse::new)
                .toList();
    }

    @Override
    public void update(String id, UserStatusUpdateRequest request) {
    }

    @Override
    public void updateByUserId(String userId, UserStatusUpdateRequest request) {
        UserStatus userStatus = userStatusRepository.findByUserId(UUID.fromString(userId))
                .orElseThrow(() -> new RuntimeException("해당 유저의 상태 정보를 찾을 수 없습니다."));
        userStatus.update(request.type());
        userStatusRepository.save(userStatus);
    }

    @Override
    public void delete(String id) {
        userStatusRepository.deleteByUserId(UUID.fromString(id));
    }

}