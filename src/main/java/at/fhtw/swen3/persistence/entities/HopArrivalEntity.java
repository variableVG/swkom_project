package at.fhtw.swen3.persistence.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.OffsetDateTime;

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

  @NotNull
  @Pattern(regexp = "^[A-Z]{4}\\d{1,4}$", message="The code must only consist of four capital letters and any number of digits between (1 - 4)")
  @JsonProperty("code")
  private String code;

  @NotNull
  @JsonProperty("description")
  private String description;

  @JsonProperty("dateTime")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @Valid
  private OffsetDateTime dateTime;

  @ManyToOne(optional = true, fetch = FetchType.LAZY)
  @JoinColumn(name = "parcel_id", nullable = true)
  private ParcelEntity parcel; //renamed this from parcelEntity to parcel, since the script didn't detect this field.


  @JsonProperty("visited")
  private boolean visited;

}

