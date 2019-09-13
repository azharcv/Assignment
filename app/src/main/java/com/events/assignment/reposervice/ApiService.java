package com.events.assignment.reposervice;


import com.events.assignment.model.Root;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("all-sections/7.json")
    Call<Root> getMostViewed(@Query("api-key") String apikey);
}
