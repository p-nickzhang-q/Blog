// Copyright (c) 2003-2016, One Network Enterprises, Inc. All rights reserved.

package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * TODO complete the class documentation
 *
 */
public class TestServlet extends HttpServlet{

  /**
   * 
   */
  private static final long serialVersionUID = -5846490989298444732L;

  /**
   * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   */
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    this.doPost(req, resp);;
  }
  
  /**
   * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   */
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, String> map = new HashMap<String, String>();
    map.put("test", "test");
    String json = new Gson().toJson(map);
    resp.setContentType("application/json");
    resp.getWriter().write(json);
  }
  
}
