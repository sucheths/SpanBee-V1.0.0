package com.spanbee.service;

import com.spanbee.requestparameters.RegisterationParameters;
import com.spanbee.requestparameters.Request;

/**
 * @author sucheth.s
 *
 */

public interface RegistrationService {
  
  public String register(RegisterationParameters registrationParams,Request request);

}

