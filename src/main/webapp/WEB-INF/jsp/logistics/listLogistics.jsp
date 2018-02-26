<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/13
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title></title>
    <script type="application/javascript">
        function viewItem() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                window.location.href = "viewSystemUser.do?userId=" + dataItem.userId;
            }
        }

        function editItem() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                window.location.href = "editSystemUser.do?userId=" + dataItem.userId;
            }
        }
        function removeItem() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                bootbox.confirm({
                    buttons: {
                        confirm: {
                            label: '确认',
                        },
                        cancel: {
                            label: '取消',
                        }
                    },
                    message: '确定删除吗？',
                    callback: function (result) {
                        if (result) {
                            $.get("removeSystemUserById.do", {
                                    userId: dataItem.userId,
                                    rad: Math.random()
                                },
                                function (data) {
                                    if (data) {
                                        commonNotify("删除成功！", "success");
                                        $("#grid").data("kendoGrid").dataSource.read();
                                    } else {
                                        commonNotify("删除失败!", "error");
                                    }
                                }

                            )
                            ;
                        }else {
                        }
                    }
                });

            }
        }


        function changeItem() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                bootbox.confirm({
                    buttons: {
                        confirm: {
                            label: '确认',
                        },
                        cancel: {
                            label: '取消',
                        }
                    },
                    message: '确定更改状态吗？',
                    callback: function (result) {
                        if (result) {
                            $.get("changeSystemUserById.do", {
                                    userId: dataItem.userId,
                                    rad: Math.random()
                                },
                                function (data) {
                                    if (data.result == "SUCCESS") {
                                        commonNotify("更改成功！", "success");
                                        $("#grid").data("kendoGrid").dataSource.read();
                                    }else {
                                        commonNotify("更改失败!", "error");
                                    }
                                }

                            )
                            ;
                        }else {
                        }
                    }
                });

            }
        }

    </script>
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
    <!-- Row ends -->

    <!-- Row starts -->
    <div class="row gutter">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="panel panel-light no-margin">
                <div class="panel-heading">
                    <h4 class="pull-left">列表</h4>
                    <div class="custom-btn-group pull-right">
                        <a class="btn btn-success btn-rounded" href="addSystemUser.do"><i class="fa fa-plus-circle"></i><span>&nbsp;&nbsp;新&nbsp;建&nbsp; </span></a>
                        <button type="button" class="btn btn-primary btn-rounded" onclick="editItem();"><i class="fa fa-edit"></i>&nbsp;&nbsp;编&nbsp;辑&nbsp;</button>
                        <button type="button" class="btn btn-danger btn-rounded" onclick="removeItem();"><i class="fa fa-minus-circle"></i>&nbsp;&nbsp;删&nbsp;除&nbsp;
                        </button>
                        <button type="button" class="btn btn-info btn-rounded" onclick="viewItem();"><i class="fa fa-minus-circle"></i>&nbsp;&nbsp;详&nbsp;情&nbsp;
                        </button>
                        <button type="button" class="btn btn-warning btn-rounded" onclick="changeItem();"><i class="fa fa-ban"></i>&nbsp;更&nbsp;改&nbsp;状&nbsp;态
                        </button>
                    </div>
                </div>
                <div class="panel-body panel-no-padding">
                    <kendo:grid name="grid" pageable="true" sortable="true" selectable="true" height="550" resizable="true" style="border-width:0px;">
                        <kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5" pageSize="15"/>
                        <kendo:grid-columns>
                            <kendo:grid-column title="物流信息" field="userName" width="80"/>
                            <kendo:grid-column title="邮箱" field="email" width="80"/>
                            <kendo:grid-column title="手机号码" field="mobile" width="80"/>
                            <kendo:grid-column title="是否启用" field="enabled" width="80" template="#= enabled ? '是' : '否'#"/>
                            <kendo:grid-column title="创建时间" field="createTime" width="80"/>
                            <kendo:grid-column title="备注" field="remark" width="80"/>
                        </kendo:grid-columns>
                        <kendo:dataSource serverPaging="true" serverFiltering="true" serverSorting="true">
                            <kendo:dataSource-schema data="content" total="totalElements"></kendo:dataSource-schema>
                            <kendo:dataSource-transport>
                                <kendo:dataSource-transport-read url="pageUser.do" type="POST" contentType="application/json"/>
                                <kendo:dataSource-transport-parameterMap>
                                    <script>
                                        function parameterMap(options, type) {
                                            return JSON.stringify(options);
                                        }
                                    </script>
                                </kendo:dataSource-transport-parameterMap>
                            </kendo:dataSource-transport>
                        </kendo:dataSource>
                    </kendo:grid>
                </div>
            </div>
        </div>
    </div>
    <!-- Row ends -->
</div>
<!-- Main container ends -->
</body>
</html>

