package com.spanbee.controller;

import java.util.Iterator;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.ObjectMapper;

import com.spanbee.requestparameters.RegisterationParameters;
import com.spanbee.requestparameters.Request;
import com.spanbee.service.CommonService;
import com.spanbee.utils.Utils;

import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.annotate.JsonAutoDetect;
/**
 * @author sucheth.s
 * 
 */
@Path("registration")
public class RegistrationController {



  private CommonService commonService;
  private static final Logger LOGGER = Logger.getLogger(RegistrationController.class);

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/register")
  public String register(String jsonReqest) {

    String version;
    String session_id;
    String request_origin;
    JsonNode data;

    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("Entered into register method with reuest string :" + jsonReqest);
    }
    try {
      if (jsonReqest != null) {

        Request request = Utils.parseJsonRequest(jsonReqest);
        if (request != null) {
          version = request.version;
          session_id = request.session_id;
          request_origin = request.request_origin;
          JsonNode dataNode = request.data;
          RegisterationParameters registerationParameters = null;
          LOGGER.debug("register Request::: " + " :: version from request: " + version
              + " :: session_id : " + session_id + " :: dataList : " + dataNode);
            JsonNode registerNode = dataNode.get("register");
            LOGGER.error("registerationParameters::" + registerNode.toString());
            registerationParameters = parseGetcodeRequest(registerNode.toString());
            

        } else {
          LOGGER.error("request object after parsing the jsonReqest is null");
        }


      } else {
        LOGGER.warn("Request obtained is null");
      }

    } catch (Exception e) {
      LOGGER.error("Exception occurred ::", e);
    }
    return "{\"status\":\"success\"}";
  }

  private RegisterationParameters parseGetcodeRequest(String registerParameters) {
    RegisterationParameters parameters = null;
    LOGGER.debug("Parsing CodevaluesRequest request for string : " + registerParameters);
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

  public CommonService getCommonService() {
    return commonService;
  }

  public void setCommonService(CommonService commonService) {
    this.commonService = commonService;
  }

}
