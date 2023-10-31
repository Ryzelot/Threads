package com.example.threads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
public class MainActivity extends AppCompatActivity {
    private ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagen = findViewById(R.id.imagen);
    }

        public void sendImg (View v){
            new Thread(new Runnable() {
                public void run() {
                    final Bitmap bitmap = loadImageFromNetwork("https://blog.playstation.com/tachyon/2023/09/9fa2819465930cedc4644349d8f2f9a56c07c38f-scaled.jpeg");

                    runOnUiThread(new Runnable() {
                        public void run() {
                            imagen.setImageBitmap(bitmap);
                        }
                    });
                }
            }).start();
        }

    private Bitmap loadImageFromNetwork(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
      }
    }