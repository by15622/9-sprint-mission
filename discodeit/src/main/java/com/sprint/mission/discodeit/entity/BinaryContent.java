package com.sprint.mission.discodeit.entity;

import com.sprint.mission.discodeit.entity.base.BaseEntity; // BaseEntity 상속이 필요할 수 있습니다.
import jakarta.persistence.Entity; // JPA 엔티티로 등록해야 합니다.
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

import java.time.Instant;
import java.util.UUID;

@Getter
@Entity // DB 테이블과 연결하기 위해 추가
@Table(name = "binary_contents")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA를 위한 기본 생성자
public class BinaryContent extends BaseEntity { // BaseEntity를 상속받는 구조라면 수정 필요

  private String fileName;
  private Long size;
  private String contentType;

  // BasicUserService에서 호출하는 생성자 모양으로 맞춰야함
  public BinaryContent(String fileName, Long size, String contentType) {
    this.fileName = fileName;
    this.size = size;
    this.contentType = contentType;
  }
}