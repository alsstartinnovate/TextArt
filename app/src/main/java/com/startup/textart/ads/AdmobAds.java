package com.startup.textart.ads;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.startup.textart.R;

import com.google.android.gms.ads.*;

import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;

public class AdmobAds {

    private static InterstitialAd interstitialAd;

    public static OnAdsCloseListener mOnAdsCloseListener;

    public interface OnAdsCloseListener {
        void onAdsClose();
    }

    public static void initFullAds(final Context context) {
        if (interstitialAd == null) {
            interstitialAd = new InterstitialAd(context);

            interstitialAd.setAdUnitId(context.getString(R.string.admob_inter_id));
            interstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    AdmobAds.loadFullAds();
                    if (AdmobAds.mOnAdsCloseListener != null) {
                        AdmobAds.mOnAdsCloseListener.onAdsClose();
                    }
                }

                @Override
                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                }
            });
        }
        loadFullAds();
    }


    public static void loadFullAds() {
        InterstitialAd interstitialAd2 = interstitialAd;
        if (interstitialAd2 != null) {
            interstitialAd2.loadAd(new AdRequest.Builder().build());
        }
    }

    public static boolean showFullAds(OnAdsCloseListener onAdsCloseListener) {
        mOnAdsCloseListener = onAdsCloseListener;
        InterstitialAd interstitialAd2 = interstitialAd;
        if (interstitialAd2 == null || !interstitialAd2.isLoaded()) {
            return false;
        }
        interstitialAd.show();
        return true;
    }

    public static void loadBanner(Activity activity) {
        AdView adView = new AdView(activity);

        adView.setAdUnitId(activity.getString(R.string.admob_banner_id));
        adView.setAdSize(AdSize.SMART_BANNER);
        ((FrameLayout) activity.findViewById(R.id.admob_banner)).addView(adView);
        AdRequest build = new AdRequest.Builder().build();
        adView.loadAd(build);
    }

    public static void loadNativeAds(Activity activity, final View view) {
        final ViewGroup viewGroup = activity.findViewById(R.id.admob_native_container);
        final UnifiedNativeAdView unifiedNativeAdView = activity.findViewById(R.id.native_ad_view);
        unifiedNativeAdView.setMediaView(unifiedNativeAdView.findViewById(R.id.media_view));
        unifiedNativeAdView.setHeadlineView(unifiedNativeAdView.findViewById(R.id.primary));
        unifiedNativeAdView.setBodyView(unifiedNativeAdView.findViewById(R.id.secondary));
        unifiedNativeAdView.setCallToActionView(unifiedNativeAdView.findViewById(R.id.cta));
        unifiedNativeAdView.setIconView(unifiedNativeAdView.findViewById(R.id.icon));
        unifiedNativeAdView.setAdvertiserView(unifiedNativeAdView.findViewById(R.id.tertiary));
        AdLoader build = new AdLoader.Builder(activity, activity.getString(R.string.admob_native_id)).forUnifiedNativeAd(unifiedNativeAd -> {
            AdmobAds.populateNativeAdView(unifiedNativeAd, unifiedNativeAdView);

            viewGroup.setVisibility(View.VISIBLE);
            ((View) viewGroup.getParent()).setVisibility(View.VISIBLE);

            if (view != null) {
                view.setVisibility(View.GONE);
            }
        }).withAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }
        }).build();
        build.loadAd(new AdRequest.Builder().build());
    }


    public static void populateNativeAdView(UnifiedNativeAd unifiedNativeAd, UnifiedNativeAdView unifiedNativeAdView) {
        ((TextView) unifiedNativeAdView.getHeadlineView()).setText(unifiedNativeAd.getHeadline());
        ((TextView) unifiedNativeAdView.getBodyView()).setText(unifiedNativeAd.getBody());
        ((TextView) unifiedNativeAdView.getCallToActionView()).setText(unifiedNativeAd.getCallToAction());
        NativeAd.Image icon = unifiedNativeAd.getIcon();
        if (icon == null) {
            unifiedNativeAdView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) unifiedNativeAdView.getIconView()).setImageDrawable(icon.getDrawable());
            unifiedNativeAdView.getIconView().setVisibility(View.VISIBLE);
        }
        if (unifiedNativeAd.getAdvertiser() == null) {
            unifiedNativeAdView.getAdvertiserView().setVisibility(View.GONE);
        } else {
            ((TextView) unifiedNativeAdView.getAdvertiserView()).setText(unifiedNativeAd.getAdvertiser());
            unifiedNativeAdView.getAdvertiserView().setVisibility(View.VISIBLE);
        }
        unifiedNativeAdView.setNativeAd(unifiedNativeAd);
    }
}