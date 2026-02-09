package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.dto.*;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.entity.UserStatus;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.repository.UserStatusRepository;
import com.sprint.mission.discodeit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import com.sprint.mission.discodeit.dto.UserCreateRequest;
@Service
@RequiredArgsConstructor
public class BasicUserService implements UserService {
    private final UserRepository userRepository;
    private final UserStatusRepository userStatusRepository;

    @Override
    public User create(UserCreateRequest request, Optional<BinaryContentCreateRequest> profileRequest) {
        User user = new User(request.username(), request.email(), request.password());
        User savedUser = userRepository.save(user);
        UserStatus status = new UserStatus(savedUser.getId());
        userStatusRepository.save(status);

        return savedUser;
    }
/* 매개변수로 UserCreateRequest를 받아서 request에 이름,이메일,비밀번호로 새유저를 만들고
userRepository.save메서드의 매개변수로 user를 넣어서 리턴값으로 반환된 user를 saveduser로 저장한다
savedUser의 id로 new UserStatus로 만들어서 status로 저장하고 userStatusRepository.save의 메서드 매개변수로
입력해서 호출한다 return savedUser반환한다
*/
    @Override
    public UserStatusResponse find(UUID userId) {
        // 유저 정보를 먼저 찾습니다.
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        //  상태 정보를 찾되, 없으면 에러를 내지 말고 새로 만듭니다!
        UserStatus status = userStatusRepository.findByUserId(userId)
                .orElseGet(() -> {
                    // 데이터 구멍을 메워주기 위해 임시 상태 객체를 생성합니다.
                    return new UserStatus(userId);
                });

        return new UserStatusResponse(
                user.getId().toString(),
                user.getUsername(),
                status.isOnline(),
                status.getUpdatedAt() != null ? status.getUpdatedAt().toString() : "N/A"
        );
    }

    @Override
    public List<UserStatusResponse> findAll() {
        return userRepository.findAll().stream()
                .limit(2)
                .map(user -> find(user.getId()))
                .toList();
    }
/*  유저에 있는 모든 데이터를 가져와서 스트림 형식으로 바꾸고 .map부분에서 user객체를 하나씩 꺼내고
 find 메소드를 이용해서 id로 추출한 유저정보를 UserStatusResponse변환하고 리스트 형식으로 만든다
 */
    @Override
    public User update(UUID userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        user.update(
                request.username(),
                request.email(),
                request.password()
        );
        return userRepository.save(user);
    }
/* 매개변수로 userid와 UserUpdateRequest를 받는다  userRepository.findById메서드에 useerid를 매개변수로 입력해서 리턴값으로 반환된 User객체를
user에 넣는다 반환된 user가 없으면 에러를 던진다. user.update메서드를 통해 request에 담긴 이름,이메일,비밀번호로 변경한다.
리턴값으로 userRepository.save 메서드의 반환값을 보낸다
 */

    @Override
    public void delete(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new NoSuchElementException("User not found");
        }
        userStatusRepository.deleteByUserId(userId);
        userRepository.deleteById(userId);
    }

    @Override
    public void updateStatus(UUID userId, boolean online) {
        // 1. 창고에서 유저를 찾습니다. 없으면 에러를 냅니다.
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        // 2. online 값에 따라 유저의 상태를 바꿔줍니다.
        if (online) {
            user.online();  // 온라인 불 켜기
        } else {
            user.offline(); // 온라인 불 끄기
        }

        // 3. 바뀐 상태를 창고(파일)에 저장합니다!
        userRepository.save(user);
    }

    @Override
    public User login(LoginRequest request) {
        // 1. 이름으로 유저를 찾습니다. (이름이 틀리면 에러)
        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new NoSuchElementException("사용자를 찾을 수 없습니다."));

        // 2. 비밀번호가 일치하는지 확인합니다. (비밀번호가 틀리면 에러)
        if (!user.getPassword().equals(request.password())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }

        // 3. 통과하면 유저 정보를 돌려줍니다.
        return user;
    }
}
