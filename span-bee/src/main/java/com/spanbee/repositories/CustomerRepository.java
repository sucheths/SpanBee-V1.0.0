package com.spanbee.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.spanbee.entities.Customer;



/**
 * @author sucheth.s
 *
 */

public interface CustomerRepository extends JpaRepository<Customer, String>{
  
  @Modifying
  @Query("update Customer customer set customer.customerStatus = ?1 where customer.uniqueId = ?2")
  int setCustomerStatus(byte customerStatus, String uniqueId);

  public Customer getCustomerInfoByEmailAddress(String emailId);
  
  @Modifying
  @Query("update Customer customer set customer.sessionId = ?1 where customer.id = ?2")
  int setCustomerSessionId(String sessionId, int customerId);


}

