package com.example.rizwan.fyp.Admin;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rizwan.fyp.Evaluator.EvaluatorMod;
import com.example.rizwan.fyp.R;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class PanelList extends AppCompatActivity {

    private ListView LV;
    private FirebaseListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Panel List");


        LV = findViewById(R.id.panel_list);

        Query query = FirebaseDatabase.getInstance().getReference().child("Evaluator");


        FirebaseListOptions<EvaluatorMod> options = new FirebaseListOptions.Builder<EvaluatorMod>()
                .setLayout(R.layout.custom_panel_list)
                .setQuery(query,EvaluatorMod.class)
                .build();

        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(View v, Object model, int position) {
                TextView panelID = v.findViewById(R.id.list_panel_id);
                TextView name1 = v.findViewById(R.id.list_panel_member_name1);
                TextView name2 = v.findViewById(R.id.list_panel_member_name2);
                TextView name3 = v.findViewById(R.id.list_panel_member_name3);


                EvaluatorMod tm = (EvaluatorMod) model;

                panelID.setText("ID : "+String.valueOf(tm.getPanelID()));
                name1.setText("Member 1 : "+ String.valueOf(tm.getPanelMemberName()));
                name2.setText("Member 2 : "+ String.valueOf(tm.getPanelMemberName2()));
                name3.setText("Member 3 : "+ String.valueOf(tm.getPanelMemberName3()));

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
