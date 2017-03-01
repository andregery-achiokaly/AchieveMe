package com.example.alexander_topilskii.achieveme.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.alexander_topilskii.achieveme.R;
import com.example.alexander_topilskii.achieveme.presenter.MainPresenter;
import com.example.alexander_topilskii.achieveme.view.view.MainView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends MvpAppCompatActivity implements MainView {
    @BindView(R.id.input_login_edittext)
    EditText loginEditText;

    @BindView(R.id.input_password)
    EditText passwordEditText;

    @InjectPresenter
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_btn)
    public void login() {
        String name = loginEditText.getText().toString();
        if (name.isEmpty()) loginEditText.setError("Login can't be empty!");
        else {
            String password = passwordEditText.getText().toString();
            if (password.isEmpty()) passwordEditText.setError("password can't be empty!");
            else {
                mainPresenter.login(name, password, getApplicationContext());
            }
        }
    }

    @Override
    public void authorizationError(String errorMessage) {
        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }
}
