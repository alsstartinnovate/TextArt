//package com.anilax.activity;
//
//import android.annotation.SuppressLint;
//import android.app.Dialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.ColorMatrix;
//import android.graphics.ColorMatrixColorFilter;
//import android.graphics.Paint;
//import android.graphics.RadialGradient;
//import android.graphics.Rect;
//import android.graphics.RectF;
//import android.graphics.Shader;
//import android.graphics.Typeface;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.Drawable;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.Window;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.Button;
//import android.widget.CompoundButton;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.SeekBar;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.SwitchCompat;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.anilax.adapter.BottomItemAllAdapter;
//import com.anilax.adapter.BottomItemOneAdapter;
//import com.anilax.adapter.ColorPickerAdapter;
//import com.anilax.model.BottomItemModel;
//import com.anilax.model.BottomItemOneModel;
//import com.anilax.textart.R;
//import com.commonutility.CheckInternet;
//import com.commonutility.FontUtils;
//import com.commonutility.GetGradientDrawable;
//import com.commonutility.GlobalData;
//import com.commonutility.GlobalVariables;
//import com.commonutility.ImageLoading;
//import com.commonutility.ItemAnimation;
//import com.commonutility.LocaleHelper;
//import com.commonutility.ShowCustomToast;
//import com.commonutility.WebServiceListener;
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//import com.retrofit.ApiInterface;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//public class ActivityMain extends AppCompatActivity implements View.OnClickListener, WebServiceListener {
//    private View[] allViewWithClick = {};
//    private int[] allViewWithClickId = {};
//    private final int PHOTO_EDITOR_REQUEST_CODE = 231;
//    private RecyclerView rvItemBottom;
//    private RelativeLayout rLayDynamic;
//    private ImageView imgMain;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.act_main);
//        StartApp();
//        OnClickCombineDeclare(allViewWithClick);
//
//        resumeApp();
//    }
//
//    public void resumeApp() {
//        initBottomMenu();
//        rLayDynamic = (RelativeLayout) findViewById(R.id.lay_dynamic);
//        AddCustomImageView(0);
//        rvItemBottom = (RecyclerView) findViewById(R.id.recycler_view_item);
//
//        setBottomItemOneAdapter();
//    }
//
//    private Context svContext;
//    private ShowCustomToast customToast;
//    private CheckInternet checkNetwork;
//    private ViewGroup root;
//
//    private void StartApp() {
//        svContext = this;
//        customToast = new ShowCustomToast(svContext);
//        checkNetwork = new CheckInternet(svContext);
//        root = (ViewGroup) findViewById(R.id.headlayout);
//        if (!GlobalVariables.CUSTOMFONTNAME.equals("")) {
//            Typeface font = Typeface.createFromAsset(getAssets(), GlobalVariables.CUSTOMFONTNAME);
//            FontUtils.setFont(root, font);
//        }
//        hideKeyboard();
//        GlobalData.SetLanguage(svContext);
//        SetLanguage("en");
//    }
//
//    private void OnClickCombineDeclare(View[] allViewWithClick) {
//        for (int j = 0; j < allViewWithClick.length; j++) {
//            allViewWithClick[j] = findViewById(allViewWithClickId[j]);
//            allViewWithClick[j].setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    switch (v.getId()) {
////                        case R.id.img_notification:
////
////                            break;
//                    }
//                }
//            });
//        }
//    }
//
//    private void SetLanguage(String languageCode) {
//        LocaleHelper.setLocale(svContext, languageCode);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//
//            default:
//                break;
//        }
//    }
//
//    private void hideKeyboard() {
//        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
//        // check if no view has focus:
//        View view = this.getCurrentFocus();
//        if (view != null) {
//            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//        }
//    }
//
//    public static void hideFragmentkeyboard(Context meraContext, View meraView) {
//        final InputMethodManager imm = (InputMethodManager) meraContext.getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(meraView.getWindowToken(), 0);
//    }
//
//    @Override
//    public void onWebServiceActionComplete(String result, String url) {
//        System.out.println(result + ".........jsonresponse....." + url);
//        if (url.contains(ApiInterface.UPDATEFCM)) {
//            try {
//                JSONObject json = new JSONObject(result);
//
//
//            } catch (JSONException e) {
//                customToast.showCustomToast(svContext, "Some error occured", customToast.ToastyError);
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        hideKeyboard();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//    }
//
//    private int selectedBottomItem = 0;
//
//    private void initBottomMenu() {
//        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
//        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                List<BottomItemModel> lstItems;
//                switch (item.getItemId()) {
//                    case R.id.nav_item_one:
//                        selectedBottomItem = 0;
//                        item.setIcon(R.drawable.menu_one_selected);
//                        setBottomItemOneAdapter();
//                        break;
//                    case R.id.nav_item_two:
//                        selectedBottomItem = 1;
//                        item.setIcon(R.drawable.menu_two_selected);
//                        lstItems = new ArrayList<>();
//                        for (int i = 0; i < strItemTwoName.length; i++) {
//                            lstItems.add(new BottomItemModel(strItemTwoName[i], itemTwoDrawable[i]));
//                        }
//                        setBottomItemdapter(lstItems);
//                        break;
//                    case R.id.nav_item_three:
//                        selectedBottomItem = 2;
//                        item.setIcon(R.drawable.menu_three_selected);
//                        lstItems = new ArrayList<>();
//                        for (int i = 0; i < strItemThreeName.length; i++) {
//                            lstItems.add(new BottomItemModel(strItemThreeName[i], itemThreeDrawable[i]));
//                        }
//                        setBottomItemdapter(lstItems);
//                        break;
//                    case R.id.nav_item_four:
//                        selectedBottomItem = 3;
//                        item.setIcon(R.drawable.menu_four_selected);
//                        lstItems = new ArrayList<>();
//                        for (int i = 0; i < strItemFourName.length; i++) {
//                            lstItems.add(new BottomItemModel(strItemFourName[i], itemFourDrawable[i]));
//                        }
//                        setBottomItemdapter(lstItems);
//                        break;
//                    case R.id.nav_item_five:
//                        selectedBottomItem = 4;
//                        item.setIcon(R.drawable.menu_five_selected);
//                        lstItems = new ArrayList<>();
//                        for (int i = 0; i < strItemFiveName.length; i++) {
//                            lstItems.add(new BottomItemModel(strItemFiveName[i], itemFiveDrawable[i]));
//                        }
//                        setBottomItemdapter(lstItems);
//                        break;
//                }
//                return false;
//            }
//        });
//    }
//
//    public void switchContent(Fragment fragment, String tag) {
//        hideKeyboard();
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.container, fragment)
//                .addToBackStack(tag)
//                .commit();
//    }
//
//    public void switchContent(Fragment fragment) {
//        hideKeyboard();
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.container, fragment)
//                .commit();
//    }
//
//    public void switchBack() {
//        hideKeyboard();
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        if (fragmentManager.getBackStackEntryCount() > 0) {
//            fragmentManager.popBackStack();
//        }
//    }
//
//    @Override
//    public void onWebServiceError(String result, String url) {
//        customToast.showCustomToast(svContext, result, customToast.ToastyError);
//    }
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//    }
//
//    private void ShowConfirmExitDialog(String head, String strTitle, String strDesc) {
//        final Dialog dialog = new Dialog(svContext);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCancelable(false);
//        dialog.setContentView(R.layout.dialog_header_twobutton);
//
//        TextView textTitle = (TextView) dialog.findViewById(R.id.dialog_title);
//        textTitle.setText(strTitle);
//        TextView textDesc = (TextView) dialog.findViewById(R.id.dialog_desc);
//        textDesc.setText(strDesc);
//        TextView textHead = (TextView) dialog.findViewById(R.id.dialog_head);
//        textHead.setText(head);
//
//        Button declineDialogButton = (Button) dialog.findViewById(R.id.bt_decline);
//        declineDialogButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//
//        Button confirmDialogButton = (Button) dialog.findViewById(R.id.bt_confirm);
//        confirmDialogButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        dialog.show();
//    }
//
//    String[] strItemOne = new String[]{"Default", "THIN", "THIN"};
//    Integer[] itemOneDrawable = new Integer[]{R.drawable.item_one, R.drawable.item_two, R.drawable.item_three};
//    Integer[] itemOneTextSize = new Integer[]{14, 18, 22};
//    String[] itemOneFontName = new String[]{"font/customfont.ttf", "font/customfont.ttf", "font/customfont.ttf"};
//    Integer[] itemOneFontColor = new Integer[]{R.color.colorPrimary, R.color.colorPrimary, R.color.colorPrimary};
//    public static final String CUSTOMFONTNAME = "font/customfont.ttf";
//
//    private void setBottomItemOneAdapter() {
//        final List<BottomItemOneModel> lstItems = new ArrayList<>();
//        for (int i = 0; i < strItemOne.length; i++) {
//            if (i == 0) {
//                lstItems.add(new BottomItemOneModel(strItemOne[i], itemOneDrawable[i], itemOneTextSize[i],
//                        itemOneFontName[i], itemOneFontColor[i], true));
//            } else {
//                lstItems.add(new BottomItemOneModel(strItemOne[i], itemOneDrawable[i], itemOneTextSize[i],
//                        itemOneFontName[i], itemOneFontColor[i], false));
//            }
//        }
//        BottomItemOneAdapter bottomItemAdapter = new BottomItemOneAdapter(this, lstItems, ItemAnimation.RIGHT_LEFT);
//        rvItemBottom.setNestedScrollingEnabled(false);
//        rvItemBottom.setAdapter(bottomItemAdapter);
//        bottomItemAdapter.setOnItemClickListener(new BottomItemOneAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, String selectionName, int position) {
//                ClickEventOnItemClick(selectionName, position, lstItems.get(position));
//            }
//        });
//    }
//
//    String[] strItemTwoName = new String[]{"edit", "delete", "copy", "to front",
//            "to back", "position", "relative position", "size",
//            "padding", "color", "texture", "opacity",
//            "rotate", "mask", "font", "style",
//            "curve", "background", "align", "spacing",
//            "line spacing", "stroke", "shadow", "inner shadow",
//            "Emboss", "perspective", "3d rotate", "3d text",
//            "3d shadow", "reflection"};
//    Integer[] itemTwoDrawable = new Integer[]{
//            R.drawable.edit, R.drawable.delete, R.drawable.edit, R.drawable.delete,
//            R.drawable.edit, R.drawable.delete, R.drawable.edit, R.drawable.delete,
//            R.drawable.edit, R.drawable.edit, R.drawable.delete, R.drawable.edit,
//            R.drawable.delete, R.drawable.edit, R.drawable.delete, R.drawable.edit,
//            R.drawable.delete, R.drawable.edit, R.drawable.delete, R.drawable.edit,
//            R.drawable.edit, R.drawable.delete, R.drawable.edit, R.drawable.delete,
//            R.drawable.edit, R.drawable.delete, R.drawable.edit, R.drawable.delete,
//            R.drawable.edit, R.drawable.delete};
//
//    String[] strItemThreeName = new String[]{"sticker", "import", "draw", "shapes",
//            "bezier", "arrow"};
//    Integer[] itemThreeDrawable = new Integer[]{
//            R.drawable.edit, R.drawable.delete, R.drawable.edit, R.drawable.delete,
//            R.drawable.edit, R.drawable.delete};
//
//    String[] strItemFourName = new String[]{"color", "transparent", "image size", "crop",
//            "image", "from gallery", "from camera"};
//    Integer[] itemFourDrawable = new Integer[]{
//            R.drawable.edit, R.drawable.delete, R.drawable.edit, R.drawable.delete,
//            R.drawable.edit, R.drawable.delete, R.drawable.edit};
//
//    String[] strItemFiveName = new String[]{"rotate", "vignette", "noise", "stripes",
//            "brightnss", "hue", "saturation"};
//    Integer[] itemFiveDrawable = new Integer[]{
//            R.drawable.edit, R.drawable.delete, R.drawable.edit, R.drawable.delete,
//            R.drawable.edit, R.drawable.delete, R.drawable.edit};
//    Object[][] settingFive = new Object[][]{
//            null,
//            {true, true, false, "alpha", 143, 30, 255, null, 0, 0, 0},
//            {false, false, false, "alpha", 43, 30, 100, "enhance", 7, 2, 15}
//    };
//
//    //autoEnable, isShowColorPicker, isRedo, settingonename, settingonedefaultvalur, settingOneminValue, settingonemaxvalue
//    //
//    private void setBottomItemdapter(final List<BottomItemModel> lstItems) {
//        BottomItemAllAdapter bottomItemAdapter = new BottomItemAllAdapter(this, lstItems, ItemAnimation.RIGHT_LEFT);
//        rvItemBottom.setNestedScrollingEnabled(false);
//        rvItemBottom.setAdapter(bottomItemAdapter);
//        bottomItemAdapter.setOnItemClickListener(new BottomItemAllAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, String selectionName, int position) {
//                ClickEventOnItemClick(selectionName, position, lstItems.get(position));
//            }
//        });
//    }
//
//    private void SetTextSize(TextView txtView, int txtSize) {
//        txtView.setTextSize(txtSize);
//    }
//
//    private void SetFont(TextView txtView, String fontName) {
//        Typeface font = Typeface.createFromAsset(getAssets(), GlobalVariables.CUSTOMFONTNAME);
//        txtView.setTypeface(font);
//    }
//
//    private void ClickEventOnItemClick(String selectionName, int settingPos, BottomItemOneModel item) {
//        if (selectedBottomItem == 0) {
//            rLayDynamic.removeAllViews();
//            AddCustomImageView(item.getDrawble());
//            AddCustomTextView(item);
//        }
//    }
//
//    private void ClickEventOnItemClick(String selectionName, int settingPos, BottomItemModel item) {
//        if (selectedBottomItem == 1) {
//            customToast.showCustomToast(selectionName, customToast.ToastySuccess);
//        } else if (selectedBottomItem == 2) {
//            customToast.showCustomToast(selectionName, customToast.ToastySuccess);
//        } else if (selectedBottomItem == 3) {
//            customToast.showCustomToast(selectionName, customToast.ToastySuccess);
//        } else if (selectedBottomItem == 4) {
//            ShowFiveSetting(selectionName, settingFive[settingPos]);
//        }
//    }
//
//    private void AddCustomImageView(int drawble) {
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        ImageView customTv = new ImageView(svContext);
//        customTv.setLayoutParams(layoutParams);
//        if (drawble == 0) {
//            ImageLoading.loadLocalImages(R.drawable.item_one, customTv);
//        } else {
//            ImageLoading.loadLocalImages(drawble, customTv);
//        }
//        customTv.setScaleType(ImageView.ScaleType.FIT_XY);
//        imgMain = customTv;
//        rLayDynamic.addView(customTv);
//    }
//
//    private int txtViewPos = 0;
//    private void AddCustomTextView(BottomItemOneModel item) {
//        KSCustomTextView customTv = new KSCustomTextView(svContext);
//        customTv.SetCustomText(item.getFontName(), item.getName(), item.getFontSize(), item.getTextColor());
//        customTv.setId(txtViewPos++);
//        rLayDynamic.addView(customTv);
//    }
//
//    public void EditText(KSCustomTextView tv, String text){
//        tv.setText(text);
//    }
//
//    public void DeleteTextView(KSCustomTextView tv){
//        tv.setText("");
//        rLayDynamic.removeViewAt(tv.getId());
//    }
//
//    public void CopyTextView(KSCustomTextView tv){
//        KSCustomTextView customTv = tv;
//        customTv.setId(txtViewPos++);
//        rLayDynamic.addView(customTv, txtViewPos);
//    }
//
//    public void tvToFront(KSCustomTextView tv){
//        tv.bringToFront();
//    }
//
//    public void tvToBack(KSCustomTextView tv){
//        customToast.showCustomToast(svContext, "to back", customToast.ToastySuccess);
//    }
//
//    public void SetPaddding(KSCustomTextView tv, int leftPadding, int rightPadding){
//        tv.setPadding(leftPadding, 0, rightPadding, 0);
//    }
//
//    public void SetTextColor(KSCustomTextView tv, int colorRes){
//        tv.setTextColor(getResources().getColor(colorRes));
//    }
//
//    public void SetTextColor(KSCustomTextView tv, String colorHexCode){
//        tv.setTextColor(Color.parseColor(colorHexCode));
//    }
//
//    public void SetMargin(KSCustomTextView tv, int marginDirection){
//        // int leftRight = 0, topBottom = 1;
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        params.setMargins(10,10,10,10);
//        tv.setLayoutParams(params);
//    }
//
//
//    private void AddCustomTextView() {
//        KSCustomTextView customTv = new KSCustomTextView(svContext);
//        customTv.SetCustomText("Test", 14, R.color.colorPrimary);
//        customTv.setId(txtViewPos++);
//        rLayDynamic.addView(customTv, txtViewPos);
//    }
//
//    private void RotateView(View imgView) {
//        imgView.setRotation(imgView.getRotation() + 90);
//    }
//
//    private Bitmap GetImageBitmap(ImageView imgView) {
//        imgView.invalidate();
//        BitmapDrawable drawable = (BitmapDrawable) imgView.getDrawable();
//        Bitmap bm = drawable.getBitmap();
//        return bm;
//    }
//
//    public Bitmap vignett(ImageView imgView, int seekBarP, int alpha, int[] gradientColor) {
////        new int[] { 0, 0, Color.BLACK }
////        p is standard value of seekbar from 1 to 100;
////        for intensity of effect u can set paint.setAlpha 0 to 255!!!
//        Bitmap bm = GetImageBitmap(imgView);
//        Bitmap image = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Bitmap.Config.ARGB_8888);
//        int rad;
//        Canvas canvas = new Canvas(image);
//        canvas.drawBitmap(bm, 0, 0, new Paint());
//        if (bm.getWidth() < bm.getHeight()) {
//            int o = (bm.getHeight() * 2) / 100;
//            rad = bm.getHeight() - o * seekBarP / 3;
//        } else {
//            int o = (bm.getWidth() * 2) / 100;
//            rad = bm.getWidth() - o * seekBarP / 3;
//        }
//        Rect rect = new Rect(0, 0, bm.getWidth(), bm.getHeight());
//        RectF rectf = new RectF(rect);
//        float[] pos = new float[]{0.0f, 0.1f, 1.0f};
//        Shader linGradLR = new RadialGradient(rect.centerX(), rect.centerY(), rad, gradientColor, pos, Shader.TileMode.CLAMP);
//        Paint paint = new Paint();
//        paint.setShader(linGradLR);
//        paint.setAntiAlias(true);
//        paint.setDither(true);
//        paint.setAlpha(alpha);
//        canvas.drawRect(rectf, paint);
//        return image;
//    }
//
//    //autoEnable, isShowColorPicker, isRedo, settingonename, settingonedefaultvalur, settingOneminValue, settingonemaxvalue
//    private void ShowFiveSetting(final String selectionName, Object[] objects) {
//        if (objects == null) {
//            onSeekChange(selectionName, 0, -1);
//        } else {
//            final RelativeLayout rlayFiveSetting = (RelativeLayout) findViewById(R.id.lay_settingfive);
//            rlayFiveSetting.setVisibility(View.VISIBLE);
//
//            final LinearLayout laySetting = (LinearLayout) findViewById(R.id.lay_btn);
//            if ((boolean) objects[0]) {
//                laySetting.setVisibility(View.VISIBLE);
//            } else {
//                laySetting.setVisibility(View.INVISIBLE);
//            }
//
//            SwitchCompat simpleSwitch = (SwitchCompat) findViewById(R.id.simpleSwitch);
//            Boolean switchState = simpleSwitch.isChecked();
//            simpleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    if (isChecked) {
//                        laySetting.setVisibility(View.VISIBLE);
//                    } else {
//                        laySetting.setVisibility(View.INVISIBLE);
//                    }
//                }
//            });
//
//            LinearLayout laySeekBarOne = (LinearLayout) findViewById(R.id.seekbar_lay);
//            LinearLayout laySeekBarTwo = (LinearLayout) findViewById(R.id.seekbar_lay_two);
//
//            TextView txtSettingOne = (TextView) findViewById(R.id.txt_settingname);
//            TextView txtSettingTwo = (TextView) findViewById(R.id.txt_settingname_two);
//            txtSettingOne.setText(String.valueOf(objects[3]));
//
//            RelativeLayout layPickerColor = (RelativeLayout) findViewById(R.id.lay_color_picker);
//            if ((boolean) objects[1]) {
//                layPickerColor.setVisibility(View.VISIBLE);
//                RecyclerView rvPickerColor = (RecyclerView) findViewById(R.id.rv_picker_color);
//                ColorPickerAdapter bottomItemAdapter = new ColorPickerAdapter(this, ItemAnimation.RIGHT_LEFT);
//                rvPickerColor.setNestedScrollingEnabled(false);
//                rvPickerColor.setAdapter(bottomItemAdapter);
//                bottomItemAdapter.setOnItemClickListener(new ColorPickerAdapter.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(View view, String colorCode, int position) {
//                        colorVignette = new int[]{Color.parseColor(colorCode), Color.parseColor(colorCode),
//                                Color.parseColor(colorCode)};
//                        onSeekChange(selectionName, 0, -1);
//                    }
//                });
//            } else {
//                layPickerColor.setVisibility(View.GONE);
//            }
//
//            final TextView txtSettingOneValue = (TextView) findViewById(R.id.txt_settingvalue);
//            final TextView txtSettingTwoValue = (TextView) findViewById(R.id.txt_settingvalue_two);
//
//            if (objects[7] == null) {
//                laySeekBarTwo.setVisibility(View.GONE);
//            } else {
//                txtSettingTwo.setText(String.valueOf(objects[7]));
//            }
//
//            KSCustomSeekbar ksCustomSeek = new KSCustomSeekbar(this, (Integer) objects[4], (Integer) objects[5], (Integer) objects[6]);
//            ksCustomSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//                @Override
//                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                    txtSettingOneValue.setText(String.valueOf(i));
//                    onSeekChange(selectionName, i, 0);
//                }
//
//                @Override
//                public void onStartTrackingTouch(SeekBar seekBar) {
//                }
//
//                @Override
//                public void onStopTrackingTouch(SeekBar seekBar) {
//                }
//            });
//            laySeekBarOne.addView(ksCustomSeek);
//
//            KSCustomSeekbar ksCustomSeekTwo = new KSCustomSeekbar(this, (Integer) objects[8], (Integer) objects[9], (Integer) objects[10]);
//            ksCustomSeekTwo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//                @Override
//                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                    txtSettingTwoValue.setText(i);
//                    onSeekChange(selectionName, i, 1);
//                }
//
//                @Override
//                public void onStartTrackingTouch(SeekBar seekBar) {
//                }
//
//                @Override
//                public void onStopTrackingTouch(SeekBar seekBar) {
//                }
//            });
//            laySeekBarTwo.addView(ksCustomSeekTwo);
//
//            Button dialogClose = (Button) findViewById(R.id.dialog_close);
//            dialogClose.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    rlayFiveSetting.setVisibility(View.GONE);
//                }
//            });
//
//            Button dialogApply = (Button) findViewById(R.id.dialog_apply);
//            dialogApply.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    onSeekChange(selectionName, 0, -1);
//                }
//            });
//        }
//
//    }
//
//    int vignetteSeekBarInitialValue = 50;
//    int vignetteSeekBarAlphaValue = 60;
//    int[] colorVignette = new int[]{0, 0, R.color.colorPrimary};
//
//    int defaultPercentageNoise = 50;
//    float defaultContrast = 0;
//    float defaultBrightness = 0;
//    float defaultHue = 0;
//    float defaultSaturation = 0;
//
//    private Bitmap modifiedWallpaper;
//    private Canvas canvas;
//
//    private void onSeekChange(String selectionName, int seekValue, int seekPosition) {
//        Bitmap bitmap = null;
//        if (selectionName.equals("rotate")) {
//            RotateView(imgMain);
//        } else if (selectionName.equals("vignett")) {
//            bitmap = vignett(imgMain, vignetteSeekBarInitialValue, vignetteSeekBarAlphaValue, colorVignette);
//            if (bitmap != null) {
//                imgMain.setImageBitmap(bitmap);
//            }
//        } else if (selectionName.equals("noise")) {
//            bitmap = applyFleaEffect(imgMain, defaultPercentageNoise);
//            if (bitmap != null) {
//                imgMain.setImageBitmap(bitmap);
//            }
//        } else if (selectionName.equals("stripes")) {
//
//        } else if (selectionName.equals("brightness")) {
//            bitmap = changeBitmapContrastBrightness(imgMain, defaultContrast, defaultBrightness);
//            if (bitmap != null) {
//                imgMain.setImageBitmap(bitmap);
//            }
//        } else if (selectionName.equals("contrast")) {
//            bitmap = adjustedContrast(imgMain, defaultBrightness);
//            if (bitmap != null) {
//                imgMain.setImageBitmap(bitmap);
//            }
//        } else if (selectionName.equals("hue")) {
//            ColorMatrix matrix = calculateColorMatrix(defaultContrast, defaultHue, defaultSaturation);
//            drawBitmap(matrix);
//        } else if (selectionName.equals("saturation")) {
//            ColorMatrix matrix = calculateColorMatrix(defaultContrast, defaultHue, defaultSaturation);
//            drawBitmap(matrix);
//        } else if(selectionName.equals("color")){
//            GetGradientDrawable.setBitmapDrawable(imgMain, "#f59200");
//        } else if(selectionName.equals("transparent")){
//            GetGradientDrawable.SetTransparentBackGround(imgMain);
//        } else if(selectionName.equals("image size")){
//            GetGradientDrawable.SetTransparentBackGround(imgMain);
//        } else if(selectionName.equals("crop")){
//
//        } else if(selectionName.equals("image")){
//
//        } else if(selectionName.equals("from gallery") || selectionName.equals("from camera") ||
//                selectionName.equals("import")) {
//            Intent intent = new Intent(ActivityMain.this, ActivityBrowseImage.class);
//            startActivity(intent);
//        } else if(selectionName.equals("stickers")){
//
//        } else if(selectionName.equals("draw")){
//
//        } else if(selectionName.equals("shapes")){
//
//        } else if(selectionName.equals("bezier")){
//
//        } else if(selectionName.equals("arrow")){
//
//        }
//    }
//
//    private void drawBitmap(ColorMatrix colorMatrix) {
//        Paint paint = new Paint();
//        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
//        Bitmap bmp = GetImageBitmap(imgMain);
//        modifiedWallpaper = bmp.copy(Bitmap.Config.ARGB_8888, true);
//        canvas = new Canvas(modifiedWallpaper);
//        canvas.drawBitmap(modifiedWallpaper, 0, 0, paint);
//        imgMain.setImageBitmap(modifiedWallpaper);
//    }
//
//    private ColorMatrix calculateColorMatrix(float contrastProgress, float hueProgress, float satProgress) {
//        ColorMatrix colorMatrix = new ColorMatrix();
//        // saturation
//        colorMatrix.setSaturation(satProgress / 256);
//        // hue
//        float cosVal = (float) Math.cos(hueProgress);
//        float sinVal = (float) Math.sin(hueProgress);
//        float lumR   = 0.213f;
//        // logic...
//        // colorMatrix.postConcat(new ColorMatrix(mat));
//
//        float cb = contrastProgress == 0 ? 0 : - (contrastProgress / 1.8f) * 5;
//        // contrast
//        colorMatrix.postConcat(new ColorMatrix(new float[]{
//                        contrastProgress, 0, 0, 0, cb,
//                        0, contrastProgress, 0, 0, cb,
//                        0, 0, contrastProgress, 0, cb,
//                        0, 0, 0, contrastProgress, 0
//                }));
//        return colorMatrix;
//    }
//
//    /**
//     * @param contrast   0..10 1 is default
//     * @param brightness -255..255 0 is default
//     * @return new bitmap
//     */
//    public Bitmap changeBitmapContrastBrightness(ImageView imgView, float contrast, float brightness) {
//        Bitmap bmp = GetImageBitmap(imgView);
//        ColorMatrix cm = new ColorMatrix(new float[]{
//                contrast, 0, 0, 0, brightness,
//                0, contrast, 0, 0, brightness,
//                0, 0, contrast, 0, brightness,
//                0, 0, 0, contrast, 0
//        });
//
//        Bitmap ret = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());
//        Canvas canvas = new Canvas(ret);
//        Paint paint = new Paint();
//        paint.setColorFilter(new ColorMatrixColorFilter(cm));
//        canvas.drawBitmap(bmp, 0, 0, paint);
//
//        return ret;
//    }
//
//    private Bitmap adjustedContrast(ImageView imgView, double brightness) {
//        Bitmap src = GetImageBitmap(imgView);
//        // image size
//        int width = src.getWidth();
//        int height = src.getHeight();
//        // create output bitmap
//        // create a mutable empty bitmap
//        Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
//        // create a canvas so that we can draw the bmOut Bitmap from source bitmap
//        Canvas c = new Canvas();
//        c.setBitmap(bmOut);
//        // draw bitmap to bmOut from src bitmap so we can modify it
//        c.drawBitmap(src, 0, 0, new Paint(Color.BLACK));
//        // color information
//        int A, R, G, B;
//        int pixel;
//        // get contrast value
//        double contrast = Math.pow((100 + brightness) / 100, 2);
//        // scan through all pixels
//        for (int x = 0; x < width; ++x) {
//            for (int y = 0; y < height; ++y) {
//                // get pixel color
//                pixel = src.getPixel(x, y);
//                A = Color.alpha(pixel);
//                // apply filter contrast for every channel R, G, B
//                R = Color.red(pixel);
//                R = (int) (((((R / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
//                if (R < 0) {
//                    R = 0;
//                } else if (R > 255) {
//                    R = 255;
//                }
//
//                G = Color.green(pixel);
//                G = (int) (((((G / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
//                if (G < 0) {
//                    G = 0;
//                } else if (G > 255) {
//                    G = 255;
//                }
//
//                B = Color.blue(pixel);
//                B = (int) (((((B / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
//                if (B < 0) {
//                    B = 0;
//                } else if (B > 255) {
//                    B = 255;
//                }
//
//                // set new pixel color to output bitmap
//                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
//            }
//        }
//        return bmOut;
//    }
//
//    public Bitmap applyFleaEffect(ImageView imgView, int percentNoise) {
//        Bitmap source = GetImageBitmap(imgView);
//        // get source image size
//        int width = source.getWidth();
//        int height = source.getHeight();
//        int[] pixels = new int[width * height];
//        // get pixel array from source
//        source.getPixels(pixels, 0, width, 0, 0, width, height);
//        // create a random object
//        Random random = new Random();
//
//        int index = 0;
//        // Note: Declare the c and randColor variables outside of the for loops
//        int c = 0;
//        int randColor = 0;
//        // iterate through pixels
//        for (int y = 0; y < height; ++y) {
//            for (int x = 0; x < width; ++x) {
//                if (random.nextInt(101) < percentNoise) {
//                    // Skip this iteration a certain percentage of the time
//                    continue;
//                }
//                // get current index in 2D-matrix
//                index = y * width + x;
//                // get random color
//                c = random.nextInt(255);
//                randColor = Color.rgb(c, c, c);
//                pixels[index] |= randColor;
//            }
//        }
//        Bitmap bmOut = Bitmap.createBitmap(width, height, source.getConfig());
//        bmOut.setPixels(pixels, 0, width, 0, 0, width, height);
//        return bmOut;
//    }
//
//    private void SetColorPaicker() {
//        colorPickerView = findViewById(R.id.colorPickerView);
//        BubbleFlag bubbleFlag = new BubbleFlag(this);
//        bubbleFlag.setFlagMode(FlagMode.FADE);
//        colorPickerView.setFlagView(bubbleFlag);
//        colorPickerView.setColorListener(new ColorEnvelopeListener() {
//            @Override
//            public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {
//                setLayoutColor(envelope);
//            }
////          Timber.d("color: %s", envelope.getHexCode());
//        });
//
//        // attach alphaSlideBar
//        final AlphaSlideBar alphaSlideBar = findViewById(R.id.alphaSlideBar);
//        colorPickerView.attachAlphaSlider(alphaSlideBar);
//
//        // attach brightnessSlideBar
//        final BrightnessSlideBar brightnessSlideBar = findViewById(R.id.brightnessSlide);
//        colorPickerView.attachBrightnessSlider(brightnessSlideBar);
//        colorPickerView.setLifecycleOwner(this);
//    }
//
//    private void ShowColorPickerDialog() {
//        ColorPickerDialog.Builder builder =
//                new ColorPickerDialog.Builder(this)
//                        .setTitle("ColorPicker Dialog")
//                        .setPreferenceName("Test")
//                        .setPositiveButton(getString(R.string.label_apply), new ColorEnvelopeListener() {
//                            @Override
//                            public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {
//                                setLayoutColor(envelope);
//                            }
//                        })
//                        .setNegativeButton(getString(R.string.label_cancel), new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                dialogInterface.dismiss();
//                            }
//                        });
//        builder.getColorPickerView().setFlagView(new BubbleFlag(this));
//        builder.show();
//    }
//
//    private ColorPickerView colorPickerView;
//
//    private void OpenColorPicker(Uri imageUri) {
//        final InputStream imageStream;
//        try {
//            imageStream = getContentResolver().openInputStream(imageUri);
//            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
//            Drawable drawable = new BitmapDrawable(getResources(), selectedImage);
//            colorPickerView.setPaletteDrawable(drawable);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @SuppressLint("SetTextI18n")
//    private void setLayoutColor(ColorEnvelope envelope) {
//        TextView textView = findViewById(R.id.textView);
//        textView.setText("#" + envelope.getHexCode());
//
//        AlphaTileView alphaTileView = findViewById(R.id.alphaTileView);
//        alphaTileView.setPaintColor(envelope.getColor());
//    }
//}