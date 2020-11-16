package org.example.multimodule.models.resource_show;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {

    private Archiver archiver;
    @JsonProperty("cache_last_updated")
    private Object cacheLastUpdated;
    @JsonProperty("cache_url")
    private Object cacheUrl;

    private String created;
    @JsonProperty("datastore_active")
    private Boolean datastoreActive;

    private String description;
    @JsonProperty("file_hash_sum")
    private String fileHashSum;

    private String format;

    private String hash;

    private String id;
    @JsonProperty("last_modified")
    private String lastModified;

    private String mimetype;
    @JsonProperty("mimetype_inner")
    private Object mimetypeInner;

    private String name;
    @JsonProperty("package_id")
    private String packageId;

    private Long position;

    private Qa qa;
    @JsonProperty("resource_revisions")
    private List<ResourceRevision> resourceRevisions;
    @JsonProperty("resource_type")
    private Object resourceType;
    @JsonProperty("revision_id")
    private String revisionId;

    private Long size;

    private String state;

    private String url;
    @JsonProperty("url_type")
    private String urlType;

    public Archiver getArchiver() {
        return archiver;
    }

    public void setArchiver(Archiver archiver) {
        this.archiver = archiver;
    }

    public Object getCacheLastUpdated() {
        return cacheLastUpdated;
    }

    public void setCacheLastUpdated(Object cacheLastUpdated) {
        this.cacheLastUpdated = cacheLastUpdated;
    }

    public Object getCacheUrl() {
        return cacheUrl;
    }

    public void setCacheUrl(Object cacheUrl) {
        this.cacheUrl = cacheUrl;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Boolean getDatastoreActive() {
        return datastoreActive;
    }

    public void setDatastoreActive(Boolean datastoreActive) {
        this.datastoreActive = datastoreActive;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileHashSum() {
        return fileHashSum;
    }

    public void setFileHashSum(String fileHashSum) {
        this.fileHashSum = fileHashSum;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public Object getMimetypeInner() {
        return mimetypeInner;
    }

    public void setMimetypeInner(Object mimetypeInner) {
        this.mimetypeInner = mimetypeInner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public Qa getQa() {
        return qa;
    }

    public void setQa(Qa qa) {
        this.qa = qa;
    }

    public List<ResourceRevision> getResourceRevisions() {
        return resourceRevisions;
    }

    public void setResourceRevisions(List<ResourceRevision> resourceRevisions) {
        this.resourceRevisions = resourceRevisions;
    }

    public Object getResourceType() {
        return resourceType;
    }

    public void setResourceType(Object resourceType) {
        this.resourceType = resourceType;
    }

    public String getRevisionId() {
        return revisionId;
    }

    public void setRevisionId(String revisionId) {
        this.revisionId = revisionId;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

}
