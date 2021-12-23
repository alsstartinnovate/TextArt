package com.anilax.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.anilax.model.SpinnerModel;
import com.anilax.textart.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssridharatiwari on 2021.
 */

public class PopulateSpinnerAdapter extends RecyclerView.Adapter<PopulateSpinnerAdapter.MyViewHolder> implements Filterable {
    private Context context;
    private List<SpinnerModel> spinnerList;
    private List<SpinnerModel> spinnerListFiltered;
    private PopulateAdapterListener listener;
    boolean isImageShow = false, isDescShow = false;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, desc;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            desc = view.findViewById(R.id.desc);
            thumbnail = view.findViewById(R.id.thumbnail);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(spinnerListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    public PopulateSpinnerAdapter(Context context, List<SpinnerModel> contactList, PopulateAdapterListener listener,
                                  boolean isImageShow, boolean isDescShow) {
        this.context = context;
        this.listener = listener;
        this.spinnerList = contactList;
        this.spinnerListFiltered = contactList;
        this.isImageShow = isImageShow;
        this.isDescShow = isDescShow;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_row_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final SpinnerModel contact = spinnerListFiltered.get(position);

        holder.title.setText(contact.getTitle());
        holder.desc.setText(contact.getDesc());

        if (isDescShow){
            holder.desc.setVisibility(View.GONE);
        }else {
            holder.desc.setVisibility(View.VISIBLE);
        }
        if (isImageShow){
            holder.thumbnail.setVisibility(View.GONE);
        }else {
            holder.thumbnail.setVisibility(View.VISIBLE);
        }
        Glide.with(context)
                .load(contact.getImage())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return spinnerListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    spinnerListFiltered = spinnerList;
                } else {
                    List<SpinnerModel> filteredList = new ArrayList<>();
                    for (SpinnerModel row : spinnerList) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getTitle().toLowerCase().contains(charString.toLowerCase()) ||
                                row.getDesc().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }
                    spinnerListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = spinnerListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                spinnerListFiltered = (ArrayList<SpinnerModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface PopulateAdapterListener {
        void onContactSelected(SpinnerModel contact);
    }
}
