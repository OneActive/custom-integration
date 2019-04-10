package com.activeai.integration.banking.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * BillerCategoriesResponse
 */
@Validated
public class BillerCategoriesResponse {

  @JsonProperty("result")
  private Result result = null;

  @JsonProperty("billerCategories")
  @Valid
  private List<BillerCategory> billerCategories = null;

  /**
   * Get result
   * 
   * @return result
   **/
  @Valid
  public Result getResult() {
    return result;
  }

  public void setResult(Result result) {
    this.result = result;
  }

  public BillerCategoriesResponse addBillerCategoriesItem(BillerCategory billerCategoriesItem) {
    if (this.billerCategories == null) {
      this.billerCategories = new ArrayList<BillerCategory>();
    }
    this.billerCategories.add(billerCategoriesItem);
    return this;
  }

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
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BillerCategoriesResponse billerCategoriesList = (BillerCategoriesResponse) o;
    return Objects.equals(this.result, billerCategoriesList.result)
        && Objects.equals(this.billerCategories, billerCategoriesList.billerCategories);
  }

  @Override
  public int hashCode() {
    return Objects.hash(result, billerCategories);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BillerCategoriesList {\n");

    sb.append("    result: ").append(toIndentedString(result)).append("\n");
    sb.append("    billerCategories: ").append(toIndentedString(billerCategories)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

