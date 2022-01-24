package com.startup.textart.unsplash.unsplash;

import com.startup.textart.model.unsplash.Stats;
import retrofit2.Call;
import retrofit2.http.GET;

public interface StatsEndpointInterface {
    @GET("stats/total")
    Call<Stats> getStats();
}
