// Copyright (c) 2003-2016, One Network Enterprises, Inc. All rights reserved.

package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;

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
public class BlogServlet extends ParentServlet {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * @see servlet.ParentServlet#process(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   */
  @Override
  void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String json = "";
    String method = req.getParameter("method");
    String callback = req.getParameter("callback");
    DBCollection collection = DBCollectionFactory.getDataDBCollection();
    if ("save".equals(method)) {
      String introText = req.getParameter("introText");
      String blogText = req.getParameter("blogText");
      int languageId = Integer.parseInt(req.getParameter("languageId"));
      collection.insert(new BasicDBObject().append("introText", introText).append("bolgText", blogText).append(
        "languageId",
        languageId).append("date", new Date()).append("objType", "blog").append("comments", new ArrayList<DBObject>()));
      json = Util.transformJsonP(callback, new Gson().toJson(true));
    }
    else {
      String _id = req.getParameter("id");
      DBObject result = collection.findOne(new BasicDBObject().append("_id", new ObjectId(_id)));
      Util.transformObjectIdObj(result);
      json = new Gson().toJson(result);
    }
    resp.setContentType("application/json");
    resp.getWriter().write(json);
  }
}
