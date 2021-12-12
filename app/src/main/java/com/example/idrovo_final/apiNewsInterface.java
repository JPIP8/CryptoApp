package com.example.idrovo_final;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface apiNewsInterface {
    @GET("top-headlines")
    Call<NewsMod> getNewsMod(

            @Query("country") String country,
            @Query("apiKey") String apiKey

    );
}
