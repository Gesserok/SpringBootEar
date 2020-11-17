package org.example.multimodule.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

@NoArgsConstructor
@Embeddable
@Table(name = "passports")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MVSUkrPassport extends Passport {

    @JsonProperty(value = "D_NUMBER")
    @JsonIgnore
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "d_number")
    private String dNumber;

    @JsonProperty(value = "D_SERIES")
    @JsonIgnore
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "d_series")
    private String dSeries;

    @JsonProperty(value = "D_STATUS")
    @JsonIgnore
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "d_status")
    private String dStatus;

    @JsonProperty(value = "D_TYPE")
    @JsonIgnore
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "d_type")
    private String dType;

    @JsonProperty(value = "ID")
    @JsonIgnore
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "external_id")
    private Long externalId;

    @JsonProperty(value = "INSERT_DATE")
    @JsonIgnore
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "insert_date")
    private String insertDate;

    @JsonProperty(value = "OVD")
    @JsonIgnore
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "ovd")
    private String ovd;

    @JsonProperty(value = "THEFT_DATA")
    @JsonIgnore
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "theft_data")
    private String theftData;

}
