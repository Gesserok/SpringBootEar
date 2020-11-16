package org.example.multimodule.models.resource_show;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Qa {

    @JsonProperty("archival_timestamp")
    private String archivalTimestamp;

    private String created;

    private String format;
    @JsonProperty("openness_score")
    private Long opennessScore;
    @JsonProperty("openness_score_reason")
    private String opennessScoreReason;
    @JsonProperty("openness_score_reason_args")
    private String opennessScoreReasonArgs;
    @JsonProperty("resource_timestamp")
    private Object resourceTimestamp;

    private String updated;
}
