package com.trishabaa.pawfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;

public class AdoptActivity extends AppCompatActivity {

    private Button mAddBtn;
    private EditText myNameAd;
    private TextView mAddress;

    private Firebase mRootRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Firebase.setAndroidContext(this);

        setContentView(R.layout.activity_adopt);

        mRootRef = new Firebase("https://fireapp-c2ad7.firebaseio.com/");

        mAddBtn = (Button) findViewById(R.id.addBtn);
        myNameAd = (EditText) findViewById(R.id.nameAd);
        mAddress = (TextView) findViewById(R.id.addAd);

        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String namead = myNameAd.getText().toString();
                String addad = mAddress.getText().toString();

                //Firebase childRef1 = mRootRef1.child(value);
                Firebase childRef = mRootRef.child(namead);
                childRef.push().setValue(addad);
                //childRef.setValue(value);
            }
        });
    }
}
