package com.bensonsworkwear.bensons.fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bensonsworkwear.bensons.R;

import java.io.FileNotFoundException;

public class PackagingFragment extends AppCompatActivity {
    //https://stackoverflow.com/questions/11144783/how-to-access-an-image-from-the-phones-photo-gallery#11144927
    ImageView targetImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_packaging);

        Button buttonLoadImage = findViewById(R.id.upload_button);
        targetImage = findViewById(R.id.preview);

        buttonLoadImage.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Uri targetUri = data.getData();

            Bitmap bitmap;
            try {
                assert targetUri != null;
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                targetImage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                Log.e("File not found:", e.toString());
            }
        }
    }
}
