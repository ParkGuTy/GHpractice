<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>detail</title>
<link href="./css/menu.css" rel="stylesheet">
</head>
<body>

	<div id="container">
		<div id="menubar">
			<c:import url="menu.jsp"/>
		</div>
		<div id="main">
			<table>
				<tr>
					<th>번호</th>
					<td>${detail.b_no }</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>${detail.b_title }</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${detail.u_id}</td>
				</tr>
				<tr>
					<th>작성일</th>
					<td>${detail.b_date }</td>
				</tr>
				<tr>
					<td colspan="2">${detail.b_content }</td>
				</tr>
				
			</table>
		</div>
	</div>
		<button onclick="location.href='./board'">게시판 ㄱㄱ</button>
</body>
</html>