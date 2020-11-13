package org.example.multimodule.models.resource_show;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Archiver {

    @JsonProperty("cache_filepath")
    private String cacheFilepath;
    @JsonProperty("cache_url")
    private String cacheUrl;

    private String created;

    private Object etag;
    @JsonProperty("failure_count")
    private Long failureCount;
    @JsonProperty("first_failure")
    private Object firstFailure;

    private String hash;
    @JsonProperty("is_broken")
    private Boolean isBroken;
    @JsonProperty("is_broken_printable")
    private String isBrokenPrintable;
    @JsonProperty("last_modified")
    private Object lastModified;
    @JsonProperty("last_success")
    private String lastSuccess;

    private String mimetype;

    private String reason;
    @JsonProperty("resource_timestamp")
    private String resourceTimestamp;

    private Long size;

    private String status;
    @JsonProperty("status_id")
    private Long statusId;

    private String updated;
    @JsonProperty("url_redirected_to")
    private String urlRedirectedTo;
}
