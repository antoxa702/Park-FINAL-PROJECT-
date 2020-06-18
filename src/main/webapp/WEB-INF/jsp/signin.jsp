<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ page errorPage="error.jsp" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href="${pageContext.request.contextPath}/css/signin.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrap/.." rel="stylesheet">
<jsp:include page="include/header.jsp"/>
<br>
<br>
<br>
<div class="login-page">
    <div class="login-block">
        <h3>Enter your data</h3>
        <form action="fcs?action=sign" method="POST">
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-user ti-user"></i></span>
                    <input type="text" class="form-control" name="login" placeholder="Your login">
                </div>
            </div>

            <hr class="hr-xs">

            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-lock ti-unlock"></i></span>
                    <input type="password" class="form-control" name="password" placeholder="Your password">
                </div>
            </div>

            <button class="btn btn-primary btn-block" type="submit">ENTER</button>

        </form>

        <div class="login-links">
            <p class="text-center">Don't have an account? <a class="txt-brand" href="fcs?action=register_page"><font color=#29aafe>Register</font></a></p>
        </div>
    </div>
</div>


<jsp:include page="include/footer.jsp"/>
