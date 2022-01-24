package com.startup.textart.fragments.imagetools.sticker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.startup.textart.R;
import com.startup.textart.interfaces.StickerListener;
import com.startup.textart.model.Sample;

import java.util.ArrayList;

public class LoveFragment extends Fragment implements StickerAdapter.StickerAdaperListener {
    RecyclerView recyclerView;
    ArrayList<Sample> sampleArrayList;
    StickerListener stickerListener;

    public void setStickerListener(StickerListener stickerListener) {
        this.stickerListener = stickerListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_sticker_recyclerview, viewGroup, false);
        this.recyclerView =  inflate.findViewById(R.id.recyclerSticker);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        this.sampleArrayList = heartList();
        this.recyclerView.setAdapter(new StickerAdapter(this.sampleArrayList, getActivity(), this));
        return inflate;
    }

    private ArrayList<Sample> heartList() {
        ArrayList<Sample> arrayList = new ArrayList<>();
        arrayList.add(new Sample(R.drawable.heart1));
        arrayList.add(new Sample(R.drawable.heart2));
        arrayList.add(new Sample(R.drawable.heart3));
        arrayList.add(new Sample(R.drawable.heart5));
        arrayList.add(new Sample(R.drawable.heart6));
        arrayList.add(new Sample(R.drawable.heart7));
        arrayList.add(new Sample(R.drawable.heart8));
        arrayList.add(new Sample(R.drawable.heart9));
        arrayList.add(new Sample(R.drawable.heart10));
        arrayList.add(new Sample(R.drawable.heart11));
        arrayList.add(new Sample(R.drawable.heart12));
        arrayList.add(new Sample(R.drawable.heart13));
        arrayList.add(new Sample(R.drawable.heart14));
        arrayList.add(new Sample(R.drawable.heart15));
        arrayList.add(new Sample(R.drawable.heart16));
        arrayList.add(new Sample(R.drawable.heart17));
        arrayList.add(new Sample(R.drawable.heart18));
        arrayList.add(new Sample(R.drawable.heart19));
        arrayList.add(new Sample(R.drawable.heart20));
        arrayList.add(new Sample(R.drawable.heart21));
        arrayList.add(new Sample(R.drawable.heart22));
        arrayList.add(new Sample(R.drawable.heart23));
        arrayList.add(new Sample(R.drawable.heart24));
        arrayList.add(new Sample(R.drawable.heart25));
        arrayList.add(new Sample(R.drawable.heart26));
        arrayList.add(new Sample(R.drawable.heart27));
        arrayList.add(new Sample(R.drawable.heart28));
        arrayList.add(new Sample(R.drawable.heart29));
        arrayList.add(new Sample(R.drawable.heart30));
        arrayList.add(new Sample(R.drawable.heart31));
        arrayList.add(new Sample(R.drawable.heart32));
        arrayList.add(new Sample(R.drawable.heart33));
        arrayList.add(new Sample(R.drawable.heart34));
        arrayList.add(new Sample(R.drawable.heart35));
        arrayList.add(new Sample(R.drawable.heart36));
        arrayList.add(new Sample(R.drawable.heart37));
        arrayList.add(new Sample(R.drawable.heart38));
        arrayList.add(new Sample(R.drawable.heart39));
        arrayList.add(new Sample(R.drawable.heart40));
        arrayList.add(new Sample(R.drawable.heart41));
        arrayList.add(new Sample(R.drawable.heart42));
        arrayList.add(new Sample(R.drawable.heart43));
        arrayList.add(new Sample(R.drawable.heart44));
        arrayList.add(new Sample(R.drawable.heart45));
        arrayList.add(new Sample(R.drawable.heart46));
        arrayList.add(new Sample(R.drawable.heart47));
        arrayList.add(new Sample(R.drawable.heart48));
        arrayList.add(new Sample(R.drawable.heart49));
        arrayList.add(new Sample(R.drawable.heart50));
        arrayList.add(new Sample(R.drawable.heart51));
        arrayList.add(new Sample(R.drawable.heart52));
        return arrayList;
    }

    public void onStickerSelected(int i) {

        if (stickerListener != null) {
            stickerListener.onStickerClick(i);
        }
    }
}
