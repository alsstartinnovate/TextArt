package com.startup.textart.activities;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.startup.textart.R;
import com.startup.textart.ads.AdmobAds;
import com.startup.textart.ads.FacebookAds;
import com.startup.textart.dialog.SettingDialog;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainAcitivty";
    private static final int TAKE_PICTURE = 111;


    public String mCurrentPhotoPath;
    RelativeLayout camera;
    RelativeLayout gallery;
    RelativeLayout sample;
    public int requestMode = 1;
    LinearLayout shareApp;
    ViewGroup viewGroup;
    LinearLayout moreAds;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main2222);
        initViews();
        AdmobAds.loadBanner(this);
        FacebookAds.initFullAds(this);
    }

    private void initViews() {
        this.sample = findViewById(R.id.btn_sample);
        this.camera = findViewById(R.id.btn_camera);
        this.gallery = findViewById(R.id.btn_galery);
        this.shareApp = findViewById(R.id.btnShareApp);
        this.moreAds = findViewById(R.id.btn_more_ads);

        this.camera.setOnClickListener(this);
        this.gallery.setOnClickListener(this);
        this.shareApp.setOnClickListener(this);
        this.moreAds.setOnClickListener(this);
        this.sample.setOnClickListener(this);
        findViewById(R.id.btnRate).setOnClickListener(this);
    }


    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnRate:
                try {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
                } catch (ActivityNotFoundException unused) {
                    Toast.makeText(this, "Couldn't find PlayStore on this device", Toast.LENGTH_SHORT).show();
                }
                return;
            case R.id.btnShareApp:
                shareApp();
                return;

            case R.id.btn_camera:
                dispatchTakePictureIntent();
                return;
            case R.id.btn_galery:
                AdmobAds.OnAdsCloseListener onAdsCloseListener = MainActivity.this::pickFromGalery;
                if (!FacebookAds.showFullAds(onAdsCloseListener) && !AdmobAds.showFullAds(onAdsCloseListener)) {
                    onAdsCloseListener.onAdsClose();
                }
                return;
            case R.id.btn_more_ads:
                AdmobAds.showFullAds(null);
                return;

            case R.id.btn_sample:
                Dexter.withContext(this).withPermissions("android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE").withListener(new MultiplePermissionsListener() {
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        if (multiplePermissionsReport.areAllPermissionsGranted()) {
                            MainActivity.this.startActivity(new Intent(MainActivity.this, SampleActivity.class));
                            if (!AdmobAds.showFullAds(null)) {
                                FacebookAds.showFullAds(null);
                            }
                        }
                        if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied()) {
                            SettingDialog.showSettingDialog(MainActivity.this);
                        }
                    }

                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).withErrorListener(dexterError -> Toast.makeText(MainActivity.this, "Error occurred! ", Toast.LENGTH_SHORT).show()).onSameThread().check();
                return;


            default:
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
                    MainActivity.this.startActivityForResult(Intent.createChooser(addCategory, "Select Picture"), MainActivity.this.requestMode);
                }
                if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied()) {
                    SettingDialog.showSettingDialog(MainActivity.this);
                }
            }

            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).withErrorListener(dexterError -> Toast.makeText(MainActivity.this.getApplicationContext(), "Error occurred! ", Toast.LENGTH_SHORT).show()).onSameThread().check();
    }

    private void dispatchTakePictureIntent() {
        Dexter.withContext(this).withPermissions("android.permission.CAMERA", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE").withListener(new MultiplePermissionsListener() {
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                if (multiplePermissionsReport.areAllPermissionsGranted()) {
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    if (intent.resolveActivity(MainActivity.this.getPackageManager()) != null) {
                        File file = null;
                        try {
                            file = MainActivity.this.createImageFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (!(file == null || MainActivity.this.mCurrentPhotoPath == null)) {
                            intent.putExtra("output", FileProvider.getUriForFile(MainActivity.this, "com.app2z.textonphoto.fileprovider", file));
                            MainActivity.this.startActivityForResult(intent, TAKE_PICTURE);
                        }
                    }
                }
                if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied()) {
                    SettingDialog.showSettingDialog(MainActivity.this);
                }
            }

            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).withErrorListener(dexterError -> Toast.makeText(MainActivity.this.getApplicationContext(), "Error occurred! ", Toast.LENGTH_SHORT).show()).onSameThread().check();
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
            Log.e(TAG, "handleCropError: ", error);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
