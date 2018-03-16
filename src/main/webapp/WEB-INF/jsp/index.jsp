<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登录</title>
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <script src="assets/js/jquery-3.1.1.min.js"></script>
    <script>
        function login() {
            $.ajax({
                type: "POST",
                dataType:"JSON",
                url: $("#login").attr('action') ,
                data: $('#login').serialize(),
                success: function (data) {
                    if (data.result =='SUCCESS'){
                        window.location.href="/vegetables/listVegetables.do";
                    }else {
                        alert("账号或者密码错误！");
                    }
                },
                error : function() {
//                    alert("异常！");
                }
            });
        }
        
        function register() {
            window.location.href="register.do";
        }
    </script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-lg-4">

        </div>
        <div class="col-md-4 col-lg-4">
            <form:form id="login" commandName="user" action="login.do">
                <h2 class="form-signin-heading">物流信息管理系统</h2>
                <label >账号</label>
                <input type="text" name="userName" class="form-control" placeholder="账号" required><br>
                <label>密码</label>
                <input type="password" name="password" class="form-control" placeholder="请输入密码" required>
                <div class="checkbox">
                    <%--<label>--%>
                        <%--<input type="checkbox" value="remember-me" checked="checked"> 记住密码--%>
                    <%--</label>--%>
                </div>
                <button onclick="login()" type="button" class="btn btn-primary">登录</button>
                <a onclick="register()" class="btn btn-default">注册</a>
            </form:form>
            <iframe style="display: none;" name="submitFrame" src="about:blank"></iframe>
        </div>
        <div class="col-md-4 col-lg-4">
        </div>
    </div>
</div>
</body>
</html>
