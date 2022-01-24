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
import java.util.List;

public class StickerChistmasFragment extends Fragment implements StickerAdapter.StickerAdaperListener {


    RecyclerView recyclerView;

    List<Sample> chistmasList;


    StickerListener stickerListener;

    public void setStickerListener(StickerListener stickerListener) {
        this.stickerListener = stickerListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_sticker_recyclerview, viewGroup, false);
        this.recyclerView = inflate.findViewById(R.id.recyclerSticker);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        this.chistmasList = chistmasList();
        this.recyclerView.setAdapter(new StickerAdapter(this.chistmasList, getActivity(), this));
        return inflate;
    }

    public List<Sample> chistmasList() {
        List<Sample> arrayList = new ArrayList<>();
        arrayList.add(new Sample(R.drawable.noel1));
        arrayList.add(new Sample(R.drawable.noel2));
        arrayList.add(new Sample(R.drawable.noel3));
        arrayList.add(new Sample(R.drawable.noel4));
        arrayList.add(new Sample(R.drawable.noel5));
        arrayList.add(new Sample(R.drawable.noel6));
        arrayList.add(new Sample(R.drawable.noel7));
        return arrayList;
    }

    public void onStickerSelected(int i) {

        if (stickerListener != null) {
            stickerListener.onStickerClick(i);
        }
    }
}
