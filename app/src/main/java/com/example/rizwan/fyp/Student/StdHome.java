package com.example.rizwan.fyp.Student;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rizwan.fyp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class StdHome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ProgressDialog progressDialog;
    private String groupID,dbProjectName,dbDescription,dbTech,spinnerValue;
    private DatabaseReference databaseReference,spinnerRef,porposelDB,validation,validationSRS;
    private TextView showTech,showDescription,showProjectName,notificationFile,txtNotification
            ,degreeNam,proposalStatus,showmarks;
    private Button uploadfile,selectfile;
    private StorageReference firebaseStorage;
    private NestedScrollView nestedScrollView;
    private Spinner areaSpinner;
    private Uri Pdf_Uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_std_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        progressDialog = new ProgressDialog(this);

        firebaseStorage = FirebaseStorage.getInstance().getReference();

        //casting
        showProjectName = findViewById(R.id.project_name);
        showDescription = findViewById(R.id.description);
        showTech = findViewById(R.id.technologies);
        selectfile = findViewById(R.id.select_file);
        uploadfile = findViewById(R.id.submit);
        notificationFile = findViewById(R.id.notification);
        txtNotification = findViewById(R.id.std_home_txt_info);
        nestedScrollView = findViewById(R.id.std_home_scrollView);
        degreeNam = findViewById(R.id.eduction_type);
        proposalStatus = findViewById(R.id.status);
        showmarks = findViewById(R.id.proposal_results);



        groupID = Objects.requireNonNull(getIntent().getExtras()).getString("GROUPID");


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Groups").child(groupID);
        spinnerRef = FirebaseDatabase.getInstance().getReference().child("Supervisor");
        porposelDB = FirebaseDatabase.getInstance().getReference().child("Groups").child(groupID);
        validation = FirebaseDatabase.getInstance().getReference().child("Validation").child("Proposal");
        validationSRS = FirebaseDatabase.getInstance().getReference().child("Validation").child("SRS").child(groupID);



        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {
                    String degree = Objects.requireNonNull(dataSnapshot.child("program").getValue()).toString();
                    degreeNam.setText(degree);

                    if (!dataSnapshot.hasChild("projectInfo"))
                    {
                        DialogInformation();
                    }
                    if (!dataSnapshot.hasChild("proposal"))
                    {
                        nestedScrollView.setVisibility(View.VISIBLE);
                        txtNotification.setVisibility(View.INVISIBLE);
//                        areaSpinner.setVisibility(View.VISIBLE);

                    }
                    if (dataSnapshot.hasChild("proposal"))
                    {
                        txtNotification.setVisibility(View.VISIBLE);
                        txtNotification.setTextSize(28);
                        txtNotification.setText("Please wait while we are evaluation your proposal");
                    }
                    if (dataSnapshot.hasChild("projectInfo"))
                    {
                        shoProjectInfo();
                        //  txtNotification.setVisibility(View.VISIBLE);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        validationSRS.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists())
                {
                    String memberMarks = Objects.requireNonNull(dataSnapshot.child("strProjMmarks").getValue()).toString();
                    String leaderMarks = Objects.requireNonNull(dataSnapshot.child("strProposalLmarks").getValue()).toString();

                    showmarks.setText("SRS Mark \nLeader marks : "+leaderMarks+" Member marks : "+memberMarks);

                    txtNotification.setVisibility(View.INVISIBLE);
                    proposalStatus.setText("Accepted");
                    txtNotification.setTextSize(0);
                    nestedScrollView.setVisibility(View.VISIBLE);
                    selectfile.setText("Select Final file");
                    uploadfile.setText("Upload Final");
                }
                else if (!dataSnapshot.exists())
                {
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                        {
                            if (dataSnapshot.hasChild("srs"))
                            {
                                txtNotification.setVisibility(View.VISIBLE);
                                txtNotification.setTextSize(28);
                                txtNotification.setText("Please wait while we are evaluation your srs");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError)
                        {

                        }
                    });
                }
                else
                {
                    validation.addListenerForSingleValueEvent(new ValueEventListener()
                    {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                        {

                            if (dataSnapshot.hasChild(groupID))
                            {
                                txtNotification.setVisibility(View.INVISIBLE);
                                String status = Objects.requireNonNull(dataSnapshot.child(groupID).child("selection").getValue()).toString();
                                String memberMarks = Objects.requireNonNull(dataSnapshot.child(groupID).child("strProjMmarks").getValue()).toString();
                                String leaderMarks = Objects.requireNonNull(dataSnapshot.child(groupID).child("strProposalLmarks").getValue()).toString();


                                if (status.equals("Accept"))
                                {
                                    proposalStatus.setText(status);
                                    txtNotification.setTextSize(0);
                                    nestedScrollView.setVisibility(View.VISIBLE);
                                    showmarks.setText("Proposal Marks \nLeader marks : "+leaderMarks+" Member marks : "+memberMarks);
                                    selectfile.setText("Select SRS file");
                                    uploadfile.setText("Upload SRS");

                                    uploadfile.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            spinnerValue = areaSpinner.getSelectedItem().toString();
                                            if (Pdf_Uri!=null)
                                            {
                                                Upload_Pdf_SRS(Pdf_Uri);

                                            }
                                            else
                                            {
                                                Toast.makeText(StdHome.this, "Please select a file", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                                }
                                else if (status.equals("Revise"))
                                {
                                    proposalStatus.setText(status);
                                    txtNotification.setTextSize(0);
                                    nestedScrollView.setVisibility(View.VISIBLE);
                                    areaSpinner.setVisibility(View.VISIBLE);
                                }
                                else if (status.equals("Reject"))
                                {
                                    txtNotification.setTextSize(0);
                                    nestedScrollView.setVisibility(View.VISIBLE);
                                    DialogInformation();
                                    areaSpinner.setVisibility(View.VISIBLE);
                                }

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError)
                        {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        selectfile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


                if(ContextCompat.checkSelfPermission(StdHome.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
                {
                    Select_File();
                }
                else
                {
                    ActivityCompat.requestPermissions(StdHome.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},10);
                }
                }

        });

        uploadfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerValue = areaSpinner.getSelectedItem().toString();
                if (Pdf_Uri!=null && !spinnerValue.equals("Select Supervisor"))
                {
                    Upload_Pdf_File(Pdf_Uri);

                    Map<String, Object> userInfo = new HashMap<>();
                    userInfo.put("supervisor", spinnerValue);
                    databaseReference.updateChildren(userInfo);

                }
                else if (spinnerValue.equals("Select Supervisor"))
                {
                    Toast.makeText(StdHome.this, "Please select a supervisor", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(StdHome.this, "Please select a file", Toast.LENGTH_SHORT).show();
                }
            }
        });


      //  shoProjectInfo();


        spinnerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Is better to use a List, because you don't know the size
                // of the iterator returned by dataSnapshot.getChildren() to
                // initialize the array
                final List<String> areas = new ArrayList<String>();

                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                    String areaName = areaSnapshot.child("supervisor").getValue(String.class);
                    areas.add(areaName);
                }

                areaSpinner = findViewById(R.id.spinner);
                ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(StdHome.this, android.R.layout.simple_spinner_item, areas);
                areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                areaSpinner.setAdapter(areasAdapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if(requestCode==10 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            Select_File();
        }
        else
        {
            Toast.makeText(this, "Please provide permission", Toast.LENGTH_SHORT).show();
        }
    }

    public void Select_File(){

        final CharSequence[] File_Items = {"Select Pdf","Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(StdHome.this);
        builder.setTitle("Select a Pdf File");
        builder.setItems(File_Items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(File_Items[which].equals("Select Pdf")){
                    Intent intentf = new Intent();
                    intentf.setType("application/pdf");
                    intentf.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intentf,86);

                }
                else if(File_Items[which].equals("Cancel")){


                    dialog.dismiss();

                }
            }
        });

        builder.show();

    }







    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

//For Selecting of Pdf Result

        if(requestCode ==86&& data !=null && resultCode== RESULT_OK)
        {
            Pdf_Uri = data.getData();
            notificationFile.setText("A file is selected : "+data.getData().getLastPathSegment());
        }
        else
        {
            Toast.makeText(StdHome.this,"Please select a File",Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


//*******************************Uploading Pdf**********************************************************************

    public void Upload_Pdf_File(final Uri Pdf_Uri){


        final ProgressDialog progressDialog1 = new ProgressDialog(this);
        progressDialog1.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog1.setTitle("Uploading File.....");
        progressDialog1.setProgress(0);
        progressDialog1.show();

        String fileName= System.currentTimeMillis()+"";
        final StorageReference storageReference = firebaseStorage.child("proposal").child(fileName);

        storageReference.putFile(Pdf_Uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String pdf_uri = uri.toString();

                        porposelDB.child("proposal").child("proposal_link").setValue(pdf_uri).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(StdHome.this,"File Successfully Uploaded",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });





            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {


                Toast.makeText(StdHome.this,"Uploading Failed",Toast.LENGTH_SHORT).show();


            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                int current_progress = (int) (100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog1.setProgress(current_progress);
            }
        });

    }

    //upload SRS

    public void Upload_Pdf_SRS(final Uri Pdf_Uri){


        final ProgressDialog progressDialog1 = new ProgressDialog(this);
        progressDialog1.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog1.setTitle("Uploading File.....");
        progressDialog1.setProgress(0);
        progressDialog1.show();

        String fileName= System.currentTimeMillis()+"";
        final StorageReference storageReference = firebaseStorage.child("SRS").child(fileName);

        storageReference.putFile(Pdf_Uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String pdf_uri = uri.toString();

                        porposelDB.child("srs").child("srs_link").setValue(pdf_uri).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(StdHome.this,"File Successfully Uploaded",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });





            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {


                Toast.makeText(StdHome.this,"Uploading Failed",Toast.LENGTH_SHORT).show();


            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                int current_progress = (int) (100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog1.setProgress(current_progress);
            }
        });

    }





















    private void shoProjectInfo()
    {
        databaseReference.child("projectInfo").addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists())
                {
                    dbProjectName = Objects.requireNonNull(dataSnapshot.child("projectName").getValue()).toString();
                    dbDescription = Objects.requireNonNull(dataSnapshot.child("description").getValue()).toString();
                    dbTech = Objects.requireNonNull(dataSnapshot.child("technology").getValue()).toString();

                    showProjectName.setText(dbProjectName);
                    showDescription.setText(dbDescription);
                    showTech.setText(dbTech);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }

    private void DialogInformation()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(StdHome.this);
        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.custom_dialog,null);

        Button btnSubmit = view.findViewById(R.id.std_home_project_btn_submit);
        final EditText projectName,description,technology;
        projectName = view.findViewById(R.id.std_home_project_name);
        description = view.findViewById(R.id.std_project_home_description);
        technology = view.findViewById(R.id.std_home_project_tech);


        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strProjectName,strDescription,strTech;

                strProjectName = projectName.getText().toString();
                strDescription = description.getText().toString();
                strTech = technology.getText().toString();


                if (strProjectName.isEmpty() || strDescription.isEmpty() || strTech.isEmpty())
                {
                    Toast.makeText(StdHome.this, "Kindly enter the details", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    progressDialog.setTitle("Saving Data");
                    progressDialog.setMessage("Please wait...");
                    progressDialog.show();

                    Map<String, Object> userInfo = new HashMap<>();
                    userInfo.put("projectName", strProjectName);
                    userInfo.put("description", strDescription);
                    userInfo.put("technology", strTech);
                    databaseReference.child("projectInfo").updateChildren(userInfo);

                    Toast.makeText(StdHome.this, "Data saved successfully", Toast.LENGTH_SHORT).show();

                    progressDialog.dismiss();

                    shoProjectInfo();

                    dialog.dismiss();

                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.std_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void uploadProposal(View view) {
        Select_File();
    }
}
