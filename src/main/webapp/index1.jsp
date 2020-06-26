<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tag" uri="/WEB-INF/tld/WeatherTagDescriptor.tld" %>

<!DOCTYPE html>
<html>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.4/css/bootstrap.min.css"
	integrity="2hfp1SzUoho7/TsGGGDaFdsuuDL0LX2hnUp6VkX3CUQ2K4K+xjboZdsXyp4oUHZj"
	crossorigin="anonymous">

<link rel="stylesheet" href="css/main.css">

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.4/js/bootstrap.min.js"
	integrity="VjEeINv9OSwtWFLAtmc4JCtEJXXBub00gtSnszmspDLCtC0I4z4nqz7rEFbIZLLU"
	crossorigin="anonymous"></script>
<head>
<meta charset="UTF-8">
<title>hello first servlet</title>
</head>
<body>
	<div class="bg">
		<p class="py-5 text-center">This example creates a full page
			background image. Try to resize the browser window to see how it
			always will cover the full screen (when scrolled to top), and that it
			scales nicely on all screen sizes.</p>

		<div>
			<form action="fcs?action=get_park_list" method="POST">
				<button type="submit" class="btn btn-danger"
						style="margin: 0px auto">GIVE ME PARK LIST
				</button>
				<button action="fcs?action=change_language" class="btn btn-success" style="margin: 0px auto"
						type="submit">RU
				</button>

			</form>
		</div>
	</div>
<tag:WeatherTag/>

</body>
</html>
