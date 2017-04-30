// Copyright (c) 2003-2016, One Network Enterprises, Inc. All rights reserved.

package dao;

import java.net.UnknownHostException;

import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.*;

/**
 * TODO complete the class documentation
 *
 */
public class DBCollectionFactory {

  private static MongoClient mongoClient;
  private static final DBCollection dataCollection = buildDataCollection();

  private static DBCollection buildDataCollection() {
    try {
      mongoClient = new MongoClient("localhost", 27017);
      Builder options = MongoClientOptions.builder();
      options.connectionsPerHost(3);
      DB db = mongoClient.getDB("Blog");
      DBCollection collection = db.getCollection("data");
      return collection;
    }
    catch (UnknownHostException e) {
      System.out.println(e);
    }
    return null;
  }

  public static DBCollection getDataDBCollection() {
    return dataCollection;
  }

  public static MongoClient getMongoClient() {
    return mongoClient;
  }
}
