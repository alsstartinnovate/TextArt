package com.anilax.customdesign.model.unsplash;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileImage implements Parcelable {
    public static final Creator<ProfileImage> CREATOR = new Creator<ProfileImage>() {
        public ProfileImage createFromParcel(Parcel parcel) {
            return new ProfileImage(parcel);
        }

        public ProfileImage[] newArray(int i) {
            return new ProfileImage[i];
        }
    };
    @SerializedName("large")
    @Expose
    private String large;
    @SerializedName("medium")
    @Expose
    private final String medium;
    @SerializedName("small")
    @Expose
    private final String small;

    public int describeContents() {
        return 0;
    }

    protected ProfileImage(Parcel parcel) {
        this.small = (String) parcel.readValue(String.class.getClassLoader());
        this.medium = (String) parcel.readValue(String.class.getClassLoader());
        this.large = (String) parcel.readValue(String.class.getClassLoader());
    }


    public void setLarge(String str) {
        this.large = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.small);
        parcel.writeValue(this.medium);
        parcel.writeValue(this.large);
    }
}
