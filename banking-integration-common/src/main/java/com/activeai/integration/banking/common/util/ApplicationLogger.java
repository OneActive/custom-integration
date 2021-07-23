package com.activeai.integration.banking.common.util;

import lombok.extern.log4j.Log4j2;
import java.text.MessageFormat;

@Log4j2
public class ApplicationLogger {

  private static final String MESSAGE_WITH_PREFIX = "{0} - {1}";

  public static void logError(String message, Object object) {
    log.error(message, object);
  }

  public static void logInfo(String message, Object object, Class<?> clazz) {
    if (log.isInfoEnabled()) {
      log.info(MessageFormat.format(MESSAGE_WITH_PREFIX, clazz.getSimpleName(), message), object);
    }
  }

  public static void logError(String message) {
    log.error(message);
  }

  public static void logInfo(String message, Class<?> clazz) {
    if (log.isInfoEnabled()) {
      log.info(MessageFormat.format(MESSAGE_WITH_PREFIX, clazz.getSimpleName(), message));
    }
  }

  public static void logWarn(String message, Class<?> clazz) {
    if (log.isWarnEnabled()) {
      log.warn(MessageFormat.format(MESSAGE_WITH_PREFIX, clazz.getSimpleName(), message));
    }
  }

  public static void logWarn(String message, Object object, Class<?> clazz) {
    if (log.isWarnEnabled()) {
      log.warn(MessageFormat.format(MESSAGE_WITH_PREFIX, clazz.getSimpleName(), message), object);
    }
  }

  private ApplicationLogger() {
    //private constructor
  }

}
