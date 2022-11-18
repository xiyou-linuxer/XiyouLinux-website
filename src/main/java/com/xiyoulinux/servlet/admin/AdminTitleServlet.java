package com.xiyoulinux.servlet.admin;


import com.xiyoulinux.dao.TitleDAO;
import com.xiyoulinux.model.Title;
import java.io.IOException;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
    name = "AdminTitleServlet",
    urlPatterns = {"/admin/title"}
)
public class AdminTitleServlet extends HttpServlet {
  public AdminTitleServlet() {
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    TitleDAO titleDAO = new TitleDAO();
    ArrayList<Title> titles = new ArrayList();

    for(int i = 1; i <= 6; ++i) {
      titles.add(titleDAO.getTitleById(i));
    }

    request.setAttribute("titleList", titles);
    request.getRequestDispatcher("/admin/title.jsp").forward(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request, response);
  }
}
