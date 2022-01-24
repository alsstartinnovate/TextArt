package com.startup.textart.views.nativead;

import android.content.Context;
import android.content.res.TypedArray;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;

import com.startup.textart.R;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.makeramen.roundedimageview.RoundedImageView;

public class TemplateView extends FrameLayout {


    private LinearLayout callToActionParentView;
    private TextView callToActionView;
    private RoundedImageView iconView;
    private MediaView mediaView;

    private UnifiedNativeAdView nativeAdView;
    private LinearLayout primaryParentView;
    private TextView primaryView;
    private TextView secondaryView;

    private LinearLayout tertiaryParentView;
    private TextView tertiaryView;

    public TemplateView(Context context) {
        super(context);
    }

    public TemplateView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context, attributeSet);
    }

    public TemplateView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView(context, attributeSet);
    }


    private boolean adHasOnlyStore(UnifiedNativeAd unifiedNativeAd) {
        return !isNullOrEmpty(unifiedNativeAd.getStore()) && isNullOrEmpty(unifiedNativeAd.getAdvertiser());
    }

    private boolean adHasOnlyAdvertiser(UnifiedNativeAd unifiedNativeAd) {
        return !isNullOrEmpty(unifiedNativeAd.getAdvertiser()) && isNullOrEmpty(unifiedNativeAd.getStore());
    }

    private boolean adHasBothStoreAndAdvertiser(UnifiedNativeAd unifiedNativeAd) {
        return !isNullOrEmpty(unifiedNativeAd.getAdvertiser()) && !isNullOrEmpty(unifiedNativeAd.getStore());
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public void setNativeAd(UnifiedNativeAd unifiedNativeAd) {

        String store = unifiedNativeAd.getStore();
        String advertiser = unifiedNativeAd.getAdvertiser();
        String headline = unifiedNativeAd.getHeadline();
        String body = unifiedNativeAd.getBody();
        String callToAction = unifiedNativeAd.getCallToAction();
        unifiedNativeAd.getStarRating();
        NativeAd.Image icon = unifiedNativeAd.getIcon();
        this.nativeAdView.setCallToActionView(this.callToActionParentView);
        this.nativeAdView.setHeadlineView(this.primaryParentView);
        this.nativeAdView.setMediaView(this.mediaView);
        if (adHasOnlyStore(unifiedNativeAd)) {
            this.nativeAdView.setStoreView(this.tertiaryView);
            this.tertiaryParentView.setVisibility(View.VISIBLE);
        } else {
            if (adHasOnlyAdvertiser(unifiedNativeAd)) {
                this.nativeAdView.setAdvertiserView(this.tertiaryView);
                this.tertiaryParentView.setVisibility(View.VISIBLE);
                this.secondaryView.setLines(1);
            } else if (adHasBothStoreAndAdvertiser(unifiedNativeAd)) {
                this.nativeAdView.setAdvertiserView(this.tertiaryView);
                this.tertiaryParentView.setVisibility(View.VISIBLE);
                this.secondaryView.setLines(1);
            } else {

                this.tertiaryParentView.setVisibility(View.VISIBLE);
                this.secondaryView.setLines(3);
            }
            store = advertiser;
        }
        this.primaryView.setText(headline);
        this.tertiaryView.setText(body);
        this.secondaryView.setText(store);
        this.callToActionView.setText(callToAction);
        if (icon != null) {
            this.iconView.setVisibility(View.VISIBLE);
            this.iconView.setImageDrawable(icon.getDrawable());
        } else {
            this.iconView.setVisibility(View.GONE);
        }
        this.nativeAdView.setNativeAd(unifiedNativeAd);
    }


    private void initView(Context context, AttributeSet attributeSet) {
        int[] templateView = {R.attr.gnt_template_type};
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, templateView, 0, 0);
        try {
            int templateType = obtainStyledAttributes.getResourceId(0, R.layout.admob_native_medium);
            obtainStyledAttributes.recycle();
            ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(templateType, this);
        } catch (Exception th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    @Override
    public void onFinishInflate() {
        super.onFinishInflate();
        this.nativeAdView = findViewById(R.id.native_ad_view);
        this.primaryView = findViewById(R.id.primary);
        this.secondaryView = findViewById(R.id.secondary);
        this.tertiaryView = findViewById(R.id.tertiary);
        this.tertiaryParentView = findViewById(R.id.third_line);
        this.callToActionView = findViewById(R.id.cta);
        this.iconView = findViewById(R.id.icon);
        this.mediaView = findViewById(R.id.media_view);
        this.primaryParentView = findViewById(R.id.headline);
        this.callToActionParentView = findViewById(R.id.cta_parent);

    }
}
