package com.sajorahasan.github.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sajora on 27-12-2016.
 */

public class GitHubRepo {

    @SerializedName("name")
    private String repoName;

    @SerializedName("description")
    private String repoDesc;

    @SerializedName("language")
    private String repoLang;

    public GitHubRepo(String repoName, String repoDesc, String repoLang) {
        this.repoName = repoName;
        this.repoDesc = repoDesc;
        this.repoLang = repoLang;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getRepoDesc() {
        return repoDesc;
    }

    public void setRepoDesc(String repoDesc) {
        this.repoDesc = repoDesc;
    }

    public String getRepoLang() {
        return repoLang;
    }

    public void setRepoLang(String repoLang) {
        this.repoLang = repoLang;
    }
}
