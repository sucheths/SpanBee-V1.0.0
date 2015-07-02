package com.spanbee.utils;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;

/**
 * @author sucheth.s
 *
 */

public class KeyGenerator {
  private static final Logger LOGGER = Logger.getLogger(KeyGenerator.class);
  private static String DEFAULT_DICTIONARY = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
  private static Object transactionIdLock =  new Object();
  
  private static int transactionId = 0;
  
  private static AtomicLong uniqueId = new AtomicLong(0) ;
  
  public static String getUniqueTransactionId(){
                  StringBuilder edrId = new StringBuilder();
                  if(transactionId > 10000){
                                  transactionId = 0;
                  }
                  synchronized (transactionIdLock) {
                                  edrId.append(System.currentTimeMillis()).append(transactionId);
                                  transactionId++;
                  }
                  return edrId.toString();
  }
  
  public static long getUniqueId(){
                  long value = uniqueId.incrementAndGet() ;
                  if (value <= 0) {
                                  value = 1 ;
                                  uniqueId.set(1) ;
                  }
       
                  return value ;
  }
  
  public static void main(String[] args) {
    System.out.println(getUniqueTransactionId());
  }
  
  
  public String generateSessionID(String ID) {
    
    byte[] result = null;
    String sessionId=null;
    try {
//        SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
        //generate a random number
        UUID random_uuid;
        random_uuid = generateUUID(ID);
        String randomNum = String.valueOf(random_uuid);

        //get its digest
        MessageDigest sha = MessageDigest.getInstance("MD5");
        result = sha.digest(randomNum.getBytes());
        sessionId=hexEncode(result);
         
    }catch (Exception ex) {
        LOGGER.error("Error while generating session id",ex);
    }
    return sessionId;
}

  private UUID generateUUID(String ID) {
   
    UUID id_one =null;
    try {

        Long long_time = System.nanoTime();
        id_one = UUID.nameUUIDFromBytes((ID.toString() + long_time.toString()).getBytes());
        
    } catch (Exception ex) {
      LOGGER.error("Error while generating session id",ex);
    }
    return id_one;
}
  private String hexEncode(byte[] aInput) {
    StringBuilder result =null;
    try {
         result = new StringBuilder();
        char[] digits = getRandomString(16).toCharArray();
        for (int idx = 0; idx < aInput.length; ++idx) {
            byte b = aInput[idx];
            result.append(digits[(b & 0xf0) >> 4]);
            result.append(digits[b & 0x0f]);
        }
        
    }  catch (Exception ex) {
      LOGGER.error("Error while generating session id",ex);
    }
    return result.toString();
}
  private String getRandomString(int size)  {
    String randomString = null;
    try {
      randomString = getRandomString(size, DEFAULT_DICTIONARY);
    } catch (Exception ex) {
      LOGGER.error("Error while generating session id",ex);
    }
    return randomString;
}

  public String getRandomString(int size, String dict)  {
    StringBuilder buffer = null;
    try {
        SecureRandom random = new SecureRandom();
        random.setSeed(System.currentTimeMillis());  //add additional seeding each time.
         buffer = new StringBuilder(size);
        int dictLength = dict.length();
        for (int j = 0; j < size; j++) {
            buffer.append(dict.charAt(random.nextInt(dictLength)));
        }
        
    }  catch (Exception ex) {
      LOGGER.error("Error while generating session id",ex);
    }
    return buffer.toString();
    
}

}

