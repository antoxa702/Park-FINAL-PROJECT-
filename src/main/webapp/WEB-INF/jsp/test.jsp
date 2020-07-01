<%@include file="include/header.jsp"%>

<h3>LOGIN : ${login}</h3>
<h3>PASSWORD : ${password}</h3>
<p> USER : </p>
<h2>firstName : ${user.firstName}</h2>
<h2>lastName : ${user.lastName}</h2>
<h2>UserType : ${user.userType.nameType}</h2>
<h2>Park : ${user.park.name}</h2>

<%@include file="include/footer.jsp"%>