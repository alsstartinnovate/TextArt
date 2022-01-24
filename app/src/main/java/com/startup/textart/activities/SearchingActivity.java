package com.startup.textart.activities;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.startup.textart.R;
import com.startup.textart.ads.AdmobAds;

import com.startup.textart.fragments.SearchingUnsplashFragment;
import com.startup.textart.model.unsplash.Photo;
import com.startup.textart.unsplash.PhotoRecyclerAdapter;
import com.startup.textart.unsplash.SplashPicker;
import com.startup.textart.unsplash.Unsplash;
import com.startup.textart.unsplash.api.Order;
import com.startup.textart.views.EndlessRecyclerViewScrollListener;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class SearchingActivity extends AppCompatActivity implements PhotoRecyclerAdapter.OnPhotoClickedListener {

    public PhotoRecyclerAdapter adapter;

    private final FragmentManager fragmentManager = getSupportFragmentManager();
    private GridLayoutManager layoutManager;

    public int page = 1;

    private RecyclerView recyclerUnsplash;
    private MaterialSearchBar searchBar;

    public SwipeRefreshLayout swipeContainer;
    private Unsplash unsplash;


    static int getPage(SearchingActivity searchingActivity) {
        int i = searchingActivity.page;
        searchingActivity.page = i + 1;
        return i;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_searching);
        showAd();
        this.swipeContainer = findViewById(R.id.swipeContainer);
        this.swipeContainer.setOnRefreshListener(() -> {
            SearchingActivity.this.page = 1;
            SearchingActivity.this.swipeContainer.setRefreshing(false);
        });
        this.searchBar = findViewById(R.id.searchBar);
        this.searchBar.hideSuggestionsList();
        this.recyclerUnsplash = findViewById(R.id.recyclerUnsplash);
        this.unsplash = new Unsplash("1d8b669a82fd44ee699880cdcfdb7e3bfc9a5c01aaa3e422dbb79b0faf0220f0");
        getData();
        this.layoutManager = new GridLayoutManager(this, 2);
        this.recyclerUnsplash.setLayoutManager(this.layoutManager);
        if (!isNetworkConnected(SearchingActivity.this)) {
            Toast.makeText(this, "No Internet", Toast.LENGTH_LONG).show();
        } else {
            isNetworkConnected(SearchingActivity.this);
        }
        setupPhotoGrid();
        setupSearch();
    }

    private void getData() {
        String stringExtra = getIntent().getStringExtra("SUGGEST");
        if (stringExtra != null) {
            searchText(stringExtra);
            this.searchBar.setText(stringExtra);
        }
    }

    private void showAd() {
        AdmobAds.loadBanner(this);

    }

    private void setupSearch() {
        this.searchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            public void onButtonClicked(int i) {
            }

            public void onSearchStateChanged(boolean z) {
            }

            public void onSearchConfirmed(CharSequence charSequence) {
                SearchingActivity.this.searchText(String.valueOf(charSequence));
            }
        });
    }


    public void searchText(String str) {
        FragmentTransaction beginTransaction = this.fragmentManager.beginTransaction();
        SearchingUnsplashFragment research = new SearchingUnsplashFragment();
        beginTransaction.addToBackStack("ttt");
        beginTransaction.add(R.id.frameSearching, research, "fragment");
        Bundle bundle = new Bundle();
        bundle.putString("sendq", str);
        research.setArguments(bundle);
        beginTransaction.commit();
        this.searchBar.clearFocus();
        ((InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(this.searchBar.getWindowToken(), 0);
    }


    public void loadPhotos() {
        this.unsplash.getPhotos(this.page, 10, Order.LATEST, new Unsplash.OnPhotosLoadedListener() {
            public void onError(String str) {
            }

            public void onComplete(List<Photo> list) {
                SearchingActivity.getPage(SearchingActivity.this);
                SearchingActivity.this.adapter.addPhotos(list);
            }
        });
    }

    private void setupPhotoGrid() {
        this.adapter = new PhotoRecyclerAdapter(new ArrayList<>(), this, this);
        this.recyclerUnsplash.setAdapter(this.adapter);
        this.recyclerUnsplash.addOnScrollListener(new EndlessRecyclerViewScrollListener(this.layoutManager) {
            public void onLoadMore(int i, int i2, RecyclerView recyclerView) {
                SearchingActivity.this.loadPhotos();
            }
        });
        loadPhotos();
    }

    public void photoClicked(Photo photo, ImageView imageView) {

        startEditPhoto(photo);
    }

    private void startEditPhoto(Photo photo) {

        Intent intent = new Intent(this, EditPhotoActivity.class);
        intent.putExtra(SplashPicker.KEY_IMAGE, photo);
        startActivity(intent);
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onBackPressed() {
        if (this.fragmentManager.getBackStackEntryCount() > 0) {
            this.fragmentManager.popBackStack(null, 1);
        } else {
            super.onBackPressed();
        }
    }


    public boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        Network[] networks = cm.getAllNetworks();
        boolean hasInternet = false;
        if (networks.length > 0) {
            for (Network network : networks) {
                NetworkCapabilities nc = cm.getNetworkCapabilities(network);
                if (nc != null && nc.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET))
                    hasInternet = true;
            }
        }
        return hasInternet;
    }
}
