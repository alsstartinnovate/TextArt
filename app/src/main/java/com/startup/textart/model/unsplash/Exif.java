package com.startup.textart.model.unsplash;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Exif implements Parcelable {
    public static final Creator<Exif> CREATOR = new Creator<Exif>() {
        public Exif createFromParcel(Parcel parcel) {
            Exif exif = new Exif();
            exif.make = (String) parcel.readValue(String.class.getClassLoader());
            exif.model = (String) parcel.readValue(String.class.getClassLoader());
            exif.exposureTime = (String) parcel.readValue(String.class.getClassLoader());
            exif.aperture = (String) parcel.readValue(String.class.getClassLoader());
            exif.focalLength = (String) parcel.readValue(String.class.getClassLoader());
            exif.iso = (Integer) parcel.readValue(Integer.class.getClassLoader());
            return exif;
        }

        public Exif[] newArray(int i) {
            return new Exif[i];
        }
    };

    @SerializedName("aperture")
    @Expose
    public String aperture;

    @SerializedName("exposure_time")
    @Expose
    public String exposureTime;

    @SerializedName("focal_length")
    @Expose
    public String focalLength;

    @SerializedName("iso")
    @Expose
    public Integer iso;

    @SerializedName("make")
    @Expose
    public String make;

    @SerializedName("model")
    @Expose
    public String model;

    public int describeContents() {
        return 0;
    }

    public Exif() {
    }


    public String getModel() {
        return this.model;
    }

    public void setModel(String str) {
        this.model = str;
    }


    public void setExposureTime(String str) {
        this.exposureTime = str;
    }


    public String getAperture() {
        return this.aperture;
    }




    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.make);
        parcel.writeValue(this.model);
        parcel.writeValue(this.exposureTime);
        parcel.writeValue(this.aperture);
        parcel.writeValue(this.focalLength);
        parcel.writeValue(this.iso);
    }
}
