package com.gitrepos.utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity  extends AppCompatActivity {

    ProgressDialog progressDialog;
    String TAG= BaseActivity.class.getSimpleName();
    private Dialog mProgressDialog;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(BaseActivity.this);
    }

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener, filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }

    public boolean isNetworkConnected()
    {
        return common.isConnectedToInternet(BaseActivity.this);
    }


    public void showLoading()
    {
        Log.d(TAG, "showLoading: ");
        if ( (mProgressDialog == null || !mProgressDialog.isShowing()))
            mProgressDialog = GRProgressDialog.showLoader(this, false);

    }


    public void hideLoading()
    {
        GRProgressDialog.dismissLoader(mProgressDialog);
    }



}
