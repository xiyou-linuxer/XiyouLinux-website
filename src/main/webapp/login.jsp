<%--
  Created by IntelliJ IDEA.
  User: zhoupan
  Date: 10/19/16
  Time: 9:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en" class="no-js">
    <head>
        <meta charset="utf-8">
        <title>XiyouLinux官网后台登陆</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- CSS -->
        <link rel="stylesheet" href="css/supersized.css">
        <link rel="stylesheet" href="css/login.css">
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

        <div class="page-container">
            <h1>Login</h1>
            <form action="/login" method="POST" id="form1">
                <input type="text" name="username" class="Username" placeholder="Username">
                <input type="password" name="password" class="Password" placeholder="Password">
                <button type="submit">Sign in</button>
            </form>
        </div>
        <div class="page-container" style="color: #dc143c">
        <%
            if(request.getAttribute("reason")!= null){
                System.out.println(request.getAttribute("reason"));
            }
        %>
        </div>
        <script src="js/jquery-1.8.2.min.js"></script>
        <script src="js/supersized.3.2.7.min.js"></script>
        <script src="js/supersized-init.js"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>

