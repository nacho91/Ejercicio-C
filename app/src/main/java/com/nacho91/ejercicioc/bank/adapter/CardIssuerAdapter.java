package com.nacho91.ejercicioc.bank.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nacho91.ejercicioc.R;
import com.nacho91.ejercicioc.model.CardIssuer;
import com.nacho91.ejercicioc.model.PaymentMethod;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Ignacio on 1/5/2017.
 */

public class CardIssuerAdapter extends BaseAdapter {
    private List<CardIssuer> cardIssuers;

    public CardIssuerAdapter(List<CardIssuer> payments) {
        this.cardIssuers = payments;
    }

    @Override
    public int getCount() {
        return cardIssuers.size();
    }

    @Override
    public Object getItem(int i) {
        return cardIssuers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_card_issuer, parent, false);
        }

        CardIssuer cardIssuer = cardIssuers.get(position);

        TextView cardIssuerName = (TextView) convertView.findViewById(android.R.id.text1);
        cardIssuerName.setText(cardIssuer.getName());

        ImageView cardIssuerImage = (ImageView) convertView.findViewById(R.id.adapter_card_issuer_image);
        ImageLoader.getInstance().displayImage(cardIssuer.getSecureThumbnail(), cardIssuerImage);

        return convertView;
    }
}
