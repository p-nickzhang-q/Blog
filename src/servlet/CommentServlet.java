// Copyright (c) 2003-2016, One Network Enterprises, Inc. All rights reserved.

package servlet;

import java.io.IOException;
import java.util.List;

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
public class CommentServlet extends ParentServlet{

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
      String blogId = req.getParameter("blog");
      BasicDBObject match = new BasicDBObject().append("_id", new ObjectId(blogId));
      String commentText = req.getParameter("commentText");
      DBObject blog = collection.findOne(match);
      List<DBObject> comments = (List<DBObject>) blog.get("comments");
      comments.add(new BasicDBObject().append("commentText", commentText));
      collection.update(match, blog);
      json = Util.transformJsonP(callback, new Gson().toJson(true));
    }
    resp.setContentType("application/json");
    resp.getWriter().write(json);
  }

}
