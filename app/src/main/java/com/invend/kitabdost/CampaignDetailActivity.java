package com.invend.kitabdost;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import models.Campaign;

public class CampaignDetailActivity extends AppCompatActivity {
    Campaign campaign;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference childReference = databaseReference.child("Campaigns");
    Map<String, Object> campaignUpdate = new HashMap<String, Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //FloatingActionButton fab = (FloatingActionButton) findViewById(fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        campaign = (Campaign) getIntent().getSerializableExtra("campaign");
        childReference = childReference.child(campaign.getKey());
        campaignUpdate.put("name", "nameIsUpdated");
        childReference.updateChildren(campaignUpdate);
    }

}
