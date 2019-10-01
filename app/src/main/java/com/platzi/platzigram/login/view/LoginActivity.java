package com.platzi.platzigram.login.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.platzi.platzigram.R;
import com.platzi.platzigram.login.presenter.LoginPresenter;
import com.platzi.platzigram.login.presenter.LoginPresenterImpl;
import com.platzi.platzigram.view.ContainerActivity;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private TextInputEditText username, password;
    private Button login;
    private ProgressBar progressBarLogin;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        progressBarLogin = findViewById(R.id.progressbarLogin);
        hideProgressBar();

        presenter = new LoginPresenterImpl(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //  if(username.equals(""))
                presenter.signIn(username.getText().toString(), password.getText().toString());
            }
        });
    }

    public void goCreateAccount(View view){
        goCreateAccount();
    }

    public void platziUrlBrowser(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.platzi.com"));
        startActivity(browserIntent);
    }

    @Override
    public void goCreateAccount() {
        final Button login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                goActivityContainer();
            }
        });
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    @Override
    public void goActivityContainer() {
        final Button login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                goActivityContainer();
            }
        });

        Intent intent = new Intent(this, ContainerActivity.class);
        startActivity(intent);
    }

    @Override
    public void enableInputs() {
        username.setEnabled(true);
        password.setEnabled(true);
        login.setEnabled(true);
    }

    @Override
    public void disableInputs() {
        username.setEnabled(false);
        password.setEnabled(false);
        login.setEnabled(false);
    }

    @Override
    public void showProgressBar() {
        progressBarLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBarLogin.setVisibility(View.GONE);
    }

    @Override
    public void loginError(String error) {
        Toast.makeText(this, getString(R.string.login_error), Toast.LENGTH_SHORT).show();
    }
}