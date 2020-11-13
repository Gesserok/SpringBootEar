
package org.example.multimodule.dto;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Archiver {

    @SerializedName("cache_filepath")
    private String cacheFilepath;
    @SerializedName("cache_url")
    private String cacheUrl;
    @Expose
    private String created;
    @Expose
    private Object etag;
    @SerializedName("failure_count")
    private Long failureCount;
    @SerializedName("first_failure")
    private Object firstFailure;
    @Expose
    private String hash;
    @SerializedName("is_broken")
    private Boolean isBroken;
    @SerializedName("is_broken_printable")
    private String isBrokenPrintable;
    @SerializedName("last_modified")
    private Object lastModified;
    @SerializedName("last_success")
    private String lastSuccess;
    @Expose
    private String mimetype;
    @Expose
    private String reason;
    @SerializedName("resource_timestamp")
    private String resourceTimestamp;
    @Expose
    private Long size;
    @Expose
    private String status;
    @SerializedName("status_id")
    private Long statusId;
    @Expose
    private String updated;
    @SerializedName("url_redirected_to")
    private String urlRedirectedTo;

    public String getCacheFilepath() {
        return cacheFilepath;
    }

    public void setCacheFilepath(String cacheFilepath) {
        this.cacheFilepath = cacheFilepath;
    }

    public String getCacheUrl() {
        return cacheUrl;
    }

    public void setCacheUrl(String cacheUrl) {
        this.cacheUrl = cacheUrl;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Object getEtag() {
        return etag;
    }

    public void setEtag(Object etag) {
        this.etag = etag;
    }

    public Long getFailureCount() {
        return failureCount;
    }

    public void setFailureCount(Long failureCount) {
        this.failureCount = failureCount;
    }

    public Object getFirstFailure() {
        return firstFailure;
    }

    public void setFirstFailure(Object firstFailure) {
        this.firstFailure = firstFailure;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Boolean getIsBroken() {
        return isBroken;
    }

    public void setIsBroken(Boolean isBroken) {
        this.isBroken = isBroken;
    }

    public String getIsBrokenPrintable() {
        return isBrokenPrintable;
    }

    public void setIsBrokenPrintable(String isBrokenPrintable) {
        this.isBrokenPrintable = isBrokenPrintable;
    }

    public Object getLastModified() {
        return lastModified;
    }

    public void setLastModified(Object lastModified) {
        this.lastModified = lastModified;
    }

    public String getLastSuccess() {
        return lastSuccess;
    }

    public void setLastSuccess(String lastSuccess) {
        this.lastSuccess = lastSuccess;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getResourceTimestamp() {
        return resourceTimestamp;
    }

    public void setResourceTimestamp(String resourceTimestamp) {
        this.resourceTimestamp = resourceTimestamp;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getUrlRedirectedTo() {
        return urlRedirectedTo;
    }

    public void setUrlRedirectedTo(String urlRedirectedTo) {
        this.urlRedirectedTo = urlRedirectedTo;
    }

}
