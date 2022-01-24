package com.startup.textart.unsplash.unsplash;

import com.startup.textart.model.unsplash.Download;
import com.startup.textart.model.unsplash.Photo;
import com.startup.textart.model.unsplash.SearchResults;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PhotosEndpointInterface {
    @GET("photos/curated")
    Call<List<Photo>> getCuratedPhotos(@Query("page") Integer num, @Query("per_page") Integer num2, @Query("order_by") String str);

    @GET("photos/{id}")
    Call<Photo> getPhoto(@Path("id") String str, @Query("w") Integer num, @Query("h") Integer num2);

    @GET("photos/{id}/download")
    Call<Download> getPhotoDownloadLink(@Path("id") String str);

    @GET("photos")
    Call<List<Photo>> getPhotos(@Query("page") Integer num, @Query("per_page") Integer num2, @Query("order_by") String str);

    @GET("photos/random")
    Call<Photo> getRandomPhoto(@Query("collections") String str, @Query("featured") Boolean bool, @Query("username") String str2, @Query("query") String str3, @Query("w") Integer num, @Query("h") Integer num2, @Query("orientation") String str4);

    @GET("photos/random")
    Call<List<Photo>> getRandomPhotos(@Query("collections") String str, @Query("featured") boolean z, @Query("username") String str2, @Query("query") String str3, @Query("w") Integer num, @Query("h") Integer num2, @Query("orientation") String str4, @Query("count") Integer num3);

    @GET("search/photos")
    Call<SearchResults> searchPhotos(@Query("query") String str, @Query("page") Integer num, @Query("per_page") Integer num2, @Query("orientation") String str2);
}
