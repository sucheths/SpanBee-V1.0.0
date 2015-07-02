package com.spanbee.utils;

import java.net.URLDecoder;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class AESSecurityTest {

  private static final Logger LOGGER = Logger.getLogger(AESSecurityTest.class);
  
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    //Loading log4j properties
    String logFileName = "log4j.properties";
    String logCompletepath = ""; 
    String web_inf_path = "";
    
    try {
        web_inf_path = URLDecoder.decode(AESSecurity.class.getProtectionDomain().getCodeSource().getLocation().getPath(), "UTF8");
        
        String[] str = web_inf_path.split("target");
        String intermediatePath = str + "\\src\\main\\webapp\\WEB-INF\\";
        
        logCompletepath = intermediatePath + logFileName;           

        PropertyConfigurator.configureAndWatch(logCompletepath);
        LOGGER.debug("Absolute Path for Log file : " + logCompletepath);
    } catch(Exception e) {          
        LOGGER.error("Exception : " + e);
        e.printStackTrace();
    }
  }
  
  @Ignore
  public void testEncrypt() {
    LOGGER.info("********* Inside testEncrypt method ************");
    String emailId = "adc@test.com";
    try {
      System.out.println("Encrypted Str : " + AESSecurity.encrypt(emailId));
    } catch(Exception e) {
        LOGGER.error("Exception occured while encrypting : " , e);
    }
    
    LOGGER.info("********* End of testRegister method ************");
  }

  @Test
  public void testDecrypt() {
    LOGGER.info("********* Inside testDecrypt method ************");
    String encrytedStr = "OE17NbhL79lGuYiVKO3ESQ==";
    
    try {
      System.out.println("Decrypted Str : " + AESSecurity.decrypt(encrytedStr));
    } catch(Exception e) {
      LOGGER.error("Exception occured while decrypting : " , e);
    }
    
    LOGGER.info("********* End of testRegister method ************");
  }

}
