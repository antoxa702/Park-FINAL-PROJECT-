<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="include/header.jsp"/>

<h3>LOGIN : ${login}</h3>
<h3>PASSWORD : ${password}</h3>
<p> USER : </p>
<h2>firstName : ${user.firstName}</h2>
<h2>lastName : ${user.lastName}</h2>
<h2>UserType : ${user.userType.nameType}</h2>
<h2>Park : ${user.park.name}</h2>

<jsp:include page="include/footer.jsp"/>