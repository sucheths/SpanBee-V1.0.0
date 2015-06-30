package com.spanbee.service;


import java.util.Date;

import org.apache.log4j.Logger;

import com.spanbee.dao.RegistrationDaoImpl;
import com.spanbee.entities.Customer;
import com.spanbee.listeners.SpringApplicationContext;
import com.spanbee.requestparameters.RegisterationParameters;
import com.spanbee.requestparameters.Request;
import com.spanbee.utils.EnumValues;
import com.spanbee.utils.KeyGenerator;
import com.spanbee.utils.Utils;

/**
 * @author sucheth.s
 *
 */

public class RegistrationServiceImpl implements RegistrationService{
  
  private RegistrationDaoImpl registrationDaoImpl;
  private static final Logger LOGGER = Logger.getLogger(RegistrationServiceImpl.class);

  @Override
  public boolean register(RegisterationParameters registrationParams,Request request) {
    
    if(LOGGER.isInfoEnabled()){
    LOGGER.info("**********@Inside register Method Of Service Layer**********");
    }
    //registrationDaoImpl =
    //    (RegistrationDaoImpl) SpringApplicationContext.getBean("registrationDaoImpl");
    Customer customer=null;
    boolean registerFlag=false;
    try{
      if(registrationParams != null && request != null){
        customer= new Customer();
        customer.setBirthDate(Utils.getDateFormat(registrationParams.getBirth_date()));
        customer.setCustomerStatus((byte)EnumValues.CustomerStatus.Active.ordinal());
        customer.setEmailAddress(registrationParams.getEmail_id());
        customer.setFirstName(registrationParams.getFirst_name());
        customer.setGender(registrationParams.getGender());
        customer.setLastName(registrationParams.getLast_name());
        customer.setMaritalStatus(Byte.valueOf(registrationParams.getMarital_status()));
        customer.setMobile(registrationParams.getMobile());
        customer.setPassword(registrationParams.getPassword());
        customer.setUniqueId(KeyGenerator.getUniqueTransactionId());
        customer.setCreatedAt(new Date());
        customer.setUpdatedAt(new Date());
        registerFlag=registrationDaoImpl.register(customer);
        
      }
    }catch(Exception e){
      LOGGER.error("Exception occurred while registration ::",e);
    }
    return registerFlag;
  }

  public RegistrationDaoImpl getRegistrationDaoImpl() {
    return registrationDaoImpl;
  }

  public void setRegistrationDaoImpl(RegistrationDaoImpl registrationDaoImpl) {
    this.registrationDaoImpl = registrationDaoImpl;
  }

}

