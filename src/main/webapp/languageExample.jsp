<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="languag" value="${not empty param.languag ? param.languag : not empty languag ? languag : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${languag}" />
<fmt:setBundle basename="ex"/>
<!DOCTYPE html>
<html lang="${languag}">
<head>
    <title>JSP/JSTL i18n demo</title>
</head>
<body>
<form>
    <select id="languag" name="languag" onchange="submit()">
        <option value="en" ${languag == 'en' ? 'selected' : ''}>English</option>
        <option value="ru" ${languag == 'ru' ? 'selected' : ''}>Russian</option>
    </select>
</form>
<form method="post">
    <label for="username"><fmt:message key="test01" />:</label>
    <input type="text" id="username" name="username">
    <br>
    <label for="password"><fmt:message key="test02" />:</label>
    <input type="password" id="password" name="password">
    <br>
    <fmt:message key="test03" var="buttonValue" />
    <input type="submit" name="submit" value="${buttonValue}">
</form>
</body>
</html>