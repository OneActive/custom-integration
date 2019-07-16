package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.model.EMIPlan;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ConvertEMIResponse extends Response {

  @JsonProperty("listofEMIPlan")
  private List<EMIPlan> listofEMIPlans;

  public List<EMIPlan> getListofEMIPlans() {
    return listofEMIPlans;
  }

  public void setListofEMIPlans(List<EMIPlan> listofEMIPlans) {
    this.listofEMIPlans = listofEMIPlans;
  }

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("ConvertEMIResponse{");
    sb.append("listofEMIPlan=").append(listofEMIPlans);
    sb.append('}');
    return sb.toString();
  }
}
