<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>팡야팡야</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${root }css/responsiveDesign.css ">
<style>
.logo {
	text-align: center;
	margin-top: 5px;
}
.forMembershipOnly a {
	text-decoration: black;
}
a:visited {
	color: black;
}
.forMembershipOnly li {
	list-style: none;
	float: right;
}
.forMembershipOnly li::after {
	content: '';
	padding-right: 20px;
}
.forMembershipOnly li:last-child::after {
	content: '';
}

/* 기본: 버튼 숨김 (PC 화면) */
.hamburger-btn {
    display: none;
}


@media (max-width: 768px) {

    /* 햄버거 버튼 */
    .hamburger-btn {
        display: block;
        position: fixed;
        top: 15px;
        left: 15px;
        font-size: 35px;
        background: transparent;
        border: none;
        padding: 10px 14px;
        cursor: pointer;
        z-index: 9999;
    }

    /* 메뉴 전체 */
    #mobileMenu {
        display: none;
        text-align: left;
    }

    /* 열렸을 때 */
    #mobileMenu.active {
        display: block;
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;          /* 👉 500px 말고 이게 더 자연스러움 */
        height: 100%;
        background: #df4759;
        padding-top: 80px;
        z-index: 9998;
        overflow-y: auto;     /* 👉 스크롤 가능 */
    }

    /* 1depth 메뉴 */
    #mobileMenu > li {
        display: block;
        margin: 15px 20px;
    }

    /* ⭐ 핵심: 서브메뉴는 흐름대로 */
    .main ul {
        position: static !important;
        display: none;
        padding-left: 15px;
    }

    /* 열렸을 때 */
    #mobileMenu li.open > ul {
        display: block;
    }

    /* 서브메뉴 li */
    .main ul li {
        margin: 10px 0;
    }

    /* float 제거 */
    .main > li ul li {
        float: none !important;
    }
}
</style>
<script>
function msg() {
    const menu = document.getElementById("mobileMenu");
    menu.classList.toggle("active");

}
</script>
</head>
<body>
	<nav class="bg-light navbar-dark fixed-top">
		<button type="button" class="hamburger-btn" onclick="msg();">☰</button>
		<div class="logo">
			<a href="${root }main"><img src="${root}image/banner.png"width="60%"></a>
		</div>
		<div class="menu">
			<ul class="forMembershipOnly">
				<c:choose>
					<c:when test="${loginMemberDTO.memberLogin == true }">
						<!-- In case that User signs in -->
						<li><a href="${root }member/delete" style="color: black;">회원탈퇴</a></li>
						<li><a href="${root }member/modify" style="color: black;">회원정보수정</a></li>
						<li><a href="${root }member/logout" style="color: black;">로그아웃</a></li>
						<li>${loginMemberDTO.member_name}님 </li>
					</c:when>
					<c:otherwise>
						<!-- In case that User dosen't sign in -->
						<li><a href="${root }member/join" style="color: black;">회원가입</a></li>
						<li><a href="${root }member/login" style="color: black;">로그인</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</nav>
</body>
</html>