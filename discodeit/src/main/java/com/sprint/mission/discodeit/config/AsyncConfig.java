package com.sprint.mission.discodeit.config;

import java.util.Map;
import org.slf4j.MDC;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.core.task.TaskDecorator;
import org.springframework.core.task.TaskExecutor;

@EnableRetry
@EnableCaching
@Configuration
@EnableAsync  // 비동기 기능 켜기
public class AsyncConfig {

  @Bean
  public TaskExecutor taskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(10);     // 기본 스레드 수
    executor.setMaxPoolSize(20);      // 최대 스레드 수
    executor.setQueueCapacity(100);   // 대기열 크기
    executor.setThreadNamePrefix("async-");  // 스레드 이름 앞에 붙을 문자
    executor.setTaskDecorator(taskDecorator());  // 아래에서 만든 데코레이터 적용
    executor.initialize();
    return executor;
  }

  // MDC와 SecurityContext를 비동기 스레드에도 전달하는 역할
  @Bean
  public TaskDecorator taskDecorator() {
    return runnable -> {
      // 현재(메인) 스레드의 정보를 미리 복사해둠
      Map<String, String> mdcContext = MDC.getCopyOfContextMap();
      SecurityContext securityContext = SecurityContextHolder.getContext();

      return () -> {
        try {
          // 비동기 스레드에 복사해둔 정보 붙여넣기
          if (mdcContext != null) {
            MDC.setContextMap(mdcContext);
          }
          SecurityContextHolder.setContext(securityContext);
          runnable.run();  // 실제 비동기 작업 실행
        } finally {
          // 작업 끝나면 정리
          MDC.clear();
          SecurityContextHolder.clearContext();
        }
      };
    };
  }
}