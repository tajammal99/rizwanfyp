package com.example.rizwan.fyp.Evaluator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.rizwan.fyp.R;

import java.util.Objects;

public class EvaluatorHome extends AppCompatActivity {

    private String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluator_home);

        ID = Objects.requireNonNull(getIntent().getExtras()).getString("ID");

    }

    public void gotoProposalDefence(View view)
    {
        startActivity(new Intent(EvaluatorHome.this,ProDefence.class));
    }

    public void gotoMidDefence(View view)
    {
        startActivity(new Intent(EvaluatorHome.this,MidDefence.class));
    }

    public void gotoFinalDefence(View view)
    {
        Intent intent = new Intent(EvaluatorHome.this,FinalProjectEvaluation.class);
        intent.putExtra("ID",ID);
        startActivity(intent);
    }
}
