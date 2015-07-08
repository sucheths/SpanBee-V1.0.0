package com.spanbee.constants;

import java.text.SimpleDateFormat;

/**
 * @author sucheth.s
 * 
 */

public class Constants {
  public static final String DATE_FORMAT = "dd/MM/yyyy";
  static SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
  //log4j configuration
  
  public static final String LOG4J_FILE = "log4j.properties";
  public static final String RESOURCE_NAME = "resource";
  public static final String APPLICATION_INI = "application.ini";
  public static final String LANGUAGE= "en";   
  
  public static final String AUTHENTICATION_SUCCESS="AUTHENTICATION_SUCCESS";
  
  public static final String REGISTRATION_SUCCESS_MESSAGE="REGISTRATION_SUCCESS_MESSAGE";

  
  public static final String ERROR_TYPE_INLINE = "INLINE";
  public static final String FILTER_REFILL_REQ = "1";
  
  public static final String FILTER_REFILL_REM = "0";
  
  public static final byte STATUS_ACTIVE= 1;
  
  public static final byte STATUS_INACTIVE= 0;
  
  
  public static final String RESPONSE_SUCCESS = "SUCCESS";
  
  public static final String RESPONSE_FAILURE = "FAILURE";
  
  public static final String HTTP_STATUS_CODE_SUCCESS = "200";
  
  public static final String HTTP_STATUS_CODE_PARTIAL_SUCCESS = "201";
  
  public static final String HTTP_STATUS_CODE_FAILURE = "500";
  
  public static final String ERROR_CODE_500= "500";
  
  public static final String GF_PASSWORD="gf";
    
  
  public static final String ERROR_CODE_501= "501";
  
  public static final String ERROR_CODE_600= "600";
  
  public static final String ERROR_CODE_700= "700";
  
  public static final String ERROR_CODE_701= "701";
  
   
  
  public static final String _ERROR_MESSAGE= "_ERROR_MESSAGE";
  
  
  

 
}
