package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.model.OneTimeTransferInput;
import com.activeai.integration.banking.model.Result;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OneTimeTransferResponse  extends Response{

  /* List of oneTimeTransferInput*/
  @JsonProperty("oneTimeTransferInputs")
  private List<OneTimeTransferInput> oneTimeTransferInputs = null;
  /**
   *
   * @return oneTimeTransferInputs
   */
  public List<OneTimeTransferInput> getOneTimeTransferInputs() {
    return oneTimeTransferInputs;
  }

  /**
   *
   * @param oneTimeTransferInputs
   */
  public void setOneTimeTransferInputs(List<OneTimeTransferInput> oneTimeTransferInputs) {
    this.oneTimeTransferInputs = oneTimeTransferInputs;
  }

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("OneTimeTransferResponse{");
    sb.append(", oneTimeTransferInputs=").append(oneTimeTransferInputs);
    sb.append('}');
    return sb.toString();
  }
}
