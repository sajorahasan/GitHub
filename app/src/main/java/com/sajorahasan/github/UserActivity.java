package com.sajorahasan.github;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView avatar;
    private TextView username, logIn, followers, followings, email;
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
        email = (TextView) findViewById(R.id.email);
        repos = (AppCompatButton) findViewById(R.id.repos);

        //fetching value from Intent
        Bundle extras;
        extras = getIntent().getExtras();
        data = extras.getString("name");



        repos.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.repos:


                break;
        }
    }
}
