package com.startup.textart.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.startup.textart.R;
import com.startup.textart.adapter.QuotesAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuotesFragment extends Fragment {
    QuotesFragmentListener quotesFragmentListener;
    ImageView btnBackQuotes;
    RecyclerView recyclerQuotes;
    QuotesAdapter quotesAdapter;
    List<Integer> listQuote;

    public interface QuotesFragmentListener {
        void onQuotes(int i);
    }

    public void setQuotesListener(QuotesFragmentListener quotesFragmentListener) {
        this.quotesFragmentListener = quotesFragmentListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_more_quotes, viewGroup, false);
        this.btnBackQuotes = inflate.findViewById(R.id.btnBackQuotes);
        this.recyclerQuotes = inflate.findViewById(R.id.recyclerQuotes);
        this.recyclerQuotes.setHasFixedSize(true);
        this.recyclerQuotes.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        this.listQuote = genQuotes();
        this.quotesAdapter = new QuotesAdapter(this.listQuote, getActivity(), (view, i) -> {
            int intValue = QuotesFragment.this.listQuote.get(i);
            if (QuotesFragment.this.quotesFragmentListener != null) {
                QuotesFragment.this.quotesFragmentListener.onQuotes(intValue);
                Objects.requireNonNull(QuotesFragment.this.getActivity()).getSupportFragmentManager().popBackStack();
            }
        });
        this.recyclerQuotes.setAdapter(this.quotesAdapter);
        this.btnBackQuotes.setOnClickListener(view -> Objects.requireNonNull(QuotesFragment.this.getActivity()).getSupportFragmentManager().popBackStack());
        return inflate;
    }

    public List<Integer> genQuotes() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(R.string.quotes1);
        arrayList.add((R.string.quotes2));
        arrayList.add((R.string.quotes3));
        arrayList.add((R.string.quotes4));
        arrayList.add((R.string.quotes5));
        arrayList.add((R.string.quotes6));
        arrayList.add((R.string.quotes7));
        arrayList.add((R.string.quotes8));
        arrayList.add((R.string.quotes9));
        arrayList.add((R.string.quotes10));
        arrayList.add((R.string.quotes11));
        arrayList.add((R.string.quotes12));
        arrayList.add((R.string.quotes13));
        arrayList.add((R.string.quotes14));
        arrayList.add((R.string.quotes15));
        arrayList.add((R.string.quotes16));
        arrayList.add((R.string.quotes17));
        arrayList.add((R.string.quotes18));
        arrayList.add((R.string.quotes19));
        arrayList.add((R.string.quotes20));
        arrayList.add((R.string.quotes21));
        arrayList.add((R.string.quotes22));
        arrayList.add((R.string.quotes23));
        arrayList.add((R.string.quotes24));
        arrayList.add((R.string.quotes25));
        arrayList.add((R.string.quotes26));
        arrayList.add((R.string.quotes27));
        arrayList.add((R.string.quotes28));
        arrayList.add((R.string.quotes29));
        arrayList.add((R.string.quotes30));
        arrayList.add((R.string.quotes31));
        arrayList.add((R.string.quotes32));
        arrayList.add((R.string.quotes33));
        arrayList.add((R.string.quotes34));
        arrayList.add((R.string.quotes35));
        arrayList.add((R.string.quotes36));
        arrayList.add((R.string.quotes37));
        arrayList.add((R.string.quotes38));
        arrayList.add((R.string.quotes39));
        arrayList.add((R.string.quotes40));
        arrayList.add((R.string.quotes41));
        arrayList.add((R.string.quotes42));
        arrayList.add((R.string.quotes43));
        arrayList.add((R.string.quotes44));
        arrayList.add((R.string.quotes45));
        arrayList.add((R.string.quotes46));
        arrayList.add((R.string.quotes47));
        arrayList.add((R.string.quotes48));
        arrayList.add((R.string.quotes49));
        arrayList.add((R.string.quotes50));
        arrayList.add((R.string.quotes51));
        arrayList.add((R.string.quotes52));
        arrayList.add((R.string.quotes53));
        arrayList.add((R.string.quotes54));
        arrayList.add((R.string.quotes55));
        arrayList.add((R.string.quotes56));
        arrayList.add((R.string.quotes57));
        arrayList.add((R.string.quotes58));
        arrayList.add((R.string.quotes59));
        arrayList.add((R.string.quotes60));
        arrayList.add((R.string.quotes61));
        arrayList.add((R.string.quotes62));
        arrayList.add((R.string.quotes63));
        arrayList.add((R.string.quotes64));
        arrayList.add((R.string.quotes65));
        arrayList.add((R.string.quotes66));
        arrayList.add((R.string.quotes67));
        arrayList.add((R.string.quotes68));
        arrayList.add((R.string.quotes69));
        arrayList.add((R.string.quotes70));
        arrayList.add((R.string.quotes71));
        arrayList.add((R.string.quotes72));
        arrayList.add((R.string.quotes73));
        arrayList.add((R.string.quotes74));
        arrayList.add((R.string.quotes75));
        arrayList.add((R.string.quotes76));
        arrayList.add((R.string.quotes77));
        arrayList.add((R.string.quotes78));
        arrayList.add((R.string.quotes79));
        arrayList.add((R.string.quotes80));
        arrayList.add((R.string.quotes81));
        arrayList.add((R.string.quotes82));
        arrayList.add((R.string.quotes83));
        arrayList.add((R.string.quotes84));
        arrayList.add((R.string.quotes85));
        arrayList.add((R.string.quotes86));
        arrayList.add((R.string.quotes87));
        arrayList.add((R.string.quotes88));
        arrayList.add((R.string.quotes89));
        arrayList.add((R.string.quotes90));
        return arrayList;
    }
}
