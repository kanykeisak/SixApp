package com.example.sixapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.sixapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding viewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivityMainBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);

        setContentView(viewBinding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String url = "https://i.pinimg.com/originals/5b/6e/ca/5b6eca63605bea0eeb48db43f77fa0ce.jpg";

        Glide.with(MainActivity.this)
                .load(url)
                .placeholder(R.color.gray)
                .into(viewBinding.imageView);

        // клик на картинку и переход на карту
        viewBinding.imageView.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(intent);
        });

        viewBinding.btn.setOnClickListener(v -> {
            Thread thread = new Thread(){
                @Override
                public void run() {
                    super.run();

                    for (int i = 0; i <= 100; i++) {

                        int percent = i;
                        runOnUiThread(() -> {
                            viewBinding.tvPercent.setText(percent + " %");
                            viewBinding.progress.setProgress(percent, true);

                        });
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            };
            thread.start();
        });

        viewBinding.btnOpenMap.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, MapsActivity.class));
        });


    }
}