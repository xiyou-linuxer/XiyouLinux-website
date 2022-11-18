package com.xiyoulinux.servlet;

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
    name = "EventsViewServlet",
    urlPatterns = {"/events"}
)
public class EventsViewServlet extends HttpServlet {
  public EventsViewServlet() {
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Events events;
    EventsDAO eventsDAO;
    if (request.getParameter("id") == null) {
      if (null != request.getParameter("page") && "ajax".equals(request.getParameter("type"))) {
        String str_page = request.getParameter("page");
        int page = 2;

        try {
          page = Integer.parseInt(str_page);
        } catch (NumberFormatException var16) {
          response.sendRedirect("/404.html");
        }

        eventsDAO = new EventsDAO();
        ArrayList<Events> eventsList = eventsDAO.getEventsByPage(page, "", 2);
        if (eventsList.size() <= 0) {
          response.getWriter().write("{}");
        } else {
          int pageCount = eventsDAO.getAllPageCount();
          int currentPage = eventsDAO.getCurrentPage();
          int allCount = eventsDAO.getAllCount();
          JSONObject json = new JSONObject();
          JSONArray jsonArray = new JSONArray();
          json.put("pageCount", pageCount);
          json.put("currPage", currentPage);
          json.put("allCount", allCount);
          Iterator var12 = eventsList.iterator();

          while(var12.hasNext()) {
            Events events1 = (Events)var12.next();
            eventsDAO.addEventsRead(events1.getId());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", events1.getId());
            jsonObject.put("title", events1.getTitle());
            jsonObject.put("date", events1.getDate());
            jsonObject.put("time", events1.getTime());
            jsonObject.put("address", events1.getAddress());
            jsonObject.put("label", events1.getLabel());
            jsonObject.put("content", events1.getContent());
            jsonObject.put("poster", events1.getPoster());
            jsonObject.put("reader", events1.getReader());
            jsonArray.put(jsonObject);
          }

          json.put("eventsList", jsonArray);
          response.setCharacterEncoding("utf-8");
          response.getWriter().write(json.toString());
        }
      } else {
        EventsDAO eventsDAO1 = new EventsDAO();
        ArrayList<Events> list = eventsDAO1.getNewEvents();
        Iterator var5 = list.iterator();

        while(var5.hasNext()) {
          events = (Events)var5.next();
          eventsDAO1.addEventsRead(events.getId());
        }

        if (null == list) {
          response.sendRedirect("/404.html");
        } else {
          request.setAttribute("eventsList", list);
          request.getRequestDispatcher("/events.jsp").forward(request, response);
        }
      }
    } else {
      int id = 0;
      String str_id = request.getParameter("id");

      try {
        id = Integer.parseInt(str_id);
      } catch (NumberFormatException var15) {
        response.sendRedirect("/404.html");
      }

      eventsDAO = new EventsDAO();
      events = eventsDAO.getEventsByID(id);
      if (null == events) {
        response.sendRedirect("/404.html");
      } else {
        eventsDAO.addEventsRead(events.getId());
        request.setAttribute("title", events.getTitle());
        request.setAttribute("date", events.getDate());
        request.setAttribute("time", events.getTime());
        request.setAttribute("address", events.getAddress());
        request.setAttribute("label", events.getLabel());
        request.setAttribute("content", events.getContent());
        request.setAttribute("poster", events.getPoster());
        request.setAttribute("reader", events.getReader());
        request.getRequestDispatcher("/eventsview.jsp").forward(request, response);
      }
    }

  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request, response);
  }
}
