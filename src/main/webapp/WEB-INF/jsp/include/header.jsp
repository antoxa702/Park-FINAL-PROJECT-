<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/footer.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/navbar.css" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>

<html >
<head>
    <title>Main page</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<body>

<div class="bg">

    <header>

        <nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
            <form action="fcs?action=change_language" method="POST">
                <button type="submit" class="btn btn-success"style="margin: 0px auto">${language.getValue("main_navi07")}</button>
            </form>
            <div class="container">
                <a class="navbar-brand" href="#">
                    <img src="${pageContext.request.contextPath}/images/forest_210_70.png?text=Logo" alt="">
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="content" id="center">
                    <p><font size="20" color="white" >${language.getValue("main_navi01")}</font></p>
                </div>

                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="fcs?action=main">${language.getValue("main_navi02")}
                                <span class="sr-only">(current)</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">${language.getValue("main_navi03")}</a>
                        </li>
                        
                        <c:if test="${user == null}">
                            <li class="nav-item">
                                <a href="fcs?action=sign_in" class="nav-link" >${language.getValue("main_navi04")}</a>
                            </li>
                        </c:if>

                        <c:if test="${user != null}">
                            <li class="nav-item">
                                <a href="fcs?action=sign_out" class="nav-link" >${language.getValue("main_navi05")}</a>
                            </li>
                        </c:if>

                        <li class="nav-item">
                            <a class="nav-link" href="fcs?action=get_park_list">${language.getValue("main_navi06")}</a>
                        </li>

                    </ul>
                </div>

                <c:if test="${user != null}">
                    <form action="fcs?action=cabinet" method="POST">
                        <button type="submit" class="btn btn-success"style="margin: 0px auto">${language.getValue("main_navi08")}</button>
                    </form>
                </c:if>

                <c:if test="${user == null}">
                    <form action="fcs?action=sign_in" method="POST">
                        <button type="submit" class="btn btn-error"style="margin: 0px auto">${language.getValue("main_navi08")}</button>
                    </form>
                </c:if>
            </div>
        </nav>
    </header>



    <div class="container">
        <h3>Current user : ${sessionScope.user.login}</h3>
<!-- // too do modal window for sign in

        <div class="modal fade" id="signin" tabindex="-1" role="dialog" aria-labelledby="signin" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="signinLabel">Sign In</h5>
                        <button aria-label="Close" class="close" data-dismiss="modal" type="button">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                </div>
            </div>

        </div>
-->

