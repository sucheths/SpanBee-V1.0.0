package com.spanbee.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.spanbee.entities.Customer;
import com.spanbee.listeners.SpringApplicationContext;
import com.spanbee.utils.AESSecurity;

public class RegistrationDaoImplTest {

  private static final Logger LOGGER = Logger.getLogger(RegistrationDaoImplTest.class);
  private static ApplicationContext applicationContext;
  private static RegistrationDaoImpl registrationDaoImpl;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    // Loading log4j properties
    String logFileName = "log4j.properties";
    String applicationContextFileName = "applicationContext.xml";
    String logCompletepath = "";
    String web_inf_path = "";

    try {
      web_inf_path =
          URLDecoder.decode(AESSecurity.class.getProtectionDomain().getCodeSource().getLocation()
              .getPath(), "UTF8");

      String[] str = web_inf_path.split("target");
      String intermediatePath = str[0] + "src/main/webapp/WEB-INF/";

      logCompletepath = intermediatePath + logFileName;
      PropertyConfigurator.configureAndWatch(logCompletepath);

      // Application Context loading
      String appCompletepath = intermediatePath + applicationContextFileName;
      LOGGER.fatal("Absolute Path for App file : " + appCompletepath);
      try {
        SpringApplicationContext springApplicationContext = new SpringApplicationContext();
        applicationContext = new FileSystemXmlApplicationContext(appCompletepath);
        springApplicationContext.setApplicationContext(applicationContext);

        registrationDaoImpl =
            (RegistrationDaoImpl) SpringApplicationContext.getBean("registrationDaoImpl");
      } catch (Exception e) {
        e.printStackTrace();
      }
      LOGGER.debug("Absolute Path for Log file : " + logCompletepath);
    } catch (Exception e) {
      LOGGER.error("Exception : " + e);
      e.printStackTrace();
    }
  }

  @Test
  public void testRegister() {
    LOGGER.info("********* Inside testRegister method ************");

    Customer customer = new Customer();

    customer.setBirthDate("");

    try {
      registrationDaoImpl.register(customer);
    } catch (Exception e) {

    }

    LOGGER.info("********* End of testRegister method ************");
  }

}
