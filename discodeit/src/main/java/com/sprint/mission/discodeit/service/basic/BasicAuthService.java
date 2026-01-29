package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.dto.LoginRequest;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BasicAuthService implements AuthService {

    // 유저 정보를 확인하기 위해 UserRepository 창고가 필요합니다.
    private final UserRepository userRepository;

    @Override
    public User login(LoginRequest request) {

        return userRepository.findByUsername(request.username())
                .filter(user -> user.getPassword().equals(request.password()))
                .orElseThrow(() -> new NoSuchElementException("아이디 또는 비밀번호가 일치하지 않습니다."));
    }
}