package com.xiyoulinux.filters;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter(
    filterName = "CharsetAllEncodingFilter",
    urlPatterns = {"/*"},
    initParams = {@WebInitParam(
        name = "encoding",
        value = "UTF-8"
    )}
)
public class CharsetAllEncodingFilter implements Filter {
  private String encoding = null;

  public CharsetAllEncodingFilter() {
  }

  public void init(FilterConfig filterConfig) throws ServletException {
    this.encoding = filterConfig.getInitParameter("encoding");
  }

  public void destroy() {
  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest)request;
    if (httpRequest.getMethod().equalsIgnoreCase("POST")) {
      request.setCharacterEncoding(this.encoding);
    }

    chain.doFilter(request, response);
  }
}
