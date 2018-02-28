<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/13
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title></title>
    <%--<script type="application/javascript">--%>
        <%--function viewItem() {--%>
            <%--var dataItem = getSelectedGridItem("grid");--%>
            <%--if (dataItem) {--%>
                <%--window.location.href = "viewSystemUser.do?userId=" + dataItem.userId;--%>
            <%--}--%>
        <%--}--%>

        <%--function editItem() {--%>
            <%--var dataItem = getSelectedGridItem("grid");--%>
            <%--if (dataItem) {--%>
                <%--window.location.href = "editSystemUser.do?userId=" + dataItem.userId;--%>
            <%--}--%>
        <%--}--%>
        <%--function removeItem() {--%>
            <%--var dataItem = getSelectedGridItem("grid");--%>
            <%--if (dataItem) {--%>
                <%--bootbox.confirm({--%>
                    <%--buttons: {--%>
                        <%--confirm: {--%>
                            <%--label: '确认',--%>
                        <%--},--%>
                        <%--cancel: {--%>
                            <%--label: '取消',--%>
                        <%--}--%>
                    <%--},--%>
                    <%--message: '确定删除吗？',--%>
                    <%--callback: function (result) {--%>
                        <%--if (result) {--%>
                            <%--$.get("removeSystemUserById.do", {--%>
                                    <%--userId: dataItem.userId,--%>
                                    <%--rad: Math.random()--%>
                                <%--},--%>
                                <%--function (data) {--%>
                                    <%--if (data) {--%>
                                        <%--commonNotify("删除成功！", "success");--%>
                                        <%--$("#grid").data("kendoGrid").dataSource.read();--%>
                                    <%--} else {--%>
                                        <%--commonNotify("删除失败!", "error");--%>
                                    <%--}--%>
                                <%--}--%>

                            <%--)--%>
                            <%--;--%>
                        <%--}else {--%>
                        <%--}--%>
                    <%--}--%>
                <%--});--%>

            <%--}--%>
        <%--}--%>


        <%--function changeItem() {--%>
            <%--var dataItem = getSelectedGridItem("grid");--%>
            <%--if (dataItem) {--%>
                <%--bootbox.confirm({--%>
                    <%--buttons: {--%>
                        <%--confirm: {--%>
                            <%--label: '确认',--%>
                        <%--},--%>
                        <%--cancel: {--%>
                            <%--label: '取消',--%>
                        <%--}--%>
                    <%--},--%>
                    <%--message: '确定更改状态吗？',--%>
                    <%--callback: function (result) {--%>
                        <%--if (result) {--%>
                            <%--$.get("changeSystemUserById.do", {--%>
                                    <%--userId: dataItem.userId,--%>
                                    <%--rad: Math.random()--%>
                                <%--},--%>
                                <%--function (data) {--%>
                                    <%--if (data.result == "SUCCESS") {--%>
                                        <%--commonNotify("更改成功！", "success");--%>
                                        <%--$("#grid").data("kendoGrid").dataSource.read();--%>
                                    <%--}else {--%>
                                        <%--commonNotify("更改失败!", "error");--%>
                                    <%--}--%>
                                <%--}--%>

                            <%--)--%>
                            <%--;--%>
                        <%--}else {--%>
                        <%--}--%>
                    <%--}--%>
                <%--});--%>

            <%--}--%>
        <%--}--%>

    <%--</script>--%>
</head>
<body>
<!-- Top bar starts -->
<div class="top-bar clearfix">
    <div class="row gutter">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <ul class="breadcrumb">
                <li><a href="../console/dashboard.do"><span class="icon-home2">首页</span></a></li>
                <li><a href="listSystemUser.do">管理员管理</a></li>
            </ul>
        </div>
    </div>
</div>
<!-- Top bar ends -->
<!-- Main container starts -->
<div class="main-container">
    <!-- Row starts -->
    <div class="row gutter">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="panel panel-light">
                <div class="panel-heading">
                    <h3>查询条件</h3>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal">
                        <div class="form-group row gutter">
                            <label class="col-lg-1 col-md-1 col-sm-1 col-xs-1 control-label">用户名称：</label>
                            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                <input type="text" data-filter="userName" data-operator="contains" class="form-control grid-filter"
                                       placeholder="请输入查询条件..."/>

                            </div>
                            <label class="col-lg-1 col-md-1 col-sm-1 col-xs-1 control-label">手机号码：</label>
                            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                <input type="text" data-filter="mobile" data-operator="contains" class="form-control grid-filter"
                                       placeholder="请输入查询条件..."/>

                            </div>
                        </div>
                        <div class="form-group row gutter">
                            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6 pull-right">
                                <a href="javascript:void(0)" class="btn btn-primary btn-rounded" onclick="reloadGridFilters('grid')">
                                    <i class="icon-facebook-with-circle icon-left"></i>查询
                                </a>
                                <a href="javascript:void(0)" class="btn btn-info btn-rounded" onclick="clearFilters('grid')">
                                    <i class="icon-twitter-with-circle icon-left"></i>重置
                                </a>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
<!-- Main container ends -->
</body>
</html>

