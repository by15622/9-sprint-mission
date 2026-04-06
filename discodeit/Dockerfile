# 1. 베이스 이미지 설정
FROM amazoncorretto:17

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. 프로젝트 파일 복사 (현재 폴더의 모든 것을 컨테이너의 /app으로 복사)
COPY .. .

# 4. 권한 부여 및 Gradle Wrapper로 애플리케이션 빌드
RUN chmod +x ./gradlew
RUN ./gradlew build -x test

# 5. 80 포트 노출
EXPOSE 80

# 6. 프로젝트 정보 환경 변수 설정
ENV PROJECT_NAME=discodeit
ENV PROJECT_VERSION=1.2-M8

# 7. JVM 옵션 환경 변수 설정 (기본값 빈 문자열)
ENV JVM_OPTS=""

# 8. 애플리케이션 실행 명령어 (환경 변수 활용하여 JAR 파일명 추론)
CMD ["sh", "-c", "java $JVM_OPTS -jar build/libs/${PROJECT_NAME}-${PROJECT_VERSION}.jar"]