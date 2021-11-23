package com.example.wheeloffortune;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mp = MediaPlayer.create(this, R.raw.roulette);


        imageView = findViewById(R.id.imageView);
        RotateAnimation rotate = new RotateAnimation(0, 2400,
                RotateAnimation.RELATIVE_TO_SELF,
                0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(7300);
        rotate.setFillAfter(true);
        rotate.setInterpolator(new DecelerateInterpolator());
        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        imageView.startAnimation(rotate);
        mp.start();

        Thread thread = new Thread() {
            public void run() {
                try {

                    TimeUnit.SECONDS.sleep(7);
                    Intent i = new Intent(MainActivity.this, DifficultyActivity.class);
                    startActivity(i);
                    mp.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();



    }


}