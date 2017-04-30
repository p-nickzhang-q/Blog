// Copyright (c) 2003-2016, One Network Enterprises, Inc. All rights reserved.

package test;

import java.util.*;

import org.bson.types.ObjectId;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import dao.DBCollectionFactory;

/**
 * TODO complete the class documentation
 *
 */
public class Init {

  static DBCollection dbConllection;

  @BeforeClass
  public static void init() {
    dbConllection = DBCollectionFactory.getDataDBCollection();
  }

  @Test
  public void testAdd() {
    BasicDBObject obj = new BasicDBObject();
    List<BasicDBObject> blog1Comments = new ArrayList<BasicDBObject>();
    blog1Comments.add(new BasicDBObject().append("commentText", "very good post."));
    blog1Comments.add(new BasicDBObject().append("commentText", "when can we learn services."));
    obj.append("date", new Date()).append("introText", "This is blog post about AngularJS").append(
      "bolgText",
      "This is blog post about AngularJS. we will cover how to build a blog.").append("comments", blog1Comments).append(
      "objType",
      "blog");
    BasicDBObject obj2 = new BasicDBObject();
    List<BasicDBObject> blog2Comments = new ArrayList<BasicDBObject>();
    blog2Comments.add(new BasicDBObject().append("commentText", "REST is great. i want to know more."));
    blog2Comments.add(new BasicDBObject().append("commentText", "Will we use Node.js for REST services?"));
    obj2.append("date", new Date()).append(
      "introText",
      "In this blog post we will learn how to build application based on REST").append(
      "bolgText",
      "In this blog post we will learn how to build application based on REST web services that ...").append(
      "comments",
      blog2Comments).append("objType", "blog");
    dbConllection.insert(obj, obj2);

  }

  @Test
  public void testFindAll() {
    BasicDBObject match = new BasicDBObject();
    match.append("objType", "blog");
    List<DBObject> result = dbConllection.find(match).toArray();
    for (DBObject dbObject : result) {
      System.out.println(dbObject);
    }
  }
  
  @Test
  public void testFindOne() {
    BasicDBObject match = new BasicDBObject();
    match.append("_id", new ObjectId("5695b3042ffc0a93b2e299c2"));
    System.out.println(dbConllection.findOne(match));
  }

  @AfterClass
  public static void destory() {
    DBCollectionFactory.getMongoClient().close();
  }
}
