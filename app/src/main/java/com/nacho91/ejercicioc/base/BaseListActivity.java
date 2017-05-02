package com.nacho91.ejercicioc.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.nacho91.ejercicioc.R;

/**
 * Created by ignacio on 02/05/17.
 */

public abstract class BaseListActivity extends AppCompatActivity {

    private CoordinatorLayout baseRoot;
    private RelativeLayout baseContainer;
    private ListView baseList;
    private Button nextButton;
    private ProgressBar progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        initControls();
        setListeners();

        toggleVisiblityView(baseContainer, false);
    }

    private void initControls(){
        baseRoot = (CoordinatorLayout) findViewById(R.id.base_root);
        baseContainer = (RelativeLayout) findViewById(R.id.base_container);
        baseList = (ListView) findViewById(R.id.base_list);
        nextButton = (Button) findViewById(R.id.base_next_button);
        progress = (ProgressBar) findViewById(R.id.base_progress);
    }

    private void setListeners(){
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNextButtonPressed();
            }
        });
    }

    public abstract void onNextButtonPressed();

    public abstract void onRetryButtonPressed();

    public void showError(String error){
        Snackbar.make(baseRoot, error, BaseTransientBottomBar.LENGTH_SHORT).show();
    }

    public void showRetryError(String error, String retry){
        Snackbar.make(baseRoot, error, BaseTransientBottomBar.LENGTH_INDEFINITE)
                .setAction(retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toggleVisiblityView(baseContainer, false);
                        toggleVisiblityView(progress, true);
                        onRetryButtonPressed();
                    }
                }).show();
    }

    public void setListAdapter(BaseAdapter adapter){
        toggleVisiblityView(progress, false);
        toggleVisiblityView(baseContainer, true);

        baseList.setAdapter(adapter);
    }

    public int getCheckedPosition(){
        return baseList.getCheckedItemPosition();
    }

    public void toggleVisiblityView(View view, boolean show){
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
