package org.example.multimodule.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Embeddable
@Table(name = "mvs_ukr_passports")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MVSUkrPassport {

    @JsonProperty("D_NUMBER")
    @JsonIgnore
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "d_number")
    private String dNumber;

    @JsonProperty("D_SERIES")
    @JsonIgnore
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "d_series")
    private String dSeries;

    @JsonProperty("D_STATUS")
    @JsonIgnore
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "d_status")
    private String dStatus;

    @JsonProperty("D_TYPE")
    @JsonIgnore
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "d_type")
    private String dType;

    @JsonProperty("ID")
    @JsonIgnore
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "external_id")
    private Long externalId;

    @JsonProperty("INSERT_DATE")
    @JsonIgnore
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "insert_date")
    private String insertDate;

    @JsonProperty("OVD")
    @JsonIgnore
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "ovd")
    private String ovd;

    @JsonProperty("THEFT_DATA")
    @JsonIgnore
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "theft_data")
    private String theftData;

}
