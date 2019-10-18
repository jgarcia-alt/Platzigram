package com.platzi.platzigram.post.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.storage.StorageReference;
import com.platzi.platzigram.PlatzigramApplication;
import com.platzi.platzigram.R;
import com.squareup.picasso.Picasso;

public class PictureDetailActivity extends AppCompatActivity {
    private static final String TAG = "PictureDetailActivity";
    private ImageView imageHeader;
    private PlatzigramApplication app;
    StorageReference storageReference;
    private String PHOTO_NAME = "JPEG_20191014_10-21-51_2102638323375367154.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Crashlytics.log("Iniciando "+ TAG);

        setContentView(R.layout.activity_picture_detail);

        app = (PlatzigramApplication) getApplicationContext();
        storageReference = app.getStorageReference();

        imageHeader = findViewById(R.id.imageHeader);

        showToolbar("", true);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().setEnterTransition(new Fade());
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            showData();
        }
    }
//                    Picasso.get().load(uri.toString()).into(imageHeader);
        private void showData() {
            storageReference.child("postImages/"+PHOTO_NAME)
                    .getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Picasso.get().load(uri.toString()).into(imageHeader);
                }

            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(PictureDetailActivity.this, "Ocurrio un error al traer la foto", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    Crashlytics.logException(e);
                }
            });
        }

    public void showToolbar(String tittle, boolean upButton) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
    }
}