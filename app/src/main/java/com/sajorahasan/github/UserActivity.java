package com.sajorahasan.github;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sajorahasan.github.model.GitHubUser;
import com.sajorahasan.github.rest.APIClient;
import com.sajorahasan.github.rest.GitHubUserEndPoints;
import com.squareup.picasso.Picasso;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class UserActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView avatar;
    private TextView username, logIn, followers, bio, followings, email;
    private String data;
    private CompositeDisposable mCompositeDisposable;
    private AppCompatButton repos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        //Initializing Views
        avatar = (ImageView) findViewById(R.id.avatar);
        username = (TextView) findViewById(R.id.username);
        logIn = (TextView) findViewById(R.id.logIn);
        followers = (TextView) findViewById(R.id.followers);
        followings = (TextView) findViewById(R.id.followings);
        bio = (TextView) findViewById(R.id.bio);
        email = (TextView) findViewById(R.id.email);
        repos = (AppCompatButton) findViewById(R.id.repos);

        //fetching value from Intent
        Bundle extras;
        extras = getIntent().getExtras();
        data = extras.getString("name");

        mCompositeDisposable = new CompositeDisposable();
        loadData();

        repos.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.repos:

                Intent i = new Intent(getApplicationContext(), RepositoriesActivity.class);
                i.putExtra("name", data);
                startActivity(i);
                break;
        }
    }

    private void loadData() {

        final GitHubUserEndPoints apiService =
                APIClient.getClient().create(GitHubUserEndPoints.class);

        mCompositeDisposable.add(apiService.getUser(data)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(GitHubUser gitHubUser) {

        if (gitHubUser.getName() == null) {
            username.setText("No name provided");
        } else {
            username.setText("Name: " + gitHubUser.getName());
        }

        if (gitHubUser.getBio() == null) {
            bio.setText("No bio provided");
        } else {
            bio.setText("Bio: " + gitHubUser.getBio());
        }

        if (gitHubUser.getEmail() == null) {
            bio.setText("No email provided");
        } else {
            email.setText("Email: " + gitHubUser.getEmail());
        }

        logIn.setText("Username: " + gitHubUser.getLogin());
        followers.setText("Followers: " + gitHubUser.getFollowers());
        followings.setText("Following: " + gitHubUser.getFollowings());

        Picasso.with(getApplicationContext())
                .load(gitHubUser.getAvatar())
                .into(avatar);

    }

    private void handleError(Throwable throwable) {
        Toast.makeText(getApplicationContext(), "Error " + throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }
}
