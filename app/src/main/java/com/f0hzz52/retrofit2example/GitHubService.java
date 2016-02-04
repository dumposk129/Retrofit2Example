package com.f0hzz52.retrofit2example;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {
    @GET("users/{username}/repos")
    Call<List<User>> getUser(@Path("username") String username);
}
