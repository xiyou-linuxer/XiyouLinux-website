<%--
  Created by IntelliJ IDEA.
  User: zhoupan
  Date: 11/3/16
  Time: 16:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/css/datepicker/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" type="text/css" href="/css/datepicker/datedropper.css">
    <link rel="stylesheet" type="text/css" href="/css/datepicker/timedropper.min.css">
    <link rel="stylesheet" href="/admin/css/editormd.css"/>
    <link rel="shortcut icon" href="/images/xiyoulinux.png">
    <title>活动管理</title>
    <link href="/admin/css/sb-admin.css" rel="stylesheet">
    <link href="/admin/css/sb-bk-theme.css" rel="stylesheet">
    <link href="/admin/css/bk.css" rel="stylesheet">

</head>
<body>


<div id="wrapper">
    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" id="hid" role="navigation">
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
            <div class="row page-header-box" >
                <div class="col-lg-12">
                    <h1 class="page-header">
                        活动管理
                    </h1>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading">
                    编辑文章
                </div>

                <br/>
                <br/>
                <br/>

                <div class="container">
                    <%
                        if (request.getAttribute("poster") != null) {
                    %>

                    <form action="/admin/eventsedit" method="post" enctype="multipart/form-data" name="myform" onsubmit="return isPic(1)">
                    <%
                    }else{
                    %>
                    <form action="/admin/eventsedit" method="post" enctype="multipart/form-data" name="myform" onsubmit="return isPic(0)">
                    <%
                        }
                    %>
                            <%
                                if (request.getAttribute("id") == null) {
                            %>
                            <input type="hidden" name="status" value="0">
                            <%
                            } else {
                            %>
                            <input type="hidden" name="status"
                                   value='<%=request.getAttribute("id")%>'>
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
                    <span style="margin: 0 auto;color: red;font-size: 20px;"><%
                        if (null != request.getAttribute("message")) {
                            out.print(request.getAttribute("message"));
                        }
                    %></span>
                                </div>
                            </div>
                            <br/>
                            <div class="row">
                                <div class="col-xs-3">
                                    <div class="input-group ">
                                        <span class="input-group-addon">日期：</span>
                                        <input type="text" class="form-control" name="date" id="pickdate"
                                               placeholder="在此输入日期"
                                               value="<%if(null != request.getAttribute("date")){out.print(request.getAttribute("date"));}%>">
                                    </div>
                                </div>
                                <div class="col-xs-3">
                                    <div class="input-group">
                                        <span class="input-group-addon">时间：</span>
                                        <input type="text" class="form-control" name="time" id="picktime"
                                               placeholder="在此输入时间"
                                               value="<%if(null != request.getAttribute("time")){out.print(request.getAttribute("time"));}%>">
                                    </div>
                                </div>
                                <div class="col-xs-3">
                                    <div class="input-group">
                                        <span class="input-group-addon">地点：</span>
                                        <input type="text" class="form-control" name="address" placeholder="在此输入地点"
                                               value="<%if(null != request.getAttribute("address")){out.print(request.getAttribute("address"));}%>">
                                    </div>
                                </div>
                                <div class="col-xs-3">
                                    <div class="input-group">
                                        <span class="input-group-addon">标签：</span>
                                        <input type="text" class="form-control" name="label" placeholder="多个标签逗号隔开"
                                               value="<%if(null != request.getAttribute("label")){out.print(request.getAttribute("label"));}%>">
                                    </div>
                                </div>
                            </div>
                            <br/>
                            <div class="row">
                                <div class="col-xs-3">
                                    <div class="form-group">

                                        <%
                                            if (request.getAttribute("poster") != null) {
                                        %>
                                        <div id="origin_poster">
                                            <span>已上传的活动海报:</span>
                                            <br/>
                                            <br/>
                                            <img src="<%=request.getAttribute("poster")%> " style="height: 200px">
                                        </div>
                                        <br/>
                                        <br/>
                                        <span>更改活动海报(小于5M):</span>
                                        <input type="file" id="inputfile" name="poster" onchange="isPic(1)">
                                        <%
                                        } else {
                                        %>
                                        <br/>
                                        <br/>
                                        <span>活动海报(小于5M):</span>
                                        <input type="file" id="inputfile" name="poster" onchange="isPic(0)">
                                        <%}%>
                                    </div>
                                </div>
                            </div>
                            <br/>
                            <textarea style="display: none" id="tmp"><%
                                if (null != request.getAttribute("mkdown")) {
                                    out.print(request.getAttribute("mkdown"));
                                }
                            %></textarea>
                            <div id="test-editormd">
                                <textarea style="display:none;width: 97%" id="text"></textarea>
                            </div>
                            <input type="hidden" name="markdown" id="markdown">
                            <input type="hidden" name="content" id="content">
                            <br/>
                            <div class="row">
                                <div class="col-xs-3">
                                    <div class="input-group">
                                        <input type="submit" class="btn btn-success" value="提交" name="submit"
                                               onclick="getHtml()">
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


<!--editor-md 依赖 -->
<script src="js/jquery.min.js"></script>
<script src="js/editormd.min.js"></script>

<!--bootstrap依赖-->
<script src="/js/jquery-2.1.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>

<!--datepicter的依赖-->
<script type="text/javascript" charset="utf-8" src="/js/datepicker/datedropper.min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/datepicker/timedropper.min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/datepicker/datepicker.js"></script>


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
<script type="text/javascript">
    function isPic(status) {
        var fileSize = 0;
        var filetypes = ["jpg", "png", "gif", "JPG", "PNG", "GIF"];
        var filepath = myform.poster.value;
        var filemaxsize = 1024 * 5;     //5M
        if (filepath) {
            var isnext = false;
            var fileend = filepath;
            while (true) {
                if (fileend.indexOf('.') < 0) {
                    break;
                }
                fileend = fileend.substring(fileend.indexOf('.') + 1);
            }

            if (filetypes && filetypes.length > 0) {
                for (var i = 0; i < filetypes.length; i++) {
                    if (filetypes[i] == fileend) {
                        isnext = true;
                        break;
                    }
                }
            }
            if (!isnext) {
                alert("只能上传jpg,png,gif文件！");
                myform.poster.value = "";
                return false;
            }
        } else {
            if (status == 1) {
                return true;
            } else {
                alert("活动海报不能为空！");
                alert(status);
                return false;
            }
        }
        if (!myform.poster.files) {
            var filePath = myform.poster.value;
            var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
            if (!fileSystem.FileExists(filePath)) {
                alert("图片不存在，请重新输入！");
                return false;
            }
            var file = fileSystem.GetFile(filePath);
            fileSize = file.Size;
        } else {
            fileSize = myform.poster.files[0].size;
        }

        var size = fileSize / 1024;


        if (size > filemaxsize) {
            alert("图片大小不能大于" + filemaxsize / 1024 + "M！");
            myform.poster.value = "";
            return false;
        } else if (size <= 0) {
            alert("图片大小不能为0M！");
            myform.poster.value = "";
            return false;
        } else {
            $("#origin_poster").hide();
            return true;
        }
    }
</script>

</body>
</html>