package com.spanbee.model;

import java.util.Date;
import java.util.List;

public class CodeTableModel {

  private int id;

  private String codeName;

  private Date createdAt;

  private String defaultValue;

  private Date updatedAt;

  private List<CodeValueModel> codeValues;

  public int getId() {
      return this.id;
  }

  public void setId(int id) {
      this.id = id;
  }

  public String getCodeName() {
      return this.codeName;
  }

  public void setCodeName(String codeName) {
      this.codeName = codeName;
  }

  public Date getCreatedAt() {
      return this.createdAt;
  }

  public void setCreatedAt(Date createdAt) {
      this.createdAt = createdAt;
  }

  public String getDefaultValue() {
      return this.defaultValue;
  }

  public void setDefaultValue(String defaultValue) {
      this.defaultValue = defaultValue;
  }

  public Date getUpdatedAt() {
      return this.updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
      this.updatedAt = updatedAt;
  }

  public List<CodeValueModel> getCodeValues() {
    return codeValues;
  }

  public void setCodeValues(List<CodeValueModel> codeValues) {
    this.codeValues = codeValues;
  }

}
