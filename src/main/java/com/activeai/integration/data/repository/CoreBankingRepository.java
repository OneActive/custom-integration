package com.activeai.integration.data.repository;

import com.activeai.integration.data.model.CoreBankingModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("coreBankingRepository")
public interface CoreBankingRepository extends CrudRepository<CoreBankingModel, String> {
}
