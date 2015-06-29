package com.spanbee.service;

import java.util.Date;

import org.apache.log4j.Logger;

import com.spanbee.dao.CommonDaoImpl;
import com.spanbee.entities.Customer;
import com.spanbee.requestparameters.RegisterationParameters;

/**
 * @author sucheth.s
 * 
 */

public class CommonServiceImpl implements CommonService {

  private CommonDaoImpl commonDaoImpl;

  private static final Logger LOGGER = Logger.getLogger(CommonServiceImpl.class);

  @Override
  public boolean registerCustomer(RegisterationParameters customerParams) {/*
    boolean status = false;
    String birthDate = null;
    String emailAddress = null;
    String firstName = null;
    String gender = null;
    String lastName = null;
    String mobile = null;
    String password = null;
    String pinCode = null;
    String place = null;
    String state = null;
    String maritalStatus = null;
    String sessionId = null;
    Customer customer = null;
    try {
      if (customerParams != null) {
        birthDate = customerParams.getBirthDate();
        emailAddress = customerParams.getEmailAddress();
        firstName = customerParams.getFirstName();
        gender = customerParams.getGender();
        lastName = customerParams.getLastName();
        mobile = customerParams.getMobile();
        password = customerParams.getPassword();
        pinCode = customerParams.getPinCode();
        place = customerParams.getPlace();
        state = customerParams.getState();
        maritalStatus = customerParams.getMaritalStatus();
        sessionId = customerParams.getSessionId();
        if (LOGGER.isInfoEnabled()) {
          LOGGER.info("Paramteres obtained are sessionId ::" + sessionId + "::birthDate ::"
              + birthDate + "::emailAddress ::" + emailAddress + "::firstName ::" + firstName
              + "::gender ::" + gender + "::mobile ::" + mobile + "::password :" + password
              + "::pinCode ::" + pinCode + "::place ::" + place + "::state ::" + state);
        }
        customer = new Customer();
        customer.setBirthDate(birthDate);
        customer.setCreatedAt(new Date());
        customer.setCreatedBy("admin");
        customer.setCustomerStatus((byte) 0);
        customer.setEmailAddress(emailAddress);
        customer.setFirstName(firstName);
        customer.setGender(Byte.valueOf(gender));
        customer.setLastName(lastName);
        customer.setMaritalStatus(maritalStatus);
        customer.setMobile(mobile);
        customer.setPassword(password);
        customer.setPinCode(pinCode);
        customer.setPlace(place);
        customer.setSessionId(sessionId);
        customer.setState(state);
        customer.setUpdatedAt(new Date());
        customer.setUpdatedBy("admin");
        status = commonDaoImpl.addCustomer(customer);
        
        if(LOGGER.isInfoEnabled()){
          LOGGER.info("customer status obtained after saving customer details is :"+status);
        }

      }
    } catch (Exception e) {
      LOGGER.info("Exception occurred ::", e);
    }
    return status;
  */
    return true;}

  public CommonDaoImpl getCommonDaoImpl() {
    return commonDaoImpl;
  }


  public void setCommonDaoImpl(CommonDaoImpl commonDaoImpl) {
    this.commonDaoImpl = commonDaoImpl;
  }


}
