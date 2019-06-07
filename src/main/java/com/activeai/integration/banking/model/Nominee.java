package com.activeai.integration.banking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

@Validated
public class Nominee {

  /** The nominee name. */
  @JsonProperty("nomineeName")
  private String nomineeName;

  /** The nominee address. */
  @JsonProperty("nomineeAddress")
  private String nomineeAddress;

  /** The nominee relationship. */
  @JsonProperty("nomineeRelationship")
  private String nomineeRelationship;

  /** if mentioned nominee is minore*/
  @JsonProperty("minor")
  private boolean minor = false;

  /** if nominee is minor the his guardian name */
  @JsonProperty("guardian")
  private String guardian;

  /**
   * Gets the nominee name.
   *
   * @return the nomineeName
   */
  public String getNomineeName() {
    return nomineeName;
  }

  /**
   * Sets the nominee name.
   *
   * @param nomineeName the nomineeName to set
   */
  public void setNomineeName(String nomineeName) {
    this.nomineeName = nomineeName;
  }

  /**
   * Gets the nominee address.
   *
   * @return the nomineeAddress
   */
  public String getNomineeAddress() {
    return nomineeAddress;
  }

  /**
   * Sets the nominee address.
   *
   * @param nomineeAddress the nomineeAddress to set
   */
  public void setNomineeAddress(String nomineeAddress) {
    this.nomineeAddress = nomineeAddress;
  }

  /**
   * Gets the nominee relationship.
   *
   * @return the nomineeRelationship
   */
  public String getNomineeRelationship() {
    return nomineeRelationship;
  }

  /**
   * Sets the nominee relationship.
   *
   * @param nomineeRelationship the nomineeRelationship to set
   */
  public void setNomineeRelationship(String nomineeRelationship) {
    this.nomineeRelationship = nomineeRelationship;
  }

  public boolean isMinor() {
    return minor;
  }

  public void setMinor(boolean minor) {
    this.minor = minor;
  }

  public String getGuardian() {
    return guardian;
  }

  public void setGuardian(String guardian) {
    this.guardian = guardian;
  }

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("Nominee{");
    sb.append("nomineeName='").append(nomineeName).append('\'');
    sb.append(", nomineeAddress='").append(nomineeAddress).append('\'');
    sb.append(", nomineeRelationship='").append(nomineeRelationship).append('\'');
    sb.append(", minor=").append(minor);
    sb.append(", guardian='").append(guardian).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
