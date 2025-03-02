# OpenJDK 17 사용
FROM openjdk:17

# WAR 파일 복사
COPY target/PanyaPanya-0.0.1-SNAPSHOT.war /app.war

# 실행 명령어
CMD ["java", "-jar", "/app.war"]