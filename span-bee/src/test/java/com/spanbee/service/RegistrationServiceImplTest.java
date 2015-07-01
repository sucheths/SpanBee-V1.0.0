package com.spanbee.service;

import java.net.URLDecoder;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.spanbee.dao.RegistrationDaoImpl;
import com.spanbee.dao.RegistrationDaoImplTest;
import com.spanbee.listeners.SpringApplicationContext;
import com.spanbee.requestparameters.RegisterationParameters;
import com.spanbee.requestparameters.Request;
import com.spanbee.utils.AESSecurity;

public class RegistrationServiceImplTest {

	private static final Logger LOGGER = Logger.getLogger(RegistrationDaoImplTest.class);
	private static ApplicationContext applicationContext;
	private static RegistrationService registrationService;
	
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

	        registrationService =
	            (RegistrationService) SpringApplicationContext.getBean("registrationService");
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
		
		RegisterationParameters registerationParameters = new RegisterationParameters();
		
		registerationParameters.setBirth_date("12/11/1990");
		registerationParameters.setEmail_id("rajeshbn1989@gmail.com");
		registerationParameters.setFirst_name("Raju");
		registerationParameters.setGender("m");
		registerationParameters.setLast_name("NN");
		registerationParameters.setMarital_status("single");
		registerationParameters.setMobile("123456789");
		registerationParameters.setPassword("test@123");
		
		Request request = new Request();
		
		request.setData(null);
		request.setRequest_origin("");
		request.setSession_id(null);
		request.setVersion("1.0");
		
		try {
			String status = registrationService.register(registerationParameters, request);
			LOGGER.fatal("Successfully registered the user : " + status);
		} catch(Exception e) {
			LOGGER.error("Exception occured while junit testing registration : " , e);
		}
		LOGGER.info("********* End of testRegister method ************");
	}
}
