package com.xiyoulinux.servlet.admin;

import com.xiyoulinux.dao.TitleDAO;
import com.xiyoulinux.model.Title;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
    name = "AdminTitleUpdateServlet",
    urlPatterns = {"/admin/titleedit"}
)
public class AdminTitleUpdateServlet extends HttpServlet {
  public AdminTitleUpdateServlet() {
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    if (null == request.getParameter("id")) {
      response.sendRedirect("/404.html");
    }

    String str_id = request.getParameter("id");
    int id = 0;

    try {
      id = Integer.parseInt(str_id);
    } catch (NumberFormatException var12) {
      response.sendRedirect("/404.html");
    }

    if (null == request.getParameter("title")) {
      TitleDAO titleDAO = new TitleDAO();
      Title title = titleDAO.getTitleById(id);
      request.setAttribute("id", title.getId());
      request.setAttribute("title", title.getTitle());
      request.setAttribute("subtitle", title.getSubtitle());
      request.setAttribute("url", title.getUrl());
      request.getRequestDispatcher("/admin/titleedit.jsp").forward(request, response);
    } else {
      if (id <= 0) {
        response.sendRedirect("/404.html");
      }

      boolean sign = true;
      String message = "";
      String title = request.getParameter("title");
      String subtitle = request.getParameter("subtitle");
      String url = request.getParameter("url");
      if (null != title && title.length() > 0 && title.length() <= 20) {
        if (null != subtitle && subtitle.length() > 0 && title.length() <= 30) {
          if (null == url || url.length() <= 0 || url.length() > 256) {
            sign = false;
            message = message + "url为空或太长！";
          }
        } else {
          sign = false;
          message = message + "副标题为空或太长(小于30个字符)！";
        }
      } else {
        sign = false;
        message = message + "标题为空或太长(小于20个字符)！";
      }

      if (sign) {
        TitleDAO titleDAO = new TitleDAO();
        Title titleObj = new Title();
        titleObj.setId(id);
        titleObj.setTitle(title);
        titleObj.setSubtitle(subtitle);
        titleObj.setUrl(url);
        titleDAO.update(titleObj);
        response.sendRedirect("/admin/title");
      } else {
        request.setAttribute("id", id);
        request.setAttribute("title", title);
        request.setAttribute("subtitle", subtitle);
        request.setAttribute("url", url);
        request.setAttribute("message", message);
        request.getRequestDispatcher("/admin/titleedit.jsp").forward(request, response);
      }
    }

  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request, response);
  }
}
