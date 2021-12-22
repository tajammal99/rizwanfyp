package com.example.rizwan.fyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rizwan.fyp.Evaluator.FinalDefence;
import com.example.rizwan.fyp.Evaluator.MidDefence;
import com.example.rizwan.fyp.Evaluator.ProDefence;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread td=new Thread(){
            public void run()
            {
                try{
                    sleep(3000);
                }catch (Exception ex)
                {
ex.printStackTrace();
                }finally {
                    Intent it=new Intent(splash.this,MainActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        };td.start();


    }
}
