package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.domain.model.AtmBranch;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

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

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("atmBranches", atmBranches).toString();
  }
}
