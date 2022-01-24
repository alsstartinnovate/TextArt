package com.startup.textart.model.unsplash;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Urls implements Parcelable {
    public static final Creator<Urls> CREATOR = new Creator<Urls>() {
        public Urls createFromParcel(Parcel parcel) {
            Urls urls = new Urls();
            urls.raw = (String) parcel.readValue(String.class.getClassLoader());
            urls.full = (String) parcel.readValue(String.class.getClassLoader());
            urls.regular = (String) parcel.readValue(String.class.getClassLoader());
            urls.small = (String) parcel.readValue(String.class.getClassLoader());
            urls.thumb = (String) parcel.readValue(String.class.getClassLoader());
            return urls;
        }

        public Urls[] newArray(int i) {
            return new Urls[i];
        }
    };

    @SerializedName("full")
    @Expose
    public String full;

    @SerializedName("raw")
    @Expose
    public String raw;

    @SerializedName("regular")
    @Expose
    public String regular;

    @SerializedName("small")
    @Expose
    public String small;

    @SerializedName("thumb")
    @Expose
    public String thumb;

    public int describeContents() {
        return 0;
    }

    public Urls() {
    }


    public String getFull() {
        return this.full;
    }




    public String getRegular() {
        return this.regular;
    }

    public void setRegular(String str) {
        this.regular = str;
    }


    public String getThumb() {
        return this.thumb;
    }

    public void setThumb(String str) {
        this.thumb = str;
    }


    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.raw);
        parcel.writeValue(this.full);
        parcel.writeValue(this.regular);
        parcel.writeValue(this.small);
        parcel.writeValue(this.thumb);
    }
}
