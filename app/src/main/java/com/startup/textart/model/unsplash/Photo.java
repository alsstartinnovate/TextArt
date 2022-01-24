package com.startup.textart.model.unsplash;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Photo implements Parcelable {
    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        public Photo createFromParcel(Parcel parcel) {
            Photo photo = new Photo();
            photo.id = (String) parcel.readValue(String.class.getClassLoader());
            photo.createdAt = (String) parcel.readValue(String.class.getClassLoader());
            photo.updatedAt = (String) parcel.readValue(String.class.getClassLoader());
            photo.width = (Integer) parcel.readValue(Integer.class.getClassLoader());
            photo.height = (Integer) parcel.readValue(Integer.class.getClassLoader());
            photo.color = (String) parcel.readValue(String.class.getClassLoader());
            photo.downloads = (Integer) parcel.readValue(Integer.class.getClassLoader());
            photo.likes = (Integer) parcel.readValue(Integer.class.getClassLoader());
            photo.likedByUser = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            photo.exif = (Exif) parcel.readValue(Exif.class.getClassLoader());
            photo.location = (Location) parcel.readValue(Location.class.getClassLoader());
            parcel.readList(photo.currentUserCollections, Collection.class.getClassLoader());
            photo.urls = (Urls) parcel.readValue(Urls.class.getClassLoader());
            parcel.readList(photo.categories, Category.class.getClassLoader());
            photo.links = (Links) parcel.readValue(Links.class.getClassLoader());
            photo.user = (User) parcel.readValue(User.class.getClassLoader());
            return photo;
        }

        public Photo[] newArray(int i) {
            return new Photo[i];
        }
    };

    @SerializedName("categories")
    @Expose
    public List<Category> categories = new ArrayList<>();

    @SerializedName("color")
    @Expose
    public String color;

    @SerializedName("created_at")
    @Expose
    public String createdAt;

    @SerializedName("current_user_collections")
    @Expose
    public List<Collection> currentUserCollections = new ArrayList<>();

    @SerializedName("downloads")
    @Expose
    public Integer downloads;

    @SerializedName("exif")
    @Expose
    public Exif exif;

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

    @SerializedName("location")
    @Expose
    public Location location;

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

    public Photo() {
    }

    public Photo(String str, String str2, String str3, Integer num, Integer num2, String str4, Integer num3, Integer num4, Boolean bool, Exif exif2, Location location2, List<Collection> list, Urls urls2, List<Category> list2, Links links2, User user2) {
        this.id = str;
        this.createdAt = str2;
        this.updatedAt = str3;
        this.width = num;
        this.height = num2;
        this.color = str4;
        this.downloads = num3;
        this.likes = num4;
        this.likedByUser = bool;
        this.exif = exif2;
        this.location = location2;
        this.currentUserCollections = list;
        this.urls = urls2;
        this.categories = list2;
        this.links = links2;
        this.user = user2;
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


    public Integer getDownloads() {
        return this.downloads;
    }

    public void setDownloads(Integer num) {
        this.downloads = num;
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


    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location2) {
        this.location = location2;
    }



    public List<Collection> getCurrentUserCollections() {
        return this.currentUserCollections;
    }

    public void setCurrentUserCollections(List<Collection> list) {
        this.currentUserCollections = list;
    }


    public Urls getUrls() {
        return this.urls;
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


    public void setUser(User user2) {
        this.user = user2;
    }



    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.id);
        parcel.writeValue(this.createdAt);
        parcel.writeValue(this.updatedAt);
        parcel.writeValue(this.width);
        parcel.writeValue(this.height);
        parcel.writeValue(this.color);
        parcel.writeValue(this.downloads);
        parcel.writeValue(this.likes);
        parcel.writeValue(this.likedByUser);
        parcel.writeValue(this.exif);
        parcel.writeValue(this.location);
        parcel.writeList(this.currentUserCollections);
        parcel.writeValue(this.urls);
        parcel.writeList(this.categories);
        parcel.writeValue(this.links);
        parcel.writeValue(this.user);
    }
}
