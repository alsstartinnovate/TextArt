package com.startup.textart.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
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
import com.commonutility.ShowCustomToast;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class ActivityLetsStart extends AppCompatActivity implements View.OnClickListener {
    private int animation_type = ItemAnimation.BOTTOM_UP;
    private Button btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_letsstart);
        StartApp();

        btnContinue = (Button) findViewById(R.id.btn_continue);

        btnContinue.setOnClickListener(this);

        RequestAppPermission();

        TextView tv = (TextView) findViewById(R.id.tv_create);
        Typeface face = Typeface.createFromAsset(getAssets(), "font/logofont.otf");
        tv.setTypeface(face);
    }

    private Context svContext;
    private ShowCustomToast customToast;
    private CheckInternet checkNetwork;
    private ViewGroup root;
    private void StartApp() {
        svContext = this;
        customToast = new ShowCustomToast(svContext);
        checkNetwork = new CheckInternet(svContext);
        root = (ViewGroup) findViewById(R.id.headlayout);

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_continue:
                Intent svIntent = new Intent(svContext, EditPhotoActivity.class);
                svIntent.putExtra("SampleBackground", R.drawable.night3);
                startActivity(svIntent);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                finish();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void RequestAppPermission(){
        Dexter.withContext(this)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {/* ... */}

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {/* ... */}
        }).check();
    }

}