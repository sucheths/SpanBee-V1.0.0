package com.spanbee.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spanbee.entities.Customer;



/**
 * @author sucheth.s
 *
 */

public interface CustomerRepository extends JpaRepository<Customer, String>{

  public Customer getCustomerInfoByEmailAddress(String emailId);

}

