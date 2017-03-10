package com.example.alexander_topilskii.achieveme.presenter;


import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.arellomobile.mvp.MvpPresenter;
import com.example.alexander_topilskii.achieveme.Application;
import com.example.alexander_topilskii.achieveme.R;
import com.example.alexander_topilskii.achieveme.model.rest.PostModel;
import com.example.alexander_topilskii.achieveme.view.activity.LoginActivity;
import com.example.alexander_topilskii.achieveme.view.view.RegistrationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.alexander_topilskii.achieveme.model.rest.PostModel.ERROR_RESPONSE;
import static com.example.alexander_topilskii.achieveme.model.rest.PostModel.SUCCESS_RESPONSE;

public class RegistrationPresenter extends MvpPresenter<RegistrationView> {
    public void registration(String name, String password, final Context context) {
        if (name.isEmpty()) getViewState().registrationError(context.getString(R.string.empty_logan_error));
        else {
            if (password.isEmpty()) getViewState().registrationError(context.getString(R.string.empty_password_error));
            else {
                sendRegistrationResponse(name, password, context);
            }
        }


    }

    private void sendRegistrationResponse(String name, String password, final Context context) {
        Application.getApi().registration(name, password).enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                if (response.body() != null && response.body().getSuccess().equals(SUCCESS_RESPONSE)) {
                    Toast.makeText(context, SUCCESS_RESPONSE, Toast.LENGTH_SHORT).show();
                    context.startActivity(new Intent(context, LoginActivity.class));
                } else {
                    Toast.makeText(context, ERROR_RESPONSE, Toast.LENGTH_SHORT).show();
                    getViewState().registrationError(ERROR_RESPONSE);
                }
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                getViewState().registrationError(ERROR_RESPONSE);
            }
        });
    }
}
