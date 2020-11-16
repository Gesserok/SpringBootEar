package org.example.multimodule.models.resource_show;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResourceRevision {

    @JsonProperty("file_hash_sum")
    private String fileHashSum;

    private String format;

    private String mimetype;

    private String name;
    @JsonProperty("resource_created")
    private String resourceCreated;

    private Long size;

    private String url;

}
