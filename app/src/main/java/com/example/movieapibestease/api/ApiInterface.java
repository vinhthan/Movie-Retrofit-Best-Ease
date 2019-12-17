package com.example.movieapibestease.api;

import com.example.movieapibestease.model.Movies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("movie/popular")
    Call<Movies> getMovies(@Query("api_key") String api_key);

}
