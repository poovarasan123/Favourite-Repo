package com.gitrepos;

import static com.gitrepos.MyFavouriteActivity.repositoryModelList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.gitrepos.model.RepositoryModel;
import com.gitrepos.utils.BaseActivity;
import com.gitrepos.utils.GRConstants;
import com.gitrepos.utils.GRPreferenceManager;
import com.gitrepos.utils.MySingleton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AddRepoActivity extends BaseActivity {

    private static final String TAG = "AddRepoActivity";

    TextInputEditText te_owner_name;
    TextInputEditText te_repo_name;
    TextView tv_add_repo;
    List<RepositoryModel> datalist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_repo);

        te_owner_name = findViewById(R.id.te_owner_name);
        te_repo_name = findViewById(R.id.te_repo_name);
        tv_add_repo = findViewById(R.id.tv_add_repo);

        tv_add_repo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoading();
                String owner = te_owner_name.getText().toString().trim();
                String repo_name = te_repo_name.getText().toString().trim();
                StringRequest stringRequest = new StringRequest(Request.Method.GET, GRConstants.BASE_URL + owner + "/" + repo_name, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hideLoading();
                        Log.e(TAG, "onResponse: response: " + response);

                        if (response != null) {
                            try {
                                JSONObject data = new JSONObject(response);

                                Log.e(TAG, "onResponse: response: " + data.getString("name"));
                                Log.e(TAG, "onResponse: response: " + data.getString("full_name"));
                                Log.e(TAG, "onResponse: response: " + data.getString("description"));
                                Log.e(TAG, "onResponse: response: " + data.getString("html_url"));
                                Log.e(TAG, "onResponse: response: " + data.getString("owner"));

                                repositoryModelList.add(new RepositoryModel(data.getString("name"), data.getString("full_name"), data.getString("html_url"), data.getString("description")));

                                GRPreferenceManager.setArrayList(GRConstants.REPOS, repositoryModelList);

                                Log.e(TAG, "onResponse: equity data list size: " + repositoryModelList.size());
                                te_owner_name.setText(null);
                                te_repo_name.setText(null);
                                Toast.makeText(AddRepoActivity.this, "Repository Added your favourite list", Toast.LENGTH_SHORT).show();
//                                finish();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(AddRepoActivity.this, "Unable find owner or repository", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        hideLoading();
                        Log.e(TAG, "onResponse: call history error: " + error.networkResponse.statusCode);
                        Toast.makeText(AddRepoActivity.this, "Unable find owner or repository", Toast.LENGTH_SHORT).show();
                    }
                });
                MySingleton.getMySingleton(AddRepoActivity.this).addToRequestQue(stringRequest);
            }
        });
    }
}