package com.anilax.customdesign.unsplash.unsplash;

import com.anilax.customdesign.model.unsplash.Stats;
import retrofit2.Call;
import retrofit2.http.GET;

public interface StatsEndpointInterface {
    @GET("stats/total")
    Call<Stats> getStats();
}
