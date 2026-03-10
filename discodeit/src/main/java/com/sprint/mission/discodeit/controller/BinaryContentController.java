package com.sprint.mission.discodeit.controller;

import com.sprint.mission.discodeit.controller.api.BinaryContentApi;
import com.sprint.mission.discodeit.dto.data.BinaryContentDto;
import com.sprint.mission.discodeit.storage.BinaryContentStorage;
import com.sprint.mission.discodeit.service.BinaryContentService;
import org.springframework.core.io.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/binary-contents")
public class BinaryContentController implements BinaryContentApi {

  private final BinaryContentService binaryContentService;
  private final BinaryContentStorage binaryContentStorage;

  @GetMapping("/{binaryContentId}/download")
  public ResponseEntity<? extends Resource> download(
      @PathVariable("binaryContentId") UUID binaryContentId
  ) {
    // 1. DB(장부)에서 파일 정보(파일명, 타입 등)를 가져옵니다.
    BinaryContentDto dto = binaryContentService.find(binaryContentId);

    // 2. 로컬 저장소(하드디스크)에 다운로드 처리를 맡깁니다.
    // LocalBinaryContentStorage의 download 메서드가 호출됩니다!
    return binaryContentStorage.download(dto);
  }

  /**
   * 2. 메타데이터 단건 조회 (DTO 반환으로 수정)
   */
  @GetMapping("/{id}/metadata")
  public ResponseEntity<BinaryContentDto> findMetadata(@PathVariable UUID id) {
    BinaryContentDto dto = binaryContentService.find(id);
    return ResponseEntity.ok(dto);
  }

  /**
   * 3. 여러 개의 메타데이터 조회
   */
  @GetMapping("/metadata")
  public ResponseEntity<List<BinaryContentDto>> findAllMetadata(
      @RequestParam List<UUID> ids) {
    List<BinaryContentDto> dtos = binaryContentService.findAllByIdIn(ids);
    return ResponseEntity.ok(dtos);
  }
}
