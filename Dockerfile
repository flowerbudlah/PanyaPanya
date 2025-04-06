# 1. Tomcat 베이스 이미지 사용
FROM tomcat:9.0

# 2. 기본 webapps 제거 (선택)
RUN rm -rf /usr/local/tomcat/webapps/*

# 3. 우리의 war 파일 복사
COPY target/PanyaPanya-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# 4. 포트 오픈
EXPOSE 8080

# 5. 톰캣 실행
CMD ["catalina.sh", "run"]