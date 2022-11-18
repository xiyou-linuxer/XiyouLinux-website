<%--
  Created by IntelliJ IDEA.
  User: zhoupan
  Date: 11/3/16
  Time: 8:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/css/datepicker/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" type="text/css" href="/css/datepicker/datedropper.css">
    <link rel="stylesheet" type="text/css" href="/css/datepicker/timedropper.min.css">
    <link rel="stylesheet" href="/admin/css/editormd.css"/>
    <link rel="shortcut icon" href="images/xiyoulinux.png">
    <link rel="shortcut icon" href="images/xiyoulinux.png">
    <title>西邮Linux兴趣小组</title>
    <link href="/admin/css/sb-admin.css" rel="stylesheet">
    <link href="/admin/css/sb-bk-theme.css" rel="stylesheet">
    <link href="/admin/css/bk.css" rel="stylesheet">


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
                    新增文章
                </div>

                <br/>
                <br/>
                <br/>

                <div class="container">
                    <form action="/admin/aboutedit" method="post">
                        <%
                            if (request.getAttribute("id") == null) {
                        %>
                        <input type="hidden" name="status" value="0">
                        <%
                        } else {
                        %>
                        <input type="hidden" name="status"
                               value="<%if(null != request.getAttribute("id")){out.print(request.getAttribute("id"));}%>">
                        <%
                            }
                        %>
                        <div class="row">
                            <div class="col-xs-3">
                                <div class="input-group">
                                    <span class="input-group-addon">标题：</span>

                                    <input type="text" class="form-control" name="title" placeholder="在此输入标题"
                                           value="<%if(null != request.getAttribute("title")){out.print(request.getAttribute("title"));}%>">
                                </div>
                            </div>
                            <div class="col-xs-3">
                                <span style="margin: 0 auto;color: red;font-size: 20px;"><%if (null != request.getAttribute("message")) {out.print(request.getAttribute("message"));}%></span>
                            </div>
                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-xs-3">
                                <div class="input-group">
                                    <span class="input-group-addon">海报：</span>
                                    <input type="text" class="form-control" name="url" placeholder="在此输海报url"
                                           value="<%if(null != request.getAttribute("url")){out.print(request.getAttribute("url"));}%>">
                                </div>
                            </div>
                        </div>
                        <br/>
                        <input type="hidden" name="markdown" id="markdown">
                        <input type="hidden" name="content" id="content">
                        <textarea style="display: none" id="tmp"><%if(null != request.getAttribute("mkdown")){out.print(request.getAttribute("mkdown"));}%></textarea>
                        <div id="test-editormd">
                            <textarea style="display:none;width: 97%" id="text"></textarea>
                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-xs-3">
                                <div class="input-group">
                                    <input type="submit" class="btn btn-success" name="submit" value="提交" onclick="getHtml()">
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <br/>
            </div>
        </div>
        <!-- /.container-fluid -->
    </div>
    <!-- /#page-wrapper -->
</div>







<!--bootstrap依赖-->
<script src="/js/jquery-2.1.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>


<!--datepicter的依赖-->
<script type="text/javascript" charset="utf-8" src="/js/datepicker/datedropper.min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/datepicker/timedropper.min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/datepicker/datepicker.js"></script>


<!--editor-md 依赖 -->
<script src="js/jquery.min.js"></script>
<script src="js/editormd.min.js"></script>
<script type="text/javascript">
    var testEditor;
    $(function () {
        testEditor = editormd("test-editormd", {
            width: "100%",
            height: 640,
            syncScrolling: "single",
            path: "../lib/",
            saveHTMLToTextarea: true,
            onfullscreen: function () {
                input = document.getElementsByClassName("form-control");
                for (var i = 0; i < input.length; i++) {
                    input[i].type = "hidden";
                }
                input = document.getElementsByName("submit");
                for (i = 0; i < input.length; i++) {
                    input[i].type = "hidden";
                }
            },
            onfullscreenExit: function () {
                input = document.getElementsByClassName("form-control");
                for (var i = 0; i < input.length; i++) {
                    input[i].type = "text";
                }
                input = document.getElementsByName("submit");
                for (i = 0; i < input.length; i++) {
                    input[i].type = "submit";
                }
            }
        });
    });

    function getHtml() {
        content = document.getElementById("content");
        content.value = testEditor.getHTML();
        md = document.getElementById("markdown");
        md.value = testEditor.getMarkdown();
    }
    function addContent() {
        content = document.getElementById("tmp");
        document.getElementById("text").value = content.value
    }
    onload = addContent();
</script>

</body>
</html>