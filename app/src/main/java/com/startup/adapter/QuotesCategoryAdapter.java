package com.startup.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.startup.model.QuotesCategoryModel;
import com.startup.textart.R;

import java.util.ArrayList;
import java.util.List;

public class QuotesCategoryAdapter extends RecyclerView.Adapter<QuotesCategoryAdapter.SingleViewHolder> {
    private Context context;
    private List<QuotesCategoryModel> item;
    private int checkedPosition = 0;

    public QuotesCategoryAdapter(Context context, List<QuotesCategoryModel> item) {
        this.context = context;
        this.item = item;
    }

    public void setEmployees(List<QuotesCategoryModel> item) {
        this.item = new ArrayList<>();
        this.item = item;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SingleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_quotes_cat, viewGroup, false);
        return new SingleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SingleViewHolder singleViewHolder, int position) {
        singleViewHolder.bind(item.get(position));
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    class SingleViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;
        private RelativeLayout layHeadChange;

        SingleViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.name);
            imageView = itemView.findViewById(R.id.iv_selected);
            layHeadChange = itemView.findViewById(R.id.lay_head_change);

        }

        void bind(final QuotesCategoryModel model) {
            if (checkedPosition == -1) {
                imageView.setVisibility(View.GONE);
            } else {
                if (checkedPosition == getAdapterPosition()) {
                    imageView.setVisibility(View.VISIBLE);
                } else {
                    imageView.setVisibility(View.GONE);
                }
            }
            textView.setText(model.getName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageView.setVisibility(View.VISIBLE);
                    if (checkedPosition != getAdapterPosition()) {
                        notifyItemChanged(checkedPosition);
                        checkedPosition = getAdapterPosition();
                    }
                }
            });

//            GetGradientDrawable.setGradientDrawable(context, layHeadChange, new float[] {20, 20, 20, 20, 20, 20, 20, 20},
//                    2, R.color.round_corner_radio_head, R.color.white);

        }
    }

    public QuotesCategoryModel getSelected() {
        if (checkedPosition != -1) {
            return item.get(checkedPosition);
        }
        return null;
    }
}