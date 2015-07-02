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
      
  
  public static final String ERROR_TYPE_INLINE = "INLINE";
  public static final String FILTER_REFILL_REQ = "1";
  
  public static final String FILTER_REFILL_REM = "0";
  
  public static final String RESPONSE_SUCCESS = "SUCCESS";
  
  public static final String RESPONSE_FAILURE = "FAILURE";
  
  public static final String HTTP_STATUS_CODE_SUCCESS = "200";
  
  public static final String HTTP_STATUS_CODE_PARTIAL_SUCCESS = "201";
  
  public static final String HTTP_STATUS_CODE_FAILURE = "500";
  
  public static final String GENERAL_ERROR_CODE= "500";
  
  public static final String _ERROR_CODE= "ERROR_MESSAGE";
  
  
  

 
}
