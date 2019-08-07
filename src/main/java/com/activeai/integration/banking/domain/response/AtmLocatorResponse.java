package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.model.AtmBranch;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AtmLocatorResponse extends Response {

  @JsonProperty("atmBranches")
  List<AtmBranch> atmBranches;

  public List<AtmBranch> getAtmBranches() {
    return atmBranches;
  }

  public void setAtmBranches(List<AtmBranch> atmBranches) {
    this.atmBranches = atmBranches;
  }

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("LocatorResponse{");
    sb.append("atmBranches=").append(atmBranches);
    sb.append('}');
    return sb.toString();
  }
}
