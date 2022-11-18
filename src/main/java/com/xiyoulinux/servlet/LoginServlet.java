package com.xiyoulinux.servlet;

import com.xiyoulinux.dao.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
    name = "LoginServlet",
    urlPatterns = {"/login"}
)
public class LoginServlet extends HttpServlet {
  public LoginServlet() {
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    if (null == request.getParameter("username")) {
      request.getRequestDispatcher("/login.jsp").forward(request, response);
    } else {
      request.setCharacterEncoding("utf-8");
      String username = request.getParameter("username");
      String password = request.getParameter("password");
      request.getSession().setAttribute("login", (Object)null);
      if (username.length() >= 1 && password.length() >= 1) {
        UserDAO login = new UserDAO();
        if (login.check(username, password)) {
          request.getSession().setAttribute("username", username);
          request.getSession().setAttribute("login", "ok");
          response.sendRedirect("/admin/events");
        } else {
          request.setAttribute("reason", "用户名或密码不正确！");
          request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
      } else {
        request.setAttribute("reason", "用户名或密码不能为空！");
        request.getRequestDispatcher("/login.jsp").forward(request, response);
      }
    }

  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request, response);
  }
}
