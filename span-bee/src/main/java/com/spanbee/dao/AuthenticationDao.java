package com.spanbee.dao;

import com.spanbee.entities.Customer;

public interface AuthenticationDao {
  
  public Customer fetchCustomerInfoByEmailId(String emailId);
  
  public boolean setCustomerSessionId(String sessionId, int customerId);

}
