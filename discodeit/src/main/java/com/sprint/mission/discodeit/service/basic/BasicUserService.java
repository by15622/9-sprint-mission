package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.dto.UserCreateRequest;
import com.sprint.mission.discodeit.dto.UserStatusResponse;
import com.sprint.mission.discodeit.dto.UserUpdateRequest;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.entity.UserStatus;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.repository.UserStatusRepository;
import com.sprint.mission.discodeit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import com.sprint.mission.discodeit.dto.UserCreateRequest;
@Service
@RequiredArgsConstructor
public class BasicUserService implements UserService {
    private final UserRepository userRepository;
    private final UserStatusRepository userStatusRepository;

    @Override
    public User create(UserCreateRequest request) {
        // request 뒤에 .username() 처럼 괄호를 꼭 붙여주세요!
        User user = new User(request.username(), request.email(), request.password());
        User savedUser = userRepository.save(user);

        UserStatus status = new UserStatus(savedUser.getId());
        userStatusRepository.save(status);

        return savedUser;
    }

    @Override
    public UserStatusResponse find(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        // 2. 유저의 접속 상태 정보를 찾습니다. (이때 userStatusRepository를 사용합니다)
        UserStatus status = userStatusRepository.findByUserId(userId)
                .orElseThrow(() -> new NoSuchElementException("User status not found"));

        // 3. DTO 상자에 정보를 예쁘게 담아서 돌려줍니다.
        return new UserStatusResponse(
                user.getUsername(),           // 사용자 이름
                status.isOnline(),            // 5분 이내 접속 여부 (아까 만든 로직!)
                status.getUpdatedAt().toString() // 마지막 접속 시간
        );
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(UUID userId, UserUpdateRequest request) {
        // 1. 수정할 유저를 찾습니다.
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        // 2. DTO 상자에서 꺼낸 값들로 업데이트합니다.
        user.update(
                request.name(),
                request.email(),
                request.password()
        );

        // 3. [고도화 추가 미션] 프로필 이미지가 있다면 연결하는 로직입니다.
        if (request.profileImageId() != null) {
            // 프로필 이미지를 대체하는 로직을 여기에 넣을 수 있습니다.
        }

        return userRepository.save(user);
    }

    @Override
    public void delete(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new NoSuchElementException("User not found");
        }
        // 1. [고도화] 유저를 지우기 전에 연결된 상태 정보를 먼저 지웁니다.
        userStatusRepository.deleteByUserId(userId);

        // 2. [고도화] 프로필 이미지(BinaryContent)도 있다면 지워줍니다.
        // binaryContentRepository.deleteByUserId(userId); (레포지토리에 메서드 추가 필요)

        // 3. 마지막으로 유저 정보를 지웁니다.
        userRepository.deleteById(userId);
    }
}
