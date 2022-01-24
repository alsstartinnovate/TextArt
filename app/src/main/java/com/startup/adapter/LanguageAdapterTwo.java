package com.startup.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.startup.model.QuotesModel;
import com.startup.textart.R;
import com.commonutility.ItemAnimation;

import java.util.ArrayList;
import java.util.List;

public class LanguageAdapterTwo extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<QuotesModel> items = new ArrayList<>();
    private Context ctx;
    private OnItemClickListener mOnItemClickListener;
    private int animation_type = 0;

    public interface OnItemClickListener {
        void onItemClick(View view, String obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public LanguageAdapterTwo(Context context, List<QuotesModel> items, int animation_type) {
        this.items = items;
        ctx = context;
        this.animation_type = animation_type;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView imgSelected;
        public View lyt_parent;

        public OriginalViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.name);
            imgSelected= (ImageView) v.findViewById(R.id.iv_selected);
            lyt_parent = (View) v.findViewById(R.id.lyt_parent);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quotes, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Log.e("onBindViewHolder", "onBindViewHolder : " + position);
        if (holder instanceof OriginalViewHolder) {
            OriginalViewHolder view = (OriginalViewHolder) holder;

            view.name.setText(items.get(position).getName());

            view.lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(view, items.get(position).getName(), position);
                    }
                }
            });
            setAnimation(view.itemView, position);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                on_attach = false;
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private int lastPosition = -1;
    private boolean on_attach = true;

    private void setAnimation(View view, int position) {
        if (position > lastPosition) {
            ItemAnimation.animate(view, on_attach ? position : -1, animation_type);
            lastPosition = position;
        }
    }
}