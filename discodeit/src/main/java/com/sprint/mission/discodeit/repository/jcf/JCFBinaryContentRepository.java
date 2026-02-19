package com.sprint.mission.discodeit.repository.jcf;

import com.sprint.mission.discodeit.entity.BinaryContent;
import com.sprint.mission.discodeit.repository.BinaryContentRepository;
import java.util.List;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@ConditionalOnProperty(name = "discodeit.repository.type", havingValue = "jcf", matchIfMissing = true)
public class JCFBinaryContentRepository implements BinaryContentRepository {

  private final Map<UUID, BinaryContent> data = new ConcurrentHashMap<>();

  @Override
  public BinaryContent save(BinaryContent binaryContent) {
    data.put(binaryContent.getId(), binaryContent);
    return binaryContent;
  }

  @Override
  public Optional<BinaryContent> findById(UUID id) {
    return Optional.ofNullable(data.get(id));
  }

  @Override
  public List<BinaryContent> findAllByIdIn(List<UUID> ids) {
    return List.of();
  }

  @Override
  public boolean existsById(UUID id) {
    return false;
  }
  
  @Override
  public void deleteById(UUID id) {
    data.remove(id);
  }
}