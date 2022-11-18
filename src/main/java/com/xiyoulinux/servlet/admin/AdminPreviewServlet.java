package com.xiyoulinux.servlet.admin;

import com.xiyoulinux.dao.AboutDAO;
import com.xiyoulinux.dao.BlogDAO;
import com.xiyoulinux.dao.EventsDAO;
import com.xiyoulinux.model.About;
import com.xiyoulinux.model.Blog;
import com.xiyoulinux.model.Events;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
    name = "AdminPreviewServlet",
    urlPatterns = {"/admin/preview"}
)
public class AdminPreviewServlet extends HttpServlet {
  public AdminPreviewServlet() {
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int id;
    String str_id;
    if ("events".equals(request.getParameter("type"))) {
      if (request.getParameter("id") == null) {
        response.sendRedirect("/admin/events");
      } else {
        id = 0;
        str_id = request.getParameter("id");

        try {
          id = Integer.parseInt(str_id);
        } catch (NumberFormatException var9) {
          response.sendRedirect("/404.html");
        }

        EventsDAO eventsDAO = new EventsDAO();
        Events events = eventsDAO.getEventsByID(id);
        if (null == events) {
          response.sendRedirect("/404.html");
        } else {
          request.setAttribute("title", events.getTitle());
          request.setAttribute("date", events.getDate());
          request.setAttribute("time", events.getTime());
          request.setAttribute("address", events.getAddress());
          request.setAttribute("label", events.getLabel());
          request.setAttribute("content", events.getContent());
          request.setAttribute("poster", events.getPoster());
          request.setAttribute("reader", events.getReader());
          request.getRequestDispatcher("/admin/eventsview.jsp").forward(request, response);
        }
      }
    } else if ("about".equals(request.getParameter("type"))) {
      if (request.getParameter("id") == null) {
        response.sendRedirect("/admin/about");
      } else {
        id = 0;
        str_id = request.getParameter("id");

        try {
          id = Integer.parseInt(str_id);
        } catch (NumberFormatException var8) {
          response.sendRedirect("/404.html");
        }

        AboutDAO aboutDAO = new AboutDAO();
        About about = aboutDAO.getAboutByID(id);
        if (null == about) {
          response.sendRedirect("/404.html");
        } else {
          request.setAttribute("title", about.getTitle());
          request.setAttribute("url", about.getPicture_url());
          request.setAttribute("content", about.getContent());
          request.setAttribute("markdown", about.getMarkdown());
          request.getRequestDispatcher("/aboutview.jsp").forward(request, response);
        }
      }
    } else if ("blog".equals(request.getParameter("type"))) {
      if (request.getParameter("id") == null) {
        response.sendRedirect("/admin/blog");
      } else {
        id = 0;
        str_id = request.getParameter("id");

        try {
          id = Integer.parseInt(str_id);
        } catch (NumberFormatException var7) {
          response.sendRedirect("/404.html");
        }

        BlogDAO blogDAO = new BlogDAO();
        Blog blog = blogDAO.getBlogByID(id);
        if (null == blog) {
          response.sendRedirect("/404.html");
        } else {
          request.setAttribute("title", blog.getTitle());
          request.setAttribute("author", blog.getAuthor());
          request.setAttribute("date", blog.getDate());
          request.setAttribute("time", blog.getTime());
          request.setAttribute("summary", blog.getSummary());
          request.setAttribute("url", blog.getUrl());
          request.getRequestDispatcher("/blogview.jsp").forward(request, response);
        }
      }
    }

  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request, response);
  }
}
