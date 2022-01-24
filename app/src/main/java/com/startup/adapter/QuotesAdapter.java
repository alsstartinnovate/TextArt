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

import com.startup.model.QuotesModel;
import com.startup.textart.R;

import java.util.ArrayList;
import java.util.List;

public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.SingleViewHolder> {
    private Context context;
    private List<QuotesModel> employees;
    // if checkedPosition = -1, there is no default selection
    // if checkedPosition = 0, 1st item is selected by default
    private int checkedPosition = 0;

    public QuotesAdapter(Context context, List<QuotesModel> employees) {
        this.context = context;
        this.employees = employees;
    }

    public void setEmployees(ArrayList<QuotesModel> employees) {
        this.employees = new ArrayList<>();
        this.employees = employees;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SingleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_quotes, viewGroup, false);
        return new SingleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SingleViewHolder singleViewHolder, int position) {
        singleViewHolder.bind(employees.get(position));
    }

    @Override
    public int getItemCount() {
        return employees.size();
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

        void bind(final QuotesModel model) {
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

    public QuotesModel getSelected() {
        if (checkedPosition != -1) {
            return employees.get(checkedPosition);
        }
        return null;
    }
}