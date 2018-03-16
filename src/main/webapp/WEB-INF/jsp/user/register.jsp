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
        function register() {
            var password1 = document.getElementById("password");
            var password2 = document.getElementById("dbPassword");
            if (password1.value === password2.value){
                $.ajax({
                    type: "POST",
                    dataType:"JSON",
                    url: $("#register").attr('action') ,
                    data: $('#register').serialize(),
                    success: function (data) {
                        if (data.result =='SUCCESS'){
                            history.go(-1);
                            alert("注册成功！");
                        }else {
                            alert(data.message);
                        }
                    },
                    error : function() {
                    }
                });
            }else {
                alert("两次密码不一致");
            }
        }
    </script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-3 col-lg-3">

        </div>
        <div class="col-md-4 col-lg-4">
            <form:form id="register" action="/user/addUser.do" method="post" commandName="user">
                <h2 class="form-signin-heading" style="text-align: center">注册</h2>
                <table class="insert-tab" width="100%" style="text-align: center;margin-top: 50px">
                    <tbody>
                    <tr style="margin-top: 10px">
                        <th style="text-align: right">用户名：</th>
                        <td><input name="userName" placeholder="请输入用户名"/></td>
                    </tr>
                    <tr style="margin-top: 10px">
                        <th style="text-align: right">密码：</th>
                        <td><input type="password" id="password" name="password" placeholder="请输入密码"/></td>
                    </tr>
                    <tr style="margin-top: 10px">
                        <th style="text-align: right">重复密码：</th>
                        <td><input type="password" id="dbPassword" placeholder="请再次输入密码"/></td>
                    </tr>
                    <tr style="margin-top: 10px">
                        <th></th>
                        <td>
                            <input class="btn btn-primary btn6 mr10" value="保存" type="button" onclick="register()">
                            <input class="btn btn6" onclick="history.go(-1)" value="返回" type="button">
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form:form>
            <iframe style="display: none;" name="submitFrame" src="about:blank"></iframe>
        </div>
        <div class="col-md-4 col-lg-4">
        </div>
    </div>
</div>
</body>
</html>