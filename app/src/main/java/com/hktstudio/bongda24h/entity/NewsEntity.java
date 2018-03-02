package com.hktstudio.bongda24h.entity;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsEntity {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("originalLink")
    @Expose
    private String originalLink;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("postImage")
    @Expose
    private String postImage;
    @SerializedName("postTime")
    @Expose
    private long postTime;
    @SerializedName("createdDate")
    @Expose
    private long createdDate;
    @SerializedName("category")
    @Expose
    private CategoryEntity category;
    @SerializedName("lastModifyDate")
    @Expose
    private long lastModifyDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalLink() {
        return originalLink;
    }

    public void setOriginalLink(String originalLink) {
        this.originalLink = originalLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(int createdDate) {
        this.createdDate = createdDate;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public long getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(int lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}