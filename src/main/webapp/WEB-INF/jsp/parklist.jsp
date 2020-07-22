<%@include file="include/header.jsp"%>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>

<div class="container">
    <h2>PARK LIST</h2>
    <table class="table table-dark table-striped">
        <thead>
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
    </table>
</div>


<%@include file="include/footer.jsp"%>
