package com.spanbee.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.annotate.JsonMethod;

import com.spanbee.exceptions.RequestParserException;
import com.spanbee.requestparameters.Request;
import com.spanbee.responseparameters.Response;


/**
 * @author sucheth.s
 * 
 */

public class Utils {
  private static final Logger LOGGER = Logger.getLogger(Utils.class);

  public static Request parseJsonRequest(String requestString) throws RequestParserException {
    Request req = null;
    try {
      ObjectMapper reqMapper = new ObjectMapper();
      reqMapper.setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
      reqMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);

      req = reqMapper.readValue(requestString, Request.class);
      if (req == null) {
        LOGGER.error("Error occurred while parsing the Request JSON Object : ");
        throw new RequestParserException("1", "Request Object could not be parsed");
      }
    } catch (Exception e) {
      LOGGER.error("Error occurred while parsing the Request JSON Object : ", e);
      throw new RequestParserException("1", "Request Object could not be parsed");
    }
    return req;
  }

  public static String getDateFormat(String birthdateString) {
    String birthDate = "";
    try {
      String newDate = birthdateString.substring(0, 10);
      String[] chunks = newDate.split("-", -1);
      String newDateString = "";
      for (int i = chunks.length - 1; i >= 0; i--) {
        newDateString = newDateString + chunks[i];
        newDateString = newDateString + "-";
      }
      birthDate = newDateString.substring(0, newDateString.length() - 1);
    } catch (Exception e) {
      LOGGER.error("Error While formatting the date,", e);
    }
    return birthDate;
  }

  public static String getResponseString(Response response) throws IOException {
    ObjectMapper mapper1 = new ObjectMapper();
    Writer strWriter1 = new StringWriter();
    mapper1.writeValue(strWriter1, response);
    return strWriter1.toString();
  }

  public static String frameResponse(String code, String status, String message, String description) {
    Response response = null;
    String responseMsg = null;
    try {
      response = new Response();
      response.setCode(code);
      response.setDescription(description);
      response.setMessage(message);
      response.setStatus(status);
      responseMsg = getResponseString(response);
    } catch (Exception e) {
      LOGGER.error("Exception occurred :", e);
    }
    return responseMsg;
  }
  
  
 



}
