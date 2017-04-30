// Copyright (c) 2003-2016, One Network Enterprises, Inc. All rights reserved.

package util;

/**
 * TODO complete the class documentation
 *
 */
public enum ResponseStatus {
  SUCCESS("true"), FAILED("false");
  private final String status;

  private ResponseStatus(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }
}
