package org.example.multimodule.models;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Table(name = "mvs_ukr_passports")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MVSUkrPassport {

    @Getter(onMethod = @__({@JsonGetter("D_NUMBER")}))
    @Setter(onMethod = @__({@JsonSetter("D_NUMBER")}))
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "d_number")
    private String dNumber;

    @Getter(onMethod = @__({@JsonGetter("D_SERIES")}))
    @Setter(onMethod = @__({@JsonSetter("D_SERIES")}))
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "d_series")
    private String dSeries;

    @Getter(onMethod = @__({@JsonGetter("D_STATUS")}))
    @Setter(onMethod = @__({@JsonSetter("D_STATUS")}))
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "d_status")
    private String dStatus;

    @Getter(onMethod = @__({@JsonGetter("D_TYPE")}))
    @Setter(onMethod = @__({@JsonSetter("D_TYPE")}))
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "d_type")
    private String dType;

    @Getter(onMethod = @__({@JsonGetter("ID")}))
    @Setter(onMethod = @__({@JsonSetter("ID")}))
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "external_id")
    private Long externalId;

    @Getter(onMethod = @__({@JsonGetter("INSERT_DATE")}))
    @Setter(onMethod = @__({@JsonSetter("INSERT_DATE")}))
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "insert_date")
    private String insertDate;

    @Getter(onMethod = @__({@JsonGetter("OVD")}))
    @Setter(onMethod = @__({@JsonSetter("OVD")}))
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "ovd")
    private String ovd;

    @Getter(onMethod = @__({@JsonGetter("THEFT_DATA")}))
    @Setter(onMethod = @__({@JsonSetter("THEFT_DATA")}))
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "theft_data")
    private String theftData;

}
