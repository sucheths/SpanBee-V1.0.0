package com.spanbee.dao;

import com.spanbee.entities.Customer;

public interface CommonDao {
  
  public Customer addCustomer(Customer customer);
  
  public Customer fetchCustomerByEmailId(String emailId);
  
  public boolean setCustomerSessionId(String sessionId, int customerId);

}
