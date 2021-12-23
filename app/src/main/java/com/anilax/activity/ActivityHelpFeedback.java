package com.anilax.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.anilax.textart.R;
import com.commonutility.CheckInternet;
import com.commonutility.CheckValidation;
import com.commonutility.FontUtils;
import com.commonutility.GlobalData;
import com.commonutility.GlobalVariables;
import com.commonutility.NoInternetScreen;
import com.commonutility.PreferenceConnector;
import com.commonutility.ShowCustomToast;
import com.commonutility.WebService;
import com.commonutility.WebServiceListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.retrofit.ApiInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

public class ActivityHelpFeedback extends AppCompatActivity implements View.OnClickListener, WebServiceListener {
    private EditText edCompalintTitle, edCompalintDesc;
    private Button btnsignUp, btnRemovePic;
    private RadioGroup radioGender;
    private ImageView imgProfilePic;

    private View[] allViewWithClick = {};
    private int[] allViewWithClickId = {};

    private EditText[] edTexts = {};
    private String[] edTextsError = {"Enter phone number"};
    private int[] editTextsClickId = {};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_helpfeedback);
        StartApp();

        OnClickCombineDeclare(allViewWithClick);
        EditTextDeclare(edTexts);

        resumeApp();
    }

    public void resumeApp(){
        edCompalintTitle = (EditText) findViewById(R.id.et_title);
        edCompalintDesc = (EditText) findViewById(R.id.et_desc);
        btnsignUp = (Button) findViewById(R.id.email_sign_in_button);
        radioGender = (RadioGroup) findViewById(R.id.gender);
        imgProfilePic = (ImageView) findViewById(R.id.imgae_dp);
        btnRemovePic = (Button) findViewById(R.id.btn_removepic);

        btnsignUp.setOnClickListener(this);
        imgProfilePic.setOnClickListener(this);
        btnRemovePic.setOnClickListener(this);
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
        errrorScreen = new NoInternetScreen(svContext, root, ActivityHelpFeedback.this);
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
        txtHeading.setText(getString(R.string.toolbar_helpandfeedback));
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
        if (url.contains(ApiInterface.SUBMITCOMPALINT)) {
            try {
                JSONObject json = new JSONObject(result);

//                String str_result = json.getString(TAG_RESULT);
//                if (str_result.equals("true")) {
//                    customToast.showCustomToast(svContext, json.getString("msg"), customToast.ToastySuccess);
//
//                    Intent svIntent;
//                    svIntent = new Intent(svContext, ActivityMain.class);
//                    startActivity(svIntent);
//                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
//                    finish();
//                } else {
//                    customToast.showCustomToast(svContext, json.getString("msg"), customToast.ToastyError);
//                }
            } catch (JSONException e) {
                customToast.showToast(getResources().getString(R.string.date_error), svContext);
                e.printStackTrace();
            }
        } else {
            try {
                JSONObject json = new JSONObject(result);
//                populateState(db.getAllCategory(db.tablenames[0]));
            } catch (JSONException e) {
                customToast.showToast(getResources().getString(R.string.date_error), svContext);
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.email_sign_in_button:
                SubmitForm();
                break;
            case R.id.img_back:
                finish();
                break;
        }
    }

    private void SubmitForm() {
        int response = 0;
        response = CheckValidation.emptyEditTextError(
                new EditText[]{edCompalintTitle, edCompalintDesc},
                new String[]{"enter complaint title", "enter complaint description"});

        String strPersonName = edCompalintTitle.getText().toString().trim();

        int selectedId = radioGender.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(selectedId);

        if (response == 0) {
            ShowConfirmDialog(new String[]{strPersonName, edCompalintDesc.getText().toString().trim()}, radioButton);
        }
    }

    private void ShowConfirmDialog(String[] strUploadData, RadioButton radioButton) {
         new MaterialAlertDialogBuilder(svContext, R.style.LightTheme)
                .setTitle("title")
                .setMessage("message")
                .setCancelable(false)
                .setPositiveButton("positiveText", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        lstUploadData = new LinkedList<>();
//                        lstUploadData.add(ApiClient.SENDERID);
//                        lstUploadData.add(ApiClient.ROUTE);
//                        lstUploadData.add(ApiClient.AUTHKEY);
//                        lstUploadData.add(ApiClient.COUNTRYCODE);
//                        lstUploadData.add(strPhoneNumber);
//                        lstUploadData.add(StrMessage);
//
//                        if (imageUri == null) {
//                            callWebService(ApiInterface.SUBMITCOMPALINT, lstUploadData);
//                        } else {
//                            String[] uploadDocsath = {imageUri.getPath()};
//                            callWebService(ApiInterface.SUBMITCOMPALINT, lstUploadData);
//                        }
                    }
                })
                .setNeutralButton("negativeText", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onWebServiceError(String result, String url) {
        customToast.showCustomToast(svContext, result, customToast.ToastyError);
    }
}