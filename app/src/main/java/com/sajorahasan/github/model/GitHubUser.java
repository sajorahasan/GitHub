package com.sajorahasan.github.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sajora on 27-12-2016.
 */

public class GitHubUser {

    @SerializedName("avatar_url")
    private String avatar;

    @SerializedName("login")
    private String login;

    @SerializedName("name")
    private String name;

    @SerializedName("bio")
    private String bio;

    @SerializedName("followers")
    private String followers;

    @SerializedName("following")
    private String followings;

    @SerializedName("email")
    private String email;


    public GitHubUser(String avatar, String login, String name, String bio, String followers, String followings, String email) {
        this.avatar = avatar;
        this.login = login;
        this.name = name;
        this.bio = bio;
        this.followers = followers;
        this.followings = followings;
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowings() {
        return followings;
    }

    public void setFollowings(String followings) {
        this.followings = followings;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
