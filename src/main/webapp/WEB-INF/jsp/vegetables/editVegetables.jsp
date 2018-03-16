<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>编辑物流</title>
    <link rel="stylesheet" type="text/css" href="../assets/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="../assets/css/main.css"/>
    <script type="text/javascript" src="../assets/js/modernizr.min.js"></script>
</head>
<body>
<div class="topbar-wrap white">
    <div class="topbar-inner clearfix">
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
                        <li><a href="/vegetables/listVegetables.do"><i class="icon-font"></i>货物信息列表</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font">保存货物</i></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form:form action="/vegetables/saveVegetables.do" method="post" commandName="vegetable">
                    <table class="insert-tab" width="100%">
                        <input type="hidden" name="vegetableId" value="${vegetable.vegetableId}"/>
                        <tbody>
                        <tr>
                            <th>蔬菜名称：</th>
                            <td><input name="vegetableName" value="${vegetable.vegetableName}"/></td>
                        </tr>
                        <tr>
                            <th>蔬菜编码：</th>
                            <td><input name="vegetableCode" value="${vegetable.vegetableCode}"/></td>
                        </tr>
                        <tr>
                            <th>公司名称：</th>
                            <td><input name="companyName" value="${vegetable.companyName}"/></td>
                        </tr>
                        <tr>
                            <th>国家：</th>
                            <td><input name="country" value="${vegetable.country}"/></td>
                        </tr>
                        <tr>
                            <th>价格：</th>
                            <td><input name="price" value="${vegetable.price}"/></td>
                        </tr>
                        <tr>
                            <th>交易类型：</th>
                            <td>
                                <form:select path="dealType" items="${dealTypes}" itemLabel="name"/>
                            </td>
                        </tr>
                        <tr>
                            <th></th>
                            <td>
                                <input class="btn btn-primary btn6 mr10" value="保存" type="submit">
                                <input class="btn btn6" onclick="history.go(-1)" value="返回" type="button">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form:form>
            </div>
        </div>

    </div>
    <!--/main-->
</div>
</body>
</html>