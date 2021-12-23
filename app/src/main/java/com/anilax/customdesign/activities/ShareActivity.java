package com.anilax.customdesign.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.anilax.textart.R;
import com.anilax.customdesign.Config;
import com.anilax.customdesign.ads.AdmobAds;
import com.anilax.customdesign.ads.FacebookAds;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;


public class ShareActivity extends AppCompatActivity implements View.OnClickListener {

    private Boolean firstTime = null;
    ImageView imageView;
    Bitmap bitmap = null;
    RelativeLayout relativeLayout;
    Uri uri;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_share);
        addControls();
        if (isFirstTime()) {
            showDialog();
        }
        this.uri = getIntent().getData();
        if (this.uri != null) {
            Glide.with(this).asBitmap().load(this.uri).into(new CustomTarget<Bitmap>() {
                public void onLoadCleared(@Nullable Drawable drawable) {
                }

                public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                    ShareActivity.this.imageView.setImageBitmap(bitmap);
                    ShareActivity.this.bitmap = bitmap;
                }
            });
            Snackbar.make(this.relativeLayout, "Image saved to gallery!", Snackbar.LENGTH_LONG).setAction("OPEN", view -> {
                ShareActivity shareActivity = ShareActivity.this;
                shareActivity.openImage(new File(Objects.requireNonNull(shareActivity.uri.getPath())));
            }).show();
        }

        findViewById(R.id.img_final_card).setOnClickListener(view -> {
            ShareActivity shareActivity = ShareActivity.this;
            shareActivity.openImage(new File(Objects.requireNonNull(shareActivity.uri.getPath())));
        });
        FacebookAds.loadNativeAds(this);
        AdmobAds.loadNativeAds(this, null);

    }

    private boolean isFirstTime() {
        if (this.firstTime == null) {
            SharedPreferences sharedPreferences = getSharedPreferences("first_time", 0);
            this.firstTime = sharedPreferences.getBoolean("firstTime", true);
            if (Boolean.TRUE.equals(this.firstTime)) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putBoolean("firstTime", false);
                edit.apply();
            }
        }
        return this.firstTime;
    }

    private void addControls() {
        this.imageView = findViewById(R.id.img_final);
        findViewById(R.id.btnWallpaper).setOnClickListener(this);
        this.relativeLayout = findViewById(R.id.relativeShare);
        findViewById(R.id.btnBackShare).setOnClickListener(this);
        findViewById(R.id.btnRate).setOnClickListener(this);
        findViewById(R.id.btn_new).setOnClickListener(this);
        findViewById(R.id.btnShareMore).setOnClickListener(this);
        findViewById(R.id.btnInstagram).setOnClickListener(this);
        findViewById(R.id.btnFacebook).setOnClickListener(this);
        findViewById(R.id.btnMessenger).setOnClickListener(this);
        findViewById(R.id.btnZalo).setOnClickListener(this);
        findViewById(R.id.btnGmail).setOnClickListener(this);
        findViewById(R.id.btnWhatsApp).setOnClickListener(this);
        findViewById(R.id.btnTwitter).setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    public void onClick(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.btnBackShare:
                    super.onBackPressed();
                    return;
                case R.id.btnFacebook:
                    sharePhoto(Config.FACE);
                    return;
                case R.id.btnGmail:
                    sharePhoto(Config.GMAIL);
                    return;
                case R.id.btnInstagram:
                    sharePhoto(Config.INSTA);
                    return;
                case R.id.btnMessenger:
                    sharePhoto(Config.MESSEGER);
                    return;
                case R.id.btnRate:
                    showDialog();
                    return;
                case R.id.btnShareMore:
                    Uri createCachefile = createcachefile();
                    if (createCachefile != null) {
                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.SEND");
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                        intent.setDataAndType(createCachefile, getContentResolver().getType(createCachefile));
                        intent.putExtra("android.intent.extra.STREAM", createCachefile);
                        startActivity(Intent.createChooser(intent, "Choose an app"));
                        return;
                    }
                    Toast.makeText(this, "Fail to sharing", Toast.LENGTH_SHORT).show();
                    return;
                case R.id.btnTwitter:
                    sharePhoto(Config.TWITTER);
                    return;
                case R.id.btnWallpaper:
                    Uri createCachefile2 = createcachefile();
                    if (createCachefile2 != null) {
                        Intent intent2 = new Intent("android.intent.action.ATTACH_DATA");
                        intent2.setDataAndType(createCachefile2, getContentResolver().getType(createCachefile2));
                        intent2.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        startActivity(Intent.createChooser(intent2, "Use as"));
                        return;
                    }
                    Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
                    return;
                case R.id.btnWhatsApp:
                    sharePhoto(Config.WHATSAPP);
                    return;
                case R.id.btnZalo:
                    sharePhoto(Config.ZALO);
                    return;
                case R.id.btn_new:
                    Intent intent3 = new Intent(this, MainActivity.class);
                    intent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent3);
                    return;
                default:
            }
        }
    }

    public void sharePhoto(String str) {
        if (isPackageInstalled(this, str)) {
            Uri createCachefile = createcachefile();
            if (createCachefile != null) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.SEND");
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setDataAndType(createCachefile, getContentResolver().getType(createCachefile));
                intent.putExtra("android.intent.extra.STREAM", createCachefile);
                intent.setPackage(str);
                startActivity(intent);
                return;
            }
            Toast.makeText(this, "Fail to sharing", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "Can't find this App, please download and try it again", Toast.LENGTH_SHORT).show();
        Intent intent2 = new Intent("android.intent.action.VIEW");
        intent2.setData(Uri.parse("market://details?id=" + str));
        startActivity(intent2);
    }

    public static boolean isPackageInstalled(Context context, String str) {
        try {
            context.getPackageManager().getPackageInfo(str, PackageManager.GET_META_DATA);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    private void showDialog() {

    }

    private Uri createcachefile() {
        try {
            File file = new File(getCacheDir(), "images");
            boolean f = file.mkdirs();
            Log.d("f", f + "");
            FileOutputStream fileOutputStream = new FileOutputStream(file + "/image.png");
            this.bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e3) {
            Log.e("NULL", String.valueOf(e3));
        }
        return FileProvider.getUriForFile(this, "com.app2z.textonphoto.fileprovider", new File(new File(getCacheDir(), "images"), "image.png"));
    }


    public void openImage(File file) {
        Uri uriFile;
        Intent intent = new Intent("android.intent.action.VIEW");
        if (Build.VERSION.SDK_INT >= 24) {
            uriFile = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", file);
        } else {
            uriFile = Uri.fromFile(file);
        }
        startActivity(intent.setDataAndType(uriFile, "image/*").addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION));
    }
}
