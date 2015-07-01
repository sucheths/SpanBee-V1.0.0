package com.spanbee.dao;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.spanbee.repositories.CustomerRepository;

/**
 * @author sucheth.s
 *
 */

public class EmailValidationDaoImpl implements EmailValidationDao {
  
  private static Logger LOGGER = Logger.getLogger(EmailValidationDaoImpl.class);
  @Resource
  private CustomerRepository customerRepository;
  @Override
  public boolean validateEmail(String uniqueId) {
    boolean validationFlag = false;
    try {
      if (uniqueId != null) {
        if (LOGGER.isInfoEnabled()) {
          LOGGER.info("Inside validateEmail method for uniqueId ::"    + uniqueId);
        }
        validationFlag= customerRepository.setCustomerStatus(1,uniqueId);
        
        LOGGER.info("Validation Flag::" +validationFlag);
        
      }
    } catch (Exception e) {
      LOGGER.error("Exception Occured while validating the customer e-Mail :", e);
    }
    return validationFlag;
  }

}

