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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MocksApplication.class, initializers = {MocksApplication.class})
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
    Mockito.when(coreBankingRepository.findById(REDIS_KEY)).thenReturn(Optional.of(coreBankingModel));
    assertEquals(coreBankingModel.getId(), coreBankingService.getCoreBankingModel(CUSTOMER_ID).getId());
  }

  @Test
  public void getEmptyCoreBankingModelIfNotPresentInCache() {
    Mockito.when(coreBankingRepository.findById(REDIS_KEY)).thenReturn(Optional.empty());
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
