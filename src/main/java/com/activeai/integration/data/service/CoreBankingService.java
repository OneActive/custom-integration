package com.activeai.integration.data.service;

import com.activeai.integration.banking.utils.PropertyUtil;
import com.activeai.integration.data.model.CoreBankingModel;
import com.activeai.integration.data.repository.CoreBankingRepository;
import com.activeai.integration.data.util.CoreBankingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service("coreBankingService")
public class CoreBankingService {

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
    if (CoreBankingUtil.isCacheEnabled()) {
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
}
