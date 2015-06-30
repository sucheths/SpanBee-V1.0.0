package com.spanbee.dao;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.spanbee.entities.Customer;
import com.spanbee.repositories.CustomerRepository;
import com.spanbee.service.RegistrationService;


/**
 * @author sucheth.s
 *
 */
@Service
public class RegistrationDaoImpl {

  private static Logger LOGGER = Logger.getLogger(RegistrationDaoImpl.class);


  @Resource
  private CustomerRepository customerRepository;
  
  public boolean register(Customer customer) {

    boolean registerFlag = false;
    try {
      if (customer != null) {
        if (LOGGER.isInfoEnabled()) {
          LOGGER.info("Inside customer register method for customerId ::"
              + customer.getId());
        }
        Customer cust= customerRepository.saveAndFlush(customer);

        if (cust != null) {
          registerFlag = true;
        } 
      }
    } catch (Exception e) {
      LOGGER.error("Exception Occured :", e);
    }
    return registerFlag;
  }

 


}

