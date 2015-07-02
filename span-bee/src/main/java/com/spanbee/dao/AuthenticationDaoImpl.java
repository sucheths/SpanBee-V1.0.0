package com.spanbee.dao;

import org.apache.log4j.Logger;

import com.spanbee.entities.Customer;


public class AuthenticationDaoImpl {

  private static Logger LOGGER = Logger.getLogger(AuthenticationDaoImpl.class);

  private CommonDao commonDao;

  public Customer fetchCustomerInfoByEmailId(String emailId) {

    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("Entered into fetchCustomerInfoByEmailId method with emailId::" + emailId);
    }
    Customer customer = null;
    try {
      if (emailId != null) {
        customer = commonDao.fetchCustomerByEmailId(emailId);
      }
    } catch (Exception e) {
      LOGGER.error("Exception occurred :", e);
    }
    return customer;
  }
  
  public boolean setCustomerSessionId(String sessionId, String uniqueId) {

    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("Entered into setCustomerSessionId method for customer with id::" + uniqueId);
    }
    boolean sessionFlag= false;
    try {
      if (uniqueId != null) {
        sessionFlag = commonDao.setCustomerSessionId(sessionId,uniqueId);
      }
    } catch (Exception e) {
      LOGGER.error("Exception occurred :", e);
    }
    return sessionFlag;
  }

  public CommonDao getCommonDao() {
    return commonDao;
  }

  public void setCommonDao(CommonDao commonDao) {
    this.commonDao = commonDao;
  }

 

}
