package at.fhtw.swen3.persistence.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * HopArrival
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonTypeName("hopArrival")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-18T13:20:22.807446Z[Etc/UTC]")
@Entity
@Setter
@Getter
@Table(name="HopArrival")
public class HopArrivalEntity {

  @Id
  @JoinColumn //added because it was an issue, no idea if it is needed
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @NotNull @Pattern(regexp = "^[A-Z]{4}\\d{1,4}$")
  @JsonProperty("code")
  private String code;

  @NotNull
  @JsonProperty("description")
  private String description;

  @JsonProperty("dateTime")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @NotNull @Valid
  private OffsetDateTime dateTime;

  @ManyToOne(optional = true, fetch = FetchType.LAZY)
  @JoinColumn(name = "parcel_id", nullable = true)
  private ParcelEntity parcel; //renamed this from parcelEntity to parcel, since the script didn't detect this field.

}

