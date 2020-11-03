package org.example.multimodule.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "tp_regions")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "region_gen")
    @SequenceGenerator(name = "region_gen", sequenceName = "gen_region_id", allocationSize = 1)
    @JsonIgnore
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @JsonProperty(value = "passports_list")
    @ElementCollection
    @CollectionTable(name = "tp_passports", joinColumns = @JoinColumn(name = "region_id"))
    private List<Passport> passports;

    @JsonProperty("resource_id")
    @Column(name = "resource_id")
    private String resourceId;

    @JsonProperty("resource_revision_url")
    @Column(name = "resource_revision_url")
    private String resourceRevisionUrl;

}
