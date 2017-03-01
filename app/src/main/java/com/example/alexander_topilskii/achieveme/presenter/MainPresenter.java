package com.example.alexander_topilskii.achieveme.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.alexander_topilskii.achieveme.Application;
import com.example.alexander_topilskii.achieveme.model.rest.PostModel;
import com.example.alexander_topilskii.achieveme.view.activity.HomeActivity;
import com.example.alexander_topilskii.achieveme.view.view.MainView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.alexander_topilskii.achieveme.view.activity.SplashActivity.IS_AUTHORIZATION;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {
    public void login(String name, String password, final Context context) {
        Application.getApi().login(name, password).enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                if (response.body() != null) {
                    PostModel postModel = response.body();
                    if (postModel.getSuccess().equals("true")) {
                        setDefaults(true, context);
                        Intent intent = new Intent(context, HomeActivity.class);
                        context.startActivity(intent);
                    } else {
                        setDefaults(false, context);
                        getViewState().authorizationError("Error my");
                    }
                } else {
                    setDefaults(false, context);
                    getViewState().authorizationError("Error my");
                }
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {


            }
        });
    }

    private void setDefaults(boolean isAuthorization, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(IS_AUTHORIZATION, isAuthorization);
        editor.apply();
    }
}
