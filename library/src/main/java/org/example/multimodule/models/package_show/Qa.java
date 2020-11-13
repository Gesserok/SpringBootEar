
package org.example.multimodule.models.package_show;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
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

    public String getArchivalTimestamp() {
        return archivalTimestamp;
    }

    public void setArchivalTimestamp(String archivalTimestamp) {
        this.archivalTimestamp = archivalTimestamp;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Long getOpennessScore() {
        return opennessScore;
    }

    public void setOpennessScore(Long opennessScore) {
        this.opennessScore = opennessScore;
    }

    public String getOpennessScoreReason() {
        return opennessScoreReason;
    }

    public void setOpennessScoreReason(String opennessScoreReason) {
        this.opennessScoreReason = opennessScoreReason;
    }

    public String getOpennessScoreReasonArgs() {
        return opennessScoreReasonArgs;
    }

    public void setOpennessScoreReasonArgs(String opennessScoreReasonArgs) {
        this.opennessScoreReasonArgs = opennessScoreReasonArgs;
    }

    public Object getResourceTimestamp() {
        return resourceTimestamp;
    }

    public void setResourceTimestamp(Object resourceTimestamp) {
        this.resourceTimestamp = resourceTimestamp;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

}
