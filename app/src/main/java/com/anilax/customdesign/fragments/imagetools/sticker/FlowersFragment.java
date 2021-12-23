package com.anilax.customdesign.fragments.imagetools.sticker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.anilax.textart.R;
import com.anilax.customdesign.interfaces.StickerListener;
import com.anilax.customdesign.model.Sample;
import java.util.ArrayList;

public class FlowersFragment extends Fragment implements StickerAdapter.StickerAdaperListener {


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
        this.sampleArrayList = flowerList();
        this.recyclerView.setAdapter(new StickerAdapter(this.sampleArrayList, getActivity(), this));
        return inflate;
    }

    private ArrayList<Sample> flowerList() {
        ArrayList<Sample> arrayList = new ArrayList<>();
        arrayList.add(new Sample(R.drawable.deco1));
        arrayList.add(new Sample(R.drawable.deco2));
        arrayList.add(new Sample(R.drawable.deco3));
        arrayList.add(new Sample(R.drawable.deco4));
        arrayList.add(new Sample(R.drawable.deco5));
        arrayList.add(new Sample(R.drawable.deco7));
        arrayList.add(new Sample(R.drawable.deco8));
        arrayList.add(new Sample(R.drawable.deco9));
        arrayList.add(new Sample(R.drawable.deco10));
        return arrayList;
    }

    public void onStickerSelected(int i) {

        if (stickerListener != null) {
            stickerListener.onStickerClick(i);
        }
    }
}
