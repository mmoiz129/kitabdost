package com.invend.kitabdost;

import android.content.Intent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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

    boolean isTrusty;
    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_listing);


        SharedPreferences prefs = getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE);
        this.isTrusty = prefs.getBoolean(getString(R.string.isTrusty), false);

        create = (Button) findViewById(R.id.createCampaign);

        this.initUI();

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CampaignListingActivity.this, CreateCampaign.class);
                startActivity(i);
            }
        });


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
            protected void populateViewHolder(CampaignViewHolder viewHolder, final Campaign campaign, final int position) {
                if (campaign != null) {
                    Log.e("::uzair", position + "");
                    viewHolder.progressBar.setMax((int) campaign.getTotalAmount());
                    viewHolder.progressBar.setProgress((int) campaign.getAmountReceived() );
                    viewHolder.campaignName.setText(campaign.getCampaignName());
                    viewHolder.amountReceived.setText(String.valueOf(campaign.getAmountReceived()));
                    viewHolder.createdBy.setText(campaign.getName());
                    viewHolder.amountTotal.setText( String.valueOf(campaign.getTotalAmount())  );
                    Log.e("hello world", mFirebaseAdapter.getRef(position).getKey());

                }
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Campaign campaign1 = mFirebaseAdapter.getItem(position);
                        campaign1.setKey(mFirebaseAdapter.getRef(position).getKey());
                        startDetailActivity(campaign1);
                        Log.e("i m clicked", position + "");
                    }
                });
            }
        };
        campaignList.setAdapter(mFirebaseAdapter);
    }

    public void initUI() {
        if(this.isTrusty) {
            create.setVisibility(View.VISIBLE);
        } else {
            create.setVisibility(View.GONE);
        }
    }

    public void startDetailActivity(Campaign campaign) {
        Intent i = new Intent(this, CampaignDetailActivity.class);
        i.putExtra("campaign", campaign);
        startActivity(i);
    }
}
