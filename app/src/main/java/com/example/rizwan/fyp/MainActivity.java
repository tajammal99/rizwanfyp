package com.example.rizwan.fyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rizwan.fyp.Admin.AdminLogin;
import com.example.rizwan.fyp.Evaluator.EvaluatorLogin;
import com.example.rizwan.fyp.Student.StudentLogin;

public class MainActivity extends AppCompatActivity {

    private Button adminBtn,stdBtn,supBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adminBtn = findViewById(R.id.welcome_btn_id_admin);
        stdBtn = findViewById(R.id.welcome_btn_id_std);
        supBtn = findViewById(R.id.welcome_btn_id_sup);

        adminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AdminLogin.class));
            }
        });
        stdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,StudentLogin.class));
            }
        });
        supBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,EvaluatorLogin.class));
            }
        });
    }
}
