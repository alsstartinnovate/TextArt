package com.startup.textart.model.unsplash;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CoverPhoto implements Parcelable {
    public static final Creator<CoverPhoto> CREATOR = new Creator<CoverPhoto>() {
        public CoverPhoto createFromParcel(Parcel parcel) {
            CoverPhoto coverPhoto = new CoverPhoto();
            coverPhoto.id = (String) parcel.readValue(String.class.getClassLoader());
            coverPhoto.width = (Integer) parcel.readValue(Integer.class.getClassLoader());
            coverPhoto.height = (Integer) parcel.readValue(Integer.class.getClassLoader());
            coverPhoto.color = (String) parcel.readValue(String.class.getClassLoader());
            coverPhoto.likes = (Integer) parcel.readValue(Integer.class.getClassLoader());
            coverPhoto.likedByUser = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            coverPhoto.user = (User) parcel.readValue(User.class.getClassLoader());
            coverPhoto.urls = (Urls) parcel.readValue(Urls.class.getClassLoader());
            parcel.readList(coverPhoto.categories, Category.class.getClassLoader());
            coverPhoto.links = (Links) parcel.readValue(Links.class.getClassLoader());
            return coverPhoto;
        }

        public CoverPhoto[] newArray(int i) {
            return new CoverPhoto[i];
        }
    };

    @SerializedName("categories")
    @Expose
    public List<Category> categories = new ArrayList<>();

    @SerializedName("color")
    @Expose
    public String color;

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

    public CoverPhoto() {
    }


    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
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
        parcel.writeValue(this.width);
        parcel.writeValue(this.height);
        parcel.writeValue(this.color);
        parcel.writeValue(this.likes);
        parcel.writeValue(this.likedByUser);
        parcel.writeValue(this.user);
        parcel.writeValue(this.urls);
        parcel.writeList(this.categories);
        parcel.writeValue(this.links);
    }
}
