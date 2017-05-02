package com.nacho91.ejercicioc.payment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nacho91.ejercicioc.R;
import com.nacho91.ejercicioc.model.PaymentMethod;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


import java.util.List;

/**
 * Created by Ignacio on 1/5/2017.
 */

public class PaymentMethodAdapter extends BaseAdapter{

    private List<PaymentMethod> payments;

    public PaymentMethodAdapter(List<PaymentMethod> payments) {
        this.payments = payments;
    }

    @Override
    public int getCount() {
        return payments.size();
    }

    @Override
    public Object getItem(int i) {
        return payments.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_payment_method, parent, false);
        }

        PaymentMethod paymentMethod = payments.get(position);

        TextView paymentName = (TextView) convertView.findViewById(android.R.id.text1);
        paymentName.setText(paymentMethod.getName());

        ImageView paymentImage = (ImageView) convertView.findViewById(R.id.adapter_payment_image);
        ImageLoader.getInstance().displayImage(paymentMethod.getSecureThumbnail(), paymentImage);

        return convertView;
    }
}
