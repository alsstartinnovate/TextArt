package com.startup.textart.unsplash.unsplash;

import com.startup.textart.model.unsplash.Collection;
import com.startup.textart.model.unsplash.Photo;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CollectionsEndpointInterface {
    @GET("collections/{id}")
    Call<Collection> getCollection(@Path("id") String str);

    @GET("collections/{id}/photos")
    Call<List<Photo>> getCollectionPhotos(@Path("id") String str, @Query("page") Integer num, @Query("per_page") Integer num2);

    @GET("collections")
    Call<List<Collection>> getCollections(@Query("page") Integer num, @Query("per_page") Integer num2);

    @GET("collections/curated/{id}")
    Call<Collection> getCuratedCollection(@Path("id") String str);

    @GET("collections/curated/{id}/photos")
    Call<List<Photo>> getCuratedCollectionPhotos(@Path("id") String str, @Query("page") Integer num, @Query("per_page") Integer num2);

    @GET("collections/curated")
    Call<List<Collection>> getCuratedCollections(@Query("page") Integer num, @Query("per_page") Integer num2);

    @GET("collections/features")
    Call<List<Collection>> getFeaturedCollections(@Query("page") Integer num, @Query("per_page") Integer num2);

    @GET("collections/{id}/related")
    Call<List<Collection>> getRelatedCollections(@Path("id") String str);
}
