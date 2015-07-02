package com.spanbee.service;

import org.apache.log4j.Logger;




import com.spanbee.constants.Constants;
import com.spanbee.dao.AuthenticationDaoImpl;
import com.spanbee.entities.Customer;
import com.spanbee.requestparameters.AuthenticationParameters;
import com.spanbee.requestparameters.Request;
import com.spanbee.responseparameters.Response;
import com.spanbee.utils.AESSecurity;
import com.spanbee.utils.KeyGenerator;
import com.spanbee.utils.Utils;

public class AuthenticationServiceImpl implements AuthenticationService{
  
  private AuthenticationDaoImpl authenticationDaoImpl;;
  private static final Logger LOGGER = Logger.getLogger(RegistrationServiceImpl.class);

  @Override
  public String authenticate(AuthenticationParameters authenticationParameters, Request request) {
    
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("**********@Inside authenticate Method Of Service Layer**********");
    }
    Customer customer = null;
    String responseString = null;
    String emailId = null;
    String password = null;
    String sessionId=null;
    boolean sessionFlag=false;
    try {
      if (authenticationParameters != null && request != null) {
        emailId = authenticationParameters.getEmail_id();
        password = authenticationParameters.getPassword();
        if (LOGGER.isInfoEnabled()) {
          LOGGER.info("Entered into authenticate method with parameters with emailId::" + emailId
              + "::password ::" + password);
        }
        if (emailId != null) {
          customer = authenticationDaoImpl.fetchCustomerInfoByEmailId(emailId);
          if (customer != null) {
            if (password.equals(customer.getPassword())) {
              sessionId=KeyGenerator.getUniqueTransactionId();
              sessionFlag=authenticationDaoImpl.setCustomerSessionId(sessionId, customer.getUniqueId());
              if(sessionFlag){
                responseString =
                    Utils.frameResponse(Constants.HTTP_STATUS_CODE_SUCCESS,
                        Constants.RESPONSE_SUCCESS, "", "");
              }else{
                responseString =
                    Utils.frameResponse(Constants.HTTP_STATUS_CODE_FAILURE,
                        Constants.RESPONSE_FAILURE, "Could Not update session id", "");
              }
             
            } else {
              responseString =
                  Utils.frameResponse(Constants.HTTP_STATUS_CODE_FAILURE,
                      Constants.RESPONSE_FAILURE, "Invalid Username or Password", "");
            }
          } else {
            responseString =
                Utils.frameResponse(Constants.HTTP_STATUS_CODE_FAILURE, Constants.RESPONSE_FAILURE,
                    "Invalid Username or Password", "");
          }
        }
      }else{
        responseString =
            Utils.frameResponse(Constants.HTTP_STATUS_CODE_FAILURE,
                Constants.RESPONSE_FAILURE, "Invalid Username or Password", "");
        
      }
    } catch (Exception e) {
      LOGGER.error("Exception occurred while authenticate ::", e);
    }
    return responseString;
  }

  public AuthenticationDaoImpl getAuthenticationDaoImpl() {
    return authenticationDaoImpl;
  }

  public void setAuthenticationDaoImpl(AuthenticationDaoImpl authenticationDaoImpl) {
    this.authenticationDaoImpl = authenticationDaoImpl;
  }


}
