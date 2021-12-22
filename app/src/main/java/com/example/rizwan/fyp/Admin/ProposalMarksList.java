package com.example.rizwan.fyp.Admin;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rizwan.fyp.Evaluator.ProDefenceModel;
import com.example.rizwan.fyp.GroupModel;
import com.example.rizwan.fyp.R;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ProposalMarksList extends AppCompatActivity
{

    private ListView LV;
    private FirebaseListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposal_marks_list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Marks List");

        LV = findViewById(R.id.marks_list);

        Query query = FirebaseDatabase.getInstance().getReference().child("Validation").child("Proposal");


        FirebaseListOptions<ProDefenceModel> options = new FirebaseListOptions.Builder<ProDefenceModel>()
                .setLayout(R.layout.list_custom_view)
                .setQuery(query,ProDefenceModel.class)
                .build();

        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(View v, Object model, int position) {
                TextView groupId = v.findViewById(R.id.list_group_id);
                TextView GLMarks = v.findViewById(R.id.list_group_leader_Reg);
                TextView memMarks = v.findViewById(R.id.group_list_GL_phone);
                TextView remarks = v.findViewById(R.id.list_group_member_Reg);


                ProDefenceModel tm = (ProDefenceModel) model;

                groupId.setText( String.valueOf(tm.getStrGroupID()));
                GLMarks.setText("GL Marks: "+ String.valueOf(tm.getStrProposalLmarks()));
                memMarks.setText("Mem Marks :  "+ String.valueOf(tm.getStrProjMmarks()));
                remarks.setText("Remarks:  "+ String.valueOf(tm.getStrProposalRemarks()));

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
