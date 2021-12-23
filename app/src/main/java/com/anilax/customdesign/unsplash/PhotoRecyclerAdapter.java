package com.anilax.customdesign.unsplash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anilax.customdesign.model.unsplash.Photo;
import com.anilax.textart.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class PhotoRecyclerAdapter extends RecyclerView.Adapter<PhotoRecyclerAdapter.ViewHolder> {
    private final Context mContext;

    public OnPhotoClickedListener mListener;

    public final List<Photo> photoList;

    public interface OnPhotoClickedListener {
        void photoClicked(Photo photo, ImageView imageView);
    }

    public PhotoRecyclerAdapter(List<Photo> list, Context context, OnPhotoClickedListener onPhotoClickedListener) {
        this.photoList = list;
        this.mContext = context;
        this.mListener = onPhotoClickedListener;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_photo, viewGroup, false));
    }

    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PhotoRecyclerAdapter.this.photoList.size() > viewHolder.getAdapterPosition()) {
                    PhotoRecyclerAdapter.this.mListener.photoClicked(PhotoRecyclerAdapter.this.photoList.get(viewHolder.getAdapterPosition()), (ImageView) view);
                }
            }
        });
        Glide.with(this.mContext).load(this.photoList.get(i).getUrls().getRegular()).transition(DrawableTransitionOptions.withCrossFade()).apply((new RequestOptions().override(400, 400)).centerCrop()).into(viewHolder.imageView);
    }

    public void addPhotos(List<Photo> list) {
        int itemCount = getItemCount();
        this.photoList.addAll(list);
        notifyItemRangeInserted(itemCount, list.size());
    }

    public void addImages(List<Photo> list) {
        this.photoList.addAll(list);
        notifyDataSetChanged();
    }



    public int getItemCount() {
        return this.photoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            this.imageView =  view.findViewById(R.id.imgViewUnsplash);
        }
    }
}
