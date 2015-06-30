package com.spanbee.model;

/**
 * @author sucheth.s
 *
 */

public class EmailModel {
  
  
  private String toaddess;
  private String fromAddress;
  private String hostName;
  private String port;
  private String userName;
  private String password;
  private String Subject;
  private String Content;
  private String protocol;
  public String getToaddess() {
    return toaddess;
  }
  public void setToaddess(String toaddess) {
    this.toaddess = toaddess;
  }
  public String getFromAddress() {
    return fromAddress;
  }
  public void setFromAddress(String fromAddress) {
    this.fromAddress = fromAddress;
  }
  public String getHostName() {
    return hostName;
  }
  public void setHostName(String hostName) {
    this.hostName = hostName;
  }
  public String getPort() {
    return port;
  }
  public void setPort(String port) {
    this.port = port;
  }
  public String getUserName() {
    return userName;
  }
  public void setUserName(String userName) {
    this.userName = userName;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getSubject() {
    return Subject;
  }
  public void setSubject(String subject) {
    Subject = subject;
  }
  public String getContent() {
    return Content;
  }
  public void setContent(String content) {
    Content = content;
  }
  public String getProtocol() {
    return protocol;
  }
  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }

}

