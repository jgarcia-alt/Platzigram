package com.platzi.platzigram;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.FacebookSdk;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

    public class PlatzigramApplication extends Application {
        private FirebaseAuth firebaseAuth;
        private FirebaseAuth.AuthStateListener authStateListener;

        @Override
        public void onCreate() {
            super.onCreate();

            FacebookSdk.sdkInitialize(getApplicationContext());

            firebaseAuth = FirebaseAuth.getInstance();
            authStateListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                    if (firebaseUser != null) {
                        Log.w("PlatzigramApplication", "Usuario Logeado" + firebaseUser.getEmail());
                    } else {
                        Log.w("PlatzigramApplication", "Usuario no logeado");
                    }
                }
            };
        }
    }
