package com.example.alexander_topilskii.achieveme.view.activity;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.alexander_topilskii.achieveme.R;
import com.example.alexander_topilskii.achieveme.presenter.HomePresenter;
import com.example.alexander_topilskii.achieveme.view.view.HomeView;

import butterknife.ButterKnife;

import static com.example.alexander_topilskii.achieveme.view.activity.SplashActivity.IS_AUTHORIZATION;

public class HomeActivity extends MvpAppCompatActivity implements HomeView {

    @InjectPresenter
    HomePresenter homePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        setDefaults(false, getApplicationContext());
    }

    private void setDefaults(boolean isAuthorization, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(IS_AUTHORIZATION, isAuthorization);
        editor.apply();
    }
}
