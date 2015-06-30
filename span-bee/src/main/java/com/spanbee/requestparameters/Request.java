package com.spanbee.requestparameters;

import org.codehaus.jackson.JsonNode;

/**
 * @author sucheth.s
 *
 */

public class Request {

  private  String version;
  private String session_id;
  private String request_origin;
  private JsonNode data;
  
  public String getVersion() {
    return version;
  }
  public void setVersion(String version) {
    this.version = version;
  }
  public String getSession_id() {
    return session_id;
  }
  public void setSession_id(String session_id) {
    this.session_id = session_id;
  }
  public String getRequest_origin() {
    return request_origin;
  }
  public void setRequest_origin(String request_origin) {
    this.request_origin = request_origin;
  }
  public JsonNode getData() {
    return data;
  }
  public void setData(JsonNode data) {
    this.data = data;
  }

}

