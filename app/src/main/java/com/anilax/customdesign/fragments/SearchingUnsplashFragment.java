package com.anilax.customdesign.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anilax.textart.R;
import com.anilax.customdesign.activities.EditPhotoActivity;
import com.anilax.customdesign.model.unsplash.Photo;
import com.anilax.customdesign.model.unsplash.SearchResults;
import com.anilax.customdesign.unsplash.PhotoRecyclerAdapter;
import com.anilax.customdesign.unsplash.SplashPicker;
import com.anilax.customdesign.unsplash.Unsplash;

import java.util.List;

public class SearchingUnsplashFragment extends Fragment {


    RecyclerView recyclerSearching;
    PhotoRecyclerAdapter photoRecyclerAdapter;
    Unsplash unsplash;

    String sendq;
    List<Photo> photoList;
    public int pageNumber = 1;

    class AddImagesUnsplash implements Unsplash.OnSearchCompleteListener {
        public void onError(String str) {
        }

        AddImagesUnsplash() {
        }

        public void onComplete(SearchResults searchResults) {
            SearchingUnsplashFragment.this.photoRecyclerAdapter.addImages(searchResults.getResults());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_searching_unsplash, viewGroup, false);
        this.recyclerSearching = inflate.findViewById(R.id.recyclerSearching);
        if (getArguments() != null) {
            this.sendq = getArguments().getString("sendq");
        }
        this.unsplash = new Unsplash("1d8b669a82fd44ee699880cdcfdb7e3bfc9a5c01aaa3e422dbb79b0faf0220f0");
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        this.recyclerSearching.setLayoutManager(gridLayoutManager);
        this.unsplash.searchPhotos(this.sendq, 1, 50, new Unsplash.OnSearchCompleteListener() {
            public void onError(String str) {
            }


            class SearchMore extends RecyclerView.OnScrollListener {
                SearchMore() {
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int i, int i2) {
                    super.onScrolled(recyclerView, i, i2);
                    int childCount = recyclerView.getChildCount();
                    int itemCount = SearchingUnsplashFragment.this.photoRecyclerAdapter.getItemCount();
                    int findFirstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition();
                    if ( childCount + findFirstVisibleItemPosition >= itemCount && findFirstVisibleItemPosition >= 0 && itemCount >= 4) {
                        SearchingUnsplashFragment.this.pageNumber = SearchingUnsplashFragment.this.pageNumber + 1;
                        SearchingUnsplashFragment.this.fetchdata();
                    }
                }
            }

            public void onComplete(SearchResults searchResults) {
                SearchingUnsplashFragment.this.photoList = searchResults.getResults();
                SearchingUnsplashFragment searchingUnsplashFragment = SearchingUnsplashFragment.this;
                searchingUnsplashFragment.photoRecyclerAdapter = new PhotoRecyclerAdapter(searchingUnsplashFragment.photoList, SearchingUnsplashFragment.this.getActivity(), (photo, imageView) -> {
                    Intent intent = new Intent(SearchingUnsplashFragment.this.getActivity(), EditPhotoActivity.class);
                    intent.putExtra(SplashPicker.KEY_IMAGE, photo);
                    SearchingUnsplashFragment.this.startActivity(intent);
                });
                SearchingUnsplashFragment.this.recyclerSearching.setAdapter(SearchingUnsplashFragment.this.photoRecyclerAdapter);
                if (SearchingUnsplashFragment.this.photoList.isEmpty()) {
                    Toast.makeText(SearchingUnsplashFragment.this.getActivity(), "NO RESULTS", Toast.LENGTH_LONG).show();
                }
                SearchingUnsplashFragment.this.recyclerSearching.addOnScrollListener(new SearchMore());
            }
        });
        return inflate;
    }


    public void fetchdata() {
        this.unsplash.searchPhotos(this.sendq, this.pageNumber, 50, new AddImagesUnsplash());
    }
}
