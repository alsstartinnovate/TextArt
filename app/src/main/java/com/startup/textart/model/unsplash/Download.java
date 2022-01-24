package com.startup.textart.model.unsplash;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Download implements Parcelable {
    public static final Creator<Download> CREATOR = new Creator<Download>() {
        public Download createFromParcel(Parcel parcel) {
            return new Download(parcel);
        }

        public Download[] newArray(int i) {
            return new Download[i];
        }
    };
    @SerializedName("url")
    @Expose
    private String url;

    public int describeContents() {
        return 0;
    }

    protected Download(Parcel parcel) {
        this.url = (String) parcel.readValue(String.class.getClassLoader());
    }



    public void setUrl(String str) {
        this.url = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.url);
    }
}
