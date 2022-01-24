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

public class LightFragment extends Fragment implements StickerAdapter.StickerAdaperListener {


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
        this.sampleArrayList = light();
        this.recyclerView.setAdapter(new StickerAdapter(this.sampleArrayList, getActivity(), this));
        return inflate;
    }

    public List<Sample> light() {
        ArrayList<Sample> arrayList = new ArrayList<>();
        arrayList.add(new Sample(R.drawable.sticker01));
        arrayList.add(new Sample(R.drawable.sticker02));
        arrayList.add(new Sample(R.drawable.sticker03));
        arrayList.add(new Sample(R.drawable.sticker04));
        arrayList.add(new Sample(R.drawable.sticker05));
        arrayList.add(new Sample(R.drawable.sticker06));
        arrayList.add(new Sample(R.drawable.sticker07));
        arrayList.add(new Sample(R.drawable.sticker08));
        arrayList.add(new Sample(R.drawable.sticker09));
        arrayList.add(new Sample(R.drawable.sticker10));
        arrayList.add(new Sample(R.drawable.sticker11));
        arrayList.add(new Sample(R.drawable.sticker12));
        arrayList.add(new Sample(R.drawable.sticker13));
        arrayList.add(new Sample(R.drawable.sticker14));
        arrayList.add(new Sample(R.drawable.sticker15));
        arrayList.add(new Sample(R.drawable.sticker16));
        arrayList.add(new Sample(R.drawable.sticker17));
        arrayList.add(new Sample(R.drawable.sticker18));
        arrayList.add(new Sample(R.drawable.sticker19));
        arrayList.add(new Sample(R.drawable.sticker20));
        arrayList.add(new Sample(R.drawable.sticker21));
        arrayList.add(new Sample(R.drawable.sticker22));
        arrayList.add(new Sample(R.drawable.sticker23));
        arrayList.add(new Sample(R.drawable.sticker24));
        arrayList.add(new Sample(R.drawable.sticker25));
        arrayList.add(new Sample(R.drawable.sticker26));
        arrayList.add(new Sample(R.drawable.sticker27));
        arrayList.add(new Sample(R.drawable.sticker28));
        arrayList.add(new Sample(R.drawable.sticker29));
        arrayList.add(new Sample(R.drawable.sticker30));
        arrayList.add(new Sample(R.drawable.sticker31));
        arrayList.add(new Sample(R.drawable.sticker32));
        arrayList.add(new Sample(R.drawable.sticker33));
        arrayList.add(new Sample(R.drawable.sticker34));
        arrayList.add(new Sample(R.drawable.sticker35));
        arrayList.add(new Sample(R.drawable.sticker36));
        arrayList.add(new Sample(R.drawable.sticker37));
        arrayList.add(new Sample(R.drawable.sticker38));
        arrayList.add(new Sample(R.drawable.sticker39));
        arrayList.add(new Sample(R.drawable.sticker40));
        arrayList.add(new Sample(R.drawable.sticker41));
        arrayList.add(new Sample(R.drawable.sticker42));
        arrayList.add(new Sample(R.drawable.sticker43));
        arrayList.add(new Sample(R.drawable.sticker44));
        arrayList.add(new Sample(R.drawable.sticker45));
        arrayList.add(new Sample(R.drawable.sticker46));
        arrayList.add(new Sample(R.drawable.sticker47));
        arrayList.add(new Sample(R.drawable.sticker48));
        arrayList.add(new Sample(R.drawable.sticker49));
        arrayList.add(new Sample(R.drawable.sticker50));
        arrayList.add(new Sample(R.drawable.sticker51));
        arrayList.add(new Sample(R.drawable.sticker52));
        arrayList.add(new Sample(R.drawable.sticker53));
        arrayList.add(new Sample(R.drawable.sticker54));
        arrayList.add(new Sample(R.drawable.sticker55));
        arrayList.add(new Sample(R.drawable.sticker56));
        return arrayList;
    }

    public void onStickerSelected(int i) {

        if (stickerListener != null) {
            stickerListener.onStickerClick(i);
        }
    }
}
