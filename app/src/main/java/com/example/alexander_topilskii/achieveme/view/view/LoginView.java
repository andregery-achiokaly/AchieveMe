package com.example.alexander_topilskii.achieveme.view.view;


import com.arellomobile.mvp.MvpView;

public interface LoginView extends MvpView {
    void authorizationError(String errorMessage);
}
