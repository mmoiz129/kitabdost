package com.invend.kitabdost;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Venturedive on 13/05/2017.
 */

public class CampaignViewHolder extends RecyclerView.ViewHolder {
    public TextView campaignName, createdBy, endDate, amountReceived, amountTotal;
    public ProgressBar progressBar;

    public CampaignViewHolder(View itemView) {
        super(itemView);
        campaignName = (TextView) itemView.findViewById(R.id.campaignName);
        createdBy = (TextView) itemView.findViewById(R.id.createdBy);
        endDate = (TextView) itemView.findViewById(R.id.endDate);
        amountReceived = (TextView) itemView.findViewById(R.id.amountReceived);
        amountTotal = (TextView) itemView.findViewById(R.id.amountTotal);
        progressBar = (ProgressBar) itemView.findViewById(R.id.progress);

    }
}
