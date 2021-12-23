package com.anilax.customdesign.model.unsplash;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location implements Parcelable {
    public static final Creator<Location> CREATOR = new Creator<Location>() {
        public Location createFromParcel(Parcel parcel) {
            Location location = new Location();
            location.city = (String) parcel.readValue(String.class.getClassLoader());
            location.country = (String) parcel.readValue(String.class.getClassLoader());
            location.position = (Position) parcel.readValue(Position.class.getClassLoader());
            return location;
        }

        public Location[] newArray(int i) {
            return new Location[i];
        }
    };

    @SerializedName("city")
    @Expose
    public String city;

    @SerializedName("country")
    @Expose
    public String country;

    @SerializedName("position")
    @Expose
    public Position position;

    public int describeContents() {
        return 0;
    }

    public Location() {
    }


    public String getCity() {
        return this.city;
    }


    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.city);
        parcel.writeValue(this.country);
        parcel.writeValue(this.position);
    }
}
