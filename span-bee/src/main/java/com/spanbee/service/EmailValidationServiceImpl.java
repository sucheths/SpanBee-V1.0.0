package com.spanbee.service;

import org.apache.log4j.Logger;

import com.spanbee.dao.EmailValidationDao;

/**
 * @author sucheth.s
 *
 */

public class EmailValidationServiceImpl implements EmailValidationService{

  private EmailValidationDao emailValidationDao;
  private static final Logger LOGGER = Logger.getLogger(EmailValidationServiceImpl.class);
  
  public void setEmailValidationDao(EmailValidationDao emailValidationDao) {
    this.emailValidationDao = emailValidationDao;
  }


  public int validateEmail(String uniqueId) {
   int flag= emailValidationDao.validateEmail(uniqueId);
   
   LOGGER.info("flag:::" +flag);
    
    return flag;
  }

}

