package com.spanbee.daotest;

import static org.junit.Assert.fail;

import java.net.URLDecoder;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.spanbee.dao.RegistrationDaoImpl;
import com.spanbee.entities.Customer;
import com.spanbee.listeners.SpringApplicationContext;
import com.spanbee.utils.EnumValues;
import com.spanbee.utils.KeyGenerator;
import com.spanbee.utils.Utils;


public class RegistrationDaoImplTest {


  private static final Logger LOGGER = Logger.getLogger(RegistrationDaoImplTest.class);
  private ApplicationContext applicationContext;

  private RegistrationDaoImpl registrationDaoImpl;

  @Before
  public void setUp() throws Exception {

    // Loading log4j properties
    String logFileName = "log4j.properties";
    String applicationContextFileName = "applicationContext.xml";
    String logCompletepath = "";
    String appCompletepath = "";
    String web_inf_path = "";
    try {
      web_inf_path =
          URLDecoder.decode(RegistrationDaoImpl.class.getProtectionDomain().getCodeSource()
              .getLocation().getPath(), "UTF8");
      web_inf_path = web_inf_path.substring(0, web_inf_path.length() - 14);
      System.out.println("web_inf_path ::" + web_inf_path);
      String absPath = "F:\\spanbee_github\\local_repository\\SpanBee-V1.0.0\\span-bee\\src\\main\\webapp\\WEB-INF\\";//web_inf_path + "WebContent/WEB-INF/";
     // absPath = absPath.substring(1, absPath.length());
      //absPath = absPath.replaceAll("/", "\\\\");

      logCompletepath = absPath + logFileName;

      PropertyConfigurator.configure(logCompletepath);
      System.out.println("Absolute Path for Log file : " + logCompletepath);

      // Application Context loading
      appCompletepath = absPath + applicationContextFileName;
      LOGGER.error("Absolute Path for App file : " + appCompletepath);
      
        SpringApplicationContext springApplicationContext = new SpringApplicationContext();
        LOGGER.error(springApplicationContext);
        applicationContext = new FileSystemXmlApplicationContext("F:/spanbee_github/local_repository/SpanBee-V1.0.0/span-bee/src/main/webapp/WEB-INF/applicationContext.xml");
        LOGGER.error("applicationContext::"+applicationContext);
        springApplicationContext.setApplicationContext(applicationContext);
        System.out.println("applicationContext ::"+applicationContext);
        registrationDaoImpl =
            (RegistrationDaoImpl) SpringApplicationContext.getBean("registrationDaoImpl");
        System.out.println("registrationDaoImpl ::"+registrationDaoImpl);
      } catch (Exception e) {
        
        LOGGER.error("Exception : " + e);
      }
  }


  @Ignore
  public void testGetAdhCampaign() {
    fail("Not yet implemented");
  }

  @Ignore
  public void testAddAdhPortalPage() {

  }

  @Ignore
  public void testAddAdhPortalAnalytics() {
    fail("Not yet implemented");
  }

  @Ignore
  public void testGetAdhEligiblePatient() {
    fail("Not yet implemented");
  }

  @Ignore
  public void testEnrollPatient() {
    fail("Not yet implemented");
  }

  @Ignore
  public void testAddPatientEmail() {
    fail("Not yet implemented");
  }

  @Ignore
  public void testGetPortalPageIds() throws Exception {
    // patientPortalDaoImpl.getPortalPageIds(1);
  }

  
  @Test
  public void testRegister() throws Exception {
    System.out.println("******************************");
    Customer customer= new Customer();
    customer.setBirthDate(Utils.getDateFormat(String.valueOf(new Date())));
    customer.setCustomerStatus((byte)EnumValues.CustomerStatus.Active.ordinal());
    customer.setEmailAddress("rjeshbn1989@gmail.com");
    customer.setFirstName("Raju");
    customer.setGender("m");
    customer.setLastName("N");
    customer.setMaritalStatus((byte)0);
    customer.setMobile("7760988271");
    customer.setPassword("Raju@3669");
    customer.setUniqueId(KeyGenerator.getUniqueTransactionId());
    customer.setCreatedAt(new Date());
    customer.setUpdatedAt(new Date());
    System.out.println(registrationDaoImpl.register(customer));

    System.out.println("******************************");
  }
}
