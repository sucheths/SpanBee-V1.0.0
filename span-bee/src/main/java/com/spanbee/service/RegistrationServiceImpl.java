package com.spanbee.service;


import org.apache.log4j.Logger;

import com.spanbee.requestparameters.RegisterationParameters;

/**
 * @author sucheth.s
 *
 */

public class RegistrationServiceImpl implements RegistrationService{
  private static final Logger LOGGER = Logger.getLogger(RegistrationServiceImpl.class);

  @Override
  public void register(RegisterationParameters registrationParams) {
    
    if(LOGGER.isInfoEnabled()){
    LOGGER.info("**********@Inside register Method Of Service Layer**********");
    }
    
    
    
  }

}

