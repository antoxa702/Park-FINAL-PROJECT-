<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.4/css/bootstrap.min.css"
	integrity="2hfp1SzUoho7/TsGGGDaFdsuuDL0LX2hnUp6VkX3CUQ2K4K+xjboZdsXyp4oUHZj"
	crossorigin="anonymous">

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.4/js/bootstrap.min.js"
	integrity="VjEeINv9OSwtWFLAtmc4JCtEJXXBub00gtSnszmspDLCtC0I4z4nqz7rEFbIZLLU"
	crossorigin="anonymous"></script>

<head>
<meta charset="UTF-8">
<title>PARKLIST</title>
</head>
<body>
	<table class="table" width="300">
		<thead class="thead-inverse">
			<tr>
				<th>#</th>
				<th>id</th>
				<th>Park name</th>
				<th>Area, sqkm</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="park" items="${parkList}">
				<tr>
					<th scope="row" />
					<td>${park.id}</td>
					<td>${park.name}</td>
					<td>${park.area}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>