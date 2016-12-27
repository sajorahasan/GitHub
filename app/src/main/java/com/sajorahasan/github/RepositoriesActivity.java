package com.sajorahasan.github;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.sajorahasan.github.adapter.ReposAdapter;
import com.sajorahasan.github.model.GitHubRepo;
import com.sajorahasan.github.rest.APIClient;
import com.sajorahasan.github.rest.GitHubRepoEndPoint;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoriesActivity extends AppCompatActivity {

    private String receivedName;
    private RecyclerView recyclerView;
    private ReposAdapter adapter;
    List<GitHubRepo> myDataSource = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);

        Bundle extras = getIntent().getExtras();
        receivedName = extras.getString("name");

        getSupportActionBar().setTitle(receivedName + "'s repositories");

        recyclerView = (RecyclerView) findViewById(R.id.repos_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        loadRepositories();
    }

    private void loadRepositories() {

        GitHubRepoEndPoint apiService =
                APIClient.getClient().create(GitHubRepoEndPoint.class);

        Call<List<GitHubRepo>> call = apiService.getRepo(receivedName);
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                myDataSource = new ArrayList<>(response.body());
                adapter = new ReposAdapter(myDataSource);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
