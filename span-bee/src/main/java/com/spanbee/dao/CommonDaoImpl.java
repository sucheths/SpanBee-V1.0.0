package com.spanbee.dao;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.spanbee.entities.Customer;
import com.spanbee.repositories.CustomerRepository;

@Service
public class CommonDaoImpl implements CommonDao{


  private static Logger LOGGER = Logger.getLogger(CommonDaoImpl.class);


  @Resource
  private CustomerRepository customerRepository;

  @Transactional
  @Override
  public Customer addCustomer(Customer customer) {
    Customer cust = null;
    try {
      if (customer != null) {
        if (LOGGER.isInfoEnabled()) {
          LOGGER.info("Inside customer register method for customerId ::" + customer.getId());
        }
        cust = customerRepository.saveAndFlush(customer);

      }
    } catch (Exception e) {
      LOGGER.error("Exception Occured while registering the User :", e);
    }
    return cust;
  }
  
  @Transactional
  @Override
  public Customer fetchCustomerByEmailId(String emailId) {
    Customer cust = null;
    try {
      if (emailId != null) {
        if (LOGGER.isInfoEnabled()) {
          LOGGER.info("Inside fetchCustomerByEmailId method for emailId ::" + emailId);
        }
        cust = customerRepository.getCustomerInfoByEmailAddress(emailId);

        LOGGER.info("Returning customerInfo for ::" + cust);
      }
    } catch (Exception e) {
      LOGGER.error("Exception Occured while registering the User :", e);
    }
    return cust;
  }

  @Transactional
  @Override
  public boolean setCustomerSessionId(String sessionId, String uniqueId) {
    boolean sessionFlag = false;
    int updationStatus=0;
    try {
      if (sessionId != null && uniqueId != null) {
        if (LOGGER.isInfoEnabled()) {
          LOGGER.info("Inside setCustomerSessionId method for customer with uniqueI ::" + uniqueId);
        }
        updationStatus = customerRepository.setCustomerSessionId(sessionId, uniqueId);
        if(updationStatus != 0){
          sessionFlag=true;
        }
        LOGGER.info("Returning status ::" + sessionFlag);
      }
    } catch (Exception e) {
      LOGGER.error("Exception Occured while registering the User :", e);
    }
    return sessionFlag;
  }
}
