package com.spanbee.dao;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.spanbee.entities.Customer;
import com.spanbee.repositories.CustomerRepository;


/**
 * @author sucheth.s
 * 
 */

public class CommonDaoImpl {

  private static Logger LOGGER = Logger.getLogger(CommonDaoImpl.class);

  @Resource
  private CustomerRepository customerRepository;

  public boolean addCustomer(Customer customer) {
    boolean custFlag = false;
    if(LOGGER.isInfoEnabled()){
      LOGGER.info("Entered into addCustomer method");
    }
    try {
      if (customer != null) {
        Customer cust = customerRepository.saveAndFlush(customer);
        if (cust != null) {
          custFlag = true;
          if(LOGGER.isInfoEnabled()){
            LOGGER.info("Customer has been added successfully with id ::"+cust.getId());
          }
        }
      }
    } catch (Exception e) {
      LOGGER.error("Exception occurred while saving customer details::", e);
    }
    return custFlag;
  }

}
