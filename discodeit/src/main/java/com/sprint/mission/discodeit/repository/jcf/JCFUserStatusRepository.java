package com.sprint.mission.discodeit.repository.jcf;

import com.sprint.mission.discodeit.entity.UserStatus;
import com.sprint.mission.discodeit.repository.UserStatusRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@ConditionalOnProperty(name = "discodeit.repository.type", havingValue = "jcf", matchIfMissing = true)
public class JCFUserStatusRepository implements UserStatusRepository {

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
  public Optional<UserStatus> findById(UUID id) {
    return userStatuses.stream()
        .filter(status -> status.getId() == id)
        .findFirst();
  }


  @Override
  public List<UserStatus> findAll() {
    return new ArrayList<>(userStatuses);
  }


  @Override
  public void deleteById(UUID id) {
    userStatuses.removeIf(userStatus -> userStatus.getUserId().equals(id));
  }


  @Override
  public void deleteByUserId(UUID userId) {
    userStatuses.removeIf(status -> status.getUserId().equals(userId));
  }

  @Override
  public boolean existsById(UUID id) { // 1. 매개변수 이름을 확인하세요.
    return userStatuses.stream()
        .anyMatch(userStatus -> userStatus.getUserId().equals(id)); // 2. 여기도 똑같이 소문자 'id'로 맞추세요.
  }
}
