<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath }/" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>팡야팡야</title>
<link rel="icon" type="image/x-icon" href="${root}image/favicon.png">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<style>
thead {
	background-color: #df4759;
}

h2 {
	font-family: 'Single Day', cursive;
}
</style>
</head>
<body>
<c:import url="/WEB-INF/view/include/head_meta.jsp" />
<c:import url="/WEB-INF/view/include/top_menu.jsp" />
	<!-- 게시글 리스트 -->
	<div class="container" style="margin-top: 50px; margin-bottom: 50px;">
		<div class="card shadow-none">
			<div class="card-body">
				<h2 class="card-title">${board_name }</h2>
				<table class="table table-hover" id='board_list'>
					<thead>
						<tr>
							<th class="text-center d-none d-md-table-cell" style="color: white">글번호</th>
							<th class="w-50" style="color: white">제목</th>
							<th class="text-center d-none d-md-table-cell" style="color: white">작성자</th>
							<th class="text-center d-none d-md-table-cell" style="color: white">작성날짜</th>
							<th class="text-center d-none d-md-table-cell" style="color: white">조회수</th>
						</tr>
					</thead>
					<tbody id="boardtable">
						<c:forEach var="postDTO" items="${postList }">
							<tr>
								<td class="text-center d-none d-md-table-cell">${postDTO.post_idx }</td>
								<td>
									<a href='${root }board/read?board_idx=${board_idx }&post_idx=${postDTO.post_idx}&page=${page}'>
										${postDTO.post_subject}
										<!-- 그림 첨부파일 업로드 한 경우 In case that the user uploaded an image file -->
										<c:if test="${postDTO.post_file != null }">
											<img src="${root }image/uploadingPhoto.png" width=20px;>
										</c:if>
										<!-- 댓글 존재할 경우 If there are comments -->
										<c:if test="${postDTO.reply_cnt > 0}">
											<span style="color: red;">[${postDTO.reply_cnt}]</span>
										</c:if>
									</a>
								</td>
								<td class="text-center d-none d-md-table-cell">${postDTO.post_writer_name }</td>
								<td class="text-center d-none d-md-table-cell"><fmt:formatDate value="${postDTO.post_date }" pattern="yyyy-MM-dd" /></td>
								<td class="text-center d-none d-md-table-cell">${postDTO.post_read_count}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- Paging start -->
				<div class="d-none d-md-block">
					<ul class="pagination justify-content-center">
						<c:choose>
							<c:when test="${pageDTO.prePage <= 0 }">
								<li class="page-item disabled">
									<a href="#" class="page-link">이전</a>
								</li>
							</c:when>
							<c:otherwise>
								<li class="page-item">
									<a href="${root }board/main?board_idx=${board_idx}&page=${pageDTO.prePage}" class="page-link">이전</a>
								</li>
							</c:otherwise>
						</c:choose>
						<c:forEach var="idx" begin="${pageDTO.min }" end="${pageDTO.max }">
							<c:choose>
								<c:when test="${idx == pageDTO.currentPage }">
									<li class="page-item active">
										<a href="${root }board/main?board_idx=${board_idx}&page=${idx}" class="page-link">${idx}</a>
									</li>
								</c:when>
								<c:otherwise>
									<li class="page-item">
										<a href="${root }board/main?board_idx=${board_idx}&page=${idx}" class="page-link">${idx}</a>
									</li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<!-- 맨 마지막 페이지인 경우에는 다음 버튼이 안 보이도록 함 Making the next button invisible when it is the last page.
							마지막 페이지가 전체 페이지 수보다 크거나 같다면 다음 버튼이 안 보이도록 함  -->
						<c:choose>
							<c:when test="${pageDTO.max >= pageDTO.pageCount }">
								<li class="page-item disabled">
									<a href="#" class="page-link">다음</a>
								</li>
							</c:when>
							<c:otherwise>
								<li class="page-item">
									<a href="${root }board/main?board_idx=${board_idx}&page=${pageDTO.nextPage}" class="page-link">다음</a>
								</li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
				<!-- Paging end -->
				<c:choose>
					<c:when test="${loginMemberDTO.memberLogin == true }">
						<c:choose>
							<%-- 공지사항 게시판일 경우 In case that it is a notice board --%>
							<c:when test="${board_idx == 1}">
								<c:choose>
									<c:when test="${loginMemberDTO.member_id eq 'admin'}">
										<%-- If the administrator sign in (Only the administrator can use the notice board) --%>
										<div class="text-right">
											<a href="${root }board/write?board_idx=${board_idx }" class="btn btn-danger" style="color: white">글쓰기</a>
										</div>
									</c:when>
									<c:otherwise>
									</c:otherwise>
								</c:choose>
							</c:when>
							<%-- 
								고객센터 게시판의 경우, 로그인을 했다면 누구나 글쓰기 버튼을 볼 수 있고 글을 작성할 수 있다. 
								In the case of the board of the customer service center, 
								Anyone who signs in can see the button to write and write a post. 
							--%>
							<c:otherwise>
								<div class="text-right">
									<a href="${root }board/write?board_idx=${board_idx }"
										class="btn btn-danger" style="color: white">글쓰기</a>
								</div>
							</c:otherwise>

						</c:choose>
					</c:when>
					<c:otherwise>
					<%-- 로그인을 안 한 상태면 글쓰기 버튼도 안 보인다. If a user didn't sign-in, He will not see the button to write. --%>
					</c:otherwise>
				</c:choose>
				<!-- Searching function start -->
				<form action="${root }board/search_result" method="get" class="text-center">
					<select name="type">
						<option value="post_subject">제목</option>
						<option value="post_text">내용</option>
						<option value="member_name">작성자</option>
					</select>
					<input type="hidden" name="post_board_idx" value="${board_idx}" required="required" />
					<input type="text" name="keyword" value="" required="required" />
					<input type="submit" value="검색" class="btn btn-danger btn-sm" />
				</form>
				<!-- Searching function end -->
			</div>
		</div>
	</div>
<c:import url="/WEB-INF/view/include/bottom_info.jsp" />
</body>
</html>