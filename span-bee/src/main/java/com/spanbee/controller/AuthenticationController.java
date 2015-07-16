package com.spanbee.controller;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.beans.factory.annotation.Autowired;

import com.spanbee.constants.Constants;
import com.spanbee.requestparameters.AuthenticationParameters;
import com.spanbee.requestparameters.Request;
import com.spanbee.responseparameters.Response;
import com.spanbee.service.AuthenticationService;
import com.spanbee.utils.PropertyReader;
import com.spanbee.utils.Utils;

/**
 * @author sucheth.s
 *
 */


@Path("login")
public class AuthenticationController {
  
  @Autowired
  private AuthenticationService authenticateService;
  private static final Logger LOGGER = Logger.getLogger(AuthenticationController.class);
  
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/authenticate")
  public String authenticate(String jsonReqest) throws Exception{

    String version = null;
    String session_id = null;
    String request_origin = null;
    JsonNode data = null;
    String responseString = null;
    String message = null;
    Response response=null;
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("Entered into register authenticate with request string :" + jsonReqest);
    }
    try {
      response=new Response();
      if (jsonReqest != null) {
        Request request = Utils.parseJsonRequest(jsonReqest);
        if (request != null) {
          version = request.getVersion();
          session_id = request.getSession_id();
          request_origin = request.getRequest_origin();
          JsonNode dataNode = request.getData();
          AuthenticationParameters authenticationParameters= null;
          LOGGER.debug("register Request::: " + " :: version from request: " + version
              + " :: session_id : " + session_id + " :: dataList : " + dataNode);
          JsonNode authenticateNode = dataNode.get("authenticate");
          LOGGER.error("registerationParameters::" + authenticateNode.toString());
          authenticationParameters = parseGetcodeRequest(authenticateNode.toString());
          LOGGER.info("registrationService:::" + authenticateService);
          if (authenticateService != null && authenticationParameters != null) {
            responseString = authenticateService.authenticate(authenticationParameters, request);
          }else{
            message =PropertyReader.resourceBundlesManager.getValueFromResourceBundle(Constants.LANGUAGE,
                Constants.ERROR_CODE_500+Constants._ERROR_MESSAGE);
         
            response.setCode(Constants.ERROR_CODE_500);
            response.setMessage(message);
            response.setStatus(Constants.RESPONSE_FAILURE);
            responseString =
                Utils.frameResponse(response);
            LOGGER.error("Something went wrong while parsing request");
          }
        } else {
          message =PropertyReader.resourceBundlesManager.getValueFromResourceBundle(Constants.LANGUAGE,
              Constants.ERROR_CODE_500+Constants._ERROR_MESSAGE);
          response.setCode(Constants.ERROR_CODE_500);
          response.setMessage(message);
          response.setStatus(Constants.RESPONSE_FAILURE);
          
          responseString =
              Utils.frameResponse(response);
          LOGGER.error("Request obtained after parsing JSON request is null");
        }
      } else {
        message =PropertyReader.resourceBundlesManager.getValueFromResourceBundle(Constants.LANGUAGE,
            Constants.ERROR_CODE_500+Constants._ERROR_MESSAGE);
        response.setCode(Constants.ERROR_CODE_500);
        response.setMessage(message);
        response.setStatus(Constants.RESPONSE_FAILURE);
        
        responseString =
            Utils.frameResponse(response);
        LOGGER.warn("Request obtained is null");
      }
    } catch (Exception e) {
      message =PropertyReader.resourceBundlesManager.getValueFromResourceBundle(Constants.LANGUAGE,
          Constants.ERROR_CODE_500+Constants._ERROR_MESSAGE);
    
      response.setCode(Constants.ERROR_CODE_500);
      response.setMessage(message);
      response.setStatus(Constants.RESPONSE_FAILURE);
      
      responseString =
          Utils.frameResponse(response);
      LOGGER.error("Exception occurred ::", e);
    }
    return responseString;
  }
  
  private AuthenticationParameters parseGetcodeRequest(String authenticationParameters) {
    AuthenticationParameters parameters = null;
    LOGGER.debug("Parsing AuthenticationRequest request for string : " + authenticationParameters);
    try {
      ObjectMapper registerationMapper = new ObjectMapper();
      registerationMapper.setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
      registerationMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
      registerationMapper
          .configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      parameters = registerationMapper.readValue(authenticationParameters, AuthenticationParameters.class);
    } catch (Exception e) {
      LOGGER.error("Error occured while parsing authentication Request Object : ", e);
    }
    return parameters;
  }


  public void setAuthenticateService(AuthenticationService authenticateService) {
    this.authenticateService = authenticateService;
  }


}

