package com.spanbee.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


import com.spanbee.constants.Constants;
import com.spanbee.utils.PropertyReader;

/**
 * @author sucheth.s
 *
 */

public class ApplicationListener implements ServletContextListener {
private static final Logger LOGGER = Logger.getLogger(ApplicationListener.class);
 

  @Override
  public void contextInitialized(ServletContextEvent arg0) {
   PropertyReader.loadLog4j(Constants.LOG4J_FILE, arg0);
   PropertyReader.loadResourceBundle(Constants.RESOURCE_NAME, arg0);
   try {
   String mesage= PropertyReader.resourceBundlesManager.getValueFromResourceBundle("en", "REGISTRATION_SUCCESS_MESSAGE");
   LOGGER.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$" +mesage);
  } catch (Exception e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }
      
  }
  
  @Override
  public void contextDestroyed(ServletContextEvent arg0) {
    // TODO Auto-generated method stub
    
  }
  
  
  
  

}

