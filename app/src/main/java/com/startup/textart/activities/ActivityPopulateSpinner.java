package com.startup.textart.activities;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.startup.adapter.PopulateSpinnerAdapter;
import com.startup.model.SpinnerModel;
import com.startup.textart.R;
import com.commonutility.CheckInternet;
import com.commonutility.FontUtils;
import com.commonutility.GlobalData;
import com.commonutility.GlobalVariables;
import com.commonutility.MyDividerItemDecoration;
import com.commonutility.NoInternetScreen;
import com.commonutility.PreferenceConnector;
import com.commonutility.ShowCustomToast;
import com.commonutility.WebService;
import com.commonutility.WebServiceListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class ActivityPopulateSpinner extends AppCompatActivity implements View.OnClickListener, WebServiceListener,
        PopulateSpinnerAdapter.PopulateAdapterListener {
    private View[] allViewWithClick = {};
    private int[] allViewWithClickId = {};

    private EditText[] edTexts = {};
    private String[] edTextsError = {"Enter phone number"};
    private int[] editTextsClickId = {};

    private RecyclerView recyclerView;
    public static List<SpinnerModel> itemList;
    private PopulateSpinnerAdapter mAdapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_populatespinner);
        StartApp();

        OnClickCombineDeclare(allViewWithClick);
        EditTextDeclare(edTexts);

        resumeApp();
    }

    public void resumeApp() {
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) findViewById(R.id.searchview);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        mAdapter = new PopulateSpinnerAdapter(svContext, itemList, this, true, true);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));
        recyclerView.setAdapter(mAdapter);

        if (itemList.size() == 0) {
            customToast.showCustomToast(svContext, "No item to show", customToast.ToastyInfo);
            onBackPressed();
        }
    }


    private void EditTextDeclare(EditText[] editTexts) {
        for (int j = 0; j < editTexts.length; j++) {
            editTexts[j] = findViewById(editTextsClickId[j]);
        }
    }

    private void OnClickCombineDeclare(View[] allViewWithClick) {
        for (int j = 0; j < allViewWithClick.length; j++) {
            allViewWithClick[j] = findViewById(allViewWithClickId[j]);
            allViewWithClick[j].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()) {
//                        case R.id.btn_finish:
//                            CheckData();
//                            break;
//                        case R.id.btn_backform:
//                            ShowBackCardView();
//                            break;
                    }
                }
            });
        }
//        btnBack = (Button) allViewWithClick[0];
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
        errrorScreen = new NoInternetScreen(svContext, root, ActivityPopulateSpinner.this);
        if (!GlobalVariables.CUSTOMFONTNAME.equals("")) {
            Typeface font = Typeface.createFromAsset(getAssets(), GlobalVariables.CUSTOMFONTNAME);
            FontUtils.setFont(root, font);
        }
        if (PreferenceConnector.readBoolean(svContext, PreferenceConnector.ISDARKTHEME, false)) {
//            FontUtils.setThemeColor(root, svContext, true);
        } else {
//            FontUtils.setThemeColor(root, svContext, false);
        }

        hideKeyboard();
        GlobalData.SetLanguage(svContext);
        if (checkNetwork.isConnectingToInternet()) {
            errrorScreen.hideError();
        } else {
            errrorScreen.showInternetError();
        }

        loadToolBar();
    }

    private ImageView imgToolBarBack;

    private void loadToolBar() {
        imgToolBarBack = (ImageView) findViewById(R.id.img_back);
        imgToolBarBack.setOnClickListener(this);

        TextView txtHeading = (TextView) findViewById(R.id.heading);
        txtHeading.setText(getString(R.string.app_name));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
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
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onContactSelected(SpinnerModel contact) {
        Toast.makeText(getApplicationContext(), "Selected: " + contact.getTitle() + ", " + contact.getDesc(), Toast.LENGTH_LONG).show();
    }

}