package com.spanbee.model;

import java.util.Date;

public class ContactUsModel {

  private int id;

  private String content;

  private Date createdAt;

  private String emailId;

  private byte problemResolutionFlag;

  private Date updatedAt;

  public ContactUsModel() {
  }

  public int getId() {
      return this.id;
  }

  public void setId(int id) {
      this.id = id;
  }

  public String getContent() {
      return this.content;
  }

  public void setContent(String content) {
      this.content = content;
  }

  public Date getCreatedAt() {
      return this.createdAt;
  }

  public void setCreatedAt(Date createdAt) {
      this.createdAt = createdAt;
  }

  public String getEmailId() {
      return this.emailId;
  }

  public void setEmailId(String emailId) {
      this.emailId = emailId;
  }

  public byte getProblemResolutionFlag() {
      return this.problemResolutionFlag;
  }

  public void setProblemResolutionFlag(byte problemResolutionFlag) {
      this.problemResolutionFlag = problemResolutionFlag;
  }

  public Date getUpdatedAt() {
      return this.updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
      this.updatedAt = updatedAt;
  }

}
