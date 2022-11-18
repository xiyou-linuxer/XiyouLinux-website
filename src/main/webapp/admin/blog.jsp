<%--
  Created by IntelliJ IDEA.
  User: zhoupan
  Date: 11/5/16
  Time: 9:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.xiyoulinux.model.Blog" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="../images/xiyoulinux.png">
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
        <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav side-nav">
                <%--<li>--%>
                <%--<a href="/admin/"><i class="fa fa-fw fa-dashboard"></i>首页</a>--%>
                <%--</li>--%>
                <li>
                    <a href="/admin/events"><i class="fa fa-fw fa-table"></i>活动管理</a>
                </li>
                <li class="active">
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
                        文章管理
                    </h1>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading">
                    文章列表
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
                    </div>

                    <table class="table table-out-bordered table-hover">
                        <tr>
                            <th style="width:50px;">序号</th>
                            <th>标题</th>
                            <th>作者</th>
                            <th>日期</th>
                            <th>时间</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        <thead>
                        </thead>
                        <tbody id="tbody">
                        <%
                            ArrayList<Blog> blogList = (ArrayList<Blog>) request.getAttribute("blogList");
                            for (int i = 0; i < blogList.size(); i++) {
                                int current = (int) request.getAttribute("currentPage") - 1;
                        %>
                        <tr>
                            <td><%=current * 20 + i + 1%>
                            </td>
                            <td><a href="<%=blogList.get(i).getUrl()%>"
                                   target="_blank"><%=blogList.get(i).getTitle()%>
                            </a></td>
                            <td><%=blogList.get(i).getAuthor()%>
                            </td>
                            <td><%=blogList.get(i).getDate()%>
                            </td>
                            <td><%=blogList.get(i).getTime()%>
                            </td>
                            <td>
                                <%
                                    if (blogList.get(i).getStatus() == 0) {
                                %>
                                <button type="button" class="btn btn-warning btn-sm"
                                        onclick="change_status(<%=blogList.get(i).getId()%>)" value="0">已停用
                                </button>
                                <%
                                } else {
                                %>
                                <button type="button" class="btn btn-info btn-sm"
                                        onclick="change_status(<%=blogList.get(i).getId()%>)" value="1">已启用
                                </button>
                                <%
                                    }
                                %>
                            </td>
                            <td>

                                <button type="button" class="btn btn-danger btn-sm"
                                        onclick="remind(<%=blogList.get(i).getId()%>)">删除
                                </button>
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

    </div>
    <!-- /.container-fluid -->
    <div id="test">
    </div>
</div>
<!-- /#page-wrapper -->
<script src="js/jquery-1.10.2.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/html5shiv.min.js"></script>
<script src="js/respond.min.js"></script>
<script type="text/javascript" src="js/dialog-min.js"></script>


</div>
<!-- /#wrapper -->
<script>
    /*通过异步传输XMLHTTP发送参数到ajaxServlet,返回符合条件的XML文档*/
    function check(page) {
        //自行修改访问的Servlet名和传递参数(get方式),也可使用post方式
        //获取ajax对象
        var title = document.getElementById("title").value;

        $.post('/admin/blog', {
            page: page,
            title: title
        }, function (data, status) {
            //从JSON中取出数据
            if ('success' == status) {
                var json = JSON.parse(data);
                var pageCount = json.pageCount;
                var currPage = json.currPage;
                var allCount = json.allCount;
                var title = json.title;
                var blogList = json.blogList;
                //获取tbody节点
                var tbody = document.getElementById("tbody");
                //清空节点原内容
                tbody.innerHTML = "";
                //循环的塞入新的内容
                for (var i = 0; i < blogList.length; i++) {
                    var id = i + 1 + 20 * (currPage - 1);
                    if (blogList[i].status == 0) {
                        tbody.innerHTML += "<tbody>" +
                            "<tr>" +
                            "<td>" + id + "</td>" +
                            "<td><a href=" + blogList[i].url + " target=\"_blank\">" + blogList[i].title + "</a></td>" +
                            "<td>" + blogList[i].author + "</td>" +
                            "<td>" + blogList[i].date + "</td>" +
                            "<td>" + blogList[i].time + " </td>" +
                            "<td>" +
                            '<button type="button" class="btn btn-warning btn-sm" onclick="change_status(' + blogList[i].id + ')" value="0">已停用' +
                            '</button>' +
                            "</td>" +
                            "<td>" +
                            '<button type="button" class="btn btn-danger btn-sm" onclick="remind(' + blogList[i].id + ')">删除' +
                            '</button>' +
                            "</td>" +
                            "</tr>" +
                            "</tbody>"
                    } else {
                        tbody.innerHTML += "<tbody>" +
                            "<tr>" +
                            "<td>" + id + "</td>" +
                            "<td><a href=" + blogList[i].url + " target=\"_blank\">" + blogList[i].title + "</a></td>" +
                            "<td>" + blogList[i].author + "</td>" +
                            "<td>" + blogList[i].date + "</td>" +
                            "<td>" + blogList[i].time + " </td>" +
                            "<td>" +
                            '<button type="button" class="btn btn-info btn-sm" onclick="change_status(' + blogList[i].id + ')" value="1">已启用' +
                            '</button>' +
                            "</td>" +
                            "<td>" +
                            '<button type="button" class="btn btn-danger btn-sm" onclick="remind(' + blogList[i].id + ')">删除' +
                            '</button>' +
                            "</td>" +
                            "</tr>" +
                            "</tbody>"
                    }
                }
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
        if (window.XMLHttpRequest) {
            req = new XMLHttpRequest();
        }
        else if (window.ActiveXObject) {
            req = new ActiveXObject("Microsoft.XMLHTTP");
        }
        if (req != null) {
            var url = "/admin/status";
            req.open("post", url, true);
            req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            //指定处理函数
            req.onreadystatechange = function () {
                if (req.readyState == 4) {// 4 = "loaded"
                    if (req.status == 200) {
                        //从JSON中取出数据
                        var json = JSON.parse(req.responseText);
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
                }
            };
            req.send("id=" + id + "&type=blog");
        } else {
            alert("Your browser does not support XMLHTTP.");
        }
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
                    type: 'blog'
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
