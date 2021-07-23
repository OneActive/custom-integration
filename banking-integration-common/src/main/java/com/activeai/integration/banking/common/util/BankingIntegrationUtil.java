package com.activeai.integration.banking.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.MessageFormat;

/**
 * @author Manjunath H
 */
public class BankingIntegrationUtil {

  private static final String OBJECT_MAPPER_MSG_PATTERN = "{0} - {1} : {2}";
  private static ObjectMapper objectMapper;

  private BankingIntegrationUtil() {
    //Restrict object creation
  }

  /**
   * @return object mapper configured to do the required task
   */
  public static ObjectMapper getObjectMapper() {
    if (null == objectMapper) {
      objectMapper = new ObjectMapper();
      objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    return objectMapper;
  }

  /**
   * Write value as String using availed objectMapper
   *
   * @param o
   * @return
   */
  public static String writeValueAsString(Object o) {
    try {
      return getObjectMapper().writeValueAsString(o);
    } catch (JsonProcessingException e) {
      ApplicationLogger.logError(MessageFormat
          .format(OBJECT_MAPPER_MSG_PATTERN, null != o ? o.getClass().getName() : "NONE", "Something went wrong while Serialising!",
              e.getStackTrace()));
    }
    return null;
  }

  /**
   * Deserialize the String using availed objectMapper
   *
   * @param s
   * @param valueType
   * @param <T>
   * @return Object based on value type
   */
  public static <T> T readValue(String s,Class<T> valueType) {
    try {
      return getObjectMapper().readValue(s, valueType);
    } catch (JsonProcessingException e) {
      ApplicationLogger.logError(MessageFormat
          .format(OBJECT_MAPPER_MSG_PATTERN, valueType.getName(), "Something went wrong while De-Serialising!", e.getStackTrace()));
    } catch (IOException e) {
      ApplicationLogger.logError(e.getMessage(), BankingIntegrationUtil.class);
    }
    return null;
  }
}
