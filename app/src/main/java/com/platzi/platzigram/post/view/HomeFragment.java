package com.platzi.platzigram.post.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crashlytics.android.Crashlytics;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.platzi.platzigram.R;
import com.platzi.platzigram.adapter.PictureAdapterRecyclerView;
import com.platzi.platzigram.model.Picture;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private static final int REQUEST_CAMERA = 1;
    private FloatingActionButton fabCamera;
    private String photoPathTemp = "";

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        showToolbar(getResources().getString(R.string.tab_home), false, view);
        RecyclerView pictureRecycler = view.findViewById(R.id.pictureRecycler);

        fabCamera = view.findViewById(R.id.fabCamera);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        pictureRecycler.setLayoutManager(linearLayoutManager);

        PictureAdapterRecyclerView pictureAdapterRecyclerView =
                new PictureAdapterRecyclerView(buildPictures(), R.layout.cardview_picture, getActivity());
        pictureRecycler.setAdapter(pictureAdapterRecyclerView);

        fabCamera.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                takePicture();
            }
        });
        return view;
    }

    private void takePicture() {
        Intent intentTakePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intentTakePicture.resolveActivity(getActivity().getPackageManager())!= null){
            File photoFile = null;

            try {
                photoFile = createImageFile();
            }catch (Exception e){
                e.printStackTrace();
                Crashlytics.logException(e);
            }

            if (photoFile != null){
                Uri photoUri = FileProvider.getUriForFile(getActivity(), "com.platzi.platzigram",photoFile);
                intentTakePicture.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intentTakePicture, REQUEST_CAMERA);
            }
        }
    }

    private File createImageFile() throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HH-mm-ss").format(new Date());
        String imageFileName = "JPEG_" + timestamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File photo = File.createTempFile(imageFileName,".jpg", storageDir);
        photoPathTemp = "file:" + photo.getAbsolutePath();
        return photo;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CAMERA && resultCode == getActivity().RESULT_OK){
            Log.d("HomeFragment", "CAMERA OK!! :)");
            Intent i = new Intent(getActivity(), NewPostActivity.class);
            i.putExtra("PHOTO_PATH_TEMP", photoPathTemp);
            startActivity(i);
        }
    }

    public ArrayList<Picture> buildPictures(){
        ArrayList<Picture> pictures = new ArrayList<>();
        pictures.add(new Picture("https://onehdwallpaper.com/wp-content/uploads/2016/07/Fantasy-Landscape-Desktop-Background.jpg", "Uriel Ramirez", "4 dias", "3 Me Gusta"));
        pictures.add(new Picture("https://www.publicdomainpictures.net/pictures/30000/velka/evening-landscape-13530956185Aw.jpg", "Juan Pablo", "11 dias", "11 Me Gusta"));
        pictures.add(new Picture("https://www.snapphotography.co.nz/wp-content/uploads/New-Zealand-Landscape-Photography-prints-23.jpg", "Anahi Salgado", "14 dias", "20 Me Gusta"));
        return pictures;
    }

    @SuppressLint("RestrictedApi")
    public void showToolbar(String tittle, boolean upButton, View view){
        Toolbar toolbar = view.findViewById(R.id.toolbar);
            AppCompatActivity parentActivity = (AppCompatActivity)getActivity();
            parentActivity.setSupportActionBar(toolbar);
            parentActivity.getSupportActionBar().setTitle(tittle);
            parentActivity.getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(upButton);
    }
}
