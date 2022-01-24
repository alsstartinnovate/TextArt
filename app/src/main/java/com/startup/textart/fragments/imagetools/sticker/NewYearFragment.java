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

public class NewYearFragment extends Fragment implements StickerAdapter.StickerAdaperListener {


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
        this.recyclerView =  inflate.findViewById(R.id.recyclerSticker);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        this.sampleArrayList = newyear();
        this.recyclerView.setAdapter(new StickerAdapter(this.sampleArrayList, getActivity(), this));
        return inflate;
    }

    public List<Sample> newyear() {
        ArrayList<Sample> arrayList = new ArrayList<>();
        arrayList.add(new Sample(R.drawable.rainbow01));
        arrayList.add(new Sample(R.drawable.rainbow02));
        arrayList.add(new Sample(R.drawable.rainbow03));
        arrayList.add(new Sample(R.drawable.rainbow04));
        arrayList.add(new Sample(R.drawable.rainbow05));
        arrayList.add(new Sample(R.drawable.rainbow06));
        arrayList.add(new Sample(R.drawable.rainbow07));
        arrayList.add(new Sample(R.drawable.rainbow08));
        arrayList.add(new Sample(R.drawable.rainbow09));
        arrayList.add(new Sample(R.drawable.rainbow10));
        arrayList.add(new Sample(R.drawable.rainbow11));
        arrayList.add(new Sample(R.drawable.rainbow12));
        arrayList.add(new Sample(R.drawable.rainbow13));
        arrayList.add(new Sample(R.drawable.rainbow14));
        arrayList.add(new Sample(R.drawable.rainbow15));
        arrayList.add(new Sample(R.drawable.rainbow16));
        arrayList.add(new Sample(R.drawable.rainbow17));
        arrayList.add(new Sample(R.drawable.rainbow18));
        arrayList.add(new Sample(R.drawable.rainbow19));
        arrayList.add(new Sample(R.drawable.rainbow20));
        return arrayList;
    }

    public void onStickerSelected(int i) {

        if (stickerListener != null) {
            stickerListener.onStickerClick(i);
        }
    }
}
