package com.startup.textart.model.unsplash;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links implements Parcelable {
    public static final Creator<Links> CREATOR = new Creator<Links>() {
        public Links createFromParcel(Parcel parcel) {
            return new Links(parcel);
        }

        public Links[] newArray(int i) {
            return new Links[i];
        }
    };
    @SerializedName("download")
    @Expose
    private final String download;
    @SerializedName("download_location")
    @Expose
    private final String downloadLocation;
    @SerializedName("html")
    @Expose
    private final String html;
    @SerializedName("likes")
    @Expose
    private String likes;
    @SerializedName("photos")
    @Expose
    private final String photos;
    @SerializedName("portfolio")
    @Expose
    private final String portfolio;
    @SerializedName("self")
    @Expose
    private final String self;

    public int describeContents() {
        return 0;
    }

    protected Links(Parcel parcel) {
        this.self = (String) parcel.readValue(String.class.getClassLoader());
        this.html = (String) parcel.readValue(String.class.getClassLoader());
        this.photos = (String) parcel.readValue(String.class.getClassLoader());
        this.likes = (String) parcel.readValue(String.class.getClassLoader());
        this.portfolio = (String) parcel.readValue(String.class.getClassLoader());
        this.download = (String) parcel.readValue(String.class.getClassLoader());
        this.downloadLocation = (String) parcel.readValue(String.class.getClassLoader());
    }


    public String getPhotos() {
        return this.photos;
    }


    public String getLikes() {
        return this.likes;
    }

    public void setLikes(String str) {
        this.likes = str;
    }


    public String getDownload() {
        return this.download;
    }




    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.self);
        parcel.writeValue(this.html);
        parcel.writeValue(this.photos);
        parcel.writeValue(this.likes);
        parcel.writeValue(this.portfolio);
        parcel.writeValue(this.download);
        parcel.writeValue(this.downloadLocation);
    }
}
