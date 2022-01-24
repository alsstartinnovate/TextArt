package com.startup.textart.model.unsplash;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Collection implements Parcelable {
    public static final Creator<Collection> CREATOR = new Creator<Collection>() {
        public Collection createFromParcel(Parcel parcel) {
            Collection collection = new Collection();
            collection.id = (Integer) parcel.readValue(Integer.class.getClassLoader());
            collection.title = (String) parcel.readValue(String.class.getClassLoader());
            collection.description = (String) parcel.readValue(String.class.getClassLoader());
            collection.publishedAt = (String) parcel.readValue(String.class.getClassLoader());
            collection.updatedAt = (String) parcel.readValue(String.class.getClassLoader());
            collection.curated = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            collection.totalPhotos = (Integer) parcel.readValue(Integer.class.getClassLoader());
            collection._private = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            collection.shareKey = (String) parcel.readValue(String.class.getClassLoader());
            collection.coverPhoto = (CoverPhoto) parcel.readValue(CoverPhoto.class.getClassLoader());
            collection.user = (User) parcel.readValue(User.class.getClassLoader());
            collection.links = (Links) parcel.readValue(Links.class.getClassLoader());
            return collection;
        }

        public Collection[] newArray(int i) {
            return new Collection[i];
        }
    };

    @SerializedName("private")
    @Expose
    public Boolean _private;

    @SerializedName("cover_photo")
    @Expose
    public CoverPhoto coverPhoto;

    @SerializedName("curated")
    @Expose
    public Boolean curated;

    @SerializedName("description")
    @Expose
    public String description;

    @SerializedName("id")
    @Expose


    public Integer id;

    @SerializedName("links")
    @Expose
    public Links links;

    @SerializedName("published_at")
    @Expose
    public String publishedAt;

    @SerializedName("share_key")
    @Expose
    public String shareKey;

    @SerializedName("title")
    @Expose
    public String title;

    @SerializedName("total_photos")
    @Expose
    public Integer totalPhotos;

    @SerializedName("updated_at")
    @Expose
    public String updatedAt;

    @SerializedName("user")
    @Expose
    public User user;

    public int describeContents() {
        return 0;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer num) {
        this.id = num;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }


    public String getPublishedAt() {
        return this.publishedAt;
    }

    public void setPublishedAt(String str) {
        this.publishedAt = str;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(String str) {
        this.updatedAt = str;
    }




    public Boolean getPrivate() {
        return this._private;
    }

    public void setPrivate(Boolean bool) {
        this._private = bool;
    }

    public String getShareKey() {
        return this.shareKey;
    }


    public void setUser(User user2) {
        this.user = user2;
    }

    public Links getLinks() {
        return this.links;
    }

    public void setLinks(Links links2) {
        this.links = links2;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.id);
        parcel.writeValue(this.title);
        parcel.writeValue(this.description);
        parcel.writeValue(this.publishedAt);
        parcel.writeValue(this.updatedAt);
        parcel.writeValue(this.curated);
        parcel.writeValue(this.totalPhotos);
        parcel.writeValue(this._private);
        parcel.writeValue(this.shareKey);
        parcel.writeValue(this.coverPhoto);
        parcel.writeValue(this.user);
        parcel.writeValue(this.links);
    }
}
