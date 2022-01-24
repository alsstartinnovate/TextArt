package com.startup.textart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.startup.textart.R;
import com.startup.textart.interfaces.ItemClickListener;

import java.util.List;

public class SuggestAdapter extends RecyclerView.Adapter<SuggestAdapter.ViewHolderSuggest> {

    List<String> suggestList;
    Context context;
    ItemClickListener itemClickListener;

    public SuggestAdapter(List<String> arrayList, Context context, ItemClickListener itemClickListener) {
        this.suggestList = arrayList;
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    public ViewHolderSuggest onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_suggest, viewGroup, false);
        final ViewHolderSuggest viewHolderSuggest = new ViewHolderSuggest(inflate);
        inflate.setOnClickListener(view -> SuggestAdapter.this.itemClickListener.onItemClick(view, viewHolderSuggest.getLayoutPosition()));
        return viewHolderSuggest;
    }

    public void onBindViewHolder(@NonNull ViewHolderSuggest viewHolderSuggest, int i) {
        viewHolderSuggest.tvSuggest.setText(this.suggestList.get(i));
    }

    public int getItemCount() {
        return this.suggestList.size();
    }

    public static class ViewHolderSuggest extends RecyclerView.ViewHolder {


        TextView tvSuggest;

        public ViewHolderSuggest(View view) {
            super(view);
            this.tvSuggest = view.findViewById(R.id.tvSuggest);
        }
    }
}
