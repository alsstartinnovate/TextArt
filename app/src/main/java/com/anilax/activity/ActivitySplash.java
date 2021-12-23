package com.anilax.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.anilax.customdesign.activities.EditPhotoActivity;
import com.anilax.textart.R;
import com.commonutility.FontUtils;
import com.commonutility.GlobalData;
import com.commonutility.GlobalVariables;
import com.commonutility.ImageLoading;
import com.commonutility.PreferenceConnector;
import com.commonutility.ShowCustomToast;

public class ActivitySplash extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GlobalData.Fullscreen(ActivitySplash.this);
        setContentView(R.layout.act_splash);

        StartApp();
        ImageLoading.loadLocalImages(R.drawable.bg, (ImageView)findViewById(R.id.img_back));
        ImageLoading.loadLocalImages(R.drawable.logo, (ImageView)findViewById(R.id.img_logo));
        //Start Coding from here
        PreferenceConnector.writeBoolean(svContext, PreferenceConnector.ISDARKTHEME, false);
        resumeApp();
    }

    public void resumeApp() {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    Intent svIntent;
                    if (PreferenceConnector.readBoolean(svContext, PreferenceConnector.ISINTROSHOW, false)) {
                        svIntent = new Intent(svContext, EditPhotoActivity.class);
                    } else {
                        PreferenceConnector.writeBoolean(svContext, PreferenceConnector.ISINTROSHOW, true);
                        svIntent = new Intent(svContext, ActivityIntro.class);
                    }
                    startActivity(svIntent);
                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                    finish();
                }
            }, 3000);
    }

    private Context svContext;
    private ShowCustomToast customToast;
    private ViewGroup root;
    private void StartApp() {
        svContext = this;
        customToast = new ShowCustomToast(svContext);
        root = (ViewGroup) findViewById(R.id.headlayout);
        if (!(GlobalVariables.CUSTOMFONTNAME).equals("")) {
            Typeface font = Typeface.createFromAsset(getAssets(), GlobalVariables.CUSTOMFONTNAME);
            FontUtils.setFont(root, font);
        }

        //change app heme from
        if (PreferenceConnector.readBoolean(svContext, PreferenceConnector.ISDARKTHEME, false)) {
//            FontUtils.setThemeColor(root, svContext, true);
        } else {
//            FontUtils.setThemeColor(root, svContext, false);
        }

        hideKeyboard();
        GlobalData.SetLanguage(svContext);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        // check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static void hideFragmentkeyboard(Context meraContext, View meraView) {
        final InputMethodManager imm = (InputMethodManager) meraContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(meraView.getWindowToken(), 0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}