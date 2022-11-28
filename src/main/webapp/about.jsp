<%--
  Created by IntelliJ IDEA.
  User: zhoupan
  Date: 12/3/16
  Time: 4:15 PM
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
                <li><a href="/events">活动</a></li>
                <li><a href="http://blog.xiyoulinux.com">群博</a></li>
                <li><a href="http://cs.xiyoulinux.com/">CS</a></li>
                <li><a href="/login">登陆</a></li>
                <li class="active"><a href="/about">关于</a></li>
            </ul>
        </div>
    </div>
</div>
<br/>
<br/>
<div class="container">
    <div class="featurette">
        <div class="row">
            <div class="col-md-8">
                <h2 class="featurette-heading">Linux操作系统 </h2>
                <p class="lead">Linux是一套免费使用和自由传播的类Unix操作系统，是一个基于POSIX和UNIX的多用户、多任务、支持多线程和多CPU的操作系统。它能运行主要的UNIX工具软件、应用程序和网络协议。它支持32位和64位硬件。Linux继承了Unix以网络为核心的设计思想，是一个性能稳定的多用户网络操作系统。<a href="http://baike.baidu.com/link?url=Y-qntQsAEvYeZMOPAMLv3SClnps75sY8l3bADg9ghUrOxgi7mL56izWLDpiKP-vt4QPwZ0Y6u9SUuSi9iGrV7a">了解更多&gt;&gt;</a></p>
            </div>
            <div class="col-md-4">
                <div class="featurette-img">
                    <img src="images/linux.jpg">
                </div>
            </div>
        </div>
        <hr class="featurette-divider">
    </div>

    <div class="featurette">
        <div class="row">
            <div class="col-md-8 col-md-push-4">
                <h2 class="featurette-heading">GNU计划 <span class="text-muted">GNU is Not Unix</span></h2>
                <p class="lead">GNU计划，又称革奴计划，是由Richard Stallman在1983年9月27日公开发起的。它的目标是创建一套完全自由的操作系统。Richard Stallman最早是在net.unix-wizards新闻组上公布该消息，并附带《GNU宣言》等解释为何发起该计划的文章，其中一个理由就是要“重现当年软件界合作互助的团结精神”。为保证GNU软件可以自由地“使用、复制、修改和发布”，所有GNU软件都有一份在禁止其他人添加任何限制的情况下授权所有权利给任何人的协议条款，GNU通用公共许可证（GNU General Public License，GPL）。即“反版权”（或称Copyleft）概念。<a href="http://baike.baidu.com/link?url=RI-uZ4SfBWdG2MDh4uZA0qTK6Wsfh4r7fOFunCxzUiEwxLf4HZNBgNZViFwccsqHdJWgn5ol7GMJX6kHjRoDoK">了解更多&gt;&gt;</a></p>
            </div>
            <div class="col-md-4 col-md-pull-8">
                <div class="featurette-img">
                    <img src="images/gnu.jpg">
                </div>
            </div>
        </div>
        <hr class="featurette-divider">
    </div>

    <div class="featurette">
        <div class="row">
            <div class="col-md-8">
                <h2 class="featurette-heading">Xiyou Linux Group <span class="text-muted"></span></h2>
                <p class="lead">西邮Linux兴趣小组直属于西安邮电大学计算机学院，由王亚刚老师,王聪,刘洋等同学于2006年9月策划并组建,现已成为陕西高校中具有深远影响力的技术小组。
                    小组本着"学习Linux系统，崇尚自由软件，宣传开源文化"的精神，在Linux的学习和应用中起到了带头的作用！小组成员主要学习方向有：Linux平台应用软件开发、Linux平台嵌入式开发、Linux内核分析、Linux服务器配置等。 <a href="http://baike.baidu.com/view/10739521.htm">了解更多&gt;&gt;</a></p>
            </div>
            <div class="col-md-4">
                <div class="featurette-img">
                    <img src="images/xiyoulinux.png">
                </div>
            </div>
        </div>
    </div>
    <!-- /END THE FEATURETTES -->
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
<script type="text/javascript">
    $(document).ready(function () {
        $('.container .copy-right-content').each(function () {
            var year = new Date().getFullYear();
            $(this).find(".year").append(year);
        })
    });
</script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/retina-1.1.0.js"></script>
<script type="text/javascript" src="js/jquery.hoverdir.js"></script>
<script type="text/javascript" src="js/jquery.hoverex.min.js"></script>
<script type="text/javascript" src="js/jquery.prettyPhoto.js"></script>
<script type="text/javascript" src="js/jquery.isotope.min.js"></script>
<script type="text/javascript" src="js/custom.js"></script>
<script type="text/javascript" src="js/main.js"></script>
</body>
</html>
