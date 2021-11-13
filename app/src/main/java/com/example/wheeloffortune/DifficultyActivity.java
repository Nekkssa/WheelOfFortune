package com.example.wheeloffortune;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DifficultyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

    }

    public void btnEasy_Click(View view) {
        Intent i = new Intent(DifficultyActivity.this, GameActivity.class);
        i.putExtra("Difficulty",1);
        startActivity(i);

    }

    public void btnNormal_Click(View view) {
        Intent i = new Intent(DifficultyActivity.this, GameActivity.class);
        i.putExtra("Difficulty",2);
        startActivity(i);

    }

    public void btnDifficult_Click(View view) {
        Intent i = new Intent(DifficultyActivity.this, GameActivity.class);
        i.putExtra("Difficulty",3);
        startActivity(i);

    }
}
