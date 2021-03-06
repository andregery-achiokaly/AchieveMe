package com.example.alexander_topilskii.achieveme.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.alexander_topilskii.achieveme.R;
import com.example.alexander_topilskii.achieveme.presenter.LoginPresenter;
import com.example.alexander_topilskii.achieveme.view.view.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends MvpAppCompatActivity implements LoginView {
    @BindView(R.id.input_login_edittext)
    EditText loginEditText;

    @BindView(R.id.input_password)
    EditText passwordEditText;

    @InjectPresenter
    LoginPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_btn)
    public void login() {
        String name = loginEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        mainPresenter.login(name, password, getApplicationContext());
    }

    @OnClick(R.id.registrationBtn)
    public void registration() {
        startActivity(new Intent(this, RegistrationActivity.class));
    }

    @Override
    public void authorizationError(String errorMessage) {
        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }
}
