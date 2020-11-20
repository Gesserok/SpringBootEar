package org.example.multimodule.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
@Table(name = "migration_passports")
public class MigrationServiceUrkPassport extends Passport{

    @Column(name = "ovd_number")
    private String nn;
    @Column(name = "status")
    private String status;
    @Column(name = "series")
    private String series;
    @Column(name = "doc_num")
    private String number;
    @Column(name = "date_edit")
    private String dateEdit;
}
