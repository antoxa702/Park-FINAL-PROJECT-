<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href="${pageContext.request.contextPath}/css/signin.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrap/.." rel="stylesheet">
<jsp:include page="include/header.jsp"/>
<div class="login-page">

<main>
    <div class="login-block">
        <img src="1.png" alt="Scanfcode">
        <h*>Введите свои данные</h*>
        <form action="#">
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-user ti-user"></i></span>
                    <input type="text" class="form-control" placeholder="Ваш логин">
                </div>
            </div>

            <hr class="hr-xs">

            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-lock ti-unlock"></i></span>
                    <input type="password" class="form-control" placeholder="Ваш суперпароль">
                </div>
            </div>

            <button class="btn btn-primary btn-block" type="submit">ВОЙТИ НА САЙТ</button>
            <div class="login-footer">
                <h6>или войдите с помощью</h6>
                <ul class="social-icons">
                    <li><a class="facebook" href="#"><i class="fa fa-facebook"></i></a></li>
                    <li><a class="twitter" href="#"><i class="fa fa-twitter"></i></a></li>
                    <li><a class="linkedin" href="#"><i class="fa fa-linkedin"></i></a></li>
                </ul>
            </div>

        </form>
    </div>

    <div class="login-links">
        <p class="text-center">Еще нету аккаунта? <a class="txt-brand" href="user-login.html"><font color=#29aafe>Регистрируйся</font></a></p>
    </div>

</main>

</div>


<jsp:include page="include/footer.jsp"/>
