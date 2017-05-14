package com.invend.kitabdost;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import models.Campaign;

public class CampaignDetailActivity extends AppCompatActivity {
    Campaign campaign;
    public TextView campaignName, createdBy, endDate, amountReceived, amountTotal,description;
    public EditText donateAmountET;
    public Button donateButton;
    public ProgressBar progressBar;
    public CardView cardView;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference childReference = databaseReference.child("Campaigns");
    Map<String, Object> campaignUpdate = new HashMap<String, Object>();
    long amountRec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        campaignName = (TextView) findViewById(R.id.campaignName);
        createdBy = (TextView) findViewById(R.id.createdBy);
        endDate = (TextView) findViewById(R.id.endDate);
        amountReceived = (TextView) findViewById(R.id.amountReceived);
        amountTotal = (TextView) findViewById(R.id.amountTotal);
        cardView = (CardView) findViewById(R.id.cardView);
        donateButton = (Button) findViewById(R.id.donateButton);
        //donationAmount = (EditText) findViewById(R.id.donationAmount);
        description = (TextView) findViewById(R.id.description);


        campaign = (Campaign) getIntent().getSerializableExtra("campaign");
        campaignName.setText(campaign.getCampaignName());
        createdBy.setText("by " + campaign.getName());
        amountRec = campaign.getAmountReceived();
        amountReceived.setText("PKR " + String.valueOf(campaign.getAmountReceived()));
        description.setText(campaign.getDescription());
        amountTotal.setText("/ PKR " + String.valueOf(campaign.totalAmount));
        childReference = childReference.child(campaign.getKey());
        donateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                donateDialog();

            }
        });


    }

    private void donateAmount() {

        amountRec = amountRec + Long.valueOf(donateAmountET.getText().toString());
        campaignUpdate.put("amountReceived", amountRec);
        childReference.updateChildren(campaignUpdate);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Donation Complete")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        finish();

                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void donateDialog() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);
        donateAmountET = (EditText) dialogView.findViewById(R.id.amountDonate);

        dialogBuilder.setTitle("Donate Now");
        dialogBuilder.setMessage("Account Number");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                amountRec = amountRec + Long.valueOf(donateAmountET.getText().toString());
                campaignUpdate.put("amountReceived", amountRec);
                childReference.updateChildren(campaignUpdate);
                AlertDialog.Builder builder = new AlertDialog.Builder(CampaignDetailActivity.this);
                builder.setMessage("Donation Complete")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                finish();

                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();

    }
}
