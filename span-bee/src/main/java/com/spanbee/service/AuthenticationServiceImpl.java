package com.spanbee.service;

import org.apache.log4j.Logger;






import com.spanbee.constants.Constants;
import com.spanbee.dao.AuthenticationDao;
import com.spanbee.dao.AuthenticationDaoImpl;
import com.spanbee.entities.Customer;
import com.spanbee.requestparameters.AuthenticationParameters;
import com.spanbee.requestparameters.Request;
import com.spanbee.responseparameters.Response;
import com.spanbee.utils.AESSecurity;
import com.spanbee.utils.KeyGenerator;
import com.spanbee.utils.PropertyReader;
import com.spanbee.utils.Utils;

public class AuthenticationServiceImpl implements AuthenticationService{
  
  private AuthenticationDao authenticationDao;;
  private static final Logger LOGGER = Logger.getLogger(RegistrationServiceImpl.class);

  @Override
  public String authenticate(AuthenticationParameters authenticationParameters, Request request) {
    
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("**********@Inside authenticate Method Of Service Layer**********");
    }
    Customer customer = null;
    String decryptedPass=null;
    String responseString = null;
    String emailId = null;
    String password = null;
    String sessionId=null;
    String message=null;
    boolean sessionFlag=false;
    try {
      if (authenticationParameters != null && request != null) {
        emailId =AESSecurity.encrypt(authenticationParameters.getEmail_id());
        password = authenticationParameters.getPassword();
        if (LOGGER.isInfoEnabled()) {
          LOGGER.info("Entered into authenticate method with parameters with emailId::" + emailId
              + "::password ::" + password);
        }
        if (emailId != null) {
          customer = authenticationDao.fetchCustomerInfoByEmailId(emailId);
          LOGGER.info("customer ::"+customer);
          if (customer != null) {
            decryptedPass=AESSecurity.decrypt(customer.getPassword());
           //yet to be done
            // customer.getCustomerStatus();
            LOGGER.info("decryptedPass ::"+decryptedPass);
            if (password.equals(decryptedPass) ) {
              sessionId= new KeyGenerator().generateSessionID(AESSecurity.decrypt(customer.getEmailAddress()));
              sessionFlag=authenticationDao.setCustomerSessionId(sessionId, customer.getUniqueId());
              if(sessionFlag){
                 message =PropertyReader.resourceBundlesManager.getValueFromResourceBundle(Constants.LANGUAGE,
                    Constants.AUTHENTICATION_SUCCESS);
                responseString =
                    Utils.frameResponse(Constants.HTTP_STATUS_CODE_SUCCESS,
                        Constants.RESPONSE_SUCCESS, message, "");
              }else{
                message =PropertyReader.resourceBundlesManager.getValueFromResourceBundle(Constants.LANGUAGE,
                    Constants.ERROR_CODE_500+Constants._ERROR_MESSAGE);
                responseString =
                    Utils.frameResponse(Constants.HTTP_STATUS_CODE_SUCCESS,
                        Constants.RESPONSE_SUCCESS, message, "");
              }
             
            } else {
              message =PropertyReader.resourceBundlesManager.getValueFromResourceBundle(Constants.LANGUAGE,
                  Constants.ERROR_CODE_501+Constants._ERROR_MESSAGE);
              responseString =
                  Utils.frameResponse(Constants.HTTP_STATUS_CODE_FAILURE,
                      Constants.RESPONSE_FAILURE, message, "");
            }
          } else {
            message =PropertyReader.resourceBundlesManager.getValueFromResourceBundle(Constants.LANGUAGE,
                Constants.ERROR_CODE_501+Constants._ERROR_MESSAGE);
            responseString =
                Utils.frameResponse(Constants.HTTP_STATUS_CODE_FAILURE,
                    Constants.RESPONSE_FAILURE, message, "");
          }
        }
      }else{
        message =PropertyReader.resourceBundlesManager.getValueFromResourceBundle(Constants.LANGUAGE,
            Constants.ERROR_CODE_500+Constants._ERROR_MESSAGE);
        responseString =
            Utils.frameResponse(Constants.HTTP_STATUS_CODE_FAILURE,
                Constants.RESPONSE_FAILURE, "Invalid Username or Password", "");
        
      }
      if (LOGGER.isInfoEnabled()) {
        LOGGER.info("Sending response message after authentication is ::"+responseString );
      }
    } catch (Exception e) {
      LOGGER.error("Exception occurred while authenticate ::", e);
    }
    return responseString;
  }

  public AuthenticationDao getAuthenticationDao() {
    return authenticationDao;
  }

  public void setAuthenticationDao(AuthenticationDao authenticationDao) {
    this.authenticationDao = authenticationDao;
  }

 


}
