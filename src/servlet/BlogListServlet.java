// Copyright (c) 2003-2016, One Network Enterprises, Inc. All rights reserved.

package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Util;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import dao.DBCollectionFactory;

/**
 * TODO complete the class documentation
 *
 */
public class BlogListServlet extends ParentServlet{

  /**
   * 
   */
  private static final long serialVersionUID = -5605381915859951341L;

  /**
   * @see servlet.ParentServlet#process(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   */
  @Override
  void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    DBCollection collection = DBCollectionFactory.getDataDBCollection();
    List<DBObject> result = collection.find(new BasicDBObject().append("objType", "blog")).toArray();
    Util.transformObjectIdList(result);
    String json = new Gson().toJson(result);
    resp.setContentType("application/json");
    resp.getWriter().write(json);
  }
  
}
