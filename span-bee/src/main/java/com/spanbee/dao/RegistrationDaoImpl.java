package com.spanbee.dao;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.spanbee.entities.Customer;
import com.spanbee.repositories.CustomerRepository;



/**
 * @author sucheth.s
 *
 */
@Service
public class RegistrationDaoImpl implements RegistrationDao {

   private static Logger LOGGER = Logger.getLogger(RegistrationDaoImpl.class);


  @Resource
  private CustomerRepository customerRepository;
  
  @Transactional
  @Override
  public Customer register(Customer customer) {
    Customer cust = null;
    try {
      if (customer != null) {
        if (LOGGER.isInfoEnabled()) {
          LOGGER.info("Inside customer register method for customerId ::"
              + customer.getId());
        }
         cust= customerRepository.saveAndFlush(customer);
        
      }
    } catch (Exception e) {
      LOGGER.error("Exception Occured while registering the User :", e);
    }
    return cust;
  }

 


}

