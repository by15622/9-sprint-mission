package com.sprint.mission.discodeit.repository.jcf;

import com.sprint.mission.discodeit.entity.UserStatus;
import com.sprint.mission.discodeit.repository.UserStatusRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JcfUserStatusRepository implements UserStatusRepository {
    @Override
    public Optional<UserStatus> findByUserId(UUID userId) {

        return Optional.empty();
    }
    @Override
    public UserStatus save(UserStatus userStatus) {
        return userStatus;
    }
    @Override
    public void deleteByUserId(UUID userId) {
    }
}