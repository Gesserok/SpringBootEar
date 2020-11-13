
package org.example.multimodule.dto;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Qa {

    @SerializedName("archival_timestamp")
    private String archivalTimestamp;
    @Expose
    private String created;
    @Expose
    private String format;
    @SerializedName("openness_score")
    private Long opennessScore;
    @SerializedName("openness_score_reason")
    private String opennessScoreReason;
    @SerializedName("openness_score_reason_args")
    private String opennessScoreReasonArgs;
    @SerializedName("resource_timestamp")
    private Object resourceTimestamp;
    @Expose
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
