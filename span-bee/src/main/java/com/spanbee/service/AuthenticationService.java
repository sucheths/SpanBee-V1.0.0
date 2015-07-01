package com.spanbee.service;

import com.spanbee.requestparameters.AuthenticationParameters;
import com.spanbee.requestparameters.Request;

public interface AuthenticationService {

  public String authenticate(AuthenticationParameters authenticationParameters,Request request);
  
}
