package com.spanbee.service;


import org.apache.log4j.Logger;

import com.spanbee.constants.Constants;
import com.spanbee.dao.CommonDao;
import com.spanbee.entities.Customer;
import com.spanbee.model.EmailModel;
import com.spanbee.service.email.SendRegistrationEmailThread;
import com.spanbee.utils.AESSecurity;
import com.spanbee.utils.PropertyReader;
import com.spanbee.utils.Utils;

/**
 * @author sucheth.s
 * 
 */

public class PasswordServiceImpl implements PasswordService {

  private CommonDao commonDao;
  private static final Logger LOGGER = Logger.getLogger(PasswordServiceImpl.class);

  @Override
  public String forgotPassword(String emailId) throws Exception {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("**********Inside forgotPassword Method Of Service Layer**********");
      LOGGER.info("Email ID : " + emailId);
    }

    String responseString = null;
    String message = null;

    try {
      if (emailId != null && !emailId.isEmpty()) {
        //Fetch the customer details for specified EmailId
        Customer customer = commonDao.fetchCustomerByEmailId(AESSecurity.encrypt(emailId));
        
        if(customer != null && customer.getRegistration_type() == Constants.NORMAL_LOGIN){
          message = PropertyReader.resourceBundlesManager.getValueFromResourceBundle(Constants.LANGUAGE,
                  Constants.FORGOTPASSWORD_SUCCESS_MESSAGE);
          message =
                  message.replace("$FIRST_NAME", customer.getFirstName()).replace("$EMAIL",AESSecurity.decrypt(customer.getEmailAddress()));
          responseString =
                  Utils.frameResponse(Constants.HTTP_STATUS_CODE_SUCCESS,
                      Constants.RESPONSE_SUCCESS, message, "");
          sendEmail(customer);
        }
        else if(customer != null && customer.getRegistration_type() == Constants.GOOGLE_LOGIN) {
          message = PropertyReader.resourceBundlesManager.getValueFromResourceBundle(Constants.LANGUAGE,
              Constants.ERROR_CODE_504 + Constants._ERROR_MESSAGE);
          responseString =
              Utils.frameResponse(Constants.ERROR_CODE_504,
                  Constants.RESPONSE_FAILURE, message, "");
          LOGGER.fatal("Trying to send password mail to already existing customer");
        }
        else if(customer != null && customer.getRegistration_type() == Constants.FACEBOOK_LOGIN) {
          message = PropertyReader.resourceBundlesManager.getValueFromResourceBundle(Constants.LANGUAGE,
              Constants.ERROR_CODE_505 + Constants._ERROR_MESSAGE);
          responseString =
              Utils.frameResponse(Constants.ERROR_CODE_505,
                  Constants.RESPONSE_FAILURE, message, "");
          LOGGER.fatal("Trying to send password mail to already existing customer");
        }
        else {
          message = PropertyReader.resourceBundlesManager.getValueFromResourceBundle(Constants.LANGUAGE,
              Constants.ERROR_CODE_502 + Constants._ERROR_MESSAGE);
          responseString =
              Utils.frameResponse(Constants.ERROR_CODE_502,
                  Constants.RESPONSE_FAILURE, message, "");
          LOGGER.fatal("Trying to send password mail to already existing customer");
        }
      } 
      else {
        message = PropertyReader.resourceBundlesManager.getValueFromResourceBundle(Constants.LANGUAGE,
            Constants.ERROR_CODE_503 + Constants._ERROR_MESSAGE);
        responseString =
            Utils.frameResponse(Constants.ERROR_CODE_503,
                Constants.RESPONSE_FAILURE, message, "");
        LOGGER.fatal("Trying to send password mail to already existing customer");
      }
    } catch (Exception e) {
    	message =PropertyReader.resourceBundlesManager.getValueFromResourceBundle(Constants.LANGUAGE,
            Constants.ERROR_CODE_500+Constants._ERROR_MESSAGE);
        responseString =
            Utils.frameResponse(Constants.HTTP_STATUS_CODE_FAILURE,
                Constants.RESPONSE_FAILURE,message, "");
        LOGGER.error("Exception occurred ::", e);
     }
    return responseString;
  }
  
  @Override
  public String resetPassword(String oldPassword, String newPassword) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * @param customer
   * @throws Exception
   */
  private void sendEmail(Customer customer) throws Exception {
    EmailModel emailModel = new EmailModel();
    emailModel.setSubject(PropertyReader.resourceBundlesManager
        .getValueFromResourceBundle("en","EMAIL_SUBJECT"));
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
    String emailTemplate =PropertyReader.resourceBundlesManager.getValueFromResourceBundle("en", "PASSWORD_EMAIL_TEMPLATE");
    emailTemplate =
        emailTemplate.replace("$EMAIL_USER_NAME",customer.getFirstName());
    emailTemplate =
        emailTemplate.replace("$EMAIL_TITLE",
            PropertyReader.resourceBundlesManager.getValueFromResourceBundle("en", "EMAIL_TITLE"));
    emailTemplate =
        emailTemplate
            .replace("$EMAIL_MESSAGE", PropertyReader.resourceBundlesManager
                .getValueFromResourceBundle("en", "PASSWORD_EMAIL_MESSAGE"));
    emailTemplate =
        emailTemplate.replace("$EMAIL_THANKS_MESSAGE", PropertyReader.resourceBundlesManager
            .getValueFromResourceBundle("en", "EMAIL_THANKS_MESSAGE"));
    emailTemplate =
        emailTemplate.replace("$PASSWORD", AESSecurity.decrypt(customer.getPassword()));
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

  public void setCommonDao(CommonDao commonDao) {
    this.commonDao = commonDao;
  }

}
