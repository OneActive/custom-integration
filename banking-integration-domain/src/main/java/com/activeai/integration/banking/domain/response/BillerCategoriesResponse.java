package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.domain.model.BillerCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * BillerCategoriesResponse
 */
@Validated
public class BillerCategoriesResponse extends Response {

  @JsonProperty("billerCategories")
  @Valid
  private List<BillerCategory> billerCategories = null;

  /**
   * Get billerCategories
   *
   * @return billerCategories
   **/
  @Valid
  public List<BillerCategory> getBillerCategories() {
    return billerCategories;
  }

  public void setBillerCategories(List<BillerCategory> billerCategories) {
    this.billerCategories = billerCategories;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("billerCategories", billerCategories).toString();
  }
}

