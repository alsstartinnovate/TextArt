package com.anilax.customdesign.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anilax.textart.R;
import com.anilax.customdesign.adapter.SuggestAdapter;
import com.anilax.customdesign.adapter.sample.BackgroundColorAdapter;
import com.anilax.customdesign.adapter.sample.BackgroundImageAdapter;
import com.anilax.customdesign.adapter.sample.BackgroundImageAdapter2;
import com.anilax.customdesign.adapter.sample.GenDataBackGround;
import com.anilax.customdesign.ads.AdmobAds;


import com.anilax.customdesign.ads.FacebookAds;
import com.anilax.customdesign.model.Sample;

import java.util.Objects;


public class SampleActivity extends AppCompatActivity {


    private RecyclerView recyclerColors;
    private RecyclerView recyclerFlower;
    private RecyclerView recyclerNature;
    private RecyclerView recyclerNightSky;
    private RecyclerView recyclerSuggest;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_sample);
        Toolbar toolbarSample = findViewById(R.id.toolbar_sample);
        Drawable drawable = ContextCompat.getDrawable(SampleActivity.this, R.drawable.ic_arrow_back);
        setSupportActionBar(toolbarSample);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        this.recyclerColors = findViewById(R.id.recyclerColor);
        setRecyclerColors();
        this.recyclerNature = findViewById(R.id.recyclerNature);
        setRecyclerNature();
        this.recyclerFlower = findViewById(R.id.recyclerFlowers);
        setRecyclerFlowers();
        this.recyclerNightSky = findViewById(R.id.recyclerNight);
        setRecyclerNightSky();
        this.recyclerSuggest = findViewById(R.id.recyclerSuggest);
        setRecyclerSuggest();
        FacebookAds.loadNativeAdsBanner(this);
        AdmobAds.loadNativeAds(this, null);
    }

    private void setRecyclerSuggest() {
        this.recyclerSuggest.setHasFixedSize(true);
        this.recyclerSuggest.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        SuggestAdapter suggestAdapter = new SuggestAdapter(GenDataBackGround.suggest(), this, (view, i) -> SampleActivity.this.sendSuggest(GenDataBackGround.suggest().get(i)));
        this.recyclerSuggest.setAdapter(suggestAdapter);
    }

    private void setRecyclerNightSky() {
        this.recyclerNightSky.setHasFixedSize(true);
        this.recyclerNightSky.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        BackgroundImageAdapter nightSkyAdapter = new BackgroundImageAdapter(GenDataBackGround.nightList(), this, (view, i) -> SampleActivity.this.sendData(GenDataBackGround.nightList().get(i)));
        this.recyclerNightSky.setAdapter(nightSkyAdapter);
    }

    private void setRecyclerFlowers() {
        this.recyclerFlower.setHasFixedSize(true);
        this.recyclerFlower.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        BackgroundImageAdapter2 flowersAdapter = new BackgroundImageAdapter2(GenDataBackGround.flowersList(), this, (view, i) -> SampleActivity.this.sendData(GenDataBackGround.flowersList().get(i)));
        this.recyclerFlower.setAdapter(flowersAdapter);
    }

    private void setRecyclerNature() {
        this.recyclerNature.setHasFixedSize(true);
        this.recyclerNature.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        BackgroundImageAdapter natureAdapter = new BackgroundImageAdapter(GenDataBackGround.nativeList(), this, (view, i) -> SampleActivity.this.sendData(GenDataBackGround.nativeList().get(i)));
        this.recyclerNature.setAdapter(natureAdapter);
    }

    private void setRecyclerColors() {
        this.recyclerColors.setHasFixedSize(true);
        this.recyclerColors.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        BackgroundColorAdapter colorAdapter = new BackgroundColorAdapter(GenDataBackGround.colorList(), this, (view, i) -> SampleActivity.this.sendData(GenDataBackGround.colorList().get(i)));
        this.recyclerColors.setAdapter(colorAdapter);
    }


    public void sendData(Sample sample) {

        Intent intent = new Intent(this, EditPhotoActivity.class);
        intent.putExtra("SampleBackground", sample.getImgSample());
        startActivity(intent);
    }

    @Override
    public void onStop() {
        super.onStop();

    }


    public void sendSuggest(String str) {
        Intent intent = new Intent(this, SearchingActivity.class);
        intent.putExtra("SUGGEST", str);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_search) {
            startActivity(new Intent(this, SearchingActivity.class));
        }
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        super.onBackPressed();
        return true;
    }
}
