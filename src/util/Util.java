// Copyright (c) 2003-2016, One Network Enterprises, Inc. All rights reserved.

package util;

import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.DBObject;

/**
 * TODO complete the class documentation
 *
 */
public class Util {

  public static void transformObjectIdList(List<DBObject> objs) {
    for (DBObject dbObject : objs) {
      ObjectId objectId = (ObjectId) dbObject.get("_id");
      dbObject.put("_id", objectId.toString());
    }
  }

  public static void transformObjectIdObj(DBObject obj) {
    ObjectId objectId = (ObjectId) obj.get("_id");
    obj.put("_id", objectId.toString());
  }

  public static String transformJsonP(String callback, String json) {
    StringBuilder builder = new StringBuilder(callback);
    builder.append("(").append(json).append(")");
    return builder.toString();
  }

}
