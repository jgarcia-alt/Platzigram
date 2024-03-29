package com.platzi.platzigram.login.presenter;

import android.app.Activity;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.platzi.platzigram.login.interactor.LoginInteractor;
import com.platzi.platzigram.login.interactor.LoginInteractorImpl;
import com.platzi.platzigram.login.view.LoginView;

public class LoginPresenterImpl implements LoginPresenter{

    private LoginView loginView;
    private LoginInteractor interactor;

    public LoginPresenterImpl(LoginView loginView){
        this.loginView = loginView;
        interactor = new LoginInteractorImpl(this);
    }

    @Override
    public void signIn(String username, String password, Activity activity, FirebaseAuth firebaseAuth) {

        loginView.disableInputs();
        loginView.showProgressBar();
        interactor.signIn(username, password, activity, firebaseAuth);
    }

    @Override
    public void loginSuccess() {
        loginView.goActivityContainer();
        loginView.hideProgressBar();
    }

    @Override
    public void loginError(String error) {
        loginView.enableInputs();
        loginView.hideProgressBar();
        loginView.loginError(error);
    }
}
