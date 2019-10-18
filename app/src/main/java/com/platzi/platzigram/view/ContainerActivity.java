package com.platzi.platzigram.view;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.platzi.platzigram.R;
import com.platzi.platzigram.login.view.CreateAccountActivity;
import com.platzi.platzigram.login.view.LoginActivity;
import com.platzi.platzigram.post.view.HomeFragment;
import com.platzi.platzigram.view.fragment.ProfileFragment;
import com.platzi.platzigram.view.fragment.SearchFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class ContainerActivity extends AppCompatActivity {
    private static final String TAG = "ContainerActivity";
    private FirebaseAuth firebaseAuth;

private FirebaseAuth.AuthStateListener authStateListener;

    @SuppressLint("ResourceType")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Crashlytics.log("Inicializando"+TAG);
        setContentView(R.layout.activity_container);
        firebaseInitialize();
        final BottomBar bottomBar = findViewById(R.id.bottombar);
        bottomBar.setDefaultTabPosition(1);
               bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
                   @Override
                   public void onTabSelected(@IdRes int tabId) {
                       Log.i("Value -> ", String.valueOf(tabId));
                       switch (tabId){
                           case R.id.profile:
                               ProfileFragment profileFragment = new ProfileFragment();
                               getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment)
                                       .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                       .addToBackStack(null).commit();
                               break;

                           case R.id.search:
                               SearchFragment searchFragment = new SearchFragment();
                               getSupportFragmentManager().beginTransaction().replace(R.id.container, searchFragment)
                                       .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                       .addToBackStack(null).commit();
                               break;
                           default:
                               HomeFragment homeFragment = new HomeFragment();
                               getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment)
                                       .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                       .addToBackStack(null).commit();
                               break;
                       }
                   }
               });
        }
    private void firebaseInitialize(){
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
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mSignOut:
                firebaseAuth.signOut();

                if (LoginManager.getInstance()!=null){
                    LoginManager.getInstance().logOut();
                }
                Toast.makeText(this, "Se cerro la sesion", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ContainerActivity.this, LoginActivity.class);
                startActivity(i);
                break;

            case R.id.mAbout:
                Toast.makeText(this, "Platzi", Toast.LENGTH_SHORT).show();
                break;

            case R.id.crash:
                Crashlytics.getInstance().crash();
        }
        return super.onOptionsItemSelected(item);
    }
}