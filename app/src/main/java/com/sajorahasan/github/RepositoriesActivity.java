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

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class RepositoriesActivity extends AppCompatActivity {

    private String receivedName;
    private RecyclerView recyclerView;
    private ReposAdapter adapter;
    private CompositeDisposable mCompositeDisposable;
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

        mCompositeDisposable = new CompositeDisposable();
        loadRepositories();
    }

    private void loadRepositories() {

        GitHubRepoEndPoint apiService =
                APIClient.getClient().create(GitHubRepoEndPoint.class);

        mCompositeDisposable.add(apiService.getRepo(receivedName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(List<GitHubRepo> gitHubRepos) {
        myDataSource = new ArrayList<>(gitHubRepos);
        adapter = new ReposAdapter(myDataSource);
        recyclerView.setAdapter(adapter);
    }

    private void handleError(Throwable throwable) {
        Toast.makeText(getApplicationContext(), "Error " + throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }
}
