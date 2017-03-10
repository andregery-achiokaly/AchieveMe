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
import com.example.alexander_topilskii.achieveme.view.view.LoginView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.alexander_topilskii.achieveme.model.rest.PostModel.ERROR_RESPONSE;
import static com.example.alexander_topilskii.achieveme.model.rest.PostModel.SUCCESS_RESPONSE;
import static com.example.alexander_topilskii.achieveme.view.activity.SplashActivity.IS_AUTHORIZATION;

@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView> {
    public static final String USER_NAME = "USER_NAME";


    public void login(final String name, final String password, final Context context) {
        if (name.isEmpty()) getViewState().authorizationError("Login can't be empty!");
        else {
            if (password.isEmpty())  getViewState().authorizationError("password can't be empty!");
            else {
                sendLoginResponse(name, password, context);
            }
        }
    }

    private void sendLoginResponse(final String name, String password, final Context context) {
        Application.getApi().login(name, password).enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                if (response.body() != null && response.body().getSuccess().equals(SUCCESS_RESPONSE)) {
                    saveUserAndOpenActivity(context, name);
                } else {
                    deleteUserAndShowError(context);
                }
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                deleteUserAndShowError(context);
            }
        });
    }

    private void deleteUserAndShowError(Context context) {
        saveUser(false, context, "");
        getViewState().authorizationError(ERROR_RESPONSE);
    }

    private void saveUserAndOpenActivity(Context context, String name) {
        getViewState().authorizationError("true");
        saveUser(true, context, name);
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }

    private void saveUser(boolean isAuthorization, Context context, String name) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(IS_AUTHORIZATION, isAuthorization);
        editor.putString(USER_NAME, name);
        editor.apply();
    }
}
