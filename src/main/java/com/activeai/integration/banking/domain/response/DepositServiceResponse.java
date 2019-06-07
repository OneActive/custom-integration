package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.constants.StatusEnum;
import com.activeai.integration.banking.model.Account;
import com.activeai.integration.banking.model.DepositPlan;
import com.activeai.integration.banking.model.Nominee;
import com.activeai.integration.banking.model.Result;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated public class DepositServiceResponse extends Response{



  /* tenure,amount,interest rate */
  @JsonProperty("depositPlans") private List<DepositPlan> depositPlans = null;

  /* deposit initiation status */
  @JsonProperty("status")
  private StatusEnum status=null;

  /* nominee of user selected */
  @JsonProperty("nominee") private Nominee nominee = null;

  /* maturity instruction might be re-invest */
  @JsonProperty("maturityInstruction") private List<String> maturityInstruction = null;

  /* Interest payable frequency */
  @JsonProperty("interestPayableFrequency") private List<String> interestPayableFrequency = null;


  /*Account number for FD*/
  @JsonProperty("FDAccountId") private String FDAccountId = null;

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

  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
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


  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("DepositServiceResponse{");
    sb.append(", status=").append(status);
    sb.append(", depositPlans=").append(depositPlans);
    sb.append(", nominee=").append(nominee);
    sb.append(", maturityInstruction=").append(maturityInstruction);
    sb.append(", interestPayableFrequency=").append(interestPayableFrequency);
    sb.append(", FDAccountId='").append(FDAccountId).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
