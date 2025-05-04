<h1>[개인 프로젝트] 팡야팡야 </h1>
2021년 여름, 프로그래밍에 대해 잘 알지 못하던 시절 실력을 키우고자 Spring MVC를 학습하며 단독으로 포트폴리오용 프로젝트를 시작했습니다.<br>
베이커리 쇼핑몰을 콘셉트로, 게시판과 장바구니 등 핵심 기능을 갖춘 웹사이트를 구현하였으나, 당시에는 배포 방법을 몰라 일시적으로 개발을 중단하기도 했습니다.이후 지속적인 유지보수와 기능 개선을 거쳐, 최근에는 웹 호스팅을 통해 실제로 배포까지 완료하였습니다.<br>
이번에 그 과정을 정리하며, 학습과 성장의 흔적이 담긴 의미 있는 프로젝트로 다시 소개하게 되었습니다.<br>
비록 실 결제 기능(PayPal 등)은 포함되어 있지 않지만, 기본적인 쇼핑몰 구조와 사용자 기능을 갖춘 웹사이트입니다.
<hr/>

1. 프로젝트 이름: 팡야팡야
2. URL: <a href="https://panyapanya-production.up.railway.app/">https://panyapanya-production.up.railway.app/</a>
3. 제작 기간: 2021년 05월 17일 ~ 2021년 06월 25일 (약 1달)<br>
  그 후 지금 2025년까지 걸쳐 유지보수와 기능 개선을 거쳐왔고, 웹 호스팅 배포는 2025년 04월 초에 완료되었습니다. 
4. 사이트 동작 영상: <a href="https://drive.google.com/file/d/1uBYLj8p66uVjzaqrXTzeIdeY7HEAjzYt/view" target='_blank'>https://drive.google.com/file/d/1uBYLj8p66uVjzaqrXTzeIdeY7HEAjzYt/view</a>
5. 사용 기술
1) Back-end
Java:

Java 버전: 1.8

maven-compiler-plugin 설정에서 source와 target이 1.8로 되어 있습니다. 즉, Java 8을 사용하고 있다는 의미입니다.

Spring Framework:

Spring Web MVC: spring-webmvc (버전 5.2.9.RELEASE)를 사용하고 있어 Spring MVC 기반의 웹 애플리케이션을 개발하고 있습니다.

Spring JDBC: 데이터베이스 연동을 위한 Spring의 JDBC 템플릿을 사용하고 있습니다.

Spring TX (Transaction): 트랜잭션 처리를 위한 spring-tx 라이브러리도 포함되어 있습니다.

MySQL:

MySQL 버전: 8.0.27

MySQL Connector/J: 데이터베이스 연결을 위한 MySQL JDBC 드라이버 (mysql-connector-java)가 포함되어 있습니다.

MyBatis:

MyBatis: SQL 매핑 프레임워크인 MyBatis와 Spring 연동을 위한 mybatis-spring이 포함되어 있습니다. 이로 인해 SQL 쿼리 처리 및 매핑이 수월해집니다.

MyBatis 버전: 3.5.6

JSP 및 JSTL:

JSP: Java Server Pages를 사용하여 동적인 웹 페이지를 생성하고 있습니다.

JSTL: JSP에서 사용할 수 있는 Java Standard Tag Library (jstl)도 포함되어 있습니다.

Jackson:

Jackson 라이브러리: JSON 처리와 관련된 라이브러리인 Jackson (jackson-core, jackson-databind, jackson-annotations)이 포함되어 있어 JSON 데이터를 처리하고 있습니다.

기타:

Lombok: lombok을 사용하여 자바 클래스에서 보일러플레이트 코드를 줄이고 있습니다.

Hibernate Validator: 데이터 검증을 위한 라이브러리인 hibernate-validator를 사용하고 있습니다.

Apache Commons DBCP2: 데이터베이스 커넥션 풀을 관리하기 위해 commons-dbcp2가 포함되어 있습니다.

Servlet API: javax.servlet-api 및 javax.servlet.jsp-api를 통해 서블릿과 JSP 관련 기능을 제공하고 있습니다.









  3) Front-end
  HTML5
JavaScript
JQuery 2.2
CSS
BootStrap 4.1


7. 기능 구현

9. 핵심 기능 설명 & 트러블 슈팅
