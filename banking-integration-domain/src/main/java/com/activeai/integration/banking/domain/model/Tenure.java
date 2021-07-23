package com.activeai.integration.banking.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public class Tenure {

  @JsonProperty("years")
  private String years;

  @JsonProperty("months")
  private String months;

  @JsonProperty("days")
  private String days;

  @JsonProperty("tenureFormatted")
  private String tenureFormatted;

  @Valid
  public String getYears() {
    return years;
  }

  public void setYears(String years) {
    this.years = years;
  }

  @Valid
  public String getMonths() {
    return months;
  }

  public void setMonths(String months) {
    this.months = months;
  }

  @Valid
  public String getDays() {
    return days;
  }

  public void setDays(String days) {
    this.days = days;
  }

  @Valid
  public String getTenureFormatted() {
    return tenureFormatted;
  }

  public void setTenureFormatted(String tenureFormatted) {
    this.tenureFormatted = tenureFormatted;
  }

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("Tenure{");
    sb.append("years='").append(years).append('\'');
    sb.append(", months='").append(months).append('\'');
    sb.append(", days='").append(days).append('\'');
    sb.append(", tenureFormatted='").append(tenureFormatted).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
