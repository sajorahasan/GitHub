package com.sajorahasan.github.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sajorahasan.github.R;
import com.sajorahasan.github.model.GitHubRepo;

import java.util.List;

/**
 * Created by Sajora on 27-12-2016.
 */

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ViewHolder> {

    private List<GitHubRepo> gitHubRepos;

    public ReposAdapter(List<GitHubRepo> gitHubRepos) {
        this.gitHubRepos = gitHubRepos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_repo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.repoName.setText(gitHubRepos.get(position).getRepoName());
        holder.repoDesc.setText(gitHubRepos.get(position).getRepoDesc());
        holder.repoLang.setText(gitHubRepos.get(position).getRepoLang());
    }

    @Override
    public int getItemCount() {
        return gitHubRepos.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView repoName, repoDesc, repoLang;

        public ViewHolder(View itemView) {
            super(itemView);
            repoName = (TextView) itemView.findViewById(R.id.repoName);
            repoDesc = (TextView) itemView.findViewById(R.id.repoDesc);
            repoLang = (TextView) itemView.findViewById(R.id.repoLang);
        }
    }
}
