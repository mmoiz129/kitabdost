package com.invend.kitabdost;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import models.Campaign;


public class CreateCampaign extends AppCompatActivity {

    Button endDateButton, signupButton;
    public static TextView endDateTV;
    public EditText name, description, campaignName, totalAmount;

    Campaign campaign;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private static final String CHILD = "Campaigns";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_campaign);

        campaign = new Campaign();

        endDateButton = (Button) findViewById(R.id.endDateButton);
        endDateTV = (TextView) findViewById(R.id.endDateTV);

        name = (EditText) findViewById(R.id.name);
        description = (EditText) findViewById(R.id.description);
        campaignName = (EditText) findViewById(R.id.campaignName);
        totalAmount = (EditText) findViewById(R.id.totalAmount);
        signupButton = (Button) findViewById(R.id.signup);



        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createCampaign();

            }
        });

        endDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");

            }
        });

    }

    public void createCampaign() {

        campaign.setCampaignName(campaignName.getText().toString());
        campaign.setName(name.getText().toString());
        campaign.setDescription(description.getText().toString());
        campaign.setTotalAmount( Long.parseLong(totalAmount.getText().toString()) );
        campaign.setEndDate(endDateTV.getText().toString());

        databaseReference.child(CHILD).push().setValue(campaign);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Campaign Successfull Created")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        finish();

                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    public static class DatePickerFragment extends android.support.v4.app.DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final java.util.Calendar c = java.util.Calendar.getInstance();
            int year = c.get(java.util.Calendar.YEAR);
            int month = c.get(java.util.Calendar.MONTH);
            int day = c.get(java.util.Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            endDateTV.setText(day + "/" + month + "/" + year);
        }
    }




}

