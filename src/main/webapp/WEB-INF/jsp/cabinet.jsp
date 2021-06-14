<%@include file="include/header.jsp"%>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>

<body>
<c:if test="${sessionScope.user.getUserType().getNameType().equals(\"forester\")}">
    <h3>My Applications (${sessionScope.user.getUserType().getNameType()}) </h3>
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="#">New</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">At work</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Completed</a>
            </li>
            <li class="nav-item">
                <a class="nav-link disabled" href="#">Approved</a>
            </li>
        </ul>
    </nav>

    <div class="container">
        <h2>Dark Striped Table</h2>
        <p>Combine .table-dark and .table-striped to create a dark, striped table:</p>
        <table class="table table-dark table-striped">
            <thead>
            <tr>
                <th>Firstname</th>
                <th>Lastname</th>
                <th>Email</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>John</td>
                <td>Doe</td>
                <td>john@example.com</td>
            </tr>
            <tr>
                <td>Mary</td>
                <td>Moe</td>
                <td>mary@example.com</td>
            </tr>
            <tr>
                <td>July</td>
                <td>Dooley</td>
                <td>july@example.com</td>
            </tr>
            </tbody>
        </table>
    </div>

</c:if>

<c:if test="${user.getUserType().getNameType().equals(\"owner\")}">
    <h3>My Applications (${user.getUserType().getNameType()}) </h3>
    <nav class="navbar navbar-expand-sm bg-light navbar-light">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="#">New</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">At work</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Completed</a>
            </li>
            <li class="nav-item">
                <a class="nav-link disabled" href="#">Approved</a>
            </li>
        </ul>
    </nav>

    <div class="container">
        <h2>Dark Striped Table</h2>
        <p>Combine .table-dark and .table-striped to create a dark, striped table:</p>
        <table class="table table-light table-striped">
            <thead>
            <tr>
                <th>Firstname</th>
                <th>Lastname</th>
                <th>Email</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>John</td>
                <td>Doe</td>
                <td>john@example.com</td>
            </tr>
            <tr>
                <td>Mary</td>
                <td>Moe</td>
                <td>mary@example.com</td>
            </tr>
            <tr>
                <td>July</td>
                <td>Dooley</td>
                <td>july@example.com</td>
            </tr>
            </tbody>
        </table>
    </div>

</c:if>
</body>
<%@include file="include/footer.jsp"%>
