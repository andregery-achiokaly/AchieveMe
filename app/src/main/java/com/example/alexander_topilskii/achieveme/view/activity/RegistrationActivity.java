package com.example.alexander_topilskii.achieveme.view.activity;


import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.alexander_topilskii.achieveme.R;
import com.example.alexander_topilskii.achieveme.presenter.RegistrationPresenter;
import com.example.alexander_topilskii.achieveme.view.view.RegistrationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationActivity extends MvpAppCompatActivity implements RegistrationView {
    @BindView(R.id.input_login_edittext)
    EditText loginEditText;

    @BindView(R.id.input_password)
    EditText passwordEditText;

    @InjectPresenter
    RegistrationPresenter registrationPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.registrationBtn)
    public void registration() {
        String name = loginEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        registrationPresenter.registration(name, password, getApplicationContext());
    }

    @Override
    public void registrationError(String errorMessage) {
        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }
}
