package com.xiyoulinux.servlet.admin;

import com.xiyoulinux.dao.EventsDAO;
import com.xiyoulinux.model.Events;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet(
    name = "AdminEventsServlet",
    urlPatterns = {"/admin/events"}
)
public class AdminEventsServlet extends HttpServlet {
  public AdminEventsServlet() {
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    EventsDAO eventsDAO;
    ArrayList eventsList;
    int page;
    int currentPage;
    int allCount;
    if (request.getParameter("page") == null) {
      eventsDAO = new EventsDAO();
      eventsList = eventsDAO.getEventsByPage(1, "");
      page = eventsDAO.getAllPageCount();
      currentPage = eventsDAO.getCurrentPage();
      allCount = eventsDAO.getAllCount();
      request.setAttribute("eventsList", eventsList);
      request.setAttribute("pageCount", page);
      request.setAttribute("currentPage", currentPage);
      request.setAttribute("allCount", allCount);
      request.getRequestDispatcher("/admin/events.jsp").forward(request, response);
    } else if ("ajax".equals(request.getParameter("type"))) {
      String pagestring = request.getParameter("page");
      page = Integer.parseInt(pagestring);
      String title;
      if (null == request.getParameter("title")) {
        title = "";
      } else {
        title = request.getParameter("title");
      }

       eventsDAO = new EventsDAO();
       eventsList = eventsDAO.getEventsByPage(page, title);
      int pageCount = eventsDAO.getAllPageCount();
       currentPage = eventsDAO.getCurrentPage();
       allCount = eventsDAO.getAllCount();
      JSONObject json = new JSONObject();
      JSONArray jsonArray = new JSONArray();
      json.put("pageCount", pageCount);
      json.put("currPage", currentPage);
      json.put("allCount", allCount);
      json.put("title", title);
      Iterator var13 = eventsList.iterator();

      while(var13.hasNext()) {
        Events events = (Events)var13.next();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", events.getId());
        jsonObject.put("title", events.getTitle());
        jsonObject.put("date", events.getDate());
        jsonObject.put("time", events.getTime());
        jsonObject.put("address", events.getAddress());
        jsonObject.put("reader", events.getReader());
        jsonObject.put("status", events.getStatus());
        jsonArray.put(jsonObject);
      }

      json.put("eventsList", jsonArray);
      response.setCharacterEncoding("utf-8");
      response.getWriter().write(json.toString());
    } else {
      eventsDAO = new EventsDAO();
      eventsList = eventsDAO.getEventsByPage((Integer)request.getAttribute("currentPage"), "");
      page = eventsDAO.getAllPageCount();
      currentPage = eventsDAO.getCurrentPage();
      allCount = eventsDAO.getAllCount();
      request.setAttribute("eventsList", eventsList);
      request.setAttribute("pageCount", page);
      request.setAttribute("currentPage", currentPage);
      request.setAttribute("allCount", allCount);
      request.getRequestDispatcher("/admin/events.jsp").forward(request, response);
    }

  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request, response);
  }
}
