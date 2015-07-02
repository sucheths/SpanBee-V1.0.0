package com.spanbee.service;


import java.util.Date;

import org.apache.log4j.Logger;

import com.spanbee.constants.Constants;
import com.spanbee.dao.RegistrationDaoImpl;
import com.spanbee.entities.Customer;
import com.spanbee.model.EmailModel;
import com.spanbee.requestparameters.RegisterationParameters;
import com.spanbee.requestparameters.Request;
import com.spanbee.responseparameters.Response;
import com.spanbee.service.email.SendRegistrationEmailThread;
import com.spanbee.utils.AESSecurity;
import com.spanbee.utils.EnumValues;
import com.spanbee.utils.KeyGenerator;
import com.spanbee.utils.PropertyReader;
import com.spanbee.utils.Utils;

/**
 * @author sucheth.s
 * 
 */

public class RegistrationServiceImpl implements RegistrationService {

  private RegistrationDaoImpl registrationDao;
  public void setRegistrationDao(RegistrationDaoImpl registrationDao) {
    this.registrationDao = registrationDao;
  }

  private static final Logger LOGGER = Logger.getLogger(RegistrationServiceImpl.class);

  @Override
  public String register(RegisterationParameters registrationParams, Request request) {

    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("**********@Inside register Method Of Service Layer**********");
    }
    // registrationDaoImpl =
    // (RegistrationDaoImpl) SpringApplicationContext.getBean("registrationDaoImpl");
    Customer customer = null;
    String responseString = null;

    try {
      if (registrationParams != null && request != null) {
        customer = new Customer();
        customer.setBirthDate(Utils.getDateFormat(registrationParams.getBirth_date()));
        customer.setCustomerStatus((byte) EnumValues.CustomerStatus.Active.ordinal());
        customer.setEmailAddress(AESSecurity.encrypt(registrationParams.getEmail_id()));
        customer.setFirstName(registrationParams.getFirst_name());
        customer.setGender(registrationParams.getGender());
        customer.setLastName(registrationParams.getLast_name());
        customer.setMaritalStatus(Byte.valueOf(registrationParams.getMarital_status()));
        customer.setMobile(registrationParams.getMobile());
        customer.setPassword(AESSecurity.encrypt(registrationParams.getPassword()));
        customer.setUniqueId(KeyGenerator.getUniqueTransactionId());
        customer.setCreatedAt(new Date());
        customer.setUpdatedAt(new Date());
        customer = registrationDao.register(customer);
        if (customer != null) {
          Response resp = new Response();
          resp.setCode(Constants.HTTP_STATUS_CODE_SUCCESS);
          resp.setStatus(Constants.RESPONSE_SUCCESS);

          String message =
              PropertyReader.resourceBundlesManager.getValueFromResourceBundle("en",
                  "REGISTRATION_SUCCESS_MESSAGE");
          message =
              message.replace("$FIRST_NAME", customer.getFirstName()).replace("$EMAIL",
                  AESSecurity.decrypt(customer.getEmailAddress()));
          resp.setMessage(message);
          resp.setDescription("");
          responseString = Utils.getResponseString(resp);
          EmailModel emailModel = new EmailModel();
          emailModel.setSubject(PropertyReader.resourceBundlesManager
              .getValueFromResourceBundle("en", "EMAIL_SUBJECT"));
          emailModel.setFromAddress(PropertyReader.iniUtils.get("EMAIL", "EMAIL_FROMADDESS"));
          emailModel.setHostName(PropertyReader.iniUtils.get("EMAIL", "EMAIL_HOSTNAME"));
          emailModel.setPassword(PropertyReader.iniUtils.get("EMAIL", "EMAIL_PASSWORD"));
          emailModel.setPort(PropertyReader.iniUtils.get("EMAIL", "EMAIL_PORT"));
          emailModel.setProtocol(PropertyReader.iniUtils.get("EMAIL", "EMAIL_PROTOCOL"));
          String emailTemplate = getEmailTemplate(customer);
          emailModel.setContent(emailTemplate);
          emailModel.setToaddess(AESSecurity.decrypt(customer.getEmailAddress()));
          emailModel.setUserName(PropertyReader.iniUtils.get("EMAIL", "EMAIL_USERNAME"));
          SendRegistrationEmailThread registrationThreadEmail =
              new SendRegistrationEmailThread(emailModel);
          Thread emailThread = new Thread(registrationThreadEmail);
          emailThread.start();

        }

      }
    } catch (Exception e) {

      LOGGER.error("Exception occurred while registration ::", e);
    }
    return responseString;
  }

  /**
   * @param customer
   * @throws Exception
   */
  private String getEmailTemplate(Customer customer) throws Exception {
    String emailTemplate =PropertyReader.resourceBundlesManager.getValueFromResourceBundle("en", "EMAIL_TEMPLATE");
    emailTemplate =
        emailTemplate.replace("$EMAIL_USER_NAME",customer.getFirstName());
    emailTemplate =
        emailTemplate.replace("$EMAIL_TITLE",
            PropertyReader.resourceBundlesManager.getValueFromResourceBundle("en", "EMAIL_TITLE"));
    emailTemplate =
        emailTemplate
            .replace("$EMAIL_MESSAGE", PropertyReader.resourceBundlesManager
                .getValueFromResourceBundle("en", "EMAIL_MESSAGE"));
    emailTemplate =
        emailTemplate.replace("$EMAIL_THANKS_MESSAGE", PropertyReader.resourceBundlesManager
            .getValueFromResourceBundle("en", "EMAIL_THANKS_MESSAGE"));
    emailTemplate =
        emailTemplate.replace("$EMAIL_CLICK_MESSAGE", PropertyReader.resourceBundlesManager
            .getValueFromResourceBundle("en", "EMAIL_CLICK_MESSAGE"));
    emailTemplate =
        emailTemplate.replace("$EMAIL_HREF_COMPANY_NAME", PropertyReader.resourceBundlesManager
            .getValueFromResourceBundle("en", "EMAIL_HREF_COMPANY_NAME"));

    String verificationLink =
        PropertyReader.resourceBundlesManager.getValueFromResourceBundle("en",
            "EMAIL_VERIFICATION_LINK") + customer.getUniqueId();

    emailTemplate = emailTemplate.replace("$EMAIL_VERIFICATION_LINK", verificationLink);
    emailTemplate =
        emailTemplate.replace("$EMAIL_EMAIL",
            PropertyReader.resourceBundlesManager.getValueFromResourceBundle("en", "EMAIL_EMAIL"));
    return emailTemplate;
  }

  


}
