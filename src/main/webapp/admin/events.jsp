<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.xiyoulinux.model.Events" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: zhoupan
  Date: 11/28/16
  Time: 10:06 AM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="/images/xiyoulinux.png">
    <title>西邮Linux兴趣小组官网-后台管理</title>

    <link href="/admin/css/bootstrap.min.css" rel="stylesheet">
    <link href="/admin/css/font-awesome.css" rel="stylesheet">
    <link href="/admin/css/sb-admin.css" rel="stylesheet">
    <link href="/admin/css/sb-bk-theme.css" rel="stylesheet">
    <link href="/admin/css/bk.css" rel="stylesheet">
    <link href="/admin/css/ui-dialog.css" rel="stylesheet">


</head>

<body>

<div id="wrapper">
    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">
                <i class="fa fa-leaf f20 mr5"></i>
                后台管理
            </a>
        </div>
        <!-- Top Menu Items -->
        <ul class="nav navbar-right top-nav">
            <li class="dropdown">
                <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> admin
                    <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li class="divider"></li>
                    <li>
                        <a href="/Logout"><i class="fa fa-fw fa-power-off"></i> 退出</a>
                    </li>
                </ul>
            </li>
        </ul>
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav side-nav">
                <li class="active">
                    <a href="/admin/events"><i class="fa fa-fw fa-table"></i>活动管理</a>
                </li>
                <li>
                    <a href="/admin/blog"><i class="fa fa-fw fa-edit"></i>文章管理</a>
                </li>
                <li>
                    <a href="/admin/title"><i class="fa fa-fw fa-desktop"></i>标题管理</a>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </nav>

    <div id="page-wrapper">

        <div class="container-fluid">

            <!-- Page Heading -->
            <div class="row page-header-box">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        活动管理
                    </h1>
                </div>
            </div>


            <div class="panel panel-default">
                <div class="panel-heading">
                    活动列表
                </div>

                <div>
                    <div class="king-wrapper">
                        <form class="form-inline king-search-form king-no-bg mt15 mb15 pull-left"
                              style="margin-left: 2%">
                            <div class="form-group">
                                <label>标题：</label>
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="请输入文章标题" name="title"
                                           id="title"
                                           value="<%if(request.getAttribute("title") != null){out.print(request.getAttribute("title"));}%>">
                                </div>
                            </div>
                            <button type="button" class="king-btn king-info" onclick="check(1)">搜索</button>
                        </form>
                        <a href="/admin/eventsedit.jsp" class="king-btn king-info pull-right mt15 ml15"
                           style="margin-right: 2%;">
                            <i class="fa fa-user-plus mr5"></i>新增文章
                        </a>
                    </div>

                    <table class="table table-out-bordered table-hover">
                        <thead>
                        <tr>
                            <th style="width:50px;" class="tc">序号</th>
                            <th>标题</th>
                            <th>日期</th>
                            <th>时间</th>
                            <th>地点</th>
                            <th>阅读量</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody id="tbody">
                        <%
                            ArrayList<Events> eventsList = (ArrayList<Events>) request.getAttribute("eventsList");
                            for (int i = 0; i < eventsList.size(); i++) {
                        %>
                        <tr>
                            <td><%=i + 1%>
                            </td>
                            <td>
                                <a href="/admin/eventsedit?id=<%=eventsList.get(i).getId()%>"><%=eventsList.get(i).getTitle()%>
                                </a>
                            </td>
                            <td><%=eventsList.get(i).getDate()%>
                            </td>
                            <td><%=eventsList.get(i).getTime()%>
                            </td>
                            <td><%=eventsList.get(i).getAddress()%>
                            </td>
                            <td><%=eventsList.get(i).getReader()%>
                            </td>
                            <td>
                                <%
                                    if (eventsList.get(i).getStatus() == 0) {
                                %>
                                <button type="button" class="btn btn-warning btn-sm"
                                        onclick="change_status(<%=eventsList.get(i).getId()%>)" value="0">已停用
                                </button>
                                <%
                                } else {
                                %>
                                <button type="button" class="btn btn-info btn-sm"
                                        onclick="change_status(<%=eventsList.get(i).getId()%>)" value="1">已启用
                                </button>
                                <%
                                    }
                                %>
                            </td>
                            <td>
                                <button type="button" class="btn btn-danger btn-sm"
                                        onclick="remind(<%=eventsList.get(i).getId()%>)">删除
                                </button>
                                <a href="/admin/preview?id=<%=eventsList.get(i).getId()%>&type=events" target="_blank">
                                    <button type="button" class="btn btn-success btn-sm">预览</button>
                                </a>
                            </td>
                        <tr>
                                <%}%>
                        </tbody>
                        <tfoot>
                        <tr>
                            <td colspan="12">
                                <div class="pagination-info pull-left" id="record">
                                    共<%=request.getAttribute("allCount")%>
                                    条记录，当前第<%=request.getAttribute("currentPage")%>
                                    /<%=request.getAttribute("pageCount")%>页，每页20条记录
                                </div>
                                <div class="pull-right king-page-box">
                                    <ul class="pagination pagination-small pull-right" id="paging">
                                        <%
                                            int page_count = (int) request.getAttribute("pageCount");
                                            int current_page = (int) request.getAttribute("currentPage");
                                            if (page_count >= 7) {
                                                for (int i = current_page - 4; i < current_page + 3 && i < page_count; i++) {
                                                    if(i < 0 ){
                                                        continue;
                                                    }

                                                    if (i == current_page - 1) {
                                        %>
                                        <li page-index="<%=i+1%>" class="active" onclick="check(<%=i+1%>)"><a><%=i + 1%>
                                        </a></li>
                                        <%
                                        } else {
                                        %>
                                        <li page-index="<%=i+1%>" onclick="check(<%=i+1%>)"><a><%=i + 1%>
                                        </a></li>
                                        <%
                                                }
                                            }
                                        } else {
                                            for (int i = 0; i < page_count; i++) {
                                                if (i == current_page - 1) {
                                        %>
                                        <li page-index="<%=i+1%>" class="active" onclick="check(<%=i+1%>)"><a><%=i + 1%>
                                        </a></li>
                                        <%
                                        } else {
                                        %>
                                        <li page-index="<%=i+1%>" onclick="check(<%=i+1%>)"><a><%=i + 1%>
                                        </a></li>
                                        <%
                                                    }
                                                }
                                            }
                                        %>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
        </div>
        <!-- /.container-fluid -->
    </div>
    <!-- /#page-wrapper -->
</div>


<%--<script src="js/jquery-1.10.2.min.js"></script>--%>
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/dialog-min.js"></script>

<script>
    function check(page) {
        //自行修改访问的Servlet名和传递参数(get方式),也可使用post方式
        //获取ajax对象
        var title = document.getElementById("title").value;
        $.post('/admin/events', {
            page: page,
            title: title,
            type: 'ajax'
        }, function (d, status) {
            if ('success' == status) {
                var json = JSON.parse(d);
                var pageCount = json.pageCount;
                var currPage = json.currPage;
                var allCount = json.allCount;
                var title = json.title;
                var eventsList = json.eventsList;
                //获取tbody节点
                //清空节点原内容
                var innerHTML = "";
                //循环的塞入新的内容
                for (var i = 0; i < eventsList.length; i++) {
                    var id = i + 1 + 20 * (currPage - 1);
                    if (eventsList[i].status == 0) {
                        innerHTML += "<tr>" +
                            "<td>" + id + "</td>" +
                            "<td><a href=\"/admin/eventsedit?id=" + eventsList[i].id + "\">" + eventsList[i].title + "</a></td>" +
                            "<td>" + eventsList[i].date + "</td>" +
                            "<td>" + eventsList[i].time + "</td>" +
                            "<td>" + eventsList[i].address + "</td>" +
                            "<td>" + eventsList[i].reader + "</td>" +
                            "<td>" + '<button type="button" class="btn btn-warning btn-sm" onclick="change_status(' + eventsList[i].id + ')" value="0">已停用</button>' + "</td>" +
                            "<td>" +
                            '<button type="button" class="btn btn-danger btn-sm" onclick="remind(' + eventsList[i].id + ')">删除 </button>' +
                            '<a href="/admin/preview?id=' + eventsList[i].id + '&type=events" target="_blank"><button type="button" class="btn btn-success btn-sm">预览</button></a>' +
                            "</td>" +
                            "</tr>";
                    } else {
                        innerHTML += "<tr>" +
                            "<td>" + id + "</td>" +
                            "<td>" +
                            "<a href=\"/admin/eventsedit?id=" + eventsList[i].id + "\">" + eventsList[i].title + "</a>" +
                            "</td>" +
                            "<td>" + eventsList[i].date + "</td>" +
                            "<td>" + eventsList[i].time + "</td>" +
                            "<td>" + eventsList[i].address + "</td>" +
                            "<td>" + eventsList[i].reader + "</td>" +
                            "<td>" + '<button type="button" class="btn btn-info btn-sm" onclick="change_status(' + eventsList[i].id + ')" value="1">已启用</button>' + "</td>" +
                            "<td>" +
                            '<button type="button" class="btn btn-danger btn-sm" onclick="remind(' + eventsList[i].id + ')">删除' +
                            '</button> ' +
                            '<a href="/admin/preview?id=' + eventsList[i].id + '&type=events" target="_blank"><button type="button" class="btn btn-success btn-sm">预览</button></a>' +
                            "</td>" +
                            "</tr>";
                    }
                }
                $("#tbody").html(innerHTML);
                //重新符之搜索title
                document.getElementById("title").value = title;
                var record = document.getElementById("record");
                var paging = document.getElementById("paging");
                paging.innerHTML = "";
                record.innerHTML = "共" + allCount + "条记录，当前第" + currPage + "/" + pageCount + "页，每页20条记录";
                if (pageCount >= 7) {
                    for (i = currPage - 4; i < currPage + 3 && i < pageCount; i++) {
                        if (i < 0) {
                            continue;
                        }
                        id = i + 1;
                        if (id == currPage) {
                            paging.innerHTML += "<li page-index=" + id + " class=\"active\" onclick=\"check(" + id + ")\"><a>" + id + "</a></li>";
                        } else {
                            paging.innerHTML += "<li page-index=" + id + " onclick=\"check(" + id + ")\"><a>" + id + "</a></li>";
                        }
                    }
                } else {
                    for (i = 0; i < pageCount; i++) {
                        id = i + 1;
                        if (id == currPage) {
                            paging.innerHTML += "<li page-index=" + id + " class=\"active\" onclick=\"check(" + id + ")\"><a>" + id + "</a></li>";
                        } else {
                            paging.innerHTML += "<li page-index=" + id + " onclick=\"check(" + id + ")\"><a>" + id + "</a></li>";
                        }
                    }
                }
            }
        });
    }
    function change_status(id) {
        //自行修改访问的Servlet名和传递参数(get方式),也可使用post方式
        //获取ajax对象
        //sta 当前操作对象状态
        var but = event.target;
        $.post('/admin/status', {
            id: id,
            type: 'events'
        }, function (data, sta) {
            if ('success' == sta) {
                var json = JSON.parse(data);
                var status = json.status;
                if (status == true) {
                    if (but.value == 0) {
                        but.value = 1;
                        but.innerHTML = "已启用";
                        but.className = "btn btn-info btn-sm";
                    } else {
                        but.value = 0;
                        but.innerHTML = "已停用";
                        but.className = "btn btn-warning btn-sm";
                    }
                } else {
                    alert("修改失败！");
                }
            }

        });
    }
</script>

<script type="text/javascript">

    function remind(id) {
        var d = dialog({
            width: 260,
            title: '消息提醒',
            content: '<h4>确定删除？</h4>',
            okValue: '确定',
            ok: function () {

                $.post('/admin/delete', {
                    id: id,
                    type: 'events'
                }, function (data, sta) {
                    if ('success' == sta) {
                        window.location.reload();
                    } else {
                        alert("删除失败！");
                    }
                });
            },
            cancelValue: '取消',
            cancel: function () {
            }
        });
        d.showModal();
    }
</script>

</body>

</html>

