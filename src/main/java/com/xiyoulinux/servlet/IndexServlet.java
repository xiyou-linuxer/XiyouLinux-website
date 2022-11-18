package com.xiyoulinux.servlet;


import com.xiyoulinux.dao.BlogDAO;
import com.xiyoulinux.dao.EventsDAO;
import com.xiyoulinux.dao.TitleDAO;
import com.xiyoulinux.model.Blog;
import com.xiyoulinux.model.Events;
import com.xiyoulinux.model.Title;
import java.io.IOException;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
    name = "IndexServlet",
    urlPatterns = {""}
)
public class IndexServlet extends HttpServlet {
  public IndexServlet() {
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println("doPost index--- titleDAO");
    TitleDAO titleDAO = new TitleDAO();
    ArrayList<Title> titles = new ArrayList();
    Title title1 = titleDAO.getTitleById(1);
    titles.add(title1);
    Title title2 = titleDAO.getTitleById(2);
    titles.add(title2);
    Title title3 = titleDAO.getTitleById(3);
    titles.add(title3);
    Title title4 = titleDAO.getTitleById(4);
    titles.add(title4);
    Title title5 = titleDAO.getTitleById(5);
    titles.add(title5);
    Title title6 = titleDAO.getTitleById(6);
    titles.add(title6);
    BlogDAO blogDAO = new BlogDAO();
    ArrayList<Blog> blogs = blogDAO.getBlogByPage(1, "", 5);
    EventsDAO eventsDAO = new EventsDAO();
    ArrayList<Events> eventss = eventsDAO.getEventsByPage(1, "", 5);
    request.setAttribute("titleList", titles);
    request.setAttribute("blogList", blogs);
    request.setAttribute("eventsList", eventss);
    request.getRequestDispatcher("main.jsp").forward(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request, response);
  }
}
