package com.startup.textart.activities;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.startup.adapter.QuotesCategoryAdapter;
import com.startup.model.QuotesCategoryModel;
import com.startup.textart.R;
import com.commonutility.MyDividerItemDecoration;
import com.commonutility.NoInternetScreen;
import com.startup.adapter.QuotesAdapter;
import com.startup.model.QuotesModel;
import com.commonutility.CheckInternet;
import com.commonutility.GlobalData;
import com.commonutility.GlobalVariables;
import com.commonutility.PreferenceConnector;
import com.commonutility.ShowCustomToast;
import com.commonutility.WebService;
import com.commonutility.WebServiceListener;
import com.commonutility.FontUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ActivityQuotes extends AppCompatActivity implements View.OnClickListener, WebServiceListener {
    private List<QuotesModel> lstItems = new ArrayList<>();
    private List<QuotesCategoryModel> lstItemsCategory = new ArrayList<>();
    private RecyclerView rvQuotesCategory, rvQuotes;
    private LinearLayout layActionBar;
    private QuotesAdapter mAdapter;
    private QuotesCategoryAdapter mAdapterCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_quotes);
        StartApp();

        layActionBar = (LinearLayout) findViewById(R.id.lay_top);
        layActionBar.setVisibility(View.GONE);

        Button btnContinue = (Button) findViewById(R.id.btn_continue);

        rvQuotes = (RecyclerView) findViewById(R.id.recyclerView);
        rvQuotesCategory = (RecyclerView) findViewById(R.id.rv_category);

        rvQuotes.setItemAnimator(new DefaultItemAnimator());
        rvQuotes.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.HORIZONTAL, 5));
        rvQuotes.setHasFixedSize(true);

        rvQuotesCategory.setHasFixedSize(true);

        btnContinue.setOnClickListener(this);

        resumeApp();
    }

    public void resumeApp(){
        setQuoteAdapter();
        setCategoryAdapter();
    }

    private Context svContext;
    private ShowCustomToast customToast;
    private CheckInternet checkNetwork;
    private NoInternetScreen errrorScreen;
    private ViewGroup root;
    private void StartApp() {
        svContext = this;
        customToast = new ShowCustomToast(svContext);
        checkNetwork = new CheckInternet(svContext);
        root = (ViewGroup) findViewById(R.id.headlayout);
        errrorScreen = new NoInternetScreen(svContext, root, ActivityQuotes.this);
        if (!GlobalVariables.CUSTOMFONTNAME.equals("")) {
            Typeface font = Typeface.createFromAsset(getAssets(), GlobalVariables.CUSTOMFONTNAME);
            FontUtils.setFont(root, font);
        }

        hideKeyboard();
        GlobalData.SetLanguage(svContext);
        if (checkNetwork.isConnectingToInternet()) {
            errrorScreen.hideError();
        }else {
            errrorScreen.showInternetError();
        }
    }

    String[] strQuotes = {"Quote_1", "Quote_2", "Quote_3", "Quote_4", "Quote_5", "Quote_6", "Quote_7", "Quote_8"};

    private void setQuoteAdapter() {
        for (int i = 0; i < strQuotes.length; i++) {
            lstItems.add(new QuotesModel(strQuotes[i]));
        }
        //set data and list adapter
        mAdapter = new QuotesAdapter(this, lstItems);
        rvQuotes.setNestedScrollingEnabled(false);
        rvQuotes.setAdapter(mAdapter);

    }

    String[] strCategory = {"Category_1", "Category_2", "Category_3", "Category_4", "Category_5", "Category_6"};

    private void setCategoryAdapter() {
        for (int i = 0; i < strCategory.length; i++) {
            lstItemsCategory.add(new QuotesCategoryModel(strCategory[i]));
        }
        //set data and list adapter
        mAdapterCategory = new QuotesCategoryAdapter(this, lstItemsCategory);
        rvQuotesCategory.setNestedScrollingEnabled(false);
        rvQuotesCategory.setAdapter(mAdapterCategory);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_continue:
                QuotesModel modelItem = mAdapter.getSelected();
                if (modelItem != null) {
                    customToast.showCustomToast(svContext, mAdapter.getSelected().getName(), customToast.ToastySuccess);
                    PreferenceConnector.writeString(svContext, PreferenceConnector.SELECTEDLANGUAGE, strQuotes[0]);

                    onBackPressed();
                } else {
                    customToast.showCustomToast(svContext, "No Quote Selected", customToast.ToastyError);
                }
                break;
            default:
                break;
        }
    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        // check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static void hideFragmentkeyboard(Context meraContext, View meraView) {
        final InputMethodManager imm = (InputMethodManager) meraContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(meraView.getWindowToken(), 0);
    }

    LinkedList<String> lstUploadData = new LinkedList<>();
    private void callWebService(String postUrl, LinkedList<String> lstUploadData) {
        WebService webService = new WebService(svContext, postUrl, lstUploadData, this, root, this);
        webService.LoadDataRetrofit(webService.callReturn());
    }

    @Override
    public void onWebServiceActionComplete(String result, String url) {
        System.out.println(result + ".........jsonresponse....." + url);
        if (url.contains("GlobalVariables.GETABOUTDATA")) {
            try {
                JSONObject json = new JSONObject(result);


            } catch (JSONException e) {
                customToast.showCustomToast(svContext, "Some error occured", customToast.ToastyError);
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onWebServiceError(String result, String url) {
        customToast.showCustomToast(svContext, result, customToast.ToastyError);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}