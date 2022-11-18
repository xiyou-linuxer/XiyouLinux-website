package com.xiyoulinux.servlet.admin;

import com.xiyoulinux.dao.BlogDAO;
import com.xiyoulinux.dao.EventsDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebServlet(
    name = "AdminStatusServlet",
    urlPatterns = {"/admin/status"}
)
public class AdminStatusServlet extends HttpServlet {
  public AdminStatusServlet() {
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String str_id;
    int blogid;
    boolean status;
    JSONObject json;
    if ("events".equals(request.getParameter("type"))) {
      str_id = request.getParameter("id");

      try {
        blogid = Integer.parseInt(str_id);
        EventsDAO eventsDAO = new EventsDAO();
        status = eventsDAO.alterEventsStatus(blogid);
        json = new JSONObject();
        json.put("status", status);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(json.toString());
      } catch (NumberFormatException var9) {
        response.sendRedirect("/404.html");
      }
    } else if ("blog".equals(request.getParameter("type"))) {
      str_id = request.getParameter("id");

      try {
        blogid = Integer.parseInt(str_id);
        BlogDAO blogDAO = new BlogDAO();
        status = blogDAO.alterBlogStatus(blogid);
        json = new JSONObject();
        json.put("status", status);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(json.toString());
      } catch (NumberFormatException var8) {
        response.sendRedirect("/404.html");
      }
    }

  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request, response);
  }
}
