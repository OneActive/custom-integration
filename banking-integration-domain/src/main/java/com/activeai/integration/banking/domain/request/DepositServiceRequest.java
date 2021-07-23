package com.activeai.integration.banking.domain.request;

import com.activeai.integration.banking.domain.model.Account;
import com.activeai.integration.banking.domain.model.DepositPlan;
import com.activeai.integration.banking.domain.model.Nominee;
import com.activeai.integration.banking.domain.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated public class DepositServiceRequest extends User {


  /* tenure,amount,interest rate */
  @JsonProperty("depositPlans") private List<DepositPlan> depositPlans;

  /* nominee of user selected */
  @JsonProperty("nominee") private Nominee nominee;

  /* maturity instruction might be re-invest */
  @JsonProperty("maturityInstruction") private List<String> maturityInstruction;

  /* Interest payable frequency */
  @JsonProperty("interestPayableFrequency") private List<String> interestPayableFrequency;

  @JsonProperty("jointAccount") private boolean jointAccount = false;

  /*Account number for FD*/
  @JsonProperty("FDAccountId") private String FDAccountId;

  /* where deposit amount need to be credited after closing deposit account*/
  @JsonProperty("creditableAccounts") private Account creditableAccounts;

  /* where deposit amount need to be credited after closing deposit account*/
  @JsonProperty("debitableAccounts") private Account debitableAccounts;

  @JsonProperty("remarks") private String remarks;

  /**
   * @return depositPlans
   */
  @Valid public List<DepositPlan> getDepositPlans() {
    return depositPlans;
  }

  /**
   * @param depositPlans
   */
  public void setDepositPlans(List<DepositPlan> depositPlans) {
    this.depositPlans = depositPlans;
  }

  /**
   * @return nominee
   */
  @Valid public Nominee getNominee() {
    return nominee;
  }

  /**
   * @param nominee
   */
  public void setNominee(Nominee nominee) {
    this.nominee = nominee;
  }

  /**
   * @return maturityInstruction
   */
  @Valid public List<String> getMaturityInstruction() {
    return maturityInstruction;
  }

  /**
   * @param maturityInstruction
   */
  public void setMaturityInstruction(List<String> maturityInstruction) {
    this.maturityInstruction = maturityInstruction;
  }

  /**
   * @return interestPayableFrequency
   */
  @Valid public List<String> getInterestPayableFrequency() {
    return interestPayableFrequency;
  }

  /**
   * @param interestPayableFrequency
   */
  public void setInterestPayableFrequency(List<String> interestPayableFrequency) {
    this.interestPayableFrequency = interestPayableFrequency;
  }

  /**
   * @return jointAccount
   */
  public boolean isJointAccount() {
    return jointAccount;
  }

  /**
   * @param jointAccount
   */
  public void setJointAccount(boolean jointAccount) {
    this.jointAccount = jointAccount;
  }

  /**
   * @return FDAccountId
   */
  @Valid public String getFDAccountId() {
    return FDAccountId;
  }

  /**
   * @param FDAccountId
   */
  public void setFDAccountId(String FDAccountId) {
    this.FDAccountId = FDAccountId;
  }

  /**
   * @return creditableAccounts
   */
  @Valid public Account getCreditableAccounts() {
    return creditableAccounts;
  }

  /**
   * @param creditableAccounts
   */
  public void setCreditableAccounts(Account creditableAccounts) {
    this.creditableAccounts = creditableAccounts;
  }

  /**
   * @return debitableAccounts
   */
  @Valid public Account getDebitableAccounts() {
    return debitableAccounts;
  }

  /**
   * @param debitableAccounts
   */
  public void setDebitableAccounts(Account debitableAccounts) {
    this.debitableAccounts = debitableAccounts;
  }

  /**
   * @return remarks
   */
  @Valid public String getRemarks() {
    return remarks;
  }

  /**
   * @param remarks
   */
  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }


  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("DepositServiceRequest{");
    sb.append("depositPlans=").append(depositPlans);
    sb.append(", nominee=").append(nominee);
    sb.append(", maturityInstruction=").append(maturityInstruction);
    sb.append(", interestPayableFrequency=").append(interestPayableFrequency);
    sb.append(", jointAccount=").append(jointAccount);
    sb.append(", FDAccountId='").append(FDAccountId).append('\'');
    sb.append(", creditableAccounts=").append(creditableAccounts);
    sb.append(", debitableAccounts=").append(debitableAccounts);
    sb.append(", remarks='").append(remarks).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
