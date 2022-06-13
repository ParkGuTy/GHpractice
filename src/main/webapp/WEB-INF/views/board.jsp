<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href="./css/menu.css" rel="stylesheet">
<style type="text/css">
table {
	width: 100%;
	height: 500px;
	margin: 0 auto;
	border-collapse: collapse;
}
tr :hover {
	min-height: 50px;
}
td {
	border-bottom: 1px solid white;
}
div{
	text-align: center;
}
</style>
<script type="text/javascript">
	function linkPage(pageNo){
		location.href = "./board?pageNo="+pageNo;
	}	
</script>
</head>
<body>
		<div id="menubar">
			<c:import url="menu.jsp"/>
			</div>
			<div id="main">
		
	<table calss="table">
	<thead>
		<tr>
			<th scope="col">번호</th>
			<th scope="col">제목</th>
			<th scope="col">날짜</th>
			<th scope="col">쓴사람</th>
			<th scope="col">조회수</th>
		</tr>
		</thead>
		<c:forEach items="${boardList}" var="b">
		<tr onclick="location.href='./detail?b_no=${b.b_no}'">
			<th id="r1">${b.b_no }</th>
			<th id="r3">${b.b_title }</th>
			<th id="r2">${b.b_date }</th>
			<th id="r2">${b.u_id }</th>
			<th id="r1">${b.b_count }</th>
		</tr>
		</c:forEach>
	</table>
	<hr>
	<div>
		<ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="linkPage"/>
	</div>
	<hr>
	<c:if test="${sessionScope.id ne null }">
		<button onclick="location.href='./write'" style="margin-left: 90%; margin-top: 5px;">글쓰기</button>
	</c:if>
	
	</div>
	
	<!-- Controller -> Service -> DAO -> DB (Model)
	<!-- Controller : 흐름을 제어 --> 
	<!-- Service : 필요한 로직
	DAO : mybatis 에게 일 시키기 
	DB : 데이터 가져오기
	
	jsp(View) -> 사용자 -->
</body>
</html>