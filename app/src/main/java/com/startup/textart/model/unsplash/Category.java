package com.startup.textart.model.unsplash;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category implements Parcelable {
    public static final Creator<Category> CREATOR = new Creator<Category>() {
        public Category createFromParcel(Parcel parcel) {
            Category category = new Category();
            category.id = (Integer) parcel.readValue(Integer.class.getClassLoader());
            category.title = (String) parcel.readValue(String.class.getClassLoader());
            category.photoCount = (Integer) parcel.readValue(Integer.class.getClassLoader());
            category.links = (Links) parcel.readValue(Links.class.getClassLoader());
            return category;
        }

        public Category[] newArray(int i) {
            return new Category[i];
        }
    };

    @SerializedName("id")
    @Expose


    public Integer id;

    @SerializedName("links")
    @Expose
    public Links links;

    @SerializedName("photo_count")
    @Expose
    public Integer photoCount;

    @SerializedName("title")
    @Expose
    public String title;

    public int describeContents() {
        return 0;
    }

    public Category() {
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






    public Links getLinks() {
        return this.links;
    }

    public void setLinks(Links links2) {
        this.links = links2;
    }



    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.id);
        parcel.writeValue(this.title);
        parcel.writeValue(this.photoCount);
        parcel.writeValue(this.links);
    }
}
