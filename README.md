<h1>[개인 프로젝트] 팡야팡야 </h1>
2021년 여름, 프로그래밍에 대해 잘 알지 못하던 시절 실력을 키우고자 Spring MVC를 학습하며 단독으로 포트폴리오용 프로젝트를 시작했습니다.<br>
베이커리 쇼핑몰을 콘셉트로, 게시판과 장바구니 등 핵심 기능을 갖춘 웹사이트를 구현하였으나, 당시에는 배포 방법을 몰라 일시적으로 개발을 중단하기도 했습니다.<br>
이후 지속적인 유지보수와 기능 개선을 거쳐, 최근에는 웹 호스팅을 통해 실제로 배포까지 완료하였습니다.<br>
이번에 그 과정을 정리하며, 학습과 성장의 흔적이 담긴 의미 있는 프로젝트로 다시 소개하게 되었습니다.<br>
비록 실 결제 기능(PayPal 등)은 포함되어 있지 않지만, 기본적인 쇼핑몰 구조와 사용자 기능을 갖춘 웹사이트입니다.
<hr/>

1. 프로젝트 이름: 팡야팡야
2. URL: <a href="https://panyapanya-production.up.railway.app/">https://panyapanya-production.up.railway.app/</a>
3. 제작 기간: 2021년 05월 17일 ~ 2021년 06월 25일 (약 1달)<br>
  그 후 2025년까지 걸쳐 유지보수와 기능 개선을 거쳐왔고, 웹 호스팅 배포는 2025년 04월 초에 완료되었습니다. 
4. 사이트 동작 영상: <a href="https://drive.google.com/file/d/1uBYLj8p66uVjzaqrXTzeIdeY7HEAjzYt/view" target='_blank'>https://drive.google.com/file/d/1uBYLj8p66uVjzaqrXTzeIdeY7HEAjzYt/view</a>
5. 사용 기술<br>1) Back-end<br>
(1) 언어 & 런타임<br>
Java 1.8 (Java 8)<br>
(2) 프레임워크<br>
i) Spring Framework 5.2.9<br>
· Spring Web MVC<br>
· Spring JDBC<br>
· Spring TX (Transaction)<br>
ii) MyBatis 3.5.6<br>
(3) 데이터베이스 & 연동 <br>
i) MySQL 8.0.27<br>
ii) MySQL Connector/J<br>
(4) 뷰 & 웹 기술<br>
i) JSP(Jakarta Server Pages) 및 JSTL(Java Standard Tag Library)<br>
ii) Servlet 4.0.1<br>
iii) WAR 패키징 구조<br>
iv) JSP 기반의 전통적 MVC 구조 사용<br>
(5) 유효성 검사 및 보조 라이브러리<br>
i) Hibernate Validator<br>
ii) JSR-303 Validation API<br>
iii) Jackson (Object ↔ JSON 직렬화/역직렬화)<br>
(6) 기타<br>
i) Lombok<br>
ii) Imgur API 연동 – 이미지 업로드 처리<br>
(7) 빌드/패키징<br>
i) Apache Maven<br>
ii) WAR 파일로 배포 (Tomcat 등에서 실행되는 구조)<br><br>2) Front-end<br>
i) JSP (JavaServer Pages)<br>
ii) JSTL (JSP Standard Tag Library)<br>
iii) Bootstrap 4<br>
vi) jQuery<br>
v) Google Fonts (Single Day 폰트)<br>
vi) Custom CSS<br>

7. 주요 기능 구현<br>1) 로그인<br>2) 회원가입<br>
(1) ID, Email 중복확인<br>
(2) 유효성 검사(BindingResult)<br>3) 회원정보수정<br>4) 상품진열, 장바구니<br>5) CRUD 게시판, 게시글 검색기능, 이미지 업로드 기능, 댓글달기<br>
8. 추가예정기능<br>1) 현재는 세션이 만료되어 로그아웃된 상태에서 요청을 보낼 경우, 서버에서 이를 제대로 처리하지 못해 HTTP 500(내부 서버 오류)이 발생하고 있습니다.
이러한 문제를 개선하여, 앞으로는 세션이 만료된 경우 오류 페이지가 아닌 로그인 페이지로 자동 이동되도록 기능을 개선할 예정입니다.
