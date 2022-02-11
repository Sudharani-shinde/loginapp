package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView textView;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        textView = findViewById(R.id.textView2);
        textView = (TextView) findViewById(R.id.textView2);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            result = bundle.getString("Result");
        }
        textView.setText(result);

      // textView.setText("Google SignIn Successful");
    }
}