package com.anilax.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anilax.model.InterestModel;
import com.anilax.textart.R;

import java.util.ArrayList;
import java.util.List;

public class ChooseInterestAdapter extends RecyclerView.Adapter<ChooseInterestAdapter.MultiViewHolder> {
    private Context context;
    private List<InterestModel> items;

    public ChooseInterestAdapter(Context context, List<InterestModel> items) {
        this.context = context;
        this.items = items;
    }

    public void setRawData(ArrayList<InterestModel> items) {
        this.items = new ArrayList<>();
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MultiViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_interest, viewGroup, false);
        return new MultiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MultiViewHolder multiViewHolder, int position) {
        multiViewHolder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class MultiViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ImageView imageView;

        MultiViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.name);
            imageView = itemView.findViewById(R.id.iv_selected);
        }

        void bind(final InterestModel model) {
            imageView.setVisibility(model.isChecked() ? View.VISIBLE : View.GONE);
            textView.setText(model.getInterestName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    model.setChecked(!model.isChecked());
                    imageView.setVisibility(model.isChecked() ? View.VISIBLE : View.GONE);
                }
            });


        }
    }

    public List<InterestModel> getAll() {
        return items;
    }

    public ArrayList<InterestModel> getSelected() {
        ArrayList<InterestModel> selected = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).isChecked()) {
                selected.add(items.get(i));
            }
        }
        return selected;
    }
}