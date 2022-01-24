package com.startup.textart.model.unsplash;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentUserCollection implements Parcelable {
    public static final Creator<CurrentUserCollection> CREATOR = new Creator<CurrentUserCollection>() {
        public CurrentUserCollection createFromParcel(Parcel parcel) {
            return new CurrentUserCollection(parcel);
        }

        public CurrentUserCollection[] newArray(int i) {
            return new CurrentUserCollection[i];
        }
    };
    @SerializedName("cover_photo")
    @Expose
    private final Object coverPhoto;
    @SerializedName("curated")
    @Expose
    private final Boolean curated;
    @SerializedName("id")
    @Expose


    private Integer id;
    @SerializedName("published_at")
    @Expose
    private String publishedAt;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("user")
    @Expose
    private Object user;

    public int describeContents() {
        return 0;
    }

    protected CurrentUserCollection(Parcel parcel) {
        this.id = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.title = (String) parcel.readValue(String.class.getClassLoader());
        this.publishedAt = (String) parcel.readValue(String.class.getClassLoader());
        this.updatedAt = (String) parcel.readValue(String.class.getClassLoader());
        this.curated = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.coverPhoto = parcel.readValue(Object.class.getClassLoader());
        this.user = parcel.readValue(Object.class.getClassLoader());
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


    public void setUser(Object obj) {
        this.user = obj;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.id);
        parcel.writeValue(this.title);
        parcel.writeValue(this.publishedAt);
        parcel.writeValue(this.updatedAt);
        parcel.writeValue(this.curated);
        parcel.writeValue(this.coverPhoto);
        parcel.writeValue(this.user);
    }
}
