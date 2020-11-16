
package org.example.multimodule.models.package_show;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class Result {

    private Archiver archiver;

    @JsonProperty("title_chosen")
    private String titleChosen;

    private String author;
    @JsonProperty("author_email")
    private String authorEmail;
    @JsonProperty("creator_user_id")
    private String creatorUserId;

    private List<Object> groups;

    private String id;

    private Boolean isopen;

    private String language;
    @JsonProperty("license_id")
    private String licenseId;
    @JsonProperty("license_title")
    private String licenseTitle;
    @JsonProperty("license_url")
    private String licenseUrl;

    private String maintainer;
    @JsonProperty("maintainer_email")
    private String maintainerEmail;
    @JsonProperty("metadata_created")
    private String metadataCreated;
    @JsonProperty("metadata_modified")
    private String metadataModified;

    private String name;

    private String notes;
    @JsonProperty("num_resources")
    private Long numResources;
    @JsonProperty("num_tags")
    private Long numTags;

    private Organization organization;
    @JsonProperty("owner_org")
    private String ownerOrg;
    @JsonProperty("private")
    private Boolean isPrivate;
    @JsonProperty("purpose_of_collecting_information")
    private String purposeOfCollectingInformation;

    private Qa qa;
    @JsonProperty("relationships_as_object")
    private List<Object> relationshipsAsObject;
    @JsonProperty("relationships_as_subject")
    private List<Object> relationshipsAsSubject;

    private List<Resource> resources;
    @JsonProperty("revision_id")
    private String revisionId;

    private String state;
    @JsonProperty("tag_string")
    private String tagString;

    private List<Tag> tags;

    private String title;

    private String type;
    @JsonProperty("update_frequency")
    private String updateFrequency;

    private Object url;

    private String version;

}
