package com.platzi.platzigram.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.platzi.platzigram.R;
import com.platzi.platzigram.adapter.PictureAdapterRecyclerView;
import com.platzi.platzigram.model.Picture;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        RecyclerView pictureRecycler = view.findViewById(R.id.searchRecycler);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(),2);
        pictureRecycler.setHasFixedSize(true);
        pictureRecycler.setPaddingRelative(10,5,5,10);
        pictureRecycler.setLayoutManager(linearLayoutManager);

        PictureAdapterRecyclerView pictureAdapterRecyclerView =
                new PictureAdapterRecyclerView(buildPictures(), R.layout.cardview_picture,getActivity());
        pictureRecycler.setAdapter(pictureAdapterRecyclerView);
        return view;
    }

    public ArrayList<Picture> buildPictures(){
        ArrayList<Picture> pictures = new ArrayList<>();
        pictures.add(new Picture("https://onehdwallpaper.com/wp-content/uploads/2016/07/Fantasy-Landscape-Desktop-Background.jpg", "Uriel Ramirez", "4 dias", "3 Me Gusta"));
        pictures.add(new Picture("https://www.publicdomainpictures.net/pictures/30000/velka/evening-landscape-13530956185Aw.jpg", "Juan Pablo", "11 dias", "11 Me Gusta"));
        pictures.add(new Picture("https://www.snapphotography.co.nz/wp-content/uploads/New-Zealand-Landscape-Photography-prints-23.jpg", "Anahi Salgado", "14 dias", "20 Me Gusta"));
        pictures.add(new Picture("https://onehdwallpaper.com/wp-content/uploads/2016/07/Fantasy-Landscape-Desktop-Background.jpg", "Uriel Ramirez", "4 dias", "3 Me Gusta"));
        pictures.add(new Picture("https://www.publicdomainpictures.net/pictures/30000/velka/evening-landscape-13530956185Aw.jpg", "Juan Pablo", "11 dias", "11 Me Gusta"));
        pictures.add(new Picture("https://www.snapphotography.co.nz/wp-content/uploads/New-Zealand-Landscape-Photography-prints-23.jpg", "Anahi Salgado", "14 dias", "20 Me Gusta"));
        pictures.add(new Picture("https://onehdwallpaper.com/wp-content/uploads/2016/07/Fantasy-Landscape-Desktop-Background.jpg", "Uriel Ramirez", "4 dias", "3 Me Gusta"));
        pictures.add(new Picture("https://www.publicdomainpictures.net/pictures/30000/velka/evening-landscape-13530956185Aw.jpg", "Juan Pablo", "11 dias", "11 Me Gusta"));
        pictures.add(new Picture("https://www.snapphotography.co.nz/wp-content/uploads/New-Zealand-Landscape-Photography-prints-23.jpg", "Anahi Salgado", "14 dias", "20 Me Gusta"));
        pictures.add(new Picture("https://onehdwallpaper.com/wp-content/uploads/2016/07/Fantasy-Landscape-Desktop-Background.jpg", "Uriel Ramirez", "4 dias", "3 Me Gusta"));
        return pictures;
    }
}