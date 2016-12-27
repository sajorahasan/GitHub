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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView avatar;
    private TextView username, logIn, followers, bio, followings, email;
    private String data;
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

        Call<GitHubUser> call = apiService.getUser(data);
        call.enqueue(new Callback<GitHubUser>() {
            @Override
            public void onResponse(Call<GitHubUser> call, Response<GitHubUser> response) {

                if (response.body().getName() == null) {
                    username.setText("No name provided");
                } else {
                    username.setText("Name: " + response.body().getName());
                }

                if (response.body().getBio() == null) {
                    bio.setText("No bio provided");
                } else {
                    bio.setText("Bio: " + response.body().getBio());
                }

                if (response.body().getEmail() == null) {
                    bio.setText("No email provided");
                } else {
                    email.setText("Email: " + response.body().getEmail());
                }

                logIn.setText("Username: " + response.body().getLogin());
                followers.setText("Followers: " + response.body().getFollowers());
                followings.setText("Following: " + response.body().getFollowings());

                Picasso.with(getApplicationContext())
                        .load(response.body().getAvatar())
                        .into(avatar);

            }

            @Override
            public void onFailure(Call<GitHubUser> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
