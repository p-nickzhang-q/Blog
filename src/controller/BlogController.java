// Copyright (c) 2003-2016, One Network Enterprises, Inc. All rights reserved.

package controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import util.Util;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import dao.DBCollectionFactory;

/**
 * TODO complete the class documentation
 *
 */
@Controller
@RequestMapping(value = "/blog")
public class BlogController {

  private DBCollection collection = DBCollectionFactory.getDataDBCollection();

  @RequestMapping(value = "/blogList.do", method = RequestMethod.GET)
  public @ResponseBody String getBlogList() {
    List<DBObject> result = collection.find(new BasicDBObject().append("objType", "blog")).toArray();
    Util.transformObjectIdList(result);
    String json = new Gson().toJson(result);
    return json;
  }

  @RequestMapping(value = "/blogPost.do", method = RequestMethod.POST)
  public @ResponseBody String getBlog(@RequestBody String postData) {
    BasicDBObject param = (BasicDBObject) JSON.parse(postData);
    String id = (String) param.get("id");
    DBObject result = collection.findOne(new BasicDBObject().append("_id", new ObjectId(id)));
    Util.transformObjectIdObj(result);
    String json = new Gson().toJson(result);
    return json;
  }

}
