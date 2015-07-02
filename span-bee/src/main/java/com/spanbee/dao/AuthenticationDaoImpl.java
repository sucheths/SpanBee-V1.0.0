package com.spanbee.dao;

import org.apache.log4j.Logger;

import com.spanbee.entities.Customer;


public class AuthenticationDaoImpl {

  private static Logger LOGGER = Logger.getLogger(AuthenticationDaoImpl.class);

  private CommonDaoImpl commonDaoImpl;

  public Customer fetchCustomerInfoByEmailId(String emailId) {

    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("Entered into fetchCustomerInfoByEmailId method with emailId::" + emailId);
    }
    Customer customer = null;
    try {
      if (emailId != null) {
        customer = commonDaoImpl.fetchCustomerByEmailId(emailId);
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
        sessionFlag = commonDaoImpl.setCustomerSessionId(sessionId,uniqueId);
      }
    } catch (Exception e) {
      LOGGER.error("Exception occurred :", e);
    }
    return sessionFlag;
  }

  public CommonDaoImpl getCommonDaoImpl() {
    return commonDaoImpl;
  }

  public void setCommonDaoImpl(CommonDaoImpl commonDaoImpl) {
    this.commonDaoImpl = commonDaoImpl;
  }

}
