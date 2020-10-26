package com.activeai.integration.data.service;

import com.activeai.integration.data.model.CoreBankingModel;
import com.activeai.integration.data.repository.CoreBankingRepository;
import com.activeai.integration.data.util.CoreBankingUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoreBankingServiceTest {

  @MockBean private CoreBankingRepository coreBankingRepository;
  @Autowired private CoreBankingService coreBankingService;
  @MockBean private CoreBankingModel coreBankingModel;

  public static String CUSTOMER_ID = "1234567";
  public static String REDIS_KEY = CoreBankingUtil.getRedisKey(CUSTOMER_ID);

  @Test
  public void getCoreBankingModelFromCache() {
    coreBankingModel.setId(REDIS_KEY);
    Mockito.when(coreBankingRepository.findOne(REDIS_KEY)).thenReturn(coreBankingModel);
    assertEquals(coreBankingModel.getId(), coreBankingService.getCoreBankingModel(CUSTOMER_ID).getId());
  }

  @Test
  public void getEmptyCoreBankingModelIfNotPresentInCache() {
    Mockito.when(coreBankingRepository.findOne(REDIS_KEY)).thenReturn(null);
    assertEquals(REDIS_KEY, coreBankingService.getCoreBankingModel(CUSTOMER_ID).getId());
  }

  @Test
  public void saveCoreBankingModel() {
    coreBankingModel.setId(REDIS_KEY);
    coreBankingRepository.save(coreBankingModel);
    Mockito.verify(coreBankingRepository, Mockito.times(1)).save(coreBankingModel);
  }

  @Test
  public void deleteCoreBankingModelOfCustomer() {
    coreBankingModel.setId(REDIS_KEY);
    coreBankingRepository.delete(coreBankingModel);
    coreBankingService.deleteCoreBankingModelOfCustomer(CUSTOMER_ID);
    Mockito.verify(coreBankingRepository, Mockito.times(1)).delete(coreBankingModel);
  }

  @Test
  public void resetAll() {
    coreBankingModel.setId(REDIS_KEY);
    coreBankingService.resetAll();
    Mockito.verify(coreBankingRepository, Mockito.times(1)).deleteAll();
  }
}
