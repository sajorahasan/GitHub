package com.sajorahasan.github.rest;

import com.sajorahasan.github.model.GitHubUser;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Sajora on 27-12-2016.
 */

public interface GitHubUserEndPoints {

    @GET("/users/{user}")
    Observable<GitHubUser> getUser(@Path("user") String user);

}
