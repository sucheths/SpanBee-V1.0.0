package com.spanbee.service;

import com.spanbee.requestparameters.RegisterationParameters;
import com.spanbee.requestparameters.Request;

/**
 * @author sucheth.s
 *
 */

public interface RegistrationService {
  
  public boolean register(RegisterationParameters registrationParams,Request request);

}

