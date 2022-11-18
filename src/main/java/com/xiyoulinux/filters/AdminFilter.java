package com.xiyoulinux.filters;
import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(
    filterName = "AdminFilter",
    urlPatterns = {"/admin/*"}
)
public class AdminFilter implements Filter {
  public AdminFilter() {
  }

  public void destroy() {
  }

  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
    HttpServletRequest request = (HttpServletRequest)req;
    HttpServletResponse response = (HttpServletResponse)resp;
    String flag = (String)request.getSession().getAttribute("login");
    if (flag != null && flag.equals("ok")) {
      chain.doFilter(request, response);
    } else {
      System.out.println("您无权访问该目录:" + flag);
      request.setAttribute("message", "您无权访问该目录");
      RequestDispatcher rd = request.getRequestDispatcher("/404.html");
      rd.forward(request, response);
    }

  }

  public void init(FilterConfig config) throws ServletException {
  }
}
