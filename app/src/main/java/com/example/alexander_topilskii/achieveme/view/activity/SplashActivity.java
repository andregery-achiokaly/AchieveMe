package com.example.alexander_topilskii.achieveme.view.activity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.alexander_topilskii.achieveme.R;

import butterknife.ButterKnife;

public class SplashActivity extends MvpAppCompatActivity {
    public static final String IS_AUTHORIZATION = "IS_AUTHORIZATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean isAuthorization = getUser(IS_AUTHORIZATION, getApplicationContext());
        if (!isAuthorization) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public boolean getUser(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(key, false);
    }
}
