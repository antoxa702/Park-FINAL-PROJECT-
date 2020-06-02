<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>

<style>
    div {
    }
    #left { text-align: left; }
    #right { text-align: right; }
    #center { text-align: center; }
    .content {
        width: 60%; /* Ширина слоя */
        border:3px;
        border-style:solid;
        border-color: #ff0000;
        border-radius: 20px;
        padding-bottom: -5px;
        padding-top: -5px;
        padding-right: 100px;
        padding-left: 100px;
    }
</style>

<style>
    body, html {
        height: 100%;
        margin: 0;
    }

    .bg {
        /* The image used */
        background-image: url("../images/background.png");

        /* Full height */
        height: 100%;

        /* Center and scale the image nicely */
        background-position: center;
        background-repeat: no-repeat;
        background-size: cover;

    }


</style>
<style>
    .navbar-dark{
        opacity: 0.9;
        color: #111111;
        background-color: #111111;
    }
    .nav-item{
       width: max-content;
    }
    collapse{
        width: max-content ;
    }
</style>

<style>
    .footer{
        position: fixed;
        left: 0;
        bottom: 10px;
        width: 100%;
        height: 50px;
        background-color: whitesmoke;
        color: gray(80);
        text-align: center;
        opacity: 65%;

    }

</style>


<html >
<head>
    <title>Главная страница</title>
    <link rel="shortcut icon" href="../images/favicon.ico" type="image/x-icon">
</head>

<body>
    <div class="bg">
<header>

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
        <div class="container">
            <a class="navbar-brand" href="#">
                <img src="../images/forest_210_70.png?text=Logo" alt="">
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="content" id="center">
                <p><font size="20" color="white" >Metsänvartian</font></p>
            </div>

            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="../index.jsp">Home
                            <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">About</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Sign in</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Sign out</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Contact</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>


        <!-- Navigation -->


<div class="container" >
    <h1 class="mt-4">Logo Nav by Start Bootstrap</h1>
    <p>The logo in the navbar is now a default Bootstrap feature in Bootstrap 4! Make sure to set the width and height of the logo within the HTML or with CSS. For best results, use an SVG image as your logo.</p>
</div>
        <div class="footer">
            <hr style="color: red; background-color: #ff0000; height: 2px; margin-top: 5px; margin-bottom: 5px;"/>
            <p style="margin: 0;">© 2020. All Rights Reserved.   If you have any problems with this site, please,
                contact us:<span style="font-size: large; color: black;">+8 10 49 511 8669 132</span>   or <a href="#"
                                                                                                              style="caret-color: #663333;">
                    <span style="font-size: large;">admin@forestry.com</span></a></p>
        </div>
</div>

</body>

</html>
