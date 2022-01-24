package com.startup.textart.fragments.imagetools.sticker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.startup.textart.R;
import com.startup.textart.model.Sample;

import com.startup.textart.views.SquareImageView;
import com.bumptech.glide.Glide;

import java.util.List;

public class StickerAdapter extends RecyclerView.Adapter<StickerAdapter.ViewHolderSticker> {

    public StickerAdaperListener listener;
    private final Context mContext;

    public List<Sample> stickerArrayList;

    public interface StickerAdaperListener {
        void onStickerSelected(int i);
    }

    public StickerAdapter(List<Sample> arrayList, Context context, StickerAdaperListener stickerAdaperListener) {
        this.stickerArrayList = arrayList;
        this.mContext = context;
        this.listener = stickerAdaperListener;
    }

    @NonNull
    public ViewHolderSticker onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolderSticker(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sticker, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull ViewHolderSticker viewHolderSticker, int i) {
        Glide.with(this.mContext).load(Integer.valueOf(this.stickerArrayList.get(i).getImgSample())).thumbnail(0.1f).into((ImageView) viewHolderSticker.imgSticker);
    }

    public int getItemCount() {
        return this.stickerArrayList.size();
    }

    public class ViewHolderSticker extends RecyclerView.ViewHolder {


        SquareImageView imgSticker;

        public ViewHolderSticker(View view) {
            super(view);
            this.imgSticker = view.findViewById(R.id.imgSticker);
            view.setOnClickListener(view1 -> {
                if (StickerAdapter.this.listener != null) {
                    StickerAdapter.this.listener.onStickerSelected((StickerAdapter.this.stickerArrayList.get(ViewHolderSticker.this.getAdapterPosition())).getImgSample());
                }
            });
        }
    }
}
