package com.anilax.customdesign.model.unsplash;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result implements Parcelable {
    public static final Creator<Result> CREATOR = new Creator<Result>() {
        public Result createFromParcel(Parcel parcel) {
            Result result = new Result();
            result.id = (String) parcel.readValue(String.class.getClassLoader());
            result.createdAt = (String) parcel.readValue(String.class.getClassLoader());
            result.updatedAt = (String) parcel.readValue(String.class.getClassLoader());
            result.width = (Integer) parcel.readValue(Integer.class.getClassLoader());
            result.height = (Integer) parcel.readValue(Integer.class.getClassLoader());
            result.color = (String) parcel.readValue(String.class.getClassLoader());
            result.likes = (Integer) parcel.readValue(Integer.class.getClassLoader());
            result.likedByUser = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            result.user = (User) parcel.readValue(User.class.getClassLoader());
            parcel.readList(result.currentUserCollections, Object.class.getClassLoader());
            result.urls = (Urls) parcel.readValue(Urls.class.getClassLoader());
            parcel.readList(result.categories, Category.class.getClassLoader());
            result.links = (Links) parcel.readValue(Links.class.getClassLoader());
            return result;
        }

        public Result[] newArray(int i) {
            return new Result[i];
        }
    };

    @SerializedName("categories")
    @Expose
    public List<Category> categories = null;

    @SerializedName("color")
    @Expose
    public String color;

    @SerializedName("created_at")
    @Expose
    public String createdAt;

    @SerializedName("current_user_collections")
    @Expose
    public List<Object> currentUserCollections = null;

    @SerializedName("height")
    @Expose
    public Integer height;

    @SerializedName("id")
    @Expose


    public String id;

    @SerializedName("liked_by_user")
    @Expose
    public Boolean likedByUser;

    @SerializedName("likes")
    @Expose
    public Integer likes;

    @SerializedName("links")
    @Expose
    public Links links;

    @SerializedName("updated_at")
    @Expose
    public String updatedAt;

    @SerializedName("urls")
    @Expose
    public Urls urls;

    @SerializedName("user")
    @Expose
    public User user;

    @SerializedName("width")
    @Expose
    public Integer width;

    public int describeContents() {
        return 0;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String str) {
        this.createdAt = str;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(String str) {
        this.updatedAt = str;
    }

    public Integer getWidth() {
        return this.width;
    }

    public void setWidth(Integer num) {
        this.width = num;
    }

    public Integer getHeight() {
        return this.height;
    }

    public void setHeight(Integer num) {
        this.height = num;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String str) {
        this.color = str;
    }

    public Integer getLikes() {
        return this.likes;
    }

    public void setLikes(Integer num) {
        this.likes = num;
    }

    public Boolean getLikedByUser() {
        return this.likedByUser;
    }

    public void setLikedByUser(Boolean bool) {
        this.likedByUser = bool;
    }


    public void setUser(User user2) {
        this.user = user2;
    }

    public List<Object> getCurrentUserCollections() {
        return this.currentUserCollections;
    }

    public void setCurrentUserCollections(List<Object> list) {
        this.currentUserCollections = list;
    }


    public void setUrls(Urls urls2) {
        this.urls = urls2;
    }


    public Links getLinks() {
        return this.links;
    }

    public void setLinks(Links links2) {
        this.links = links2;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.id);
        parcel.writeValue(this.createdAt);
        parcel.writeValue(this.updatedAt);
        parcel.writeValue(this.width);
        parcel.writeValue(this.height);
        parcel.writeValue(this.color);
        parcel.writeValue(this.likes);
        parcel.writeValue(this.likedByUser);
        parcel.writeValue(this.user);
        parcel.writeList(this.currentUserCollections);
        parcel.writeValue(this.urls);
        parcel.writeList(this.categories);
        parcel.writeValue(this.links);
    }
}
