
package org.example.multimodule.models.package_show;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tag {

    @JsonProperty("display_name")
    private String displayName;

    private String id;

    private String name;

    private String state;
    @JsonProperty("vocabulary_id")
    private Object vocabularyId;


}
