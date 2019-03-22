package com.activeai.integration.banking.model;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * BillerCategory
 */
@Validated
public class BillerCategory {

  @JsonProperty("categoryId")
  private String categoryId = null;

  @JsonProperty("categoryName")
  private String categoryName = null;

  public BillerCategory categoryId(String categoryId) {
    this.categoryId = categoryId;
    return this;
  }

  /**
   * Biller Category ID
   * 
   * @return categoryId
   **/
  public String getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }

  public BillerCategory categoryName(String categoryName) {
    this.categoryName = categoryName;
    return this;
  }

  /**
   * Biller Category
   * 
   * @return categoryName
   **/
  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BillerCategory billerCategory = (BillerCategory) o;
    return Objects.equals(this.categoryId, billerCategory.categoryId) && Objects.equals(this.categoryName, billerCategory.categoryName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(categoryId, categoryName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BillerCategory {\n");

    sb.append("    categoryId: ").append(toIndentedString(categoryId)).append("\n");
    sb.append("    categoryName: ").append(toIndentedString(categoryName)).append("\n");
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

