package com.startup.textart.unsplash;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.startup.textart.model.unsplash.Collection;

import com.startup.textart.model.unsplash.Photo;
import com.startup.textart.model.unsplash.SearchResults;
import com.startup.textart.model.unsplash.Stats;
import com.startup.textart.unsplash.api.HeaderInterceptor;
import com.startup.textart.unsplash.api.Order;
import com.startup.textart.unsplash.unsplash.CollectionsEndpointInterface;
import com.startup.textart.unsplash.unsplash.PhotosEndpointInterface;
import com.startup.textart.unsplash.unsplash.StatsEndpointInterface;
import java.util.List;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Unsplash {
    private static final String BASE_URL = "https://api.unsplash.com/";


    public String TAG = "Unsplash";
    private final CollectionsEndpointInterface collectionsApiService;
    private final PhotosEndpointInterface photosApiService;
    private final StatsEndpointInterface statsApiService;

    public interface OnCollectionLoadedListener {
        void onComplete(Collection collection);

        void onError(String str);
    }

    public interface OnCollectionsLoadedListener {
        void onComplete(List<Collection> list);

        void onError(String str);
    }



    public interface OnPhotosLoadedListener {
        void onComplete(List<Photo> list);

        void onError(String str);
    }

    public interface OnSearchCompleteListener {
        void onComplete(SearchResults searchResults);

        void onError(String str);
    }

    public interface OnStatsLoadedListener {
        void onComplete(Stats stats);

        void onError(String str);
    }

    public Unsplash(String str) {
        Retrofit build = new Retrofit.Builder().baseUrl(BASE_URL).client(new OkHttpClient.Builder().addInterceptor(new HeaderInterceptor(str)).build()).addConverterFactory(GsonConverterFactory.create()).build();
        this.photosApiService =  build.create(PhotosEndpointInterface.class);
        this.collectionsApiService =  build.create(CollectionsEndpointInterface.class);
        this.statsApiService =  build.create(StatsEndpointInterface.class);
    }

    public void getPhotos(Integer num, Integer num2, Order order, OnPhotosLoadedListener onPhotosLoadedListener) {
        this.photosApiService.getPhotos(num, num2, order.getOrder()).enqueue(getMultiplePhotoCallback(onPhotosLoadedListener));
    }


    public void getCuratedPhotos(Integer num, Integer num2, Order order, OnPhotosLoadedListener onPhotosLoadedListener) {
        this.photosApiService.getCuratedPhotos(num, num2, order.getOrder()).enqueue(getMultiplePhotoCallback(onPhotosLoadedListener));
    }



    public void getRandomPhotos(@Nullable String str, @Nullable Boolean bool, @Nullable String str2, @Nullable String str3, @Nullable Integer num, @Nullable Integer num2, @Nullable String str4, @Nullable Integer num3, OnPhotosLoadedListener onPhotosLoadedListener) {
        this.photosApiService.getRandomPhotos(str, bool, str2, str3, num, num2, str4, num3).enqueue(getMultiplePhotoCallback(onPhotosLoadedListener));
    }

    public void searchPhotos(@NonNull String str, Integer num, Integer num2, OnSearchCompleteListener onSearchCompleteListener) {
        searchPhotos(str, num, num2, null, onSearchCompleteListener);
    }

    public void searchPhotos(@NonNull String str, @Nullable Integer num, @Nullable Integer num2, @Nullable String str2, OnSearchCompleteListener onSearchCompleteListener) {
        this.photosApiService.searchPhotos(str, num, num2, str2).enqueue(getSearchResultsCallback(onSearchCompleteListener));
    }



    public void getCuratedCollections(Integer num, Integer num2, OnCollectionsLoadedListener onCollectionsLoadedListener) {
        this.collectionsApiService.getCuratedCollections(num, num2).enqueue(getMultipleCollectionsCallback(onCollectionsLoadedListener));
    }



    public void getCuratedCollection(String str, OnCollectionLoadedListener onCollectionLoadedListener) {
        this.collectionsApiService.getCuratedCollection(str).enqueue(getSingleCollectionCallback(onCollectionLoadedListener));
    }

    public void getCollectionPhotos(String str, Integer num, Integer num2, OnPhotosLoadedListener onPhotosLoadedListener) {
        this.collectionsApiService.getCollectionPhotos(str, num, num2).enqueue(getMultiplePhotoCallback(onPhotosLoadedListener));
    }


    public void getCuratedCollectionPhotos(String str, Integer num, Integer num2, OnPhotosLoadedListener onPhotosLoadedListener) {
        this.collectionsApiService.getCuratedCollectionPhotos(str, num, num2).enqueue(getMultiplePhotoCallback(onPhotosLoadedListener));
    }

    public void getStats(final OnStatsLoadedListener onStatsLoadedListener) {
        this.statsApiService.getStats().enqueue(new Callback<Stats>() {
            public void onResponse(@NonNull Call<Stats> call, @NonNull Response<Stats> response) {
                int code = response.code();
                String a = Unsplash.this.TAG;
                Log.d(a, "Status Code = " + code);
                if (code == 200) {
                    onStatsLoadedListener.onComplete(response.body());
                } else if (code == 401) {
                    Log.d(Unsplash.this.TAG, "Unauthorized, Check your client Id");
                }
            }

            public void onFailure(@NonNull Call<Stats> call, @NonNull Throwable th) {
                onStatsLoadedListener.onError(th.getMessage());
            }
        });
    }



    private Callback<List<Photo>> getMultiplePhotoCallback(final OnPhotosLoadedListener onPhotosLoadedListener) {
        return new Callback<List<Photo>>() {
            public void onResponse(@NonNull Call<List<Photo>> call, @NonNull Response<List<Photo>> response) {
                int code = response.code();
                String a = Unsplash.this.TAG;
                Log.d(a, "Url = " + call.request().url());
                String a2 = Unsplash.this.TAG;
                Log.d(a2, "Status Code = " + code);
                if (code == 200) {
                    onPhotosLoadedListener.onComplete(response.body());
                } else if (code == 401) {
                    Log.d(Unsplash.this.TAG, "Unauthorized, Check your client Id");
                }
            }

            public void onFailure(@NonNull Call<List<Photo>> call, @NonNull Throwable th) {
                String a = Unsplash.this.TAG;
                Log.d(a, "Url = " + call.request().url());
                onPhotosLoadedListener.onError(th.getMessage());
            }
        };
    }

    private Callback<Collection> getSingleCollectionCallback(final OnCollectionLoadedListener onCollectionLoadedListener) {
        return new Callback<Collection>() {
            public void onResponse(@NonNull Call<Collection> call, @NonNull Response<Collection> response) {
                int code = response.code();
                String a = Unsplash.this.TAG;
                Log.d(a, "Status Code = " + code);
                if (code == 200) {
                    onCollectionLoadedListener.onComplete(response.body());
                } else if (code == 401) {
                    Log.d(Unsplash.this.TAG, "Unauthorized, Check your client Id");
                }
            }

            public void onFailure(@NonNull Call<Collection> call, @NonNull Throwable th) {
                onCollectionLoadedListener.onError(th.getMessage());
            }
        };
    }

    private Callback<SearchResults> getSearchResultsCallback(final OnSearchCompleteListener onSearchCompleteListener) {
        return new Callback<SearchResults>() {
            public void onResponse(@NonNull Call<SearchResults> call, @NonNull Response<SearchResults> response) {
                int code = response.code();
                String a = Unsplash.this.TAG;
                Log.d(a, "Status Code = " + code);
                if (code == 200) {
                    onSearchCompleteListener.onComplete(response.body());
                } else if (code == 401) {
                    Log.d(Unsplash.this.TAG, "Unauthorized, Check your client Id");
                }
            }

            public void onFailure(@NonNull Call<SearchResults> call, @NonNull Throwable th) {
                onSearchCompleteListener.onError(th.getMessage());
            }
        };
    }

    private Callback<List<Collection>> getMultipleCollectionsCallback(final OnCollectionsLoadedListener onCollectionsLoadedListener) {
        return new Callback<List<Collection>>() {
            public void onResponse(@NonNull Call<List<Collection>> call, @NonNull Response<List<Collection>> response) {
                int code = response.code();
                String a = Unsplash.this.TAG;
                Log.d(a, "Status Code = " + code);
                if (code == 200) {
                    onCollectionsLoadedListener.onComplete(response.body());
                } else if (code == 401) {
                    Log.d(Unsplash.this.TAG, "Unauthorized, Check your client Id");
                }
            }

            public void onFailure(@NonNull Call<List<Collection>> call, @NonNull Throwable th) {
                onCollectionsLoadedListener.onError(th.getMessage());
            }
        };
    }
}
