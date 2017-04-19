package com.trishabaa.pawfinder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ReportActivity extends AppCompatActivity {

    private EditText mLocationField;
    private Button mAddBtn;
    private EditText mNameValue;
    private TextView mDateLabel;

    private Button mSelectImage;

    private StorageReference mStorage;

    private static final int GALLERY_INTENT = 2;

    private ProgressDialog mProgressDialog;

    private Firebase mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Firebase.setAndroidContext(this);

        setContentView(R.layout.activity_report);

        mRootRef = new Firebase("https://fireapp-c2ad7.firebaseio.com/");

        mLocationField = (EditText) findViewById(R.id.locationField);
        mAddBtn = (Button) findViewById(R.id.addBtn);
        mNameValue = (EditText) findViewById(R.id.nameValue);
        mDateLabel = (TextView) findViewById(R.id.dateLabel);
        mStorage = FirebaseStorage.getInstance().getReference();
        mSelectImage = (Button) findViewById(R.id.selectImage);

        mProgressDialog = new ProgressDialog(this);

        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String location = mLocationField.getText().toString();
                String name = mNameValue.getText().toString();
                String date = mDateLabel.getText().toString();

                //Firebase childRef1 = mRootRef1.child(value);
                Firebase childRef = mRootRef.child(name);

                childRef.push().setValue(date);
                childRef.push().setValue(location);
                //childRef.setValue(value);
            }
        });

        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENT);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_INTENT && resultCode == RESULT_OK){

            mProgressDialog.setMessage("Uploading...");
            mProgressDialog.show();

            Uri uri = data.getData();

            StorageReference filepath = mStorage.child("Photos").child(uri.getLastPathSegment());

            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Toast.makeText(ReportActivity.this, "Upload Done.", Toast.LENGTH_LONG).show();
                    mProgressDialog.dismiss();

                }
            });

        }
    }

}
