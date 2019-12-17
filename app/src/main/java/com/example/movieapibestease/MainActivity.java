package com.example.movieapibestease;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.movieapibestease.adapter.MovieAdapter;
import com.example.movieapibestease.api.ApiClient;
import com.example.movieapibestease.api.ApiInterface;
import com.example.movieapibestease.model.Constants;
import com.example.movieapibestease.model.Movies;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Movies.Results> mList;
    private MovieAdapter mAdapter;

    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);

        getMovies();



    }

    private void getMovies() {
        recyclerView= findViewById(R.id.recyclerView);
        mList = new ArrayList<>();
        mAdapter = new MovieAdapter(mList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);

        Call<Movies> call = apiInterface.getMovies(Constants.KEY_API);
        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                if (response.isSuccessful()){
                    if (!mList.isEmpty()){
                        mList.clear();
                    }
                    //add list
                    mList.addAll(response.body().getResults());
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
/*
        api key:
        034bbd1b233d6726e0c7dc7f338657f9

        url:
        https://api.themoviedb.org/3/movie/popular?api_key=034bbd1b233d6726e0c7dc7f338657f9

        poster:
        https://image.tmdb.org/t/p/w500/kqjL17yufvn9OVLyXYpvtyrFfak.jpg*/