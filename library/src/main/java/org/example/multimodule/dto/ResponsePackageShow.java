package org.example.multimodule.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.multimodule.models.package_show.Result;

@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponsePackageShow {
    private String help;
    private Result result;
    private Boolean success;
}
