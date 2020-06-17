<%@ page language="java" contentType="text/html charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8"/>
<title>PARKLIST</title>
</head>
<body>
	<table class="table" width="300">
		<thead class="thead-inverse">
			<tr>
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