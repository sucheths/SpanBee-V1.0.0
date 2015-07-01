/**
 * 
 * @author sbandi
 */
package com.spanbee.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

/**
 * Aes encryption
 */
public class AESSecurity {

  private static SecretKeySpec secretKeySpec;
  private static Cipher cipher;
  private static final String encryptingKey = "SpanBee";
  private static final String decryptingKey = "SpanBee";
  private static Logger LOGGER = Logger.getLogger(AESSecurity.class);

  public static void setMySQLAESKey(final String key, final String encoding) {
    try {
      if (LOGGER.isInfoEnabled()) {
        LOGGER.info("Entered into AES key generation method ");
      }
      final byte[] finalKey = new byte[16];
      int i = 0;
      if(key != null && encoding != null){
        for (byte b : key.getBytes(encoding))
          finalKey[i++ % 16] ^= b;
        cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        secretKeySpec = new SecretKeySpec(finalKey, "AES");
      }
    } catch (UnsupportedEncodingException e) {
      LOGGER.error("Exception occurred :", e);
    } catch (Exception e) {
      LOGGER.error("Exception occurred :", e);
    }
  }


  public static byte[] encrypt(byte[] strToEncrypt) {

    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("Entered into encrypt method" );
    }
    byte[] encryptedText = null;
    try {
      if (strToEncrypt != null) {
        setMySQLAESKey(encryptingKey, "UTF-8");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        encryptedText = cipher.doFinal(strToEncrypt);
        LOGGER.info("Encrypted Text : " + encryptedText);
      }
    } catch (Exception e) {
      System.out.println("err:" + e.getLocalizedMessage());
      LOGGER.error("Exception occurred while encrypting data :", e);
    }
    return encryptedText;
  }

  public static byte[] decrypt(byte[] strToDecrypt) {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("Entered into decrypt method ::");
    }
    byte[] decryptedText = null;
    try {
      if (strToDecrypt != null && decryptingKey != null) {
        setMySQLAESKey(decryptingKey, "UTF-8");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        decryptedText = cipher.doFinal(strToDecrypt);
      }
    } catch (Exception e) {
      LOGGER.error("Exception occurred while decrypting data :", e);
    }
    return decryptedText;
  }

  public static SecretKeySpec getSecretKeySpec() {
    return secretKeySpec;
  }

  public static void setSecretKeySpec(SecretKeySpec secretKeySpec) {
    AESSecurity.secretKeySpec = secretKeySpec;
  }
}
