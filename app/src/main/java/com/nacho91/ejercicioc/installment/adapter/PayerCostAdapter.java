package com.nacho91.ejercicioc.installment.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nacho91.ejercicioc.R;
import com.nacho91.ejercicioc.model.PayerCost;

import java.util.List;

/**
 * Created by Ignacio on 1/5/2017.
 */

public class PayerCostAdapter extends BaseAdapter {

    private List<PayerCost> payerCosts;

    public PayerCostAdapter(List<PayerCost> payerCosts) {
        this.payerCosts = payerCosts;
    }

    @Override
    public int getCount() {
        return payerCosts.size();
    }

    @Override
    public Object getItem(int i) {
        return payerCosts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_payer_cost, parent, false);
        }

        PayerCost payerCost = payerCosts.get(position);

        TextView payerCostMessage = (TextView) convertView.findViewById(android.R.id.text1);
        payerCostMessage.setText(payerCost.getRecommendedMessage());

        return convertView;
    }
}
