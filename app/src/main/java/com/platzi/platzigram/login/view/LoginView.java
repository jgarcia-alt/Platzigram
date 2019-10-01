package com.platzi.platzigram.login.view;

import android.view.View;

public interface LoginView {
    void goCreateAccount();
    void enableInputs();
    void disableInputs();

    void showProgressBar();
    void hideProgressBar();

    void loginError(String error);


    void goActivityContainer();
}