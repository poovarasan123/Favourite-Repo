package com.gitrepos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gitrepos.adapter.RepositoriesAdapter;
import com.gitrepos.model.RepositoryModel;
import com.gitrepos.utils.BaseActivity;
import com.gitrepos.utils.GRConstants;
import com.gitrepos.utils.GRPreferenceManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyFavouriteActivity extends BaseActivity {

    RecyclerView rv_repositories;
    LinearLayout layout_empty;
    RepositoriesAdapter repositoriesAdapter;
    public static List<RepositoryModel> repositoryModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favourite);
        ActionBar actionBar = getSupportActionBar();
        rv_repositories = findViewById(R.id.rv_repositories);
        layout_empty = findViewById(R.id.layout_empty);


        repositoryModelList = GRPreferenceManager.getArraylist(GRConstants.REPOS);

        rv_repositories.setLayoutManager(new LinearLayoutManager(this));
        rv_repositories.setHasFixedSize(true);

        Log.e("TAG", "onResume: " + GRPreferenceManager.getString(GRConstants.REPOS, ""));

        if (repositoryModelList.size() != 0) {
            layout_empty.setVisibility(View.GONE);
            rv_repositories.setVisibility(View.VISIBLE);
            repositoriesAdapter = new RepositoriesAdapter(this, repositoryModelList);
            rv_repositories.setAdapter(repositoriesAdapter);
        } else {
            layout_empty.setVisibility(View.VISIBLE);
            rv_repositories.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        repositoryModelList = GRPreferenceManager.getArraylist(GRConstants.REPOS);
        if (repositoryModelList.size() != 0) {
            layout_empty.setVisibility(View.GONE);
            rv_repositories.setVisibility(View.VISIBLE);
            repositoriesAdapter = new RepositoriesAdapter(this, repositoryModelList);
            rv_repositories.setAdapter(repositoriesAdapter);
            repositoriesAdapter.notifyDataSetChanged();
        } else {
            layout_empty.setVisibility(View.VISIBLE);
            rv_repositories.setVisibility(View.GONE);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_repos:
                startActivity(new Intent(this, AddRepoActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void AddRepo(View view) {
        startActivity(new Intent(this, AddRepoActivity.class));
    }
}