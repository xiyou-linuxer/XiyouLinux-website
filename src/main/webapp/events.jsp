<%@ page import="java.util.ArrayList" %>
<%@ page import="com.xiyoulinux.model.Events" %>
<%--
  Created by IntelliJ IDEA.
  User: zhoupan
  Date: 11/5/16
  Time: 6:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="images/xiyoulinux.png">
    <title>西邮Linux兴趣小组</title>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <script>
        var _hmt = _hmt || [];
        (function() {
            var hm = document.createElement("script");
            hm.src = "https://hm.baidu.com/hm.js?9c494ad140deef94cd67400c530455b9";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
    </script>
</head>

<body>
<!--导航栏-->
<div class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-logo" href="/"><img src="images/xiyoulinux.png"
                                                         style="width: 50px;height: 50px;">西邮Linux兴趣小组</a>
        </div>
        <div class="navbar-collapse collapse navbar-right">
            <ul class="nav navbar-nav">
                <li><a href="/">主页</a></li>
                <li class="active"><a href="/events">活动</a></li>
                <li><a href="http://blog.xiyoulinux.com">群博</a></li>
                <li><a href="http://cs.xiyoulinux.org/">CS</a></li>
                <li><a href="/login">登陆</a></li>
                <li><a href="/about">关于</a></li>
            </ul>
        </div>
    </div>
</div>
<!--导航栏结束-->

<div class="container mtb" id="container">
    <div class="row" id="article">
        <!-- BLOG POSTS LIST -->
        <%
            ArrayList<Events> eventsList = (ArrayList<Events>) request.getAttribute("eventsList");
            for (int i = 0; i < eventsList.size(); i++) {
        %>
        <div class="col-lg-10 col-lg-offset-1" id="events-list">
            <!-- Blog Post -->
            <div class="row event" data-event-date="<%=eventsList.get(i).getDate()%>">
                <div class="col-lg-9">
                    <div class="event-title">
                        <a href="#"><h3 class="ctitle"><%=eventsList.get(i).getTitle()%>
                        </h3></a>
                    </div>
                    <div class="event-meta">
                        <span><strong><i class="fa fa-calendar"></i> 时间: </strong><%=eventsList.get(i).getDate()%>&nbsp;&nbsp;<%=eventsList.get(i).getTime()%></span>
                        <span><strong><i
                                class="fa fa-map-marker"></i> 地点:</strong><%=eventsList.get(i).getAddress()%></span>
                        <span class="event-tags">
                            <%
                                String label = eventsList.get(i).getLabel();
                                String str = "";
                                for (int j = 0; j < label.length(); j++) {
                                    if (label.charAt(j) == ',' || label.charAt(j) == '，') {
                                        if (!(str.equals("") || str.equals(",") || str.equals("，"))) {
                            %>
                                        <span class="label label-success"><%=str%></span>
                            <%
                                        }
                                        str = "";
                                    } else {
                                        str += label.charAt(j);
                                    }
                                }
                                if (!(str.equals("") || str.equals(",") || str.equals("，"))) {
                            %>
                            <span class="label label-success"><%=str%></span>
                            <%
                                }
                            %>
                        </span>
                    </div>
                    <div class="event-content">
                        <%=eventsList.get(i).getContent()%>
                        <p>阅读量：<%=eventsList.get(i).getReader()%>&nbsp;&nbsp;&nbsp;&nbsp;<a
                                href="/events">[ReadMore]</a></p>
                    </div>
                </div>
                <div class="col-lg-3">
                    <img class="img-responsive event-poster" src="<%=eventsList.get(i).getPoster()%>">
                </div>
            </div>
            <div class="hline"></div>
            <div class="spacing"></div>
        </div>
        <%}%>
    </div>
</div>
<!--网站底部-->
<footer>
    <div class="container">
        <div class="content">
            <div class="row">
                <div class="col-md-3">
                    <div class="link-address">
                        <p class="head">链接</p>
                        <ul>
                            <li><a href="http://www.xupt.edu.cn/">西安邮电大学</a></li>
                            <li><a href="http://cs.xupt.edu.cn">西安邮电大学计算机学院</a></li>
                            <li><a href="http://www.kerneltravel.net/">Linux内核之旅</a></li>
                            <li><a href="https://www.kernel.org/">The Linux Kernel Archives</a></li>
                            <li><a href="https://www.linuxfoundation.org/">The Linux Foundation</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="link-address">
                        <p class="head">社区</p>
                        <ul>
                            <li>邮件列表: <a href="https://groups.google.com/forum/#!forum/xiyoulinux">xiyoulinux</a></li>
                            <li>新浪微博: <a href="http://weibo.com/xylinux">@西邮Linux兴趣小组</a></li>
                            <li>GNU：<a href="http://www.gnu.org">GNU's Not Unix</a></li>
                            <li>LWN：<a href="https://lwn.net/">Linux Weekly News</a></li>
                            <li>Linux Story：<a href="https://linuxstory.org/">Linux Story</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="link-address">
                        <p class="head">地址</p>
                        <ul>
                            <li>
                                陕西省 西安市 长安区<br>
                                西安邮电大学长安校区<br>
                                东区 教学实验楼 FZ118<br>
                                邮编：710121
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-3 text-center">
                    <div class="link-address">
                        <p class="head">关注我们</p>
                        <ul>
                            <li>
                                <img class="qr" src="images/weixin.jpg">
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="copy-right-content">
          <span>
             Copyright © 2006 - <span class="year"></span> 西邮Linux兴趣小组 All Rights Reserved
          </span>
        </div>
    </div>
</footer>
<!--网站底部-->

<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/retina-1.1.0.js"></script>
<script type="text/javascript" src="js/jquery.hoverdir.js"></script>
<script type="text/javascript" src="js/jquery.hoverex.min.js"></script>
<script type="text/javascript" src="js/jquery.prettyPhoto.js"></script>
<script type="text/javascript" src="js/jquery.isotope.min.js"></script>
<script type="text/javascript" src="js/custom.js"></script>
<script type="text/javascript" src="js/main.js"></script>


<script>
    //设定当前页面
    var curpage = 1;
    //记录当前已经加载的事件
    var id_array = new Array();
    //记录当前已经加载的页面
    var page_array = new Array();
    //创建事件
    function ScrollEvent() {
        //var wScrollY = window.scrollY; // 当前滚动条位置
        var wScrollY = getScrollTop();
        var wInnerH = window.innerHeight; // 设备窗口的高度（不会变）
        var bScrollH = document.body.scrollHeight; // 滚动条总高度
        if (wScrollY + wInnerH >= bScrollH - 400) {
            if (page_array.indexOf(curpage + 1) == -1) {
                page_array.push(curpage + 1);
                check(curpage + 1);
            }
        }
    }

    function getScrollTop() {
        var scrollTop = 0;
        if (document.documentElement && document.documentElement.scrollTop) {
            scrollTop = document.documentElement.scrollTop;
        }
        else if (document.body) {
            scrollTop = document.body.scrollTop;
        }
        return scrollTop;
    }

    //绑定事件
    window.addEventListener("scroll", ScrollEvent, false);

    /*通过异步传输XMLHTTP发送参数到ajaxServlet,返回符合条件的XML文档*/
    function check(page) {
        //自行修改访问的Servlet名和传递参数(get方式),也可使用post方式
        //获取ajax对象
        $.post('/events', {
            page: page,
            type: 'ajax'
        }, function (data, status) {
            if ('success' == status) {
                //从JSON中取出数据
                var json = JSON.parse(data);
                var pageCount = json.pageCount;
                var currPage = json.currPage;
                var allCount = json.allCount;
                var eventsList = json.eventsList;
                //获取tbody节点
                var article = document.getElementById("article");
                //更新当前页
                curpage = currPage;
                if (currPage == pageCount) {
                    window.removeEventListener("scroll", ScrollEvent, false);
                }
                for (var i = 0; i < eventsList.length; i++) {
                    if (id_array.indexOf(eventsList[i].id) > -1) {
                        continue;
                    }
                    id_array.push(eventsList[i].id);
                    var label = splitLabel(eventsList[i].label);
                    article.innerHTML += "" +
                        "<div class=\"col-lg-10 col-lg-offset-1\" id=\"events-list\">" +
                        "<div class=\"row event\" data-event-date=\"" + eventsList[i].date + "\">" +
                        "<div class=\"col-lg-9\">" +
                        "<div class=\"event-title\">" +
                        "<a href=\"#\"><h3 class=\"ctitle\">" + eventsList[i].title + "</h3></a>" +
                        "</div>" +
                        "<div class=\"event-meta\">" +
                        "<span><strong><i class=\"fa fa - calendar\"></i> 时间: </strong>" + eventsList[i].date + "&nbsp;&nbsp;" + eventsList[i].time + "</span>" +
                        "<span><strong><i class=\"fa fa-map-marker\"></i> 地点:</strong>" + eventsList[i].address + " </span>" +
                        "<span class=\"event-tags\">" + label + "</span>" +
                        "</div>" +
                        "<div class=\"event-content\">" + eventsList[i].content + "" +
                        "<p>阅读量：" + eventsList[i].reader + " " +
                        "</div>" +
                        "</div>" +
                        "<div class=\"col-lg-3\">" +
                        "<img class=\"img-responsive event-poster\" src=\"" + eventsList[i].poster + "\">" +
                        "</div>" +
                        "</div>" +
                        "<div class=\"hline\"></div>" +
                        "<div class=\"spacing\"></div>" +
                        "</div>"
                }
            }
        });
    }
    function splitLabel(label) {
        var str = "";
        var rtu = "";
        for (var i = 0; i < label.length; i++) {
            if (label[i] == ',' || label[i] == '，') {
                if (!((str == "") || str == "," || str == "，")) {
                    rtu += "<span class=\"label label-success\">" + str + "</span>";
                }
                str = "";
            } else {
                str += label[i];
            }
        }
        if (!(str == "" || str == "," || str == "，")) {
            rtu += "<span class=\"label label-success\">" + str + "</span>";
        }
        return rtu;
    }

</script>


<script type="text/javascript">
    $(document).ready(function () {
        $('.container .copy-right-content').each(function () {
            var year = new Date().getFullYear();
            $(this).find(".year").append(year);
        })
    });
</script>
</body>
</html>
