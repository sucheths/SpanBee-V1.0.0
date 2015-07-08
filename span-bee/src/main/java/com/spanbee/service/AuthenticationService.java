package com.spanbee.service;

import com.spanbee.requestparameters.AuthenticationParameters;
import com.spanbee.requestparameters.RegisterationParameters;
import com.spanbee.requestparameters.Request;

public interface AuthenticationService {

  public String authenticate(AuthenticationParameters authenticationParameters,Request request) throws Exception;
  public String tpauthenticate(RegisterationParameters tpauthenticateParams,Request request) throws Exception;
}
