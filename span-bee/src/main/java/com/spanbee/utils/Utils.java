package com.spanbee.utils;

import org.apache.log4j.Logger;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.annotate.JsonMethod;
import com.spanbee.exceptions.RequestParserException;
import com.spanbee.requestparameters.Request;


/**
 * @author sucheth.s
 *
 */

public class Utils {
  private static final Logger LOGGER= Logger.getLogger(Utils.class);
  
  
  public static Request parseJsonRequest(String requestString) throws RequestParserException{
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

}

