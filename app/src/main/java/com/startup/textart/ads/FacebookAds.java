package com.startup.textart.ads;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


import com.startup.textart.R;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;

import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeAdView;
import com.facebook.ads.NativeBannerAd;
import com.facebook.ads.NativeBannerAdView;

public class FacebookAds {

    private static InterstitialAd interstitialAd;

    public static AdmobAds.OnAdsCloseListener mOnAdsCloseListener;

    public static void initFullAds(final Context context) {
        if (interstitialAd == null) {
            interstitialAd = new InterstitialAd(context, context.getString(R.string.facebook_inter_id));
            interstitialAd.setAdListener(new InterstitialAdListener() {
                public void onAdClicked(Ad ad) {
                }

                public void onInterstitialDisplayed(Ad ad) {
                }

                public void onLoggingImpression(Ad ad) {
                }

                public void onAdLoaded(Ad ad) {
                    Log.d("ADSSSSS", "onAdLoaded");
                }

                public void onInterstitialDismissed(Ad ad) {
                    FacebookAds.loadFullAds();
                    if (FacebookAds.mOnAdsCloseListener != null) {
                        FacebookAds.mOnAdsCloseListener.onAdsClose();
                    }
                }

                public void onError(Ad ad, AdError adError) {
                    Log.d("ADSSSSS", "Error " + adError.getErrorMessage());
                }
            });
        }
        loadFullAds();
    }


    public static void loadFullAds() {
        if (interstitialAd != null) {
            Log.d("ADSSSSS", "loadFullAds");
            interstitialAd.loadAd();
        }
    }

    public static boolean showFullAds(AdmobAds.OnAdsCloseListener onAdsCloseListener) {
        mOnAdsCloseListener = onAdsCloseListener;
        InterstitialAd interstitialAd2 = interstitialAd;
        if (interstitialAd2 == null || !interstitialAd2.isAdLoaded()) {
            return false;
        }
        interstitialAd.show();
        return true;
    }

    public static void destroyAd() {
        InterstitialAd interstitialAd2 = interstitialAd;
        if (interstitialAd2 != null) {
            interstitialAd2.destroy();
        }
    }

    public static void loadBanner(final Activity activity) {
        AdView adView = new AdView((Context) activity, activity.getString(R.string.facebook_banner_id), AdSize.BANNER_HEIGHT_50);
        ((FrameLayout) activity.findViewById(R.id.fb_banner)).addView(adView);
        adView.setAdListener(new AdListener() {
            public void onAdClicked(Ad ad) {
            }

            public void onError(Ad ad, AdError adError) {
            }

            public void onLoggingImpression(Ad ad) {
            }

            public void onAdLoaded(Ad ad) {
                ((View) activity.findViewById(R.id.admob_banner).getParent()).setVisibility(View.GONE);
            }
        });
        adView.loadAd();
    }

    public static void loadNativeAds(final Activity activity) {
        final NativeAd nativeAd = new NativeAd((Context) activity, activity.getString(R.string.facebook_native_id));
        nativeAd.setAdListener(new NativeAdListener() {
            public void onAdClicked(Ad ad) {
            }

            public void onLoggingImpression(Ad ad) {
            }

            public void onMediaDownloaded(Ad ad) {
            }

            public void onError(Ad ad, AdError adError) {
                Log.d("ADSSSSS", "Native Error " + adError.getErrorMessage());
            }

            public void onAdLoaded(Ad ad) {
                ((LinearLayout) activity.findViewById(R.id.fb_native_container)).addView(NativeAdView.render(activity, nativeAd));
            }
        });
        nativeAd.loadAd();
    }

    public static void loadNativeAdsBanner(final Activity activity) {
        final NativeBannerAd nativeBannerAd = new NativeBannerAd((Context) activity, activity.getString(R.string.facebook_banner_native));
        nativeBannerAd.setAdListener(new NativeAdListener() {
            public void onAdClicked(Ad ad) {
            }

            public void onLoggingImpression(Ad ad) {
            }

            public void onMediaDownloaded(Ad ad) {
            }

            public void onError(Ad ad, AdError adError) {
                Log.d("ADSSSSS", "Native Error " + adError.getErrorMessage());
            }

            public void onAdLoaded(Ad ad) {
                ((LinearLayout) activity.findViewById(R.id.fb_native_container)).addView(NativeBannerAdView.render(activity, nativeBannerAd, NativeBannerAdView.Type.HEIGHT_120));
            }
        });
        nativeBannerAd.loadAd();
    }
}
