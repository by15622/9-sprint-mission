package com.sprint.mission.discodeit.repository.jcf;

import com.sprint.mission.discodeit.entity.UserStatus;
import com.sprint.mission.discodeit.repository.UserStatusRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JcfUserStatusRepository implements UserStatusRepository {
    private final List<UserStatus> userStatuses = new ArrayList<>();

    @Override
    public Optional<UserStatus> findByUserId(UUID userId) {
        return userStatuses.stream()
                .filter(status -> status.getUserId().equals(userId))
                .findFirst();
    }

    @Override
    public UserStatus save(UserStatus userStatus) {
        userStatuses.add(userStatus);
        return userStatus;
    }

    @Override
    public Optional<UserStatus> findById(String id) {
        return userStatuses.stream()
                .filter(status -> status.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<UserStatus> findAll() {
        return new ArrayList<>(userStatuses);
    }

    @Override
    public void deleteByUserId(UUID userId) {
    }

    @Override
    public boolean existsByUserId(UUID userId) {
        return userStatuses.stream()
                .anyMatch(userStatus -> userStatus.getUserId().equals(userId));
    }
}