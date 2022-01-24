package com.startup.textart.model.unsplash;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Badge implements Parcelable {
    public static final Creator<Badge> CREATOR = new Creator<Badge>() {
        public Badge createFromParcel(Parcel parcel) {
            return new Badge(parcel);
        }

        public Badge[] newArray(int i) {
            return new Badge[i];
        }
    };
    @SerializedName("link")
    @Expose
    private final String link;
    @SerializedName("primary")
    @Expose
    private final Boolean primary;
    @SerializedName("slug")
    @Expose
    private final String slug;
    @SerializedName("title")
    @Expose
    private String title;

    public int describeContents() {
        return 0;
    }

    protected Badge(Parcel parcel) {
        this.title = (String) parcel.readValue(String.class.getClassLoader());
        this.primary = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.slug = (String) parcel.readValue(String.class.getClassLoader());
        this.link = (String) parcel.readValue(String.class.getClassLoader());
    }


    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }



    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.title);
        parcel.writeValue(this.primary);
        parcel.writeValue(this.slug);
        parcel.writeValue(this.link);
    }
}
