package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.model.OneTimeTransferInput;
import com.activeai.integration.banking.model.Result;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OneTimeTransferResponse  {
  @JsonProperty("result")
  private Result result;

  /* List of oneTimeTransferInput*/
  @JsonProperty("oneTimeTransferInputs")
  private List<OneTimeTransferInput> oneTimeTransferInputs;

  /**
   *
   * @return result
   */
  public Result getResult() {
    return result;
  }

  /**
   *
   * @param result
   */
  public void setResult(Result result) {
    this.result = result;
  }

  /**
   *
   * @return oneTimeTransferInputs
   */
  public List<OneTimeTransferInput> getOneTimeTransferInputList() {
    return oneTimeTransferInputs;
  }

  /**
   *
   * @param oneTimeTransferInputs
   */
  public void setOneTimeTransferInputList(List<OneTimeTransferInput> oneTimeTransferInputs) {
    this.oneTimeTransferInputs = oneTimeTransferInputs;
  }

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("OneTimeTransferResponse{");
    sb.append("result=").append(result);
    sb.append(", oneTimeTransferInputList=").append(oneTimeTransferInputs);
    sb.append('}');
    return sb.toString();
  }
}
