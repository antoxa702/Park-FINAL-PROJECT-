<script type="text/javascript">
    var setDisabled = function(id, status) { /* пропишем функцию активации/деактивации полей */
        document.getElementById(id).disabled=status;
    }
</script>


<%@include file="include/header.jsp"%>>




<div class="login-page">
    <div class="login-block">
        <h3>Enter your data</h3>
        <form action="fcs?action=register" method="POST">
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
                    <input type="text" class="form-control" name="last_name" placeholder="Last name" required>
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
                    <input type="text" class="form-control" name="email" placeholder="Your email">
                </div>
            </div>

            <div>
                SELECT YOUR PARK : <select name="parks" required="true">
                    <c:forEach var="park" items="${parkList}">
                        <option>${park.name}</option>
                    </c:forEach>
                </select>
            </div>

            <input type="radio" name="user_type" value="owner" onclick="setDisabled('key', false)"/>Owner
            <input type="radio" name="user_type" value="forester" checked onclick="setDisabled('key', true)"/>Forester

            <input type="password" class="form-control" name="key" id="key" placeholder="Secret key" disabled/>

            <button class="btn btn-primary btn-block" type="submit">ENTER</button>

        </form>

        <div class="login-links">
            <p class="text-center">Have an account? <a class="txt-brand" href="fcs?action=sign_in"><font color=#29aafe>Sign in</font></a></p>
        </div>
    </div>
</div>




<%@include file="include/footer.jsp"%>
