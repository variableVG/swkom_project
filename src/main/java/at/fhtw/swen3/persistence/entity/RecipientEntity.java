package at.fhtw.swen3.persistence.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Recipient
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonTypeName("recipient")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-18T13:20:22.807446Z[Etc/UTC]")
@Entity
@Table(name="Recipient")
public class RecipientEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @Pattern(regexp = "[a-zA-ZÄÖÜäöüß]+", message = "username must have only upper & lowercase letters")
  @NotNull(message = "name cannot be null")
  @NotBlank(message = "name cannot be blank")
  @JsonProperty("name")
  private String name;

  @Pattern(regexp = "[a-zA-Z0-9ÄÖÜäöüß / ]+", message = "streetname must have only upper, lowercase letters, Numbers and /")
  @NotBlank(message = "streetname cannot be blank")
  @NotNull(message = "streetname cannot be null")
  @JsonProperty("street")
  private String street;

  @JsonProperty("postalCode")
  @Pattern(regexp = "[A0-9-]+", message = "username must have only upper & lowercase letters")
  @NotNull(message = "PostalCode cannot be null")
  @Size(min = 4, max = 6, message = "A valid PostalCode must contain (A-, 4 digits, 0000-9999) ")
  private String postalCode;


  @Pattern(regexp = "[a-zA-ZÄÖÜäöüß]+", message = "cityname must have only upper & lowercase letters")
  @NotNull(message = "cityname cannot be null")
  @NotBlank(message = "cityname cannot be blank")
  @JsonProperty("city")
  private String city;

  @NotNull(message = "countryname cannot be null")
  @JsonProperty("country")
  private String country;


  public RecipientEntity name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of person or company.
   * @return name
  */
  @NotNull 
  @Schema(name = "name", description = "Name of person or company.", required = true)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public RecipientEntity street(String street) {
    this.street = street;
    return this;
  }

  /**
   * Street
   * @return street
  */
  @NotNull 
  @Schema(name = "street", description = "Street", required = true)
  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public RecipientEntity postalCode(String postalCode) {
    this.postalCode = postalCode;
    return this;
  }

  /**
   * Postalcode
   * @return postalCode
  */
  @NotNull 
  @Schema(name = "postalCode", description = "Postalcode", required = true)
  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public RecipientEntity city(String city) {
    this.city = city;
    return this;
  }

  /**
   * City
   * @return city
  */
  @NotNull 
  @Schema(name = "city", description = "City", required = true)
  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public RecipientEntity country(String country) {
    this.country = country;
    return this;
  }

  /**
   * Country
   * @return country
  */
  @NotNull 
  @Schema(name = "country", description = "Country", required = true)
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RecipientEntity recipientEntity = (RecipientEntity) o;
    return Objects.equals(this.name, recipientEntity.name) &&
        Objects.equals(this.street, recipientEntity.street) &&
        Objects.equals(this.postalCode, recipientEntity.postalCode) &&
        Objects.equals(this.city, recipientEntity.city) &&
        Objects.equals(this.country, recipientEntity.country);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, street, postalCode, city, country);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Recipient {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    street: ").append(toIndentedString(street)).append("\n");
    sb.append("    postalCode: ").append(toIndentedString(postalCode)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

