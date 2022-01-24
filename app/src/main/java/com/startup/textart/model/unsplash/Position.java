package com.startup.textart.model.unsplash;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Position implements Parcelable {
    public static final Creator<Position> CREATOR = new Creator<Position>() {
        public Position createFromParcel(Parcel parcel) {
            Position position = new Position();
            position.latitude = (Double) parcel.readValue(Double.class.getClassLoader());
            position.longitude = (Double) parcel.readValue(Double.class.getClassLoader());
            return position;
        }

        public Position[] newArray(int i) {
            return new Position[i];
        }
    };

    @SerializedName("latitude")
    @Expose
    public Double latitude;

    @SerializedName("longitude")
    @Expose
    public Double longitude;

    public int describeContents() {
        return 0;
    }

    public Position() {
    }


    public Double getLatitude() {
        return this.latitude;
    }


    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.latitude);
        parcel.writeValue(this.longitude);
    }
}
