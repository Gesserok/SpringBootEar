package org.example.multimodule.models;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@NoArgsConstructor
@Embeddable
@Table(name = "passports")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Passport {

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

    public String getDNumber() {
        return dNumber;
    }

    public void setDNumber(String dNumber) {
        this.dNumber = dNumber;
    }

    public String getDSeries() {
        return dSeries;
    }

    public void setDSeries(String dSeries) {
        this.dSeries = dSeries;
    }

    public String getdStatus() {
        return dStatus;
    }

    public void setDStatus(String dStatus) {
        this.dStatus = dStatus;
    }

    public String getDType() {
        return dType;
    }

    public void setDType(String dType) {
        this.dType = dType;
    }

    public Long getExternalId() {
        return externalId;
    }

    public void setExternalId(Long externalId) {
        this.externalId = externalId;
    }

    public String getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(String insertDate) {
        this.insertDate = insertDate;
    }

    public String getOvd() {
        return ovd;
    }

    public void setOvd(String ovd) {
        this.ovd = ovd;
    }

    public String getTheftData() {
        return theftData;
    }

    public void setTheftData(String theftData) {
        this.theftData = theftData;
    }
}
