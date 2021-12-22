package com.example.rizwan.fyp.Admin;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rizwan.fyp.GroupModel;
import com.example.rizwan.fyp.R;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class GroupList extends AppCompatActivity
{

    private ListView LV;
    private FirebaseListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Group List");

        LV = findViewById(R.id.group_list_view);

        Query query = FirebaseDatabase.getInstance().getReference().child("Groups");


        FirebaseListOptions<GroupModel> options = new FirebaseListOptions.Builder<GroupModel>()
                .setLayout(R.layout.list_custom_view)
                .setQuery(query,GroupModel.class)
                .build();

        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(View v, Object model, int position) {
                TextView groupId = v.findViewById(R.id.list_group_id);
                TextView GLReg = v.findViewById(R.id.list_group_leader_Reg);
                TextView GLMobile = v.findViewById(R.id.group_list_GL_phone);
                TextView GMreg = v.findViewById(R.id.list_group_member_Reg);


                GroupModel tm = (GroupModel) model;

                groupId.setText( String.valueOf(tm.getGroupLeaderMobile()));
                GLReg.setText("GL Reg: "+ String.valueOf(tm.getGroupLeaderReg()));
                GLMobile.setText("Member Reg :  "+ String.valueOf(tm.getMemberReg()));
                GMreg.setText("Email:  "+ String.valueOf(tm.getGroupLeaderEmail()));

            }
        };
        LV.setAdapter(adapter);

        LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               GroupModel groupModel = (GroupModel) parent.getAdapter().getItem(position);

                // jo jo edit krna hai

                Bundle bundle = new Bundle();
                bundle.putString("GROUPLNAME",groupModel.getGroupLeaderName());
                bundle.putString("GROUPLREG",groupModel.getGroupLeaderReg());
                bundle.putString("GROUPLEMAIL",groupModel.getGroupLeaderEmail());
                bundle.putString("GROUPLMOBILE",groupModel.getGroupLeaderMobile());
                bundle.putString("GROUPID",groupModel.getGroupID());
                bundle.putString("GROUPMEMBER",groupModel.getMemberName());
                bundle.putString("GROUPMEMBERREG",groupModel.getMemberReg());
                bundle.putString("GROUPMEMAIL",groupModel.getMemberEmail());
                bundle.putString("GROUPPASSWORD",groupModel.getGroupPassword());
                bundle.putString("GROUPCONPASSWORD",groupModel.getGroupConfirmPassword());


                //uper inverted coma m capital m name likh or agy wahi chez get kr m ata hn

                Intent intent = new Intent(GroupList.this,AddNewGroup.class);
                intent.putExtras(bundle);
                intent.putExtra("Value","v");
                startActivity(intent);
            }
        });
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
