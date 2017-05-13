package com.invend.kitabdost;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import models.Campaign;

public class CampaignListingActivity extends AppCompatActivity {
    private FirebaseRecyclerAdapter<Campaign, CampaignViewHolder> mFirebaseAdapter;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    RecyclerView campaignList;
    private LinearLayoutManager mLinearLayoutManager;
    ArrayList<Campaign> campaignArrayList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_listing);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        campaignList = (RecyclerView) findViewById(R.id.campaignList);
        mLinearLayoutManager = new LinearLayoutManager(this);
        campaignList.setLayoutManager(mLinearLayoutManager);

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Campaign, CampaignViewHolder>(Campaign.class, R.layout.campaign_item,
                CampaignViewHolder.class, databaseReference.child("Campaigns")) {
            @Override
            protected void populateViewHolder(CampaignViewHolder viewHolder, Campaign campaign, int position) {
                if (campaign != null) {
                    Log.e("::uzair", position + "");
                    viewHolder.campaignName.setText(campaign.getCampaignName());
                    viewHolder.amountReceived.setText(String.valueOf(campaign.getAmountReceived()));
                    viewHolder.createdBy.setText(campaign.getName());
                }
            }
        };
        campaignList.setAdapter(mFirebaseAdapter);
    }

}
