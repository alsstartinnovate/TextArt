package com.startup.textart.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.startup.textart.R;
import com.commonutility.CheckInternet;
import com.commonutility.FontUtils;
import com.commonutility.GlobalData;
import com.commonutility.GlobalVariables;
import com.commonutility.ItemAnimation;
import com.commonutility.NoInternetScreen;
import com.commonutility.PreferenceConnector;
import com.commonutility.ShowCustomToast;
import com.commonutility.WebService;
import com.commonutility.WebServiceListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

public class ActivitySelectTheme extends AppCompatActivity implements View.OnClickListener, WebServiceListener {
    private int animation_type = ItemAnimation.BOTTOM_UP;
    private LinearLayout layActionBar;
    private Button btnContinue;
    private RelativeLayout layDarkMode, layLightMode;
    private ImageView imgLightMode, imgDarkMode;
    private boolean isDarkMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_theme);
        StartApp();


        resumeApp();
    }

    public void resumeApp(){
        layActionBar = (LinearLayout) findViewById(R.id.lay_top);
        layActionBar.setVisibility(View.GONE);

        btnContinue = (Button) findViewById(R.id.btn_continue);

        layDarkMode = (RelativeLayout) findViewById(R.id.lay_dark_mode);
        layLightMode = (RelativeLayout) findViewById(R.id.lay_light_mode);

        imgLightMode = (ImageView) findViewById(R.id.img_lightmode);
        imgDarkMode = (ImageView) findViewById(R.id.img_darkmode);

        layDarkMode.setOnClickListener(this);
        layLightMode.setOnClickListener(this);

        btnContinue.setOnClickListener(this);

        imgLightMode.setVisibility(View.INVISIBLE);
        imgDarkMode.setVisibility(View.INVISIBLE);
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
        errrorScreen = new NoInternetScreen(svContext, root, ActivitySelectTheme.this);
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

        loadToolBar();
    }

    private ImageView imgToolBarBack;
    private void loadToolBar(){
        imgToolBarBack = (ImageView)findViewById(R.id.img_back);
        imgToolBarBack.setOnClickListener(this);

        TextView txtHeading = (TextView)findViewById(R.id.heading);
        txtHeading.setText(getString(R.string.toolbar_selecttheme));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lay_dark_mode:
                isDarkMode = false;
                imgLightMode.setVisibility(View.INVISIBLE);
                imgDarkMode.setVisibility(View.VISIBLE);
                break;
            case R.id.lay_light_mode:
                isDarkMode = true;
                imgLightMode.setVisibility(View.VISIBLE);
                imgDarkMode.setVisibility(View.INVISIBLE);
                break;
            case R.id.btn_continue:
                if (isDarkMode) {
                    PreferenceConnector.writeBoolean(svContext, PreferenceConnector.ISDARKTHEME, true);
                    Intent svIntent = new Intent(svContext, ActivityLetsStart.class);
                    startActivity(svIntent);
                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                    finish();
                } else {
                    PreferenceConnector.writeBoolean(svContext, PreferenceConnector.ISDARKTHEME, false);
                    customToast.showCustomToast(svContext, "No theme Selected", customToast.ToastyError);
                }
                break;
            default:
                break;
        }
    }

    private RelativeLayout layConnection, progressbarInternet;
    private TextView textError;
    private ProgressBar progressBarLayconnection;

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