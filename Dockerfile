FROM tomcat:9-jdk17

# 기존 WAR 삭제
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# 당신의 WAR 복사 (ROOT로 덮기)
COPY target/PanyaPanya-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war