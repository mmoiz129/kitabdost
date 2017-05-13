package com.invend.kitabdost;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class SignUpActivity extends AppCompatActivity {

    EditText cuicNum, orginazationName;
    Spinner type;
    Button signup;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        type = (Spinner) findViewById(R.id.type);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cuicNum = (EditText) findViewById(R.id.cuinNum);
        orginazationName = (EditText) findViewById(R.id.orginazationName);

        signup = (Button) findViewById(R.id.signup);

        initUI();

        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                initUI();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

    }

    public void signup() {
        Intent i = new Intent(SignUpActivity.this, HomeActivity.class);
        startActivity(i);
    }



    public void initUI() {

        if (type.getSelectedItem().toString().equals("Organization")) {
            cuicNum.setVisibility(View.VISIBLE);
            orginazationName.setVisibility(View.VISIBLE);
        } else {
            cuicNum.setVisibility(View.GONE);
            orginazationName.setVisibility(View.GONE);
        }

    }
}
