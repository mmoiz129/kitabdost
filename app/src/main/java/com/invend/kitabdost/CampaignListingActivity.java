package com.invend.kitabdost;

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
import android.widget.LinearLayout;
import android.widget.ProgressBar;

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
    FloatingActionButton fab;
    boolean isTrusty;
    Button create;
    private ProgressBar spinner;
    LinearLayout progressContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_listing);


        SharedPreferences prefs = getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE);
        this.isTrusty = prefs.getBoolean(getString(R.string.isTrusty), false);
        create = (Button) findViewById(R.id.createCampaign);

        spinner = (ProgressBar) findViewById(R.id.progressBar1);
        spinner.setVisibility(View.VISIBLE);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CampaignListingActivity.this, CreateCampaign.class);
                startActivity(i);
            }
        });

        this.initUI();

        campaignList = (RecyclerView) findViewById(R.id.campaignList);
        mLinearLayoutManager = new LinearLayoutManager(this);
        campaignList.setLayoutManager(mLinearLayoutManager);

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Campaign, CampaignViewHolder>(Campaign.class, R.layout.campaign_item,
                CampaignViewHolder.class, databaseReference.child("Campaigns")) {
            @Override
            protected void populateViewHolder(CampaignViewHolder viewHolder, final Campaign campaign, final int position) {
                if (campaign != null) {
                    Log.e("::uzair", position + "");
                    viewHolder.progressBar.setMax(100);
                    viewHolder.progressBar.setProgress((int) calculateProgress((int) campaign.getTotalAmount(), (int) campaign.getAmountReceived()));
                    viewHolder.campaignName.setText(campaign.getCampaignName());
                    viewHolder.amountReceived.setText("PKR " + String.valueOf(campaign.getAmountReceived()));
                    viewHolder.createdBy.setText("by " + campaign.getName());
                    viewHolder.amountTotal.setText( "/ PKR " + String.valueOf(campaign.getTotalAmount())  );
                    viewHolder.description.setText(campaign.getDescription());
                    int mod = position % 3;
                    if (mod == 0)
                        viewHolder.imageView.setImageResource(R.drawable.books);
                    else if (mod == 1)
                        viewHolder.imageView.setImageResource(R.drawable.furniture);
                    else
                        viewHolder.imageView.setImageResource(R.drawable.stationary);

                    Log.e("hello world", mFirebaseAdapter.getRef(position).getKey());

                }
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Campaign campaign1 = mFirebaseAdapter.getItem(position);
                        campaign1.setKey(mFirebaseAdapter.getRef(position).getKey());
                        int mod = position % 3;
                        startDetailActivity(campaign1,mod);
                        Log.e("i m clicked", position + "");
                    }
                });

                spinner.setVisibility(View.GONE);
            }
        };
        campaignList.setAdapter(mFirebaseAdapter);
    }

    public int calculateProgress(int total, int amount) {
        if (total != 0 && amount != 0) {

            double _amount = Double.valueOf("" + amount);
            double _total = Double.valueOf("" + total);
            Double ans = (_amount / _total);
            Double percentage = ans * 100;


            return percentage.intValue();

        } else {
            return 0;
        }

    }

    public void initUI() {
        if (this.isTrusty) {
            fab.setVisibility(View.VISIBLE);
            //create.setVisibility(View.VISIBLE);
        } else {
            fab.setVisibility(View.GONE);
            //create.setVisibility(View.GONE);
        }
    }

    public void startDetailActivity(Campaign campaign, int mod) {
        Intent i = new Intent(this, CampaignDetailActivity.class);
        i.putExtra("campaign", campaign);
        i.putExtra("mod", mod);
        startActivity(i);
    }
}
