
package org.example.multimodule.dto;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ResourceRevision {

    @SerializedName("file_hash_sum")
    private String fileHashSum;
    @Expose
    private String format;
    @Expose
    private String mimetype;
    @Expose
    private String name;
    @SerializedName("resource_created")
    private String resourceCreated;
    @Expose
    private Long size;
    @Expose
    private String url;

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

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceCreated() {
        return resourceCreated;
    }

    public void setResourceCreated(String resourceCreated) {
        this.resourceCreated = resourceCreated;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
