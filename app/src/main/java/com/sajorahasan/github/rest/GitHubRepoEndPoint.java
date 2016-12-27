package com.sajorahasan.github.rest;

import com.sajorahasan.github.model.GitHubRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Sajora on 27-12-2016.
 */

public interface GitHubRepoEndPoint {

    @GET("/users/{user}/repos")
    Call<List<GitHubRepo>> getRepo(@Path("user") String name);
}
