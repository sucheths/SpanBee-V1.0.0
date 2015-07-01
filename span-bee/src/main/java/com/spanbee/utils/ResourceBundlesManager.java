package com.spanbee.utils;

import java.io.File;
import java.io.Serializable;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * @author sucheth.s
 *
 */

public class ResourceBundlesManager implements Serializable {
  

  
  private static final Logger LOGGER = Logger.getLogger(ResourceBundlesManager.class);

  /**
   * 
   */
  private static final long serialVersionUID = 6569187625858147327L;

  /** The rb hashmap. */
  private static HashMap<String, ResourceBundle> rbHashmap = new HashMap<String, ResourceBundle>();

  /** The base file name. */
  private String baseFileName = null;

  /**
   * Instantiates a new resource bundles manager.
   *
   * @param baseFileName the base file name
   */
  public ResourceBundlesManager(String baseFileName)
  {
      if(baseFileName!=null){
          this.baseFileName = baseFileName;
      }
  }
  
 

  /**
   * This method for retrieving value based on key supports i18n.
   * <p>
   *
   * @param language language for selecting proper ResourceBundle.
   * @param key key to retrieve value from ResourceBundle.
   * @return byte array containing the parsed values of the given string.
   * @throws Exception the exception
   */
  public String getValueFromResourceBundle(String language, String key) throws Exception
  {
      Locale locale = new Locale(language);
      ResourceBundle res = null;
      String rbMessage = null;
      try
      {
          res = rbHashmap.get(this.baseFileName + language);
          if (res == null)
          {
              res = ResourceBundle.getBundle(this.baseFileName, locale, new ResourceBundleReader());
              rbHashmap.put(this.baseFileName + language, res);
          }
          rbMessage = res.getString(key);
      } catch (Exception e)
      {
          throw e;
      }
      return rbMessage;
  }

  /**
   * The Class ResourceBundleReader.
   */
  private static class ResourceBundleReader extends ClassLoader
  {

      /* (non-Javadoc)
       * @see java.lang.ClassLoader#findResource(java.lang.String)
       */
      @Override
      protected URL findResource(String name)
      {

          try
          {
            
            LOGGER.info("File Name::" +name);
              File f = new File(name);
              if(LOGGER.isDebugEnabled())
              {
              LOGGER.debug("Finding:"+f.getName()+" in "+f.getAbsolutePath());
              }
              URI uri = f.toURI();
              return uri.toURL();
          } catch (Exception e)
          {
              e.printStackTrace();
          }
          return super.findResource(name);
      }
  }

  /**
   * The main method.
   *
   * @param args the arguments
   * @throws Exception the exception
   */
  public static void main(String[] args) throws Exception
  {
      ResourceBundlesManager resourceBundlesManager = new ResourceBundlesManager("E:\\tmp\\MyApp");
      System.out.println(resourceBundlesManager.getValueFromResourceBundle("en", "OKButtonLabel"));
      System.out.println(resourceBundlesManager.getValueFromResourceBundle("ja", "OKButtonLabel"));
      /*ResourceBundlesManager resourceBundlesManager = new ResourceBundlesManager("E://res/MyResource");
      System.out.println(resourceBundlesManager.getValueFromResourceBundle("en", "OK_TEXT"));
      System.out.println(resourceBundlesManager.getValueFromResourceBundle("ja", "OK_TEXT"));
      System.out.println(resourceBundlesManager.getValueFromResourceBundle("ak", "OK_TEXT"));

      resourceBundlesManager = new ResourceBundlesManager("E://res/YourResource");
      System.out.println(resourceBundlesManager.getValueFromResourceBundle("en", "OK_TEXT"));
      System.out.println(resourceBundlesManager.getValueFromResourceBundle("ja", "OK_TEXT"));
      System.out.println(resourceBundlesManager.getValueFromResourceBundle("ak", "OK_TEXT"));*/
  }

}

