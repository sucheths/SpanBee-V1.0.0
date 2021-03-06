package com.spanbee.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;



import org.apache.log4j.Logger;

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
   PropertyReader.loadIniFile(Constants.APPLICATION_INI, arg0);
   
  }
  
  @Override
  public void contextDestroyed(ServletContextEvent arg0) {
    // TODO Auto-generated method stub
    
  }
  
  
  
  

}

