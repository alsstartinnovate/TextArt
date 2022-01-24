package com.startup.textart.model.unsplash;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResults implements Parcelable {
    public static final Creator<SearchResults> CREATOR = new Creator<SearchResults>() {
        public SearchResults createFromParcel(Parcel parcel) {
            SearchResults searchResults = new SearchResults();
            searchResults.total = (Integer) parcel.readValue(Integer.class.getClassLoader());
            searchResults.totalPages = (Integer) parcel.readValue(Integer.class.getClassLoader());
            parcel.readList(searchResults.results, Photo.class.getClassLoader());
            return searchResults;
        }

        public SearchResults[] newArray(int i) {
            return new SearchResults[i];
        }
    };

    @SerializedName("results")
    @Expose
    public List<Photo> results = null;

    @SerializedName("total")
    @Expose
    public Integer total;

    @SerializedName("total_pages")
    @Expose
    public Integer totalPages;

    public int describeContents() {
        return 0;
    }


    public List<Photo> getResults() {
        return this.results;
    }

    public void setResults(List<Photo> list) {
        this.results = list;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.total);
        parcel.writeValue(this.totalPages);
        parcel.writeList(this.results);
    }
}
