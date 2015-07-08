package com.spanbee.dao;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;

import com.spanbee.entities.Customer;
import com.spanbee.utils.AESSecurity;


public class AuthenticationDaoImpl implements AuthenticationDao {

  private static Logger LOGGER = Logger.getLogger(AuthenticationDaoImpl.class);

  private CommonDao commonDao;

  @Transactional
  @Override
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
  
  @Transactional
  @Override
  public boolean setCustomerSessionId(String sessionId, int customerId) {

    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("Entered into setCustomerSessionId method for customer with id::" + customerId);
    }
    boolean sessionFlag= false;
    try {
        sessionFlag = commonDao.setCustomerSessionId(sessionId,customerId);
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
