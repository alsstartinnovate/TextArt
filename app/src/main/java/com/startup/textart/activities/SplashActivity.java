package com.startup.textart.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.startup.textart.R;
import com.startup.textart.ads.AdmobAds;

import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.MobileAds;

public class SplashActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(1024, 1024);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        loadAds();
        new Handler().postDelayed(() -> {
            SplashActivity.this.startToMainActivity();
            AdmobAds.showFullAds(null);
        }, 3000);
    }

    private void loadAds() {
        MobileAds.initialize(this);
        AdmobAds.initFullAds(this);
        AudienceNetworkAds.initialize(this);


    }


    public void startToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
