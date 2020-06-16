<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
    var setDisabled = function(id, status) { /* пропишем функцию активации/деактивации полей */
        document.getElementById(id).disabled=status;
    }
</script>

<jsp:include page="include/header.jsp"/>



<div class="login-page">
    <div class="login-block">
        <h3>Enter your data</h3>
        <form action="reg">
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-user ti-user"></i></span>
                    <input type="text" class="form-control" name="login" placeholder="Your login" required>
                </div>
            </div>

            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-lock ti-unlock"></i></span>
                    <input type="password" class="form-control" name="password" placeholder="Your password" required>
                </div>
            </div>

            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-lock ti-unlock"></i></span>
                    <input type="password" class="form-control" name="repeat_password" placeholder="Repeat password" required>
                </div>
            </div>

            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-user ti-user"></i></span>
                    <input type="text" class="form-control" name="first_name" placeholder="First name" required>
                </div>
            </div>

            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-lock ti-unlock"></i></span>
                    <input type="password" class="form-control" name="last_name" placeholder="Last name" required>
                </div>
            </div>

            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-user ti-user"></i></span>
                    <input type="text" class="form-control" name="phone_number" placeholder="Your phone number">
                </div>
            </div>

            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-lock ti-unlock"></i></span>
                    <input type="password" class="form-control" name="email" placeholder="Your email">
                </div>
            </div>

            <form>
                SELECT YOUR PARK : <select name="parks" required>
                    <c:forEach var="park" items="${parkList}">
                        <option>${park.name}</option>
                    </c:forEach>
                </select>
            </form>

            <label><input type="radio" name="userType" value="owner" onclick="setDisabled('k', false)">Owner</label>
            <label><input type="radio" name="userType" value="forester" checked onclick="setDisabled('k', true)">Forester</label>

            <input type="text" class="form-control" name="key" id="k" placeholder="Secret key" disabled>


            <button class="btn btn-primary btn-block" type="submit">ENTER</button>

        </form>

        <div class="login-links">
            <p class="text-center">Have an account? <a class="txt-brand" href="fcs?action=sign_in"><font color=#29aafe>Sign in</font></a></p>
        </div>
    </div>
</div>




<jsp:include page="include/footer.jsp"/>
