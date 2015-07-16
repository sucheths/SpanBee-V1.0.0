package com.spanbee.service;


import java.util.Date;

import org.apache.log4j.Logger;

import com.spanbee.constants.Constants;
import com.spanbee.dao.AuthenticationDao;
import com.spanbee.dao.RegistrationDao;
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

  private RegistrationDao registrationDao;
  private AuthenticationDao authenticationDao;


  private static final Logger LOGGER = Logger.getLogger(RegistrationServiceImpl.class);

  @Override
  public String register(RegisterationParameters registrationParams, Request request)
      throws Exception {

    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("**********@Inside register Method Of Service Layer**********");
    }
    // registrationDaoImpl =
    // (RegistrationDaoImpl) SpringApplicationContext.getBean("registrationDaoImpl");
    Customer customer = null;
    String responseString = null;
    String message = null;
    Response response = null;

    try {
      response = new Response();
      if (registrationParams != null && request != null) {
        // Check if customer is already registered
        customer =
            authenticationDao.fetchCustomerInfoByEmailId(AESSecurity.encrypt(registrationParams
                .getEmail_id()));
        if (customer == null) {
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
            message =
                PropertyReader.resourceBundlesManager.getValueFromResourceBundle(
                    Constants.LANGUAGE, Constants.REGISTRATION_SUCCESS_MESSAGE);
            message =
                message.replace("$FIRST_NAME", customer.getFirstName()).replace("$EMAIL",
                    AESSecurity.decrypt(customer.getEmailAddress()));
          
            response.setCode(Constants.HTTP_STATUS_CODE_SUCCESS);
            response.setMessage(message);
            response.setStatus(Constants.RESPONSE_SUCCESS);
            sendEmail(customer);
          }
        } else if (customer != null & customer.getCustomerStatus() == Constants.STATUS_ACTIVE) {
          message =
              PropertyReader.resourceBundlesManager.getValueFromResourceBundle(Constants.LANGUAGE,
                  Constants.ERROR_CODE_520 + Constants._ERROR_MESSAGE);

          response.setCode(Constants.ERROR_CODE_520);
          response.setMessage(message);
          response.setStatus(Constants.RESPONSE_FAILURE);
          LOGGER.fatal("Trying to register with already existing email id ");
        } else if (customer != null & customer.getCustomerStatus() == Constants.STATUS_INACTIVE) {

          message =
              PropertyReader.resourceBundlesManager.getValueFromResourceBundle(Constants.LANGUAGE,
                  Constants.ERROR_CODE_521 + Constants._ERROR_MESSAGE);

          response.setCode(Constants.ERROR_CODE_521);
          response.setMessage(message);
          response.setStatus(Constants.RESPONSE_FAILURE);
          LOGGER.fatal("Customer has registered already but did not verify still");
          sendEmail(customer);
        }
      }
      responseString = Utils.frameResponse(response);
    } catch (Exception e) {
      message =
          PropertyReader.resourceBundlesManager.getValueFromResourceBundle(Constants.LANGUAGE,
              Constants.ERROR_CODE_500 + Constants._ERROR_MESSAGE);

      response.setCode(Constants.ERROR_CODE_500);
      response.setMessage(message);
      response.setStatus(Constants.RESPONSE_FAILURE);
      responseString = Utils.frameResponse(response);
      LOGGER.error("Exception occurred ::", e);
    }
    return responseString;
  }

  /**
   * @param customer
   * @throws Exception
   */
  private void sendEmail(Customer customer) throws Exception {
    EmailModel emailModel = new EmailModel();
    emailModel.setSubject(PropertyReader.resourceBundlesManager.getValueFromResourceBundle("en",
        "EMAIL_SUBJECT"));
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

  /**
   * @param customer
   * @throws Exception
   */
  private String getEmailTemplate(Customer customer) throws Exception {
    String emailTemplate =
        PropertyReader.resourceBundlesManager.getValueFromResourceBundle("en", "EMAIL_TEMPLATE");
    emailTemplate = emailTemplate.replace("$EMAIL_USER_NAME", customer.getFirstName());
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

  public void setRegistrationDao(RegistrationDao registrationDao) {
    this.registrationDao = registrationDao;
  }

  public void setAuthenticationDao(AuthenticationDao authenticationDao) {
    this.authenticationDao = authenticationDao;
  }



}
