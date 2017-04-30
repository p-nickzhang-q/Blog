// Copyright (c) 2003-2016, One Network Enterprises, Inc. All rights reserved.

package controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import util.ResponseStatus;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;

/**
 * TODO complete the class documentation
 *
 */
@Controller
public class LoginController {

  @RequestMapping(value = "/login.do", method = RequestMethod.POST)
  public @ResponseBody String login(@RequestBody String postData) {
    BasicDBObject param = (BasicDBObject) JSON.parse(postData);
    Map<String, Object> result = new HashMap<String, Object>();
    if ("zh".equals(param.get("username")) && "123".equals(param.get("password"))) {
      result.put("authenticated", ResponseStatus.SUCCESS.getStatus());
    }
    else {
      result.put("authenticated", ResponseStatus.FAILED.getStatus());
    }
    return new Gson().toJson(result);
  }

}
