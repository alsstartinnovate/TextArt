package com.anilax.activity;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.anilax.textart.R;

public class SetActionTopBar {
    private TextView textTopBarTitle;
    private ImageView imgBack;
    private Activity act;
    public SetActionTopBar(View view, Activity activity, String strHead) {
        this.act = activity;
        textTopBarTitle = (TextView) view.findViewById(R.id.heading);
        imgBack = (ImageView) view.findViewById(R.id.img_back);

        textTopBarTitle.setText(strHead);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                onBackButtonClicked(act);
            }
        });
    }

    private void onBackButtonClicked(Activity act) {
        act.onBackPressed();
    }
}
