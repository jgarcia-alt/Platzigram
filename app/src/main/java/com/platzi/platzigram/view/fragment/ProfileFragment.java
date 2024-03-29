package com.platzi.platzigram.view.fragment;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crashlytics.android.Crashlytics;
import com.platzi.platzigram.R;
import com.platzi.platzigram.adapter.PictureAdapterRecyclerView;
import com.platzi.platzigram.model.Picture;

import java.util.ArrayList;
/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    private String TAG = "ProfileFragment";
    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Crashlytics.log("Inicializando " + TAG);
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        showToolbar("",false, view);

        RecyclerView pictureRecycler = view.findViewById(R.id.pictureProfileRecycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        pictureRecycler.setLayoutManager(linearLayoutManager);

        PictureAdapterRecyclerView pictureAdapterRecyclerView =
                new PictureAdapterRecyclerView(buildPictures(), R.layout.cardview_picture, getActivity());
        pictureRecycler.setAdapter(pictureAdapterRecyclerView);
        return view;
    }

    public ArrayList<Picture> buildPictures(){
        ArrayList<Picture> pictures = new ArrayList<>();
        pictures.add(new Picture("https://onehdwallpaper.com/wp-content/uploads/2016/07/Fantasy-Landscape-Desktop-Background.jpg", "Uriel Ramirez", "4 dias", "3 Me Gusta"));
        pictures.add(new Picture("https://www.publicdomainpictures.net/pictures/30000/velka/evening-landscape-13530956185Aw.jpg", "Juan Pablo", "11 dias", "11 Me Gusta"));
        pictures.add(new Picture("https://www.snapphotography.co.nz/wp-content/uploads/New-Zealand-Landscape-Photography-prints-23.jpg", "Anahi Salgado", "14 dias", "20 Me Gusta"));
        return pictures;
    }

    public void showToolbar(String tittle, boolean upButton, View view){
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        AppCompatActivity parentActivity = (AppCompatActivity)getActivity();
        parentActivity.setSupportActionBar(toolbar);
        parentActivity.getSupportActionBar().setTitle(tittle);
        parentActivity.getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(upButton);
    }
}


