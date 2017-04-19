package com.trishabaa.pawfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;

public class VolunteerActivity extends AppCompatActivity {

    private EditText mReasonVol;
    private Button mAddBtn;
    private EditText mNameVol;
    private TextView mAgeVol;

    private Firebase mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Firebase.setAndroidContext(this);

        setContentView(R.layout.activity_volunteer);

        mRootRef = new Firebase("https://fireapp-c2ad7.firebaseio.com/");

        mReasonVol = (EditText) findViewById(R.id.reasonVol);
        mAddBtn = (Button) findViewById(R.id.addBtn);
        mNameVol = (EditText) findViewById(R.id.nameVol);
        mAgeVol = (TextView) findViewById(R.id.ageVol);

        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String reason = mReasonVol.getText().toString();
                String namev = mNameVol.getText().toString();
                String age = mAgeVol.getText().toString();

                //Firebase childRef1 = mRootRef1.child(value);
                Firebase childRef = mRootRef.child(namev);
                childRef.push().setValue(age);
                childRef.push().setValue(reason);
                //childRef.setValue(value);
            }
        });
    }
}
