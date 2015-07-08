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
import com.spanbee.requestparameters.RegisterationParameters;
import com.spanbee.requestparameters.Request;
import com.spanbee.service.RegistrationService;
import com.spanbee.utils.PropertyReader;
import com.spanbee.utils.Utils;

/**
 * @author sucheth.s
 * 
 */
@Path("registration") 
public class RegistrationController {

 
  @Autowired
  private RegistrationService registrationService;
  private static final Logger LOGGER = Logger.getLogger(RegistrationController.class);

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/register")
  public String register(String jsonReqest) throws Exception {

    String version = null;
    String session_id = null;
    String request_origin = null;
    JsonNode data = null;
    String responseString = null;
    RegisterationParameters registerationParameters = null;
    String message=null;
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("Entered into register method with request string :" + jsonReqest);
    }
    try {
      if (jsonReqest != null) {
        Request request = Utils.parseJsonRequest(jsonReqest);
        if (request != null) {
          version = request.getVersion();
          session_id = request.getSession_id();
          request_origin = request.getRequest_origin();
          JsonNode dataNode = request.getData();
          LOGGER.debug("register Request::: " + " :: version from request: " + version
              + " :: session_id : " + session_id + " :: dataList : " + dataNode);
          JsonNode registerNode = dataNode.get("register");
          LOGGER.error("registerationParameters::" + registerNode.toString());
          registerationParameters = parseRegisterRequest(registerNode.toString());
          LOGGER.info("registrationService:::" + registrationService);
          if (registrationService != null && registerationParameters != null) {
            responseString = registrationService.register(registerationParameters, request);
          }else{
            message =PropertyReader.resourceBundlesManager.getValueFromResourceBundle(Constants.LANGUAGE,
                Constants.ERROR_CODE_500+Constants._ERROR_MESSAGE);
            responseString =
                Utils.frameResponse(Constants.HTTP_STATUS_CODE_FAILURE,
                    Constants.RESPONSE_FAILURE,message, "");
            LOGGER.error("request object after parsing the jsonReqest is null");
          }
        } else {
          message =PropertyReader.resourceBundlesManager.getValueFromResourceBundle(Constants.LANGUAGE,
              Constants.ERROR_CODE_500+Constants._ERROR_MESSAGE);
          responseString =
              Utils.frameResponse(Constants.HTTP_STATUS_CODE_FAILURE,
                  Constants.RESPONSE_FAILURE,message, "");
          LOGGER.error("request object after parsing the jsonReqest is null");
        }
      } else {
        message =PropertyReader.resourceBundlesManager.getValueFromResourceBundle(Constants.LANGUAGE,
            Constants.ERROR_CODE_500+Constants._ERROR_MESSAGE);
        responseString =
            Utils.frameResponse(Constants.HTTP_STATUS_CODE_FAILURE,
                Constants.RESPONSE_FAILURE,message, "");
        LOGGER.warn("JSON Request obtained is null");
      }
      LOGGER.fatal("Sending response to the customer ::"+responseString);

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

  private RegisterationParameters parseRegisterRequest(String registerParameters) {
    RegisterationParameters parameters = null;
    LOGGER.debug("Parsing RegisterationRequest request for string : " + registerParameters);
    try {
      ObjectMapper registerationMapper = new ObjectMapper();
      registerationMapper.setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
      registerationMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
      registerationMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      parameters = registerationMapper.readValue(registerParameters, RegisterationParameters.class);
    } catch (Exception e) {
      LOGGER.error("Error occured while parsing registeration Request Object : ", e);
    }
    return parameters;
  }



  public void setRegistrationService(RegistrationService registrationService) {
    this.registrationService = registrationService;
  }



}
