package com.invend.kitabdost;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button loginButton;
    TextView signInTextView;
    EditText email, password;
    CheckBox trusty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = (Button) findViewById(R.id.loginButton);
        signInTextView = (TextView) findViewById(R.id.signInTextView);
        trusty = (CheckBox) findViewById(R.id.trusty);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startHomeActivity();
            }
        });
        signInTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignUpActivity();
            }
        });

    }

    private void startHomeActivity() {

//        SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE).edit();
//
//        if (trusty.isChecked()) {
//
//            editor.putBoolean(getString(R.string.isTrusty), true);
//        } else {
//            editor.putBoolean(getString(R.string.isTrusty), false);
//        }

    //    editor.commit();
        Intent i = new Intent(this, CampaignListingActivity.class);
        startActivity(i);
    }

    private void startSignUpActivity() {
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);

    }
}
