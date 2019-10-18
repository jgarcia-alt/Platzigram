package com.platzi.platzigram;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.crashlytics.android.Crashlytics;
import com.facebook.FacebookSdk;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class PlatzigramApplication extends Application {
        private FirebaseAuth firebaseAuth;
        private FirebaseAuth.AuthStateListener authStateListener;
        private FirebaseStorage firebaseStorage;
        String TAG = "PlatzigramApplication";


    @Override
        public void onCreate() {
            super.onCreate();
            Crashlytics.log("Inicializando variables PlatzigramApplication");
            FacebookSdk.sdkInitialize(getApplicationContext());

            firebaseAuth = FirebaseAuth.getInstance();
            authStateListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                    if (firebaseUser != null) {
                        Crashlytics.log(Log.WARN, TAG, "Usuario Logeado"+ firebaseUser.getEmail());
                    } else {
                        Crashlytics.log(Log.WARN, TAG, "Usuario No Logeado");
                    }
                }
            };
            firebaseStorage = FirebaseStorage.getInstance();
        }

        public StorageReference getStorageReference(){
            return firebaseStorage.getReference();
        }
    }
