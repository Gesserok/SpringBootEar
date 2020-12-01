package org.example.multimodule.searcher.models;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.example.multimodule.models.MVSUkrPassport;
import org.example.multimodule.models.MigrationServiceUrkPassport;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@ToString
@RequiredArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"extId", "dSeries", "dNumber", "dStatus", "theftData", "insertDate", "ovd", "dType"})
public class SOAPPassport {

    private String dSeries;

    private String dNumber;

    private String dStatus;

    private String dType;

    private String extId;

    private String insertDate;

    private String ovd;

    private String theftData;

    public SOAPPassport(MigrationServiceUrkPassport migrationServiceUrkPassport) {
        this.dSeries = migrationServiceUrkPassport.getSeries();
        this.dNumber = migrationServiceUrkPassport.getNumber();
        this.dStatus = migrationServiceUrkPassport.getStatus();
        this.dType = "БД Міграційної служби";
        String nn = migrationServiceUrkPassport.getNn();
        this.extId = nn;
        this.ovd = nn.substring(0, nn.length() - 8);
        this.theftData = migrationServiceUrkPassport.getDateEdit();
        this.insertDate = migrationServiceUrkPassport.getDateEdit();
    }

    public SOAPPassport(MVSUkrPassport mvsUkrPassport) {
        this.dSeries = mvsUkrPassport.getDSeries();
        this.dNumber = mvsUkrPassport.getDNumber();
        this.dStatus = mvsUkrPassport.getDStatus();
        this.dType = mvsUkrPassport.getDType();
        this.extId = mvsUkrPassport.getExternalId();
        this.ovd = mvsUkrPassport.getOvd();
        this.theftData = mvsUkrPassport.getTheftData();
        this.insertDate = mvsUkrPassport.getInsertDate();
    }
}
