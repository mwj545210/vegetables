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
        function del(){
            var checkbox = document.getElementsByName("checkbox");
            var num = checkbox.length;
            var ids = "";
            for(var index =0 ; index<num ; index++){
                if(checkbox[index].checked){
                    ids += checkbox[index].value + ",";
                }
            }
            if(ids!=""){
                if(window.confirm("确定删除所选记录？")){
                    $.ajax( {
                        type : "get",
                        url : '/vegetable/delLogistics.do?ids=' + ids, //要自行删除的action
                        dataType : 'json',
                        success : function(data) {
                            if (data.result == "SUCCESS"){
                                alert("删除成功");
                                window.location.reload();
                            }else {
                                alert("删除失败");
                            }
                        }
                    });
                }
            }else{
                alert("请选择要删除的物流");
            }
        }
        function edit(){
            var checkbox = $("input[type='checkbox']:checked");
            var num = checkbox.length;
            if(num == 0){
                alert("请选择要编辑的物流");
            }else if (num > 1){
                alert("最多选择一个物流进行编辑")
            }else {
                var id = checkbox[0].value;
                window.location.href="/vegetable/editLogistics.do?id=" + id;
            }
        }

        function query() {
            var code = document.getElementById("code").value;
            if(code!="") {
                $.ajax({
                    type: "get",
                    url: '/vegetable/queryLogistics.do?code=' + code,
                    dataType: 'json',
                    success: function (data) {
                        $("#table tr:first").siblings('tr').remove();
                        var item;
                        if (data.data != null){
                            $.each(data.data, function (i, result) {
                                item = "<tr><td class='tc'><input name='checkbox' type='checkbox' value='" + result['logisticId'] + "'></td><td>" +
                                    result['logisticId'] + "</td><td>" + result['logisticName'] + "</td><td>" + result['logisticCode'] +
                                    "</td><td>" + result['logisticCompany'] + "</td><td>" + result['logisticContent'] + "</td></tr>";
                                $('#table').append(item);
                            });
                        }
                    }
                });
            }else {
                window.location.reload();
            }
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
                        <li><a href="#">物流信息列表</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font">物流列表</i></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                    <table class="search-tab">
                        <tr>
                            <th width="70">物流编码:</th>
                            <td><input class="common-text" placeholder="关键字" name="keywords" id="code" type="text"></td>
                            <td><button class="btn btn-primary btn2" name="sub" onclick="query()">查询</button></td>
                        </tr>
                    </table>
            </div>
        </div>
        <div class="result-wrap">
            <form name="myform" id="myform" method="post">
                <div class="result-title">
                    <c:if test="${authority}">
                        <div class="result-list">
                            <a class="btn btn2" href="/logistics/addLogistics.do">新增物流</a>
                            <a class="btn btn2" onclick="edit()">编辑物流</a>
                            <a class="btn btn2" onclick="del()">删除物流</a>
                        </div>
                    </c:if>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%" id="table">
                        <tr>
                            <th class="tc" width="5%"></th>
                            <th>物流ID</th>
                            <th>物流名</th>
                            <th>物流编码</th>
                            <th>物流公司</th>
                            <th>物流内容</th>
                        </tr>
                        <c:forEach items="${logistics}" var="logistic">
                        <tr>
                            <td class="tc"><input name="checkbox" type="checkbox" value="${logistic.logisticId}"></td>
                            <td>${logistic.logisticId}</td> <!--标签ID-->
                            <td>${logistic.logisticName}</td>
                            <td>${logistic.logisticCode}</td> <!--购买者用户名-->
                            <td>${logistic.logisticCompany}</td> <!--销售者用户名-->
                            <td>${logistic.logisticContent}</td> <!--订单时间-->
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