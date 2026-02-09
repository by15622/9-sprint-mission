package com.sprint.mission.discodeit.dto;

import java.io.InputStream;

public record BinaryContentCreateRequest(
        String fileName,        // 파일 이름
        String contentType,     // 파일 형식 (image/png 등)
        Long fileSize,          // 파일 크기
        InputStream inputStream // 실제 파일 데이터 스트림
) {
}