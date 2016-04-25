package com.example.glmgracy.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String HELLO = "Hello Android Studio";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);
        setTitle("滋补炖品");
    }

    public void showInfo(View view)
    {
        Toast.makeText(MainActivity.this, "You're stupid!", Toast.LENGTH_SHORT).show();
    }
}
