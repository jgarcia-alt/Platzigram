package com.platzi.platzigram.view;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.platzi.platzigram.R;
import com.platzi.platzigram.post.view.HomeFragment;
import com.platzi.platzigram.view.fragment.ProfileFragment;
import com.platzi.platzigram.view.fragment.SearchFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class ContainerActivity extends AppCompatActivity {

    @SuppressLint("ResourceType")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

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
}