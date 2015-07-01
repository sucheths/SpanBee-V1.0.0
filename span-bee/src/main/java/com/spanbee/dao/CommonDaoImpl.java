package com.spanbee.dao;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.spanbee.entities.Customer;
import com.spanbee.repositories.CustomerRepository;

@Service
public class CommonDaoImpl {


  private static Logger LOGGER = Logger.getLogger(RegistrationDaoImpl.class);


  @Resource
  private CustomerRepository customerRepository;


  public Customer addCustomer(Customer customer) {
    Customer cust = null;
    try {
      if (customer != null) {
        if (LOGGER.isInfoEnabled()) {
          LOGGER.info("Inside customer register method for customerId ::" + customer.getId());
        }
        cust = customerRepository.saveAndFlush(customer);

      }
    } catch (Exception e) {
      LOGGER.error("Exception Occured while registering the User :", e);
    }
    return cust;
  }

  public Customer fetchCustomerByEmailId(String emailId) {
    Customer cust = null;
    try {
      if (emailId != null) {
        if (LOGGER.isInfoEnabled()) {
          LOGGER.info("Inside fetchCustomerByEmailId method for emailId ::" + emailId);
        }
        cust = customerRepository.getCustomerInfoByEmailAddress(emailId);

        LOGGER.info("Returning customerInfo for ::" + cust);
      }
    } catch (Exception e) {
      LOGGER.error("Exception Occured while registering the User :", e);
    }
    return cust;
  }

}
