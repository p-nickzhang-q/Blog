// Copyright (c) 2003-2016, One Network Enterprises, Inc. All rights reserved.

package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Util;

import com.google.gson.Gson;

/**
 * TODO complete the class documentation
 *
 */
public class LoginServlet extends ParentServlet {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * @see servlet.ParentServlet#process(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   */
  @Override
  void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = req.getParameter("username");
    String password = req.getParameter("password");
    String callback = req.getParameter("callback");
    if ("zh".equals(username) && "123".equals(password)) {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("authenticated", true);
      String json = new Gson().toJson(map);
      resp.setContentType("application/json");
      resp.getWriter().write(Util.transformJsonP(callback, json));
    }
  }

}
