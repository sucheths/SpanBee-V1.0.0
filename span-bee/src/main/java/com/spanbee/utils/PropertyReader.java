package com.spanbee.utils;

import java.io.File;

import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


/**
 * @author sucheth.s
 *
 */

public class PropertyReader {
 
  private static final Logger LOGGER = Logger.getLogger(PropertyReader.class);
  public static ResourceBundlesManager resourceBundlesManager = null;
  
  public static void loadLog4j(String log4jFile, ServletContextEvent sce) {
    try {
        String log4jFullPath = getFilePath(log4jFile, sce);
        LOGGER.debug("LOg4j full path:::" + log4jFullPath);
        PropertyConfigurator.configureAndWatch(log4jFullPath, 5000);
        LOGGER.debug("Absolute Path for Log file : " + log4jFullPath);
    } catch (Exception e) {
        e.printStackTrace();
    }
  }

  /**
   * @param log4jFile
   * @param sce
   * @return
   */
  private static String getFilePath(String fileName, ServletContextEvent sce) {
    String absPath = sce.getServletContext().getRealPath("/");
    LOGGER.debug("absPath::" + absPath);
    String filePath = null;
    if (absPath.endsWith(File.separator)) {
      filePath = absPath.trim() + "WEB-INF" + File.separator
                + fileName;
        System.out.println("Absolute path aftre removing file separator:::"+ absPath);
    } else {
      filePath = absPath.trim() + File.separator + "WEB-INF"+ File.separator + fileName;
    }
    return filePath;
  }

  public static void loadResourceBundle(String resourceName, ServletContextEvent sce){
        String resourceFilePath=getFilePath(resourceName, sce);
        
        if(resourceFilePath !=null)
        {
        resourceBundlesManager=  new ResourceBundlesManager(resourceFilePath);
        }
  }
  
}

