package com.sprint.mission.discodeit.repository.jcf;

import com.sprint.mission.discodeit.entity.ReadStatus;
import com.sprint.mission.discodeit.repository.ReadStatusRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class JcfReadStatusRepository implements ReadStatusRepository {
    private final Map<UUID, ReadStatus> database = new HashMap<>();

    @Override
    public ReadStatus save(ReadStatus readStatus) {
        database.put(readStatus.getId(), readStatus);
        return readStatus;
    }

    @Override
    public Optional<ReadStatus> findById(UUID id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public List<ReadStatus> findAllByUserId(UUID userId) {
        return database.values().stream()
                .filter(status -> status.getUserId().equals(userId))
                .toList();
    }

    @Override
    public boolean existsByUserIdAndChannelId(UUID userId, UUID channelId) {
        return database.values().stream()
                .anyMatch(status -> status.getUserId().equals(userId)
                        && status.getChannelId().equals(channelId));
    }

    @Override
    public boolean existsById(UUID id) {
        return database.containsKey(id);
    }

    @Override
    public void deleteById(UUID id) {
        database.remove(id);
    }
}