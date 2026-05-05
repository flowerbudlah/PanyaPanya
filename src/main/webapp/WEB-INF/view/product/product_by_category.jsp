<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="root" value="${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팡야팡야</title>
<link rel="icon" type="image/x-icon" href="${root}image/favicon.png">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Single+Day&display=swap" rel="stylesheet">
<style>



/* 폰트 적용 및 기본 설정 */
body {
    font-family: 'Single Day', cursive;
}

/* 상품 카드 컨테이너 */
.goods-card {
    width: 300px;  /* 이미지 크기에 맞춤 */
    height: 300px;
    position: relative;
    overflow: hidden; /* 호버 효과가 카드 밖으로 나가지 않게 함 */
    border-radius: 10px; /* 살짝 둥글게 하면 더 예뻐요 */
    box-shadow: 0 4px 15px rgba(0,0,0,0.1);
}

.image-wrapper img {
    width: 100%;
    height: 300px;
    object-fit: cover; /* 이미지가 찌그러지지 않게 꽉 채움 */
    display: block;
}

/* 호버 레이어 정리 */
.test_hover {
    position: absolute;
    inset: 0; /* top, left, right, bottom: 0과 동일 */
    background: rgba(0, 0, 0, 0.6);
    display: flex;
    flex-direction: column;
    justify-content: center; /* 수직 중앙 정렬 */
    align-items: center;     /* 수평 중앙 정렬 */
    opacity: 0;
    transition: all 0.3s ease-in-out;
    color: white;
}

.goods-card:hover .test_hover {
    opacity: 1;
}

/* 호버 내부 텍스트 스타일 */
.product-title { font-size: 1.5rem; margin-bottom: 10px; }
.product-price { font-size: 1.2rem; color: #ffeb3b; margin-bottom: 20px; }

.buy-btn {
    color: white;
    border: 1px solid white;
    padding: 8px 20px;
    text-decoration: none;
    transition: 0.2s;
}

.buy-btn:hover {
    background: white;
    color: black;
    text-decoration: none;
}


</style>
</head>
<body>
<c:import url="/WEB-INF/view/include/head_meta.jsp" />
<c:import url="/WEB-INF/view/include/top_menu.jsp" />

<div class="container" style="margin-top: 50px; margin-bottom: 50px;">
    <!-- row가 자동으로 항목들을 가로로 나열하고 중앙 정렬을 도와줍니다 -->
    <div class="row">
        <c:forEach items="${productListByCategory}" var="productDTO">
            <div class="col-md-4 d-flex justify-content-center mb-5">
                <div class="goods-card">
                    <div class="image-wrapper">
                        <c:choose>
                            <c:when test="${fn:startsWith(productDTO.product_img, 'https://i.imgur.com/')}">
                                <img src="${productDTO.product_img}" alt="${productDTO.product_name}">
                            </c:when>
                            <c:otherwise>
                                <img src="${root}image/product/${productDTO.product_img}" alt="${productDTO.product_name}">
                            </c:otherwise>
                        </c:choose>
                        
                        <!-- 마우스 호버 시 나타나는 레이어 -->
                        <div class="test_hover">
                            <h3 class="product-title">${productDTO.product_name}</h3>
                            <h2 class="product-price">
                                <fmt:formatNumber value="${productDTO.product_price}" pattern="###,###,###" />￦
                            </h2>
                            <a href="${root}product/product_detail?product_idx=${productDTO.product_idx}" class="buy-btn">사러가기!</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <!-- 관리자 버튼 -->
    <c:if test="${loginMemberDTO.member_id eq 'admin'}">
        <div class="row mt-4">
            <div class="col-12 text-right">
                <a href="${root}product/upload" class="btn btn-danger px-5">상품 등록하기</a>
            </div>
        </div>
    </c:if>

</div>	
<c:import url="/WEB-INF/view/include/bottom_info.jsp" />
</body>
</html>