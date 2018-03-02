package com.hktstudio.bongda24h.entity;

/**
 * Created by Hai on 27/02/2018.
 */

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class NewsDetailEntity {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("postImage")
    @Expose
    private String postImage;
    @SerializedName("postTime")
    @Expose
    private long postTime;
    @SerializedName("originalLink")
    @Expose
    private String originalLink;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("createdDate")
    @Expose
    private long createdDate;
    @SerializedName("lastModifyDate")
    @Expose
    private long lastModifyDate;
    @SerializedName("category")
    @Expose
    private CategoryEntity category;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public long getPostTime() {
        return postTime;
    }

    public void setPostTime(int postTime) {
        this.postTime = postTime;
    }

    public String getOriginalLink() {
        return originalLink;
    }

    public void setOriginalLink(String originalLink) {
        this.originalLink = originalLink;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(int createdDate) {
        this.createdDate = createdDate;
    }

    public long getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(int lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
