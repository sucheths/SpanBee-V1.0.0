package com.spanbee.model;

import java.util.Date;

public class CodeValueModel {

  private int id;

  private String codeDisplay;

  private String codeValue;

  private Date createdAt;

  private String isEnabled;

  private Date updatedAt;

  private CodeTableModel codeTableModel;

  public CodeValueModel() {
  }

  public int getId() {
      return this.id;
  }

  public void setId(int id) {
      this.id = id;
  }

  public String getCodeDisplay() {
      return this.codeDisplay;
  }

  public void setCodeDisplay(String codeDisplay) {
      this.codeDisplay = codeDisplay;
  }

  public String getCodeValue() {
      return this.codeValue;
  }

  public void setCodeValue(String codeValue) {
      this.codeValue = codeValue;
  }

  public Date getCreatedAt() {
      return this.createdAt;
  }

  public void setCreatedAt(Date createdAt) {
      this.createdAt = createdAt;
  }

  public String getIsEnabled() {
      return this.isEnabled;
  }

  public void setIsEnabled(String isEnabled) {
      this.isEnabled = isEnabled;
  }

  public Date getUpdatedAt() {
      return this.updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
      this.updatedAt = updatedAt;
  }

  public CodeTableModel getCodeTableModel() {
    return codeTableModel;
  }

  public void setCodeTableModel(CodeTableModel codeTableModel) {
    this.codeTableModel = codeTableModel;
  }

}
