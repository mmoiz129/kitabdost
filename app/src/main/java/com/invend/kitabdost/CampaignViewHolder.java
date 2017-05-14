package com.invend.kitabdost;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Venturedive on 13/05/2017.
 */

public class CampaignViewHolder extends RecyclerView.ViewHolder {
    public TextView campaignName, createdBy, endDate, amountReceived, amountTotal,description;
    public ProgressBar progressBar;
    public CardView cardView;
    public ImageView imageView;

    public CampaignViewHolder(View itemView) {
        super(itemView);
        campaignName = (TextView) itemView.findViewById(R.id.campaignName);
        createdBy = (TextView) itemView.findViewById(R.id.createdBy);
        endDate = (TextView) itemView.findViewById(R.id.endDate);
        amountReceived = (TextView) itemView.findViewById(R.id.amountReceived);
        amountTotal = (TextView) itemView.findViewById(R.id.amountTotal);
        progressBar = (ProgressBar) itemView.findViewById(R.id.progress);
        description = (TextView) itemView.findViewById(R.id.description);
        cardView = (CardView) itemView.findViewById(R.id.cardView);
        imageView =(ImageView)itemView.findViewById(R.id.campaignPhoto);

    }
}
