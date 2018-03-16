<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理员中心</title>
    <link rel="stylesheet" type="text/css" href="../assets/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="../assets/css/main.css"/>
    <script src="../assets/js/jquery-3.1.1.min.js"></script>
    <script>
        function del(vegetableId) {

            if (window.confirm("确定删除所选记录？")) {
                $.ajax({
                    type: "get",
                    url: '/vegetables/delVegetables.do?ids=' + vegetableId, //要自行删除的action
                    dataType: 'json',
                    success: function (data) {
                        if (data.result == "SUCCESS") {
                            alert("删除成功");
                            window.location.reload();
                        } else {
                            alert("删除失败");
                        }
                    }
                });
            }
        }

        function edit(vegetableId) {
            window.location.href = "/vegetables/editVegetables.do?id=" + vegetableId;
        }

        function query() {
            var code = document.getElementById("code").value;
            if (code != "") {
                $.ajax({
                    type: "get",
                    url: '/vegetables/queryVegetables.do?code=' + code,
                    dataType: 'json',
                    success: function (data) {
                        $("#table tr:first").siblings('tr').remove();
                        var item;
                        if (data.data != null) {
                            $.each(data.data, function (i, result) {
                                item = "<tr><td></td><td>" + result['vegetableName'] + "</td><td>" + result['vegetableCode'] +
                                    "</td><td>" + result['companyName'] + "</td><td>" + result['country'] + "</td><td>" + result['price'] +
                                    "</td><td>" +chooseType(result['dealType']) + "</td><a style='cursor: pointer;' onclick='del("+
                                    result['vegetableId']+")'>删除</a>&nbsp;&nbsp;<a style='cursor: pointer;' onclick='edit("+result['vegetableId']+")'>编辑</a><td></tr>";
                                $('#table').append(item);
                            });
                        }
                    }
                });
            } else {
                window.location.reload();
            }
        }

        function chooseType(dealType) {
            return dealType == 'EXPORT' ? '出口' : '进口';
        }
    </script>
</head>
<body>
<div class="topbar-wrap white">
    <!--<div class="topbar-inner clearfix">-->
    <div class=" clearfix">
        <h1 class=" none"><a href="index.html">管理员中心</a></h1>
    </div>
</div>
</div>
<div class="container clearfix">
    <div class="sidebar-wrap">
        <div class="sidebar-title">
            <h1>菜单</h1>
        </div>
        <div class="sidebar-content">
            <ul class="sidebar-list">
                <li>
                    <ul class="sub-menu">
                        <li><a href="#">货物信息列表</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font">货物列表</i></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <table class="search-tab">
                    <tr>
                        <th width="70">货物编码:</th>
                        <td><input class="common-text" placeholder="关键字" name="keywords" id="code" type="text"></td>
                        <td>
                            <button class="btn btn-primary btn2" name="sub" onclick="query()">查询</button>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="result-wrap">
            <form name="myform" id="myform" method="post">
                <div class="result-title">
                    <c:if test="${authority}">
                        <div class="result-list">
                            <a class="btn btn2" href="/logistics/addLogistics.do">新增货物</a>
                        </div>
                    </c:if>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%" id="table">
                        <tr>
                            <th class="tc" width="5%"></th>
                            <th>蔬菜名</th>
                            <th>蔬菜编码</th>
                            <th>公司名称</th>
                            <th>国家</th>
                            <th>价格</th>
                            <th>交易类型</th>
                            <th>操作</th>
                        </tr>
                        <c:forEach items="${vegetables}" var="vegetable">
                            <tr>
                                <td></td>
                                <td>${vegetable.vegetableName}</td>
                                <td>${vegetable.vegetableCode}</td>
                                <td>${vegetable.companyName}</td>
                                <td>${vegetable.country}</td>
                                <td>${vegetable.price}</td>
                                <td>${vegetable.dealType == 'EXPORT' ? '出口' : '进口' }</td>
                                <td><a style="cursor: pointer;" onclick="del(${vegetable.vegetableId})">删除</a>&nbsp;&nbsp;<a style="cursor: pointer;" onclick="edit(${vegetable.vegetableId})">编辑</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                    <%--<div class="list-page"> 2 条 1/1 页</div>--%>
                </div>
            </form>
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>