package com.example.rizwan.fyp.Admin;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rizwan.fyp.Evaluator.EvaluatorMod;
import com.example.rizwan.fyp.R;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class FinalProjectMarksList extends AppCompatActivity {


    ListView LV;
    private FirebaseListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_project_marks_list);

        LV = findViewById(R.id.final_project_marks);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Marks List");



        Query query = FirebaseDatabase.getInstance().getReference().child("Marks");


        FirebaseListOptions<totalMarksModel> options = new FirebaseListOptions.Builder<totalMarksModel>()
                .setLayout(R.layout.custom_panel_list)
                .setQuery(query,totalMarksModel.class)
                .build();

        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(View v, Object model, int position) {
                TextView panelID = v.findViewById(R.id.list_panel_id);
                TextView name1 = v.findViewById(R.id.list_panel_member_name1);
                TextView name2 = v.findViewById(R.id.list_panel_member_name2);
                TextView name3 = v.findViewById(R.id.list_panel_member_name3);


                totalMarksModel tm = (totalMarksModel) model;

                panelID.setText("GL : "+String.valueOf(tm.getGlName()));
                name1.setText("GL Marks : "+ String.valueOf(tm.getGlTotalMarks()));
                name2.setText("Member : "+ String.valueOf(tm.getMemName()));
                name3.setText("Member Marks : "+ String.valueOf(tm.getMemTotalMarks()));

            }
        };
        LV.setAdapter(adapter);
    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
