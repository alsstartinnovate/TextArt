package com.startup.textart.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.startup.textart.R;
import com.hitomi.cslibrary.CrazyShadow;

import java.util.Objects;
import java.util.Random;

public class TextEditorFragment extends Fragment implements View.OnClickListener, QuotesFragment.QuotesFragmentListener {


    EditText editText;


    TextFragmentListener textFragmentListener;


    int[] quotes = {R.string.quotes1, R.string.quotes2, R.string.quotes3, R.string.quotes4, R.string.quotes5, R.string.quotes6, R.string.quotes7, R.string.quotes8, R.string.quotes9, R.string.quotes10, R.string.quotes11, R.string.quotes12, R.string.quotes13, R.string.quotes14, R.string.quotes15, R.string.quotes16, R.string.quotes17, R.string.quotes18, R.string.quotes19, R.string.quotes20, R.string.quotes21, R.string.quotes22, R.string.quotes23, R.string.quotes24, R.string.quotes25, R.string.quotes26, R.string.quotes27, R.string.quotes28, R.string.quotes29, R.string.quotes30, R.string.quotes31, R.string.quotes32, R.string.quotes33, R.string.quotes34, R.string.quotes35, R.string.quotes36, R.string.quotes37, R.string.quotes38, R.string.quotes39, R.string.quotes40, R.string.quotes41, R.string.quotes42, R.string.quotes43, R.string.quotes44, R.string.quotes45, R.string.quotes46, R.string.quotes47, R.string.quotes48, R.string.quotes49, R.string.quotes50, R.string.quotes51, R.string.quotes52, R.string.quotes53, R.string.quotes54, R.string.quotes55, R.string.quotes56, R.string.quotes57, R.string.quotes58, R.string.quotes59, R.string.quotes60, R.string.quotes61, R.string.quotes62, R.string.quotes63, R.string.quotes64, R.string.quotes65, R.string.quotes66, R.string.quotes67, R.string.quotes68, R.string.quotes69, R.string.quotes70, R.string.quotes71, R.string.quotes72, R.string.quotes73, R.string.quotes74, R.string.quotes75, R.string.quotes76, R.string.quotes77, R.string.quotes78, R.string.quotes79, R.string.quotes80, R.string.quotes81, R.string.quotes82, R.string.quotes83, R.string.quotes84, R.string.quotes85, R.string.quotes86, R.string.quotes87, R.string.quotes88, R.string.quotes89, R.string.quotes90};

    public interface TextFragmentListener {
        void onText(String str);
    }

    public void setTextListener(TextFragmentListener textFragmentListener) {
        this.textFragmentListener = textFragmentListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Objects.requireNonNull(getActivity()).getWindow().setSoftInputMode(32);
        View inflate = layoutInflater.inflate(R.layout.fragment_text_editor, viewGroup, false);
        inflate.findViewById(R.id.btnCancel).setOnClickListener(this);
        inflate.findViewById(R.id.btnDone).setOnClickListener(this);
        this.editText = inflate.findViewById(R.id.edtQuotes);
        inflate.findViewById(R.id.btnRandom).setOnClickListener(this);
        inflate.findViewById(R.id.btnMoreQuotes).setOnClickListener(this);
        if (getArguments() != null) {
            this.editText.setText(getArguments().getString("edittext"));
        }
        new CrazyShadow.Builder().setContext(getActivity()).setDirection(64).setShadowRadius(30.0f).setCorner(30.0f).setImpl(CrazyShadow.IMPL_DRAW).action(inflate.findViewById(R.id.edtQuotes));
        if (this.editText.requestFocus()) {
            new Handler().postDelayed(() -> ((InputMethodManager) TextEditorFragment.this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(TextEditorFragment.this.editText, 1), 200);
        }
        return inflate;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCancel:
                this.editText.setText("");
                return;
            case R.id.btnDone:
                String obj = this.editText.getText().toString();

                if (textFragmentListener != null) {
                    textFragmentListener.onText(obj);
                    Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack(null, 1);
                    View currentFocus = getActivity().getCurrentFocus();
                    if (currentFocus != null) {
                        ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
                    }
                }
                return;
            case R.id.btnMoreQuotes:
//                QuotesFragment quotesFragment = new QuotesFragment();
//                quotesFragment.setQuotesListener(this);
//                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right).add(R.id.frameLayoutEditMai, quotesFragment, "QUOTES").addToBackStack("QUOTES").commit();
//                View currentFocus2 = getActivity().getCurrentFocus();
//                if (currentFocus2 != null) {
//                    ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(currentFocus2.getWindowToken(), 0);
//                }
                return;
            case R.id.btnRandom:
                this.editText.setText(this.quotes[new Random().nextInt(this.quotes.length)]);
                return;
            default:
        }
    }

    public void onQuotes(int i) {
        this.editText.setText(i);
    }
}
