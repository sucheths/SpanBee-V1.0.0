package com.spanbee.constants;

import java.text.SimpleDateFormat;

/**
 * @author sucheth.s
 * 
 */

public class Constants {
  public static final String DATE_FORMAT = "dd/MM/yyyy";
  static SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

  public static void main(String[] args) {

    String str = "2015-06-28T18:30:00.000Z";

    String newDate = str.substring(0, 10);
    System.out.println(newDate);
    
    String[] chunks= newDate.split("-",-1);
    System.out.println(chunks.length);
    String newDateString="";
    for(int i= chunks.length-1;i>=0;i--){
      System.out.println(chunks[i]);
      newDateString = newDateString + chunks[i];
          newDateString = newDateString + "-";
    }
    
    System.out.println(newDateString.substring(0, newDateString.length()-1));
  }

}
