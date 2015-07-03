package com.spanbee.service;


/**
 * @author sucheth.s
 *
 */

public interface PasswordService {
  
  /**
   * This method is called to send the password in the system to Email registered
   * @param emailId
   * @param request
   * @return
   * @throws Exception
   */ 
  public String forgotPassword(String emailId) throws Exception;

  /**
   * This method to reset the existing password in the system
   * @param oldPassword
   * @param newPassword 
   * @param request
   * @return
   * @throws Exception
   */
  public String resetPassword(String oldPassword, String newPassword) throws Exception;

}

