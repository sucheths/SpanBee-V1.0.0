package com.spanbee.utils;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author sucheth.s
 *
 */

public class KeyGenerator {
  
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

}

