package com.spanbee.requestparameters;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author sucheth.s
 *
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class RegisterationParameters{
  
  private String first_name;
  private String last_name;
  private String email_id;
  private String password;
  private String birth_date;
  private String gender;
  private String mobile;
  private String marital_status;
  public String getFirst_name() {
    return first_name;
  }
  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }
  public String getLast_name() {
    return last_name;
  }
  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }
  public String getEmail_id() {
    return email_id;
  }
  public void setEmail_id(String email_id) {
    this.email_id = email_id;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getBirth_date() {
    return birth_date;
  }
  public void setBirth_date(String birth_date) {
    this.birth_date = birth_date;
  }
  public String getGender() {
    return gender;
  }
  public void setGender(String gender) {
    this.gender = gender;
  }
  public String getMobile() {
    return mobile;
  }
  public void setMobile(String mobile) {
    this.mobile = mobile;
  }
  public String getMarital_status() {
    return marital_status;
  }
  public void setMarital_status(String marital_status) {
    this.marital_status = marital_status;
  }
  
  
}

