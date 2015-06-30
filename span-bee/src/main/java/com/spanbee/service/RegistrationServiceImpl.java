package com.spanbee.service;


import java.util.Date;

import org.apache.log4j.Logger;

import com.spanbee.constants.Constants;
import com.spanbee.dao.RegistrationDaoImpl;
import com.spanbee.entities.Customer;
import com.spanbee.listeners.SpringApplicationContext;
import com.spanbee.model.EmailModel;
import com.spanbee.requestparameters.RegisterationParameters;
import com.spanbee.requestparameters.Request;
import com.spanbee.responseparameters.Response;
import com.spanbee.service.email.SendRegistrationEmailThread;
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
  public String register(RegisterationParameters registrationParams,Request request) {
    
    if(LOGGER.isInfoEnabled()){
    LOGGER.info("**********@Inside register Method Of Service Layer**********");
    }
    //registrationDaoImpl =
    //    (RegistrationDaoImpl) SpringApplicationContext.getBean("registrationDaoImpl");
    Customer customer=null;
    String responseString = null;
    
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
        customer=registrationDaoImpl.register(customer);
        
        if(customer!=null){
          Response resp = new Response();
          resp.setCode(Constants.HTTP_STATUS_CODE_SUCCESS);
          resp.setStatus(Constants.RESPONSE_SUCCESS);
          resp.setMessage("Dear " +customer.getFirstName()+",Thank you for registering with us.Please click on the link that has been sent to "+customer.getEmailAddress());
          resp.setDescription("");
          responseString = Utils.getResponseString(resp);
          
           EmailModel emailModel = new EmailModel();
          emailModel.setSubject("Check E-mail SPAN-BEE");
          emailModel.setFromAddress("admin@shopus365.com");
          emailModel.setHostName("mail.shopus365.com");
          emailModel.setPassword("Ashraya@13");
          emailModel.setPort("587");
          emailModel.setProtocol("smtp");
          emailModel.setContent("<h1>Hello welcome to SPAN BEE</h1>");
          emailModel.setToaddess(customer.getEmailAddress());
          emailModel.setUserName("admin@shopus365.com");
          SendRegistrationEmailThread registrationThreadEmail = new SendRegistrationEmailThread(emailModel);
          Thread emailThread= new Thread(registrationThreadEmail);
          emailThread.start();
          
        }
        
      }
    }catch(Exception e){
      LOGGER.error("Exception occurred while registration ::",e);
    }
    return responseString;
  }

  public RegistrationDaoImpl getRegistrationDaoImpl() {
    return registrationDaoImpl;
  }

  public void setRegistrationDaoImpl(RegistrationDaoImpl registrationDaoImpl) {
    this.registrationDaoImpl = registrationDaoImpl;
  }

}

