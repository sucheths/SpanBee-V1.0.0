package com.spanbee.controller;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;

import com.spanbee.constants.Constants;
import com.spanbee.requestparameters.Request;
import com.spanbee.service.PasswordService;
import com.spanbee.utils.PropertyReader;
import com.spanbee.utils.Utils;

/**
 * @author sbandi
 *
 */


@Path("login")
public class PasswordController {
  
  @Autowired
  private PasswordService passwordService;
  private static final Logger LOGGER = Logger.getLogger(PasswordController.class);
  
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/forgotPassword")
  public String forgotPassword(String jsonReqest) throws Exception{
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("Entered into register authenticate with request string :" + jsonReqest);
    }
    
    String version = null;
    String session_id = null;
    String responseString = null;
    String message = null;
    
    try {
      if (jsonReqest != null) {
        Request request = Utils.parseJsonRequest(jsonReqest);
        if (request != null) {
          version = request.getVersion();
          session_id = request.getSession_id();
          JsonNode dataNode = request.getData();
          
          if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("register Request::: " + " :: version from request: " + version
                + " :: session_id : " + session_id + " :: dataList : " + dataNode);
          }
              
          JsonNode forgotPasswordNode = dataNode.get("forgotPassword");
          LOGGER.error("forgotPasswordParameters::" + forgotPasswordNode.toString());
          
          if (passwordService != null ) {
            String emailId = forgotPasswordNode.get("email_id").getTextValue();
            
            responseString = passwordService.forgotPassword(emailId);
          } else {
            message = PropertyReader.resourceBundlesManager.getValueFromResourceBundle(Constants.LANGUAGE,
                Constants.ERROR_CODE_500 + Constants._ERROR_MESSAGE);
            responseString =
                Utils.frameResponse(Constants.HTTP_STATUS_CODE_FAILURE,
                    Constants.RESPONSE_FAILURE,message, "");
            LOGGER.error("Request obtained after parsing JSON request is null");
          }
          
        } else {
          message = PropertyReader.resourceBundlesManager.getValueFromResourceBundle(Constants.LANGUAGE,
              Constants.ERROR_CODE_500 + Constants._ERROR_MESSAGE);
          responseString =
              Utils.frameResponse(Constants.HTTP_STATUS_CODE_FAILURE,
                  Constants.RESPONSE_FAILURE,message, "");
          LOGGER.error("Request obtained after parsing JSON request is null");
        }
      } else {
        message = PropertyReader.resourceBundlesManager.getValueFromResourceBundle(Constants.LANGUAGE,
            Constants.ERROR_CODE_500 + Constants._ERROR_MESSAGE);
        responseString =
            Utils.frameResponse(Constants.HTTP_STATUS_CODE_FAILURE,
                Constants.RESPONSE_FAILURE,message, "");
        LOGGER.warn("Request obtained is null");
      }
    } catch (Exception e) {
      message = PropertyReader.resourceBundlesManager.getValueFromResourceBundle(Constants.LANGUAGE,
          Constants.ERROR_CODE_500 + Constants._ERROR_MESSAGE);
      responseString =
          Utils.frameResponse(Constants.HTTP_STATUS_CODE_FAILURE,
              Constants.RESPONSE_FAILURE,message, "");
      LOGGER.error("Exception occurred ::", e);
    }
    return responseString;
  }
  
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/resetPassword")
  public String resetPassword(String jsonReqest) throws Exception{
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("Entered into register authenticate with request string :" + jsonReqest);
    }
    
    String version = null;
    String session_id = null;
    String responseString = null;
    String message = null;
    
    try {
      if (jsonReqest != null) {
        Request request = Utils.parseJsonRequest(jsonReqest);
        if (request != null) {
          version = request.getVersion();
          session_id = request.getSession_id();
          JsonNode dataNode = request.getData();
          
          if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("register Request::: " + " :: version from request: " + version
                + " :: session_id : " + session_id + " :: dataList : " + dataNode);
          }
              
          JsonNode forgotPasswordNode = dataNode.get("forgotPassword");
          LOGGER.error("forgotPasswordParameters::" + forgotPasswordNode.toString());
          
          if (passwordService != null ) {
            String emailId = forgotPasswordNode.get("email_id").getTextValue();
          }
          
        } else {
          message =PropertyReader.resourceBundlesManager.getValueFromResourceBundle(Constants.LANGUAGE,
              Constants.ERROR_CODE_500+Constants._ERROR_MESSAGE);
          responseString =
              Utils.frameResponse(Constants.HTTP_STATUS_CODE_FAILURE,
                  Constants.RESPONSE_FAILURE,message, "");
          LOGGER.error("Request obtained after parsing JSON request is null");
        }
      } else {
        message =PropertyReader.resourceBundlesManager.getValueFromResourceBundle(Constants.LANGUAGE,
            Constants.ERROR_CODE_500+Constants._ERROR_MESSAGE);
        responseString =
            Utils.frameResponse(Constants.HTTP_STATUS_CODE_FAILURE,
                Constants.RESPONSE_FAILURE,message, "");
        LOGGER.warn("Request obtained is null");
      }
    } catch (Exception e) {
      message =PropertyReader.resourceBundlesManager.getValueFromResourceBundle(Constants.LANGUAGE,
          Constants.ERROR_CODE_500+Constants._ERROR_MESSAGE);
      responseString =
          Utils.frameResponse(Constants.HTTP_STATUS_CODE_FAILURE,
              Constants.RESPONSE_FAILURE,message, "");
      LOGGER.error("Exception occurred ::", e);
    }
    return responseString;
  }

  public void setPasswordService(PasswordService passwordService) {
    this.passwordService = passwordService;
  }
}

