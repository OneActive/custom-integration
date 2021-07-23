package com.activeai.integration.banking.domain.request;

import com.activeai.integration.banking.domain.model.Payee;
import com.activeai.integration.banking.domain.model.User;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * Payees request object
 */
public class PayeesRequest extends User {

  /**
   * other details of payee if needed
   */
  private Payee payee;

  /**
   * Get payees associated with these accountIds
   */
  private List<String> accountIds;

  public Payee getPayee() {
    return payee;
  }

  public void setPayee(Payee payee) {
    this.payee = payee;
  }

  public List<String> getAccountIds() {
    return accountIds;
  }

  public void setAccountIds(List<String> accountIds) {
    this.accountIds = accountIds;
  }

  @Override public String toString() {
    return new ToStringBuilder(this).append("payee", payee).append("accountIds", accountIds).toString();
  }
}
