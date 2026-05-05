<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=], initial-scale=1.0">
<title>팡야팡야</title>
<link rel="stylesheet" href="css/responsiveDesign.css" />
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Single+Day&display=swap" rel="stylesheet">
<style>
.main {
	text-align: center;
	margin: 0;
	padding: 0;
	background: #df4759;
	font-family: 'Single Day', cursive;
	font-size: 20px;
}

.main li {
	display: inline-block;
	font-weight: bolder;
	list-style: none;
	color: white;
}

.main ul {
	opacity: 0;
	transition: opacity 0.4s;
	pointer-events: none;
	position: absolute;
	padding-left: 0px;
}

.main:after {
	content: '';
	display: block;
	clear: both;
}

.main>li {
	margin-right: 50px;
	margin-left: 50px;
	margin-top: 145px;
	margin-bottom: 70px;
	line-height: 25px;
	padding-bottom: 5px;
}

.main>li ul li {
	float: left;
	list-style: none;
	font-weight: bolder;
	font-size: 15px;
	position: relative;
	margin: 0 auto;
}

.main>li:hover ul {
	opacity: 1;
	pointer-events: auto;
}

/* 기본: 버튼 숨김 (PC 화면) */
.close-btn {
    display: none;
}

/* 화면이 768px 이하일 때만 표시 (태블릿/모바일) */
@media (max-width: 768px) {

	 .close-btn {
	 	color: white;
        display: block;
        position: fixed;
        top: 15px;
        left: 15px;
        font-size: 50px;
        background-color: transparent;
        border: none;
        border-radius: `0px;
        padding: 0px 5px 5px 5px;
        cursor: pointer;
        z-index: 9999;
    }

	/* 마우스 올렸을 때 */
	.close-btn:hover {
    	background-color: transparent;
    	transform: scale(1.05);
	}

	/* 클릭했을 때 */
	.close-btn:active {
    	transform: scale(0.95);
	}

}
</style>
<script>
document.addEventListener("DOMContentLoaded", function () {
    const menuItems = document.querySelectorAll("#mobileMenu > li");

    menuItems.forEach(item => {
        item.addEventListener("click", function (e) {
            this.classList.toggle("open");
        });
    });
});
</script>
</head>
<body>
	<ul class="main" id="mobileMenu">
	<button type="button" class="close-btn" onclick="msg();">X</button>
		<li>상품소개
			<ul>
				<li>
					<a href="${root }product/product_by_category?category_idx=1" style="color: white;">빵 · 샌드위치</a>
				</li>
				<br>
				<li>
					<a href="${root }product/product_by_category?category_idx=2" style="color: white;">케이크</a>
				</li>
				<br>
				<li>
					<a href="${root }product/product_by_category?category_idx=3" style="color: white;">쿠키 · 과자 · 전병</a>
				</li>
			</ul>
		</li>
		<li>마이페이지
			<ul>
				<c:choose>
					<c:when test="${loginMemberDTO.memberLogin == true }">
						<li>
							<a href="${root}mypage/cart/cartlist/${loginMemberDTO.member_id}" style="color: white;">장바구니</a>
						</li>
						<br>
						<li>
							<a href="${root}mypage/order/orderInfo" style="color: white;">주문 · 결제 · 배송 현황</a>
						</li>
					</c:when>
				</c:choose>
			</ul>
		</li>
		<%-- 공지사항 게시판(The Notice Board), 고객센터 게시판(Customer Service Center Board) --%>
		<c:forEach var="dto" items="${topMenuList }">
		<li>
			<a href="${root }board/main?board_idx=${dto.board_idx}" style="color: white;">${dto.board_name }</a>
		</li>
		</c:forEach>
		<li>
			<a href="${root }etc/map" style="color: white;">오시는 길</a>
		</li>
		<li>관리자
			<ul>
				<c:choose>
					<c:when test="${loginMemberDTO.memberLogin == true }">
						<li>
							<a href="${root}admin/order_payment_delivery" style="color: white;">주문 · 결제 · 배송 관리</a>
						</li>
						<br>
						<li>
							<a href="${root }admin/member" style="color: white;">회원관리</a>
						</li>
					</c:when>
					<c:otherwise></c:otherwise>
				</c:choose>
			</ul>
		</li>
	</ul>
</body>
</html>