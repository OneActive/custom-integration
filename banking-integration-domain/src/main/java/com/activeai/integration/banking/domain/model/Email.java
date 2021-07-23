package com.activeai.integration.banking.domain.model;

import com.activeai.integration.banking.domain.constants.EmailTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * Email
 */
@Validated
public class Email {

  @JsonProperty("emailId")
  private String emailId;

  @JsonProperty("emailType")
  private EmailTypeEnum emailType;

  public String getEmailId() {
    return emailId;
  }

  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }

  public EmailTypeEnum getEmailType() {
    return emailType;
  }

  public void setEmailType(EmailTypeEnum emailType) {
    this.emailType = emailType;
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Email email = (Email) o;
    return Objects.equals(emailId, email.emailId) && emailType == email.emailType;
  }

  @Override public int hashCode() {
    return Objects.hash(emailId, emailType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Email {\n");

    sb.append("    emailId: ").append(toIndentedString(emailId)).append("\n");
    sb.append("    emailType: ").append(toIndentedString(emailType)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
