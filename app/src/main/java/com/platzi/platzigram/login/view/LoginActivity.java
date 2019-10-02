package com.platzi.platzigram.login.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.platzi.platzigram.R;
import com.platzi.platzigram.login.presenter.LoginPresenter;
import com.platzi.platzigram.login.presenter.LoginPresenterImpl;
import com.platzi.platzigram.view.ContainerActivity;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private TextInputEditText username, password;
    private Button loginButtonFacebook;
    private Button login;
    private ProgressBar progressBarLogin;
    private LoginPresenter presenter;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private static final String TAG = "LoginRepositoryImpl";
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        callbackManager = CallbackManager.Factory.create();

        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    Log.w(TAG, "Usuario Logeado" + firebaseUser.getEmail());
                }else{
                    Log.w(TAG, "Usuario no logeado");
                }
            }
        };

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        progressBarLogin = findViewById(R.id.progressbarLogin);
        loginButtonFacebook = findViewById(R.id.login_buttonFacebook);
        hideProgressBar();

        presenter = new LoginPresenterImpl(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //  if(username.equals(""))
                signIn(username.getText().toString(), password.getText().toString());
            }
        });

        loginButtonFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>(){

            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    private void signIn(String username, String password) {
        presenter.signIn(username, password, this, firebaseAuth );

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

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }
}