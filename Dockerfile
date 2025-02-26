# Tomcat 이미지를 기반으로 설정
FROM tomcat:9.0

# WAR 파일을 Tomcat 웹 앱 디렉터리로 복사
COPY target/Panya-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/

# Tomcat 실행
CMD ["catalina.sh", "run"]