package com.spanbee.requestparameters;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author sucheth.s
 *
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class RegisterationParameters{
  
  
  public String first_name;
  public String last_name;
  public String email_id;
  public String password;
  public String birth_date;
  public String gender;
  public String mobile;
  public String marital_status;
  
  
}

