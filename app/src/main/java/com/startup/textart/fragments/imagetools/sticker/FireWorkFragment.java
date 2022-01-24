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

public class FireWorkFragment extends Fragment implements StickerAdapter.StickerAdaperListener {
    RecyclerView recyclerView;
    List<Sample> sampleArrayList;
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
        this.sampleArrayList = fireWorks();
        this.recyclerView.setAdapter(new StickerAdapter(this.sampleArrayList, getActivity(), this));
        return inflate;
    }

    public List<Sample> fireWorks() {
        List<Sample> arrayList = new ArrayList<>();
        arrayList.add(new Sample(R.drawable.firework1));
        arrayList.add(new Sample(R.drawable.firework2));
        arrayList.add(new Sample(R.drawable.firework3));
        arrayList.add(new Sample(R.drawable.firework4));
        arrayList.add(new Sample(R.drawable.firework5));
        arrayList.add(new Sample(R.drawable.firework6));
        arrayList.add(new Sample(R.drawable.firework7));
        arrayList.add(new Sample(R.drawable.firework8));
        arrayList.add(new Sample(R.drawable.firework9));
        arrayList.add(new Sample(R.drawable.firework10));
        arrayList.add(new Sample(R.drawable.firework11));
        return arrayList;
    }

    public void onStickerSelected(int i) {

        if (stickerListener != null) {
            stickerListener.onStickerClick(i);
        }
    }
}
