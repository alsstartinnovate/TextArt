package com.anilax.textart;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.anilax.activity.ActivitySelectTheme;
import com.commonutility.NoInternetScreen;
import com.anilax.adapter.ChooseInterestAdapter;
import com.anilax.model.InterestModel;
import com.commonutility.CheckInternet;
import com.commonutility.GlobalData;
import com.commonutility.GlobalVariables;
import com.commonutility.ItemAnimation;
import com.commonutility.ShowCustomToast;
import com.commonutility.WebService;
import com.commonutility.WebServiceListener;
import com.commonutility.FontUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ActivityChooseInterest extends AppCompatActivity implements View.OnClickListener, WebServiceListener {
    private int animation_type = ItemAnimation.BOTTOM_UP;
    private List<InterestModel> lstItems = new ArrayList<InterestModel>();
    private RecyclerView recyclerView;
    private LinearLayout layActionBar;
    private Button btnContinue;
    private ChooseInterestAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_chooseinterest);
        StartApp();

        layActionBar = (LinearLayout) findViewById(R.id.lay_top);
        layActionBar.setVisibility(View.GONE);

        btnContinue = (Button) findViewById(R.id.btn_continue);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setHasFixedSize(true);

        btnContinue.setOnClickListener(this);

        resumeApp();
    }

    public void resumeApp(){
        setAdapter();
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
        errrorScreen = new NoInternetScreen(svContext, root,ActivityChooseInterest.this);
        if (!GlobalVariables.CUSTOMFONTNAME.equals("")) {
            Typeface font = Typeface.createFromAsset(getAssets(), GlobalVariables.CUSTOMFONTNAME);
            FontUtils.setFont(root, font);
        }
//        if (PreferenceConnector.readBoolean(svContext, PreferenceConnector.ISDARKTHEME, false)) {
//            FontUtils.setThemeColor(root, svContext, true);
//        } else {
//            FontUtils.setThemeColor(root, svContext, false);
//        }

        hideKeyboard();
        GlobalData.SetLanguage(svContext);

        if (checkNetwork.isConnectingToInternet()) {
            errrorScreen.hideError();
        }else {
            errrorScreen.showInternetError();
        }
    }

    String[] strId = {"0", "1", "2", "3", "4", "5", "6"};
    String[] strImages = {"English", "Hindi", "Marathi", "Telagu", "Tamil", "Kannada", "Punjabi", "Gujarati"};
    String[] strName = {"Emotional", "Comedy", "Bollywood", "Beauty & Style", "Animals", "Music", "School Talent"};

    private void setAdapter() {
        for (int i = 0; i < strName.length; i++) {
            lstItems.add(new InterestModel(strId[i], strImages[i], strName[i]));
        }
        //set data and list adapter
        mAdapter = new ChooseInterestAdapter(this, lstItems);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_continue:
                if (mAdapter.getSelected().size() > 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < mAdapter.getSelected().size(); i++) {
                        stringBuilder.append(mAdapter.getSelected().get(i).getInterestName());
                        stringBuilder.append("\n");
                    }

                    customToast.showCustomToast(svContext, stringBuilder.toString().trim(), customToast.ToastySuccess);

                    Intent svIntent = new Intent(svContext, ActivitySelectTheme.class);
                    startActivity(svIntent);
                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                    finish();
                } else {
                    customToast.showCustomToast(svContext, "No Interest Selected", customToast.ToastyError);
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