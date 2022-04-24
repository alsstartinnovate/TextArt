package com.startup.textart.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.startup.textart.dialog.SettingDialog;
import com.startup.textart.fragments.QuotesFragment;
import com.startup.textart.R;
import com.startup.textart.adapter.ColorAdapter;
import com.startup.textart.adapter.ViewPagerAdapter;
import com.startup.textart.ads.AdmobAds;
import com.startup.textart.ads.FacebookAds;
import com.startup.textart.dialog.DiscardDialog;
import com.startup.textart.dialog.TextEditorDialog;
import com.startup.textart.fragments.ColorFragment;
import com.startup.textart.fragments.TextEditorFragment;
import com.startup.textart.fragments.imagetools.EmojiFragment;
import com.startup.textart.fragments.imagetools.OverlaysFragment;
import com.startup.textart.fragments.imagetools.overplay.ChristmasFragment;
import com.startup.textart.fragments.imagetools.overplay.EmoticonsFragment;
import com.startup.textart.fragments.imagetools.overplay.FitnessFragment;
import com.startup.textart.fragments.imagetools.overplay.FoodFragment;
import com.startup.textart.fragments.imagetools.overplay.GeometryFragment;
import com.startup.textart.fragments.imagetools.overplay.HalloweenFragment;
import com.startup.textart.fragments.imagetools.overplay.LoveFragment;
import com.startup.textart.fragments.imagetools.overplay.MotivationFragment;
import com.startup.textart.fragments.imagetools.overplay.NativeFragment;
import com.startup.textart.fragments.imagetools.overplay.PhrasesFragment;
import com.startup.textart.fragments.imagetools.overplay.SayingsFragment;
import com.startup.textart.fragments.imagetools.overplay.SummerFragment;
import com.startup.textart.fragments.imagetools.overplay.TravelFragment;
import com.startup.textart.fragments.imagetools.overplay.WinterFragment;
import com.startup.textart.fragments.imagetools.PhotoFragment;
import com.startup.textart.fragments.imagetools.StickerFragment;
import com.startup.textart.fragments.imagetools.TuneFragment;
import com.startup.textart.fragments.texttools.FontFragment;
import com.startup.textart.fragments.texttools.FormatTextFragment;
import com.startup.textart.fragments.texttools.HightLightTextFragment;
import com.startup.textart.fragments.texttools.ShadowTextFragment;
import com.startup.textart.fragments.texttools.SpacingTextFragment;
import com.startup.textart.interfaces.ColorFragmentListener;
import com.startup.textart.interfaces.FontFragmentListener;
import com.startup.textart.interfaces.FormatTextFragmentListener;
import com.startup.textart.interfaces.HightLightFragmentListener;
import com.startup.textart.interfaces.OverlaysFragmentListener;
import com.startup.textart.interfaces.OverplayListener;
import com.startup.textart.interfaces.ShadowFragmentListener;
import com.startup.textart.interfaces.SpacingFragmentListener;
import com.startup.textart.interfaces.StrokeFragmentListener;
import com.startup.textart.model.unsplash.Photo;
import com.startup.textart.photoeditor.OnPhotoEditorListener;
import com.startup.textart.photoeditor.PhotoEditor;
import com.startup.textart.photoeditor.PhotoEditorView;
import com.startup.textart.photoeditor.RoundFrameLayout;
import com.startup.textart.photoeditor.RoundViewDelegate;
import com.startup.textart.photoeditor.SaveSettings;
import com.startup.textart.photoeditor.StrokeTextView;
import com.startup.textart.photoeditor.ViewType;
import com.startup.textart.unit.BitmapProcess;
import com.startup.textart.unit.ColorFilterGenerator;
import com.startup.textart.unit.ViewAnimation;
import com.startup.textart.unsplash.SplashPicker;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.google.android.material.tabs.TabLayout;
import com.startup.textart.Utils;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import nl.invissvenska.modalbottomsheetdialog.Item;
import nl.invissvenska.modalbottomsheetdialog.ModalBottomSheetDialog;

public class EditPhotoActivity extends AppCompatActivity implements
        View.OnClickListener, TextEditorFragment.TextFragmentListener,
        EmojiFragment.EmojiListener, PhotoFragment.PhotoListener,
        StickerFragment.StickerFragmentListener, TuneFragment.TuneFragmentListener,
        ColorFragmentListener, FontFragmentListener, FormatTextFragmentListener,
        HightLightFragmentListener, OverlaysFragmentListener, OverplayListener,
        ShadowFragmentListener, SpacingFragmentListener, StrokeFragmentListener,
        OnPhotoEditorListener, ModalBottomSheetDialog.Listener, QuotesFragment.QuotesFragmentListener,
        PhotoFragment.PhotoBackListener {
    private RoundFrameLayout border;

    private int brightnessFinal = 0;
    private RoundFrameLayout btnColorPicker;
    public ImageView btnFinish;
    public ImageView btnRedo, btnNone;
    public ImageView btnUndo;
    private int colorBackground;
    private int colorTextShadow = Color.parseColor("#000000");
    private int constrantFinal = 1;
    private int countMain = 0;
    private int countOverplay = 0;
    private int countPhoto = 0;
    private int countText = 0;
    public int currentTabTool = 0;
    private final FragmentManager fragmentManager = getSupportFragmentManager();
    private int hueFinal = 1;
    public ImageView imageViewMain;
    private PhotoEditorView imgPhotoEditor;
    private ChristmasFragment mChristmasFragment;
    private EmoticonsFragment mEmoticonsFragment;
    private FitnessFragment mFitnessFragment;
    private FoodFragment mFoodFragment;
    private GeometryFragment mGeometryFragment;
    private HalloweenFragment mHalloweenFragment;
    private LoveFragment mLoveFragment;
    private MotivationFragment mMotivationFragment;
    private NativeFragment mNativeFragment;
    public PhotoEditor mPhotoEditor;
    private PhrasesFragment mPhrasesFragment;
    private SayingsFragment mSayingFragment;
    private SummerFragment mSummerFragment;
    private TravelFragment mTravelFragment;
    private WinterFragment mWinterFragment;
    public int numberAddedView;
    private String opticalBackground = "66";
    private int opticalText = 255;
    public ProgressBar progressBarLoading;
    private int rRadius = 0;
    private int rY = 0;
    private RecyclerView recyclerPhotoColor;
    public Bitmap resourceGraphic;
    private RelativeLayout rlColorPhoto;
    public RelativeLayout rlMainTool;
    private RelativeLayout rlPhotoTools;
    private RelativeLayout rlTextTool;
    private int saturationFinal = 1;
    private SeekBar sbRotatePhoto;
    private SeekBar sbTranparencyPhoto;
    private int styleText;
    private TabLayout tabLayoutTextTools;
    public TabLayout tablayoutImageTools;
    private StrokeTextView textViewMain;
    public View viewMain;
    private ViewPager viewPagerImageTools;
    private ViewPager viewPagerTextTools;
    DiscardDialog discardDialog;

    public void onStartViewChangeListener(ViewType viewType) {
    }

    public void onStopViewChangeListener(ViewType viewType) {
    }

    @Override
    public void onCreate(Bundle bundle) {
        long currentThreadTimeMillis = SystemClock.currentThreadTimeMillis();
        super.onCreate(bundle);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_edit_photo);
        Log.d("XXXXXX", "Time1 " + (SystemClock.currentThreadTimeMillis() - currentThreadTimeMillis));
        long currentThreadTimeMillis2 = SystemClock.currentThreadTimeMillis();
        addControls();
        addPhotoColor();
        setImageTranparency();
        Log.d("XXXXXX", "Time2 " + (SystemClock.currentThreadTimeMillis() - currentThreadTimeMillis2));
        long currentThreadTimeMillis3 = SystemClock.currentThreadTimeMillis();
        this.mPhotoEditor = new PhotoEditor.Builder(this, this.imgPhotoEditor).setPinchTextScalable(true).setDefaultEmojiTypeface(Typeface.createFromAsset(getAssets(), "font/san_regular.ttf")).build();
        this.mPhotoEditor.setOnPhotoEditorListener(this);

        Log.d("XXXXXX", "Time3 " + (SystemClock.currentThreadTimeMillis() - currentThreadTimeMillis3));
        long currentThreadTimeMillis4 = SystemClock.currentThreadTimeMillis();
        getData();

//        String strDefaultData = "http://rawdata.ksinfoteck.in/textart/defaultimage.jpg";
//        Glide.with(this).load(strDefaultData).into(this.imgPhotoEditor.getSource());

        Log.d("XXXXXX", "Time4 " + (SystemClock.currentThreadTimeMillis() - currentThreadTimeMillis4));
        long currentThreadTimeMillis5 = SystemClock.currentThreadTimeMillis();
        setupViewPager(this.viewPagerTextTools);
        this.tabLayoutTextTools.setupWithViewPager(this.viewPagerTextTools);
        setupViewPagerImageTools(this.viewPagerImageTools);
        this.tablayoutImageTools.setupWithViewPager(this.viewPagerImageTools);
        Log.d("XXXXXX", "Time5 " + (SystemClock.currentThreadTimeMillis() - currentThreadTimeMillis5));
        long currentThreadTimeMillis6 = SystemClock.currentThreadTimeMillis();
        setupTabIconsTextTool();
        setupTabIconsImageTool();
        Log.d("XXXXXX", "Time6 " + (SystemClock.currentThreadTimeMillis() - currentThreadTimeMillis6));
        long currentThreadTimeMillis7 = SystemClock.currentThreadTimeMillis();
//        AdmobAds.loadBanner(this);
//        FacebookAds.loadBanner(this);
        Log.d("XXXXXX", "Time7 " + (SystemClock.currentThreadTimeMillis() - currentThreadTimeMillis7));
    }

    private static final String TAG = "MainAcitivty";
    private static final int TAKE_PICTURE = 111;
    public int requestMode = 1;
    public String mCurrentPhotoPath;

    private void setImageTranparency() {
        this.sbTranparencyPhoto.setMax(255);
        this.sbTranparencyPhoto.setProgress(255);
        this.sbTranparencyPhoto.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (EditPhotoActivity.this.imageViewMain != null) {
                    EditPhotoActivity.this.imageViewMain.setImageAlpha(i);
                }
            }
        });
        this.sbRotatePhoto.setMax(360);
        this.sbRotatePhoto.setProgress(0);
        this.sbRotatePhoto.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (EditPhotoActivity.this.viewMain != null) {
                    EditPhotoActivity.this.viewMain.setRotation((float) i);
                }
            }
        });
    }

    private void addPhotoColor() {
        this.recyclerPhotoColor.setHasFixedSize(true);
        this.recyclerPhotoColor.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        ColorAdapter colorAdapter = new ColorAdapter(this, i -> BitmapProcess.changeBitmapColor(EditPhotoActivity.this.resourceGraphic, EditPhotoActivity.this.imageViewMain, i));
        this.recyclerPhotoColor.setAdapter(colorAdapter);
        this.btnColorPicker.setOnClickListener(view -> ColorPickerDialogBuilder.with(EditPhotoActivity.this).setTitle("Select color").wheelType(ColorPickerView.WHEEL_TYPE.FLOWER).density(12).setPositiveButton("OK", (dialogInterface, i, numArr) -> BitmapProcess.changeBitmapColor(EditPhotoActivity.this.resourceGraphic, EditPhotoActivity.this.imageViewMain, i)).setNegativeButton("Cancel", (dialogInterface, i) -> {
        }).build().show());
    }

    private void setupTabIconsTextTool() {
        TextView textView = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        textView.setText("Font");
        textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_text_font_focus, 0, 0);
        Objects.requireNonNull(this.tabLayoutTextTools.getTabAt(0)).setCustomView(textView);
        TextView textView2 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        textView2.setText("Format");
        textView2.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_text_format_focus, 0, 0);
        Objects.requireNonNull(this.tabLayoutTextTools.getTabAt(1)).setCustomView(textView2);
        TextView textView3 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        textView3.setText("Color");
        textView3.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_text_color_focus, 0, 0);
        Objects.requireNonNull(this.tabLayoutTextTools.getTabAt(2)).setCustomView(textView3);


        TextView textView5 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        textView5.setText("Highlight");
        textView5.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_text_highlight_focus, 0, 0);
        Objects.requireNonNull(this.tabLayoutTextTools.getTabAt(3)).setCustomView(textView5);
        TextView textView6 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        textView6.setText("Shadow");
        textView6.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_text_shadow_focus, 0, 0);
        Objects.requireNonNull(this.tabLayoutTextTools.getTabAt(4)).setCustomView(textView6);
        if (Build.VERSION.SDK_INT >= 21) {
            TextView textView7 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
            textView7.setText("Spacing");
            textView7.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_text_spacing_focus, 0, 0);
            Objects.requireNonNull(this.tabLayoutTextTools.getTabAt(5)).setCustomView(textView7);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        FontFragment fontFragment = new FontFragment();
        fontFragment.setListener(this);
        viewPagerAdapter.addFrag(fontFragment, "Font");
        FormatTextFragment formatTextFragment = new FormatTextFragment();
        viewPagerAdapter.addFrag(formatTextFragment, "Format");
        formatTextFragment.setListener(this);
        ColorFragment colorFragment = new ColorFragment();
        viewPagerAdapter.addFrag(colorFragment, "Color");
        colorFragment.setListener(this);

        HightLightTextFragment hightLightTextFragment = new HightLightTextFragment();
        viewPagerAdapter.addFrag(hightLightTextFragment, "Highlight");
        hightLightTextFragment.setListener(this);
        ShadowTextFragment shadowTextFragment = new ShadowTextFragment();
        viewPagerAdapter.addFrag(shadowTextFragment, "Shadow");
        shadowTextFragment.setListener(this);
        if (Build.VERSION.SDK_INT >= 21) {
            SpacingTextFragment spacingTextFragment = new SpacingTextFragment();
            viewPagerAdapter.addFrag(spacingTextFragment, "Spacing");
            spacingTextFragment.setListener(this);
        }
        viewPager.setAdapter(viewPagerAdapter);
        if (Build.VERSION.SDK_INT >= 21) {
            viewPager.setOffscreenPageLimit(7);
        } else {
            viewPager.setOffscreenPageLimit(6);
        }
    }

    @SuppressLint("SetTextI18n")
    private void setupTabIconsImageTool() {
        TextView textView = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        textView.setText("Add");
        textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_add_text, 0, 0);
        Objects.requireNonNull(this.tablayoutImageTools.getTabAt(0)).setCustomView(textView);
        TextView textView2 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        textView2.setText("Sticker");
        textView2.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_add_sticker, 0, 0);
        Objects.requireNonNull(this.tablayoutImageTools.getTabAt(1)).setCustomView(textView2);
        TextView textView3 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        textView3.setText("Overlays");
        textView3.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_overplay, 0, 0);
        Objects.requireNonNull(this.tablayoutImageTools.getTabAt(2)).setCustomView(textView3);
        int i = 3;
        if (Build.VERSION.SDK_INT >= 21) {
            TextView textView4 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
            textView4.setText("Emoji");
            textView4.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_emoji, 0, 0);
            Objects.requireNonNull(this.tablayoutImageTools.getTabAt(3)).setCustomView(textView4);
            i = 4;
        }
        TextView textView5 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        textView5.setText("Photo");
        textView5.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_add_photo, 0, 0);
        Objects.requireNonNull(this.tablayoutImageTools.getTabAt(i)).setCustomView(textView5);

        TextView textView6 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        textView6.setText("Tune");
        textView6.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tune, 0, 0);
        Objects.requireNonNull(this.tablayoutImageTools.getTabAt(i + 1)).setCustomView(textView6);

        TextView tvChange = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tvChange.setText("Change");
        tvChange.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_add_text, 0, 0);
        Objects.requireNonNull(this.tablayoutImageTools.getTabAt(i + 2)).setCustomView(tvChange);

        if ((getResources().getConfiguration().screenLayout & 15) == 4) {
            this.tablayoutImageTools.setTabMode(TabLayout.MODE_FIXED);
        }
        this.tablayoutImageTools.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @SuppressLint("ResourceType")
            public void onTabSelected(TabLayout.Tab tab) {
                EditPhotoActivity.this.currentTabTool = tab.getPosition();
                if (tab.getPosition() != 0) {
                    tab.getPosition();
                } else if (EditPhotoActivity.this.numberAddedView < 6) {
                    EditPhotoActivity.this.mPhotoEditor.addText(EditPhotoActivity.this.getString(R.string.double_tap), ContextCompat.getColor(EditPhotoActivity.this, 17170443));
                } else {
                    Toast.makeText(EditPhotoActivity.this, R.string.max_item, Toast.LENGTH_SHORT).show();
                }
            }

            @SuppressLint("ResourceType")
            public void onTabReselected(TabLayout.Tab tab) {
                if (tab.getPosition() != 0) {
                    return;
                }
                if (EditPhotoActivity.this.numberAddedView < 6) {
                    EditPhotoActivity.this.mPhotoEditor.addText(EditPhotoActivity.this.getString(R.string.double_tap), ContextCompat.getColor(EditPhotoActivity.this, 17170443));
                } else {
                    Toast.makeText(EditPhotoActivity.this, R.string.max_item, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setupViewPagerImageTools(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFrag(new Fragment(), "Add");
        StickerFragment stickerFragment = new StickerFragment();
        viewPagerAdapter.addFrag(stickerFragment, "Sticker");
        stickerFragment.setStickerFragmentListener(this);
        OverlaysFragment overlaysFragment = new OverlaysFragment();
        viewPagerAdapter.addFrag(overlaysFragment, "Overlays");
        overlaysFragment.setListener(this);
        if (Build.VERSION.SDK_INT >= 21) {
            EmojiFragment emojiFragment = new EmojiFragment();
            viewPagerAdapter.addFrag(emojiFragment, "Emoji");
            emojiFragment.setEmojiListener(this);
        }
        PhotoFragment photoFragment = new PhotoFragment();
        viewPagerAdapter.addFrag(photoFragment, "Photo");
        photoFragment.setPhotoListener(this);
        TuneFragment tuneFragment = new TuneFragment();
        viewPagerAdapter.addFrag(tuneFragment, "Tunes");
        tuneFragment.setTuneFragmentListener(this);

        PhotoFragment changePhotoFragment = new PhotoFragment();
        viewPagerAdapter.addFrag(changePhotoFragment, "Change");
        changePhotoFragment.setBackPhotoListener(this);

        viewPager.setAdapter(viewPagerAdapter);

        Log.d("@@", "setupViewPagerImageTools " + Objects.requireNonNull(viewPager.getAdapter()).getCount());
    }

    @SuppressLint("ResourceType")
    private void getData() {
        SystemClock.currentThreadTimeMillis();
        Intent intent = getIntent();
        Uri data = intent.getData();
        if (data != null) {
            try {
                Bitmap mainBitmap = BitmapProcess.handleSamplingAndRotationBitmap(this, data);
                Glide.with(this).load(mainBitmap).into(this.imgPhotoEditor.getSource());
                this.mPhotoEditor.addText(getString(R.string.double_tap), ContextCompat.getColor(EditPhotoActivity.this, 17170443));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        int intExtra = intent.getIntExtra("SampleBackground", 0);
        if (intExtra != 0) {
            Glide.with(this).load(intExtra).into(this.imgPhotoEditor.getSource());
//            this.mPhotoEditor.addText(getString(R.string.double_tap), ContextCompat.getColor(EditPhotoActivity.this, 17170443));
        }
        Photo photo = intent.getParcelableExtra(SplashPicker.KEY_IMAGE);
        if (photo != null) {
            this.progressBarLoading.setVisibility(View.VISIBLE);
            this.btnFinish.setClickable(false);
            this.btnRedo.setClickable(false);
            this.btnUndo.setClickable(false);
            Glide.with(this).load(photo.getUrls().getFull()).apply((new RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE)).dontAnimate()).listener(new RequestListener<Drawable>() {
                public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                    return false;
                }

                public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                    EditPhotoActivity.this.progressBarLoading.setVisibility(View.GONE);
//                    EditPhotoActivity.this.mPhotoEditor.addText(EditPhotoActivity.this.getString(R.string.double_tap), ContextCompat.getColor(EditPhotoActivity.this, 17170443));
                    EditPhotoActivity.this.btnFinish.setClickable(true);
                    EditPhotoActivity.this.btnRedo.setClickable(true);
                    EditPhotoActivity.this.btnUndo.setClickable(true);
                    return false;
                }
            }).into(this.imgPhotoEditor.getSource());
        }
    }


    private void addControls() {
        this.btnUndo = findViewById(R.id.btnUndo);
        ImageView btnBack = findViewById(R.id.btnBack);
        this.btnRedo = findViewById(R.id.btnRedo);
        this.btnNone = findViewById(R.id.btn_none);
        this.btnFinish = findViewById(R.id.btnFinish);
        ImageView btnBackTextTools = findViewById(R.id.btnBackTextTools);
        ImageView btnAddText = findViewById(R.id.btnAddText_toolbar);
        this.rlTextTool = findViewById(R.id.rl_text_tool);
        this.rlMainTool = findViewById(R.id.rl_main_tool);
        this.rlPhotoTools = findViewById(R.id.rl_photo_tool);
        this.rlColorPhoto = findViewById(R.id.rl_color_photo);

        this.imgPhotoEditor = findViewById(R.id.imgPhotoEditor);
        this.viewPagerTextTools = findViewById(R.id.viewpagerTextTools);
        this.tabLayoutTextTools = findViewById(R.id.tablayoutTextTools);
        this.viewPagerImageTools = findViewById(R.id.viewpagerImageTools);
        this.tablayoutImageTools = findViewById(R.id.tablayoutImageTools);
        this.progressBarLoading = findViewById(R.id.progress_circular_loading);
        this.mChristmasFragment = new ChristmasFragment();
        this.mChristmasFragment.setOverplayListener(this);
        this.mEmoticonsFragment = new EmoticonsFragment();
        this.mEmoticonsFragment.setOverplayListener(this);
        this.mFitnessFragment = new FitnessFragment();
        this.mFitnessFragment.setOverplayListener(this);
        this.mFoodFragment = new FoodFragment();
        this.mFoodFragment.setOverplayListener(this);
        this.mGeometryFragment = new GeometryFragment();
        this.mGeometryFragment.setOverplayListener(this);
        this.mHalloweenFragment = new HalloweenFragment();
        this.mHalloweenFragment.setOverplayListener(this);
        this.mLoveFragment = new LoveFragment();
        this.mLoveFragment.setOverplayListener(this);
        this.mMotivationFragment = new MotivationFragment();
        this.mMotivationFragment.setOverplayListener(this);
        this.mNativeFragment = new NativeFragment();
        this.mNativeFragment.setOverplayListener(this);
        this.mPhrasesFragment = new PhrasesFragment();
        this.mPhrasesFragment.setOverplayListener(this);
        this.mSayingFragment = new SayingsFragment();
        this.mSayingFragment.setOverplayListener(this);
        this.mSummerFragment = new SummerFragment();
        this.mSummerFragment.setOverplayListener(this);
        this.mTravelFragment = new TravelFragment();
        this.mTravelFragment.setOverplayListener(this);
        this.mWinterFragment = new WinterFragment();
        this.mWinterFragment.setOverplayListener(this);
        btnBack.setOnClickListener(this);
        btnBackTextTools.setOnClickListener(this);
        this.btnUndo.setOnClickListener(this);
        this.btnRedo.setOnClickListener(this);
        this.btnFinish.setOnClickListener(this);
        this.imgPhotoEditor.setOnClickListener(this);
        btnAddText.setOnClickListener(this);
        this.recyclerPhotoColor = findViewById(R.id.recycler_color_photo);
        this.sbTranparencyPhoto = findViewById(R.id.seekbar_photo_transparency);
        this.sbRotatePhoto = findViewById(R.id.seekbar_rotate);
        this.btnColorPicker = findViewById(R.id.btn_picker_color_photo);
    }

    ModalBottomSheetDialog modalDialog;

    @SuppressLint("ResourceType")
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddText_toolbar:
                modalDialog = new ModalBottomSheetDialog.Builder()
                        .setHeader("Select Option") // optional
//                        .setHeaderLayout(R.layout.bottom_sheet_fragment_header) // optional (TextView must have id 'header' in layout)
                        .add(R.menu.additem_menu) // can be used more then once
                        .setItemLayout(R.layout.item_bottomadditem) // optional (TextView with id 'title' or ImageView with id 'icon' must be defined in layout)
                        .setColumns(1) // optional (default is 1)
                        .setRoundedModal(false) // optional (default is false)
                        .build();

                modalDialog.show(getSupportFragmentManager(), "selctaddoption");

                return;
            case R.id.btnBack:
                discardDialog = new DiscardDialog(this);
                Objects.requireNonNull(discardDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(0));
                try {
                    discardDialog.show();
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            case R.id.btnBackTextTools:
                if (this.countMain == 0) {
                    ViewAnimation.animationView(this.rlMainTool);
                    this.rlTextTool.setVisibility(View.GONE);
                    this.rlPhotoTools.setVisibility(View.GONE);
                    Objects.requireNonNull(this.tablayoutImageTools.getTabAt(1)).select();
                    this.countText = 0;
                    this.countMain++;
                    this.countOverplay = 0;
                    this.countPhoto = 0;

                }
                return;
            case R.id.btn_camera:
                dispatchTakePictureIntent();
                return;
            case R.id.btn_galery:
                AdmobAds.OnAdsCloseListener onAdsCloseListener = EditPhotoActivity.this::pickFromGalery;
                if (!FacebookAds.showFullAds(onAdsCloseListener) && !AdmobAds.showFullAds(onAdsCloseListener)) {
                    onAdsCloseListener.onAdsClose();
                }
                return;
            case R.id.btnFinish:
                sendImageToShare();
                return;
            case R.id.btnRedo:
                this.mPhotoEditor.redo();
                return;
            case R.id.btnUndo:
                this.mPhotoEditor.undo();
                return;
            case R.id.imgPhotoEditor:
                if (this.countMain == 0) {
                    this.mPhotoEditor.clearHelperBox();
                    ViewAnimation.animationView(this.rlMainTool);
                    this.rlTextTool.setVisibility(View.GONE);
                    this.rlPhotoTools.setVisibility(View.GONE);
                    if (this.currentTabTool == 0) {
                        this.currentTabTool = 1;
                    }
                    Objects.requireNonNull(this.tablayoutImageTools.getTabAt(this.currentTabTool)).select();
                    this.countText = 0;
                    this.countMain++;
                    this.countPhoto = 0;
                    this.countOverplay = 0;
                }
                return;
            default:
        }
    }

    private void sendImageToShare() {
        final File outputMediaFile = Utils.getOutputMediaFile();
        try {
            if (outputMediaFile != null) {
                Boolean b = outputMediaFile.createNewFile();
                Log.d("b", b + "");
            }
            SaveSettings build = new SaveSettings.Builder().setClearViewsEnabled(false).setTransparencyEnabled(true).build();
            if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0 && outputMediaFile != null) {

                this.mPhotoEditor.saveAsFile(outputMediaFile.getAbsolutePath(), build, new PhotoEditor.OnSaveListener() {
                    public void onFailure(@NonNull Exception exc) {
                    }

                    public void onSuccess(@NonNull String str) {
                        Uri fromFile = Uri.fromFile(new File(str));
                        Intent intent = new Intent(EditPhotoActivity.this, ShareActivity.class);
                        intent.setData(fromFile);
                        MediaScannerConnection.scanFile(EditPhotoActivity.this, new String[]{outputMediaFile.toString()}, null, (str1, uri) -> Log.i("ExternalStorage", "Scanned" + str1 + ":"));

                        EditPhotoActivity.this.startActivity(intent);
                        if (!FacebookAds.showFullAds(null)) {
                            AdmobAds.showFullAds(null);
                        }
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void shareApp() {
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("text/plain");
            intent.putExtra("android.intent.extra.SUBJECT", "My application name");
            intent.putExtra("android.intent.extra.TEXT", "\nLet me recommend you this application\n\n" + "https://play.google.com/store/apps/details?id=" + getPackageName());
            startActivity(Intent.createChooser(intent, "Choose one"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pickFromGalery() {
        Dexter.withContext(this).withPermissions("android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE").withListener(new MultiplePermissionsListener() {
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                if (multiplePermissionsReport.areAllPermissionsGranted()) {
                    Intent addCategory = new Intent("android.intent.action.GET_CONTENT").setType("image/*").addCategory("android.intent.category.OPENABLE");
                    addCategory.putExtra("android.intent.extra.MIME_TYPES", new String[]{"image/jpeg", "image/png"});
                    EditPhotoActivity.this.startActivityForResult(Intent.createChooser(addCategory, "Select Picture"), EditPhotoActivity.this.requestMode);
                }
                if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied()) {
                    SettingDialog.showSettingDialog(EditPhotoActivity.this);
                }
            }

            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).withErrorListener(dexterError -> Toast.makeText(EditPhotoActivity.this.getApplicationContext(), "Error occurred! ", Toast.LENGTH_SHORT).show()).onSameThread().check();
    }

    private void dispatchTakePictureIntent() {
        Dexter.withContext(this).withPermissions("android.permission.CAMERA", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE").withListener(new MultiplePermissionsListener() {
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                if (multiplePermissionsReport.areAllPermissionsGranted()) {
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    if (intent.resolveActivity(EditPhotoActivity.this.getPackageManager()) != null) {
                        File file = null;
                        try {
                            file = EditPhotoActivity.this.createImageFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (!(file == null || EditPhotoActivity.this.mCurrentPhotoPath == null)) {
                            intent.putExtra("output", FileProvider.getUriForFile(EditPhotoActivity.this, "com.app2z.textonphoto.fileprovider", file));
                            EditPhotoActivity.this.startActivityForResult(intent, TAKE_PICTURE);
                        }
                    }
                }
                if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied()) {
                    SettingDialog.showSettingDialog(EditPhotoActivity.this);
                }
            }

            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).withErrorListener(dexterError -> Toast.makeText(EditPhotoActivity.this.getApplicationContext(), "Error occurred! ", Toast.LENGTH_SHORT).show()).onSameThread().check();
    }

    public File createImageFile() throws IOException {
        @SuppressLint("SimpleDateFormat") String format = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File createTempFile = File.createTempFile("JPEG_" + format + "_", ".jpg", getExternalFilesDir(Environment.DIRECTORY_PICTURES));
        this.mCurrentPhotoPath = createTempFile.getAbsolutePath();
        return createTempFile;
    }

    @Override
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        String sb = "onActivityResult " +
                i2 +
                " " +
                i +
                " " +
                (intent != null);
        Log.d("XXXXXX", sb);
        if (i2 == -1) {
            if (i == this.requestMode) {
                Uri uri = null;
                if (intent != null) {
                    uri = intent.getData();
                }
                if (uri != null) {
                    startCrop(uri);
                } else {
                    Toast.makeText(this, "Cannot retrieve selected image", Toast.LENGTH_SHORT).show();
                }
            } else if (i == TAKE_PICTURE) {
                Uri fromFile = Uri.fromFile(new File(this.mCurrentPhotoPath));
                if (fromFile != null) {
                    startCrop(fromFile);
                } else {
                    Toast.makeText(this, "Cannot capture picture", Toast.LENGTH_SHORT).show();
                }
            } else if (i == 69) {
                if (intent != null) {
                    handleCropResult(intent);
                } else {
                    return;
                }
            }
        }
        if (i2 == 96 && intent != null) {
            handleCropError(intent);
        }
    }

    private void handleCropError(Intent intent) {
        Throwable error = UCrop.getError(intent);
        if (error != null) {
            Log.e("HANDLECROPERROR", "handleCropError: ", error);
            Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(this, "Unexpected error", Toast.LENGTH_SHORT).show();
    }

    private void handleCropResult(Intent intent) {
        Uri output = UCrop.getOutput(intent);
        if (output != null) {
            Intent intent2 = new Intent(this, EditPhotoActivity.class);
            intent2.setData(output);
            startActivity(intent2);
            return;
        }
        Toast.makeText(this, "Cannot retrieve cropped image", Toast.LENGTH_SHORT).show();
    }

    private void startCrop(Uri uri) {
        String sampleCropImage = "SampleCropImage";
        UCrop of = UCrop.of(uri, Uri.fromFile(new File(getCacheDir(), sampleCropImage)));
        of.useSourceImageAspectRatio();
        of.useSourceImageAspectRatio();
        UCrop.Options options = new UCrop.Options();
        options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
        options.setFreeStyleCropEnabled(true);
        of.withOptions(options);
        of.start(this);
    }


    public void onEditTextChangeListener(View view, String str, int i) {
        showTextEditorFragment(str);
    }

    private void showTextEditorFragment(String str) {
        TextEditorDialog textEditorDialog = new TextEditorDialog(this, str);
        textEditorDialog.setOnDismissListener(dialogInterface -> {
            View currentFocus = EditPhotoActivity.this.getCurrentFocus();
            if (currentFocus != null) {
                ((InputMethodManager) EditPhotoActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
            }
            if (EditPhotoActivity.this.rlMainTool != null && EditPhotoActivity.this.rlMainTool.getVisibility() == View.VISIBLE) {
                Objects.requireNonNull(EditPhotoActivity.this.tablayoutImageTools.getTabAt(1)).select();
            }
        });
        textEditorDialog.setTextListener(this);
        textEditorDialog.show();
    }

    public void onAdded(StrokeTextView strokeTextView, RoundFrameLayout roundFrameLayout) {
        this.textViewMain = strokeTextView;
        this.border = roundFrameLayout;
    }

    public void onClickGetEditTextChangeListener(StrokeTextView
                                                         strokeTextView, RoundFrameLayout roundFrameLayout) {
        this.textViewMain = strokeTextView;
        this.border = roundFrameLayout;
        if (this.countText == 0) {
            ViewAnimation.animationView(this.rlTextTool);
            this.rlMainTool.setVisibility(View.GONE);
            this.rlPhotoTools.setVisibility(View.GONE);
            this.countMain = 0;
            this.countText++;
            this.countPhoto = 0;
            this.countOverplay = 0;
        }
    }

    public void onClickGetImageViewListener(ImageView imageView, View view) {
        this.imageViewMain = imageView;
        this.viewMain = view;
        if (this.countPhoto == 0) {
            ViewAnimation.animationView(this.rlPhotoTools);
            this.rlMainTool.setVisibility(View.GONE);
            this.rlTextTool.setVisibility(View.GONE);
            this.rlColorPhoto.setVisibility(View.GONE);
            this.countPhoto++;
            this.countMain = 0;
            this.countText = 0;
            this.countOverplay = 0;
        }
    }

    public void onClickGetGraphicViewListener(ImageView imageView, View view, View view2) {
        this.imageViewMain = imageView;
        this.viewMain = view;
        if (this.countOverplay == 0) {
            ViewAnimation.animationView(this.rlPhotoTools);
            this.rlMainTool.setVisibility(View.GONE);
            this.rlTextTool.setVisibility(View.GONE);
            this.rlColorPhoto.setVisibility(View.VISIBLE);
            this.countPhoto = 0;
            this.countOverplay++;
            this.countMain = 0;
            this.countText = 0;
        }
    }

    public void onClickGetBitmaoOverlay(Bitmap bitmap) {
        this.resourceGraphic = bitmap;
    }

    public void onAddViewListener(ViewType viewType, int i) {
        this.numberAddedView = i;
    }

    public void onRemoveViewListener(int i) {
        this.numberAddedView = i;
    }

    public void onRemoveViewListener(ViewType viewType, int i) {
        ViewAnimation.animationView(this.rlMainTool);
        this.rlColorPhoto.setVisibility(View.GONE);
        this.rlTextTool.setVisibility(View.GONE);
        this.rlPhotoTools.setVisibility(View.GONE);
        if (this.currentTabTool == 0) {
            this.currentTabTool = 1;
        }
        Objects.requireNonNull(this.tablayoutImageTools.getTabAt(this.currentTabTool)).select();
        this.countText = 0;
        this.countMain++;
        this.countOverplay = 0;
        this.countPhoto = 0;
    }

    @Override
    public void onBackPressed() {
        if ((getSupportFragmentManager().findFragmentByTag("QUOTES")) != null) {
            getSupportFragmentManager().popBackStack("EDIT", 0);
        } else if ((getSupportFragmentManager().findFragmentByTag("EDIT")) != null) {
            this.fragmentManager.popBackStack(null, 1);
        } else {
            DiscardDialog dialog = new DiscardDialog(this);
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(0));
            dialog.show();
        }
    }

    @SuppressLint("WrongConstant")
    public void onTextAlign(int i) {
        switch (i) {
            case 1:
                this.textViewMain.setGravity(3);
                return;
            case 2:
                this.textViewMain.setGravity(17);
                return;
            case 3:
                this.textViewMain.setGravity(5);
                StrokeTextView strokeTextView = this.textViewMain;
                strokeTextView.setTypeface(strokeTextView.getTypeface(), 2);
                return;
            default:
        }
    }

    @SuppressLint("WrongConstant")
    public void onTextStyle(int i) {
        switch (i) {
            case 1:
                StrokeTextView strokeTextView = this.textViewMain;
                strokeTextView.setTypeface(strokeTextView.getTypeface(), 3);
                this.styleText = i;
                return;
            case 2:
                StrokeTextView strokeTextView2 = this.textViewMain;
                strokeTextView2.setTypeface(strokeTextView2.getTypeface(), 1);
                this.styleText = i;
                return;
            case 3:
                StrokeTextView strokeTextView3 = this.textViewMain;
                strokeTextView3.setTypeface(strokeTextView3.getTypeface(), 2);
                this.styleText = i;
                return;
            case 4:
                StrokeTextView strokeTextView4 = this.textViewMain;
                strokeTextView4.setTypeface(Typeface.create(strokeTextView4.getTypeface(), 0));
                this.styleText = i;
                return;
            case 5:
                this.textViewMain.setAllCaps(true);
                return;
            case 6:
                this.textViewMain.setAllCaps(false);
                return;
            default:
        }
    }

    public void onTextSize(int i) {
        this.textViewMain.setTextSize((float) i);
        Log.d("TEXTTTTT", "onTextSize " + i);
    }

    public void onTextPadding(int i) {
        this.border.setPadding(i, i, i, i);
    }

    @SuppressLint("WrongConstant")
    public void onFontSelected(String str) {
        AssetManager assets = getAssets();
        Typeface typeface = Typeface.createFromAsset(assets, "font/" + str);
        switch (this.styleText) {
            case 1:
                this.textViewMain.setTypeface(typeface, 3);
                return;
            case 2:
                this.textViewMain.setTypeface(typeface, 1);
                return;
            case 3:
                this.textViewMain.setTypeface(typeface, 2);
                return;
            case 4:
            default:
                this.textViewMain.setTypeface(typeface, 0);
        }
    }

    public void onHightLightColorSelected(int i) {
        this.colorBackground = i;
        String format = String.format("%06X", this.colorBackground & 16777215);
        RoundViewDelegate delegate = this.border.getDelegate();
        delegate.setBackgroundColor(Color.parseColor("#" + this.opticalBackground + format));
    }

    public void onHightLightColorOpacityChangeListerner(String str) {
        String format = String.format("%06X", this.colorBackground & 16777215);
        this.opticalBackground = str;
        RoundViewDelegate delegate = this.border.getDelegate();
        delegate.setBackgroundColor(Color.parseColor("#" + this.opticalBackground + format));
    }

    public void onHighLightRadius(int i) {
        this.border.getDelegate().setCornerRadius(i);
    }

    public void onColorSelected(int i) {
        this.colorTextShadow = i;
        this.textViewMain.getPaint().setShader(null);
        this.textViewMain.setTextColor(i);
        StrokeTextView strokeTextView = this.textViewMain;
        strokeTextView.setTextColor(strokeTextView.getTextColors().withAlpha(this.opticalText));
    }

    public void onColorOpacityChangeListerner(int i) {
        this.opticalText = i;
        StrokeTextView strokeTextView = this.textViewMain;
        strokeTextView.setTextColor(strokeTextView.getTextColors().withAlpha(i));
    }

    public void onLineHeight(int i) {
        setLineGeight(this.textViewMain, i);
    }

    private void setLineGeight(TextView textView, int i) {
        textView.setLineSpacing((float) (dpToPixel((float) i) - textView.getPaint().getFontMetricsInt(null)), 1.0f);
    }

    public int dpToPixel(float f) {
        return (int) (f * (((float) getResources().getDisplayMetrics().densityDpi) / 160.0f));
    }

    @RequiresApi(api = 21)
    @SuppressLint({"NewApi"})
    public void onSpacingLetter(float f) {
        this.textViewMain.setLetterSpacing(f);
    }

    public void onStrokeColorSelected(int i) {
        this.textViewMain.setStrokeColor(i);
    }

    public void onStrokeWidthChangeListener(int i) {

        this.textViewMain.setStrokeWidth(i);
    }

    public void onDyShadowChangeListener(int i) {
        this.rY = i;
        this.textViewMain.setStrokeWidth(0);
        float f = (float) i;
        this.textViewMain.setShadowLayer((float) this.rRadius, f, f, this.colorTextShadow);
    }

    public void onRadiusChangeListener(int i) {
        this.rRadius = i;
        this.textViewMain.setStrokeWidth(0);
        int i2 = this.rY;
        this.textViewMain.setShadowLayer((float) this.rRadius, (float) i2, (float) i2, this.colorTextShadow);
    }

    public void onShadowColorSelected(int i) {
        this.colorTextShadow = i;
        this.textViewMain.setStrokeWidth(0);
        int i2 = this.rY;
        this.textViewMain.setShadowLayer((float) this.rRadius, (float) i2, (float) i2, this.colorTextShadow);
    }

    public void onEmojiClick(String str) {
        if (this.numberAddedView < 6) {
            this.mPhotoEditor.addEmoji(str);
        } else {
            Toast.makeText(this, R.string.max_item, Toast.LENGTH_SHORT).show();
        }
    }

    public void onPhotoClick(String str) {
        if (this.numberAddedView < 6) {
            this.mPhotoEditor.addImage(BitmapProcess.getBitmapFromLocalPath(str, 4));
            return;
        }
        Toast.makeText(this, R.string.max_item, Toast.LENGTH_SHORT).show();
    }

    public void onPhotoBackClick(String str) {
        Bitmap bitmap = BitmapProcess.getBitmapFromLocalPath(str, 4);
        Glide.with(this).load(bitmap).into(this.imgPhotoEditor.getSource());
    }

    public void onStickerFragmentClick(Bitmap bitmap) {
        if (this.numberAddedView < 6) {
            this.mPhotoEditor.addImage(bitmap);
        } else {
            Toast.makeText(this, R.string.max_item, Toast.LENGTH_SHORT).show();
        }
    }

    public void onOverplayClick(Bitmap bitmap) {
        int i;
        int i2;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width > height) {
            i2 = (int) ((300.0f / ((float) width)) * ((float) height));
            i = (int) 300.0f;
        } else {
            i = (int) ((300.0f / ((float) height)) * ((float) width));
            i2 = (int) 300.0f;
        }
        if (this.numberAddedView < 6) {
            this.mPhotoEditor.addImage(Bitmap.createScaledBitmap(bitmap, i, i2, false));
        } else {
            Toast.makeText(this, R.string.max_item, Toast.LENGTH_SHORT).show();
        }
    }

    public void onPhrases() {
        if (!this.mPhrasesFragment.isAdded()) {
            this.mPhrasesFragment.show(getSupportFragmentManager(), this.mPhrasesFragment.getTag());
        }
    }

    public void onFood() {
        if (!this.mFoodFragment.isAdded()) {
            this.mFoodFragment.show(getSupportFragmentManager(), this.mFoodFragment.getTag());
        }
    }

    public void onLove() {
        if (!this.mLoveFragment.isAdded()) {
            this.mLoveFragment.show(getSupportFragmentManager(), this.mLoveFragment.getTag());
        }
    }

    public void onChristmas() {
        if (!this.mChristmasFragment.isAdded()) {
            this.mChristmasFragment.show(getSupportFragmentManager(), this.mChristmasFragment.getTag());
        }
    }

    public void onSayings() {
        if (!this.mSayingFragment.isAdded()) {
            this.mSayingFragment.show(getSupportFragmentManager(), this.mSayingFragment.getTag());
        }
    }

    public void onNative() {
        if (!this.mNativeFragment.isAdded()) {
            this.mNativeFragment.show(getSupportFragmentManager(), this.mNativeFragment.getTag());
        }
    }

    public void onSummer() {
        if (!this.mSummerFragment.isAdded()) {
            this.mSummerFragment.show(getSupportFragmentManager(), this.mSummerFragment.getTag());
        }
    }

    public void onWinter() {
        if (!this.mWinterFragment.isAdded()) {
            this.mWinterFragment.show(getSupportFragmentManager(), this.mWinterFragment.getTag());
        }
    }

    public void onTravel() {
        if (!this.mTravelFragment.isAdded()) {
            this.mTravelFragment.show(getSupportFragmentManager(), this.mTravelFragment.getTag());
        }
    }

    public void onEmoticons() {
        if (!this.mEmoticonsFragment.isAdded()) {
            this.mEmoticonsFragment.show(getSupportFragmentManager(), this.mEmoticonsFragment.getTag());
        }
    }

    public void onMotivation() {
        if (!this.mMotivationFragment.isAdded()) {
            this.mMotivationFragment.show(getSupportFragmentManager(), this.mMotivationFragment.getTag());
        }
    }

    public void onFitness() {
        if (!this.mFitnessFragment.isAdded()) {
            this.mFitnessFragment.show(getSupportFragmentManager(), this.mFitnessFragment.getTag());
        }
    }

    public void onGeometry() {
        if (!this.mGeometryFragment.isAdded()) {
            this.mGeometryFragment.show(getSupportFragmentManager(), this.mGeometryFragment.getTag());
        }
    }

    public void onHalloween() {
        if (!this.mHalloweenFragment.isAdded()) {
            this.mHalloweenFragment.show(getSupportFragmentManager(), this.mHalloweenFragment.getTag());
        }
    }

    public void onBrightnessChosse(int i) {
        this.brightnessFinal = i;
        this.imgPhotoEditor.getSource().setColorFilter(ColorFilterGenerator.adjustColor(this.brightnessFinal, this.saturationFinal, this.constrantFinal, this.hueFinal));
    }

    public void onConstrastChosse(int i) {
        this.constrantFinal = i;
        this.imgPhotoEditor.getSource().setColorFilter(ColorFilterGenerator.adjustColor(this.brightnessFinal, this.saturationFinal, this.constrantFinal, this.hueFinal));
    }

    public void onHueChosee(int i) {
        this.hueFinal = i;
        this.imgPhotoEditor.getSource().setColorFilter(ColorFilterGenerator.adjustColor(this.brightnessFinal, this.saturationFinal, this.constrantFinal, this.hueFinal));
    }

    public void onSaturationChosse(int i) {
        this.saturationFinal = i;
        this.imgPhotoEditor.getSource().setColorFilter(ColorFilterGenerator.adjustColor(this.brightnessFinal, this.saturationFinal, this.constrantFinal, this.hueFinal));
    }

    public void onText(String str) {
        this.textViewMain.setText(str);
        if (this.countText == 0) {
            ViewAnimation.animationView(this.rlTextTool);
            this.rlMainTool.setVisibility(View.GONE);
            this.rlPhotoTools.setVisibility(View.GONE);
            this.countMain = 0;
            this.countText++;
            this.countPhoto = 0;
            this.countOverplay = 0;
        }
    }

    @Override
    protected void onDestroy() {
        if (discardDialog != null) {
            discardDialog.dismiss();
        }
        super.onDestroy();
    }

    @Override
    public void onItemSelected(String tag, Item item) {
        modalDialog.dismiss();

        if (item.getTitle().equals("Text")) {
            if (this.numberAddedView < 6) {
                this.mPhotoEditor.addText(getString(R.string.double_tap), ContextCompat.getColor(EditPhotoActivity.this, R.color.white));
            } else {
                Toast.makeText(this, R.string.max_item, Toast.LENGTH_SHORT).show();
            }
        } else if (item.getTitle().equals("Date")) {
            if (this.numberAddedView < 6) {
                this.mPhotoEditor.addText(getFormatedcurrentDate(), ContextCompat.getColor(EditPhotoActivity.this, R.color.white));
            } else {
                Toast.makeText(this, R.string.max_item, Toast.LENGTH_SHORT).show();
            }
        } else if (item.getTitle().equals("Quote")) {
//            if (this.numberAddedView < 6) {
//                this.mPhotoEditor.addText(getString(R.string.double_tap), ContextCompat.getColor(EditPhotoActivity.this, R.color.white));
//            } else {
//                Toast.makeText(this, R.string.max_item, Toast.LENGTH_SHORT).show();
//            }
            QuotesFragment quotesFragment = new QuotesFragment();
            quotesFragment.setQuotesListener(this);
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right).add(R.id.frameLayoutEditMai, quotesFragment, "QUOTES").addToBackStack("QUOTES").commit();
            View currentFocus = getCurrentFocus();
            if (currentFocus != null) {
                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
            }
        } else if (item.getTitle().equals("Image From Gallery")) {
//            AdmobAds.OnAdsCloseListener onAdsCloseListener = EditPhotoActivity.this::pickFromGalery;
//            if (!FacebookAds.showFullAds(onAdsCloseListener) && !AdmobAds.showFullAds(onAdsCloseListener)) {
//                onAdsCloseListener.onAdsClose();
//            }
            Intent svIntent = new Intent(EditPhotoActivity.this, ActivityBrowseImage.class);
            startActivity(svIntent);
            finish();
        } else if (item.getTitle().equals("Image From Camera")) {
//            dispatchTakePictureIntent();
            Intent svIntent = new Intent(EditPhotoActivity.this, ActivityBrowseImage.class);
            startActivity(svIntent);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public static String getFormatedcurrentDate() {
        Calendar today = Calendar.getInstance();
        int date = today.get(Calendar.DATE);
        int month = today.get(Calendar.MONTH);
        int year = today.get(Calendar.YEAR);

        String mon = "";
        String[] monthdayArray = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        String[] monthArray = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug ", "Sep", "Oct", "Nov", "Dec"};
        for (int i = 0; i < monthdayArray.length; i++) {
            if (("" + (month + 1)).equals(monthdayArray[i])) {
                mon = monthArray[i];
                break;
            }
        }
        return (mon) + " " + date + ", " + year + "";
    }

    public void onQuotes(int i, String strValue) {
        if (this.numberAddedView < 6) {
            this.mPhotoEditor.addText(strValue, ContextCompat.getColor(EditPhotoActivity.this, R.color.white));
        } else {
            Toast.makeText(this, R.string.max_item, Toast.LENGTH_SHORT).show();
        }
    }
}
