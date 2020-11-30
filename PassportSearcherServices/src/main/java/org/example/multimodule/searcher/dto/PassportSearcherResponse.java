package org.example.multimodule.searcher.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.multimodule.searcher.models.SOAPPassport;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PassportSearcherResponse {
    private String code;
    private String errorMessage;

    @XmlElement(name = "Passport")
    private List<SOAPPassport> passports;
}
