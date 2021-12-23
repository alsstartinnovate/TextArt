package com.anilax.customdesign.model.unsplash;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stats implements Parcelable {
    public static final Creator<Stats> CREATOR = new Creator<Stats>() {
        public Stats createFromParcel(Parcel parcel) {
            Stats stats = new Stats();
            stats.totalPhotos = (Integer) parcel.readValue(Integer.class.getClassLoader());
            stats.photoDownloads = (Integer) parcel.readValue(Integer.class.getClassLoader());
            return stats;
        }

        public Stats[] newArray(int i) {
            return new Stats[i];
        }
    };

    @SerializedName("photo_downloads")
    @Expose
    public Integer photoDownloads;

    @SerializedName("total_photos")
    @Expose
    public Integer totalPhotos;

    public int describeContents() {
        return 0;
    }


    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.totalPhotos);
        parcel.writeValue(this.photoDownloads);
    }
}
