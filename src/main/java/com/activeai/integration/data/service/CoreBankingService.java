package com.activeai.integration.data.service;

import com.activeai.integration.banking.utils.PropertyUtil;
import com.activeai.integration.data.model.CoreBankingModel;
import com.activeai.integration.data.repository.CoreBankingRepository;
import com.activeai.integration.data.util.CoreBankingUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@Service("coreBankingService")
public class CoreBankingService {

  public static final String API_DATE_PATTERN = "yyyy-MM-dd";

  @Autowired
  @Qualifier("coreBankingRepository")
  protected CoreBankingRepository coreBankingRepository;
  @Autowired
  protected PropertyUtil propertyUtil;

  /**
   * Fetch CoreBankingModel from Redis if null then new Object of CoreBankingModel initialised
   *
   * @param customerId
   * @return CoreBankingModel
   */
  public CoreBankingModel getCoreBankingModel(String customerId) {
    String redisKey = CoreBankingUtil.getRedisKey(customerId);
    CoreBankingModel coreBankingModel = coreBankingRepository.findOne(redisKey);
    if (Objects.isNull(coreBankingModel)) {
      coreBankingModel = new CoreBankingModel();
      coreBankingModel.setId(redisKey);
    }
    return coreBankingModel;
  }

  /**
   * Save CoreBankingModel in Redis
   *
   * @param coreBankingModel
   */
  public void saveCoreBankingModel(CoreBankingModel coreBankingModel) {
    if (CoreBankingUtil.isCacheEnabled) {
      coreBankingRepository.save(coreBankingModel);
    }
  }

  /**
   * Delete CoreBankingModel from cache for specific User
   *
   * @param customerId
   */
  public void deleteCoreBankingModelOfCustomer(String customerId){
    String redisKey = CoreBankingUtil.getRedisKey(customerId);
    coreBankingRepository.delete(redisKey);
  }

  public void resetAll(){
    coreBankingRepository.deleteAll();
  }

  /**
   * Generate Transaction stub date
   *
   * @param startInclusive
   * @param endExclusive
   * @return
   */
  public static LocalDate getRandomDate(LocalDate startInclusive, LocalDate endExclusive) {
    long startEpochDay = startInclusive.toEpochDay();
    long endEpochDay = endExclusive.toEpochDay();
    long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);

    return LocalDate.ofEpochDay(randomDay);
  }

  /**
   * Get Local date in required string format
   *
   * @param date
   * @return
   */
  public static String getLocalDateAsString(LocalDate date, String pattern) {
    if (StringUtils.isNotEmpty(pattern)) {
      return date.format(DateTimeFormatter.ofPattern(pattern).withResolverStyle(ResolverStyle.STRICT));
    }
    return date.format(DateTimeFormatter.ofPattern(API_DATE_PATTERN).withResolverStyle(ResolverStyle.STRICT));
  }
}
