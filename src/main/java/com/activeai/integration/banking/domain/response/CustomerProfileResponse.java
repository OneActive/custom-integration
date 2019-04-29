package com.activeai.integration.banking.domain.response;

import java.util.Objects;
import javax.validation.Valid;
import com.activeai.integration.banking.model.CustomerProfile;
import com.activeai.integration.banking.model.Result;
import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * CustomerProfileResponse
 */
@Validated
public class CustomerProfileResponse {

  @JsonProperty("result")
  private Result result = null;

  @JsonProperty("customerProfile")
  private CustomerProfile customerProfile = null;

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

  /**
   * Get customerProfile
   * 
   * @return customerProfile
   **/
  @Valid
  public CustomerProfile getCustomerProfile() {
    return customerProfile;
  }

  public void setCustomerProfile(CustomerProfile customerProfile) {
    this.customerProfile = customerProfile;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomerProfileResponse customerProfileResponse = (CustomerProfileResponse) o;
    return Objects.equals(this.result, customerProfileResponse.result)
        && Objects.equals(this.customerProfile, customerProfileResponse.customerProfile);
  }

  @Override
  public int hashCode() {
    return Objects.hash(result, customerProfile);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CustomerProfileResponse {\n");

    sb.append("    result: ").append(toIndentedString(result)).append("\n");
    sb.append("    customerProfile: ").append(toIndentedString(customerProfile)).append("\n");
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

