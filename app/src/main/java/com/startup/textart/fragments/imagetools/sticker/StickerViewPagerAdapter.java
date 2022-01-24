package com.startup.textart.fragments.imagetools.sticker;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.startup.textart.interfaces.StickerListener;

public class StickerViewPagerAdapter extends FragmentPagerAdapter implements StickerListener {
    StickerViewPagerAdapterListener stickerViewPagerAdapterListener;
    private final Fragment[] chilFragment;


    public interface StickerViewPagerAdapterListener {
        void onSticker(int i);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int i) {
        switch (i) {
            case 0:
                return "Rainbow";
            case 1:
                return "Heart";
            case 2:
                return "Sticker";
            case 3:
                return "Chistmas";
            case 4:
                return "Flowers";
            case 5:
                return "Firework";
            default:
                return "";
        }
    }

    public StickerViewPagerAdapter(FragmentManager fragmentManager, StickerViewPagerAdapterListener stickerViewPagerAdapterListener) {
        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        NewYearFragment newYearFragment = new NewYearFragment();
        newYearFragment.setStickerListener(this);
        StickerChistmasFragment stickerChistmasFragment = new StickerChistmasFragment();
        stickerChistmasFragment.setStickerListener(this);
        LightFragment lightFragment = new LightFragment();
        lightFragment.setStickerListener(this);
        LoveFragment loveFragment = new LoveFragment();
        loveFragment.setStickerListener(this);
        FlowersFragment flowersFragment = new FlowersFragment();
        flowersFragment.setStickerListener(this);
        FireWorkFragment fireWorkFragment = new FireWorkFragment();
        fireWorkFragment.setStickerListener(this);
        this.chilFragment = new Fragment[]{newYearFragment, loveFragment, lightFragment, stickerChistmasFragment, flowersFragment, fireWorkFragment};

        this.stickerViewPagerAdapterListener = stickerViewPagerAdapterListener;
    }


    @NonNull
    public Fragment getItem(int i) {
        return this.chilFragment[i];
    }

    public int getCount() {
        return this.chilFragment.length;
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup viewGroup, int i, @NonNull Object obj) {
        super.setPrimaryItem(viewGroup, i, obj);
    }

    public void onStickerClick(int i) {

        if (stickerViewPagerAdapterListener != null) {
            stickerViewPagerAdapterListener.onSticker(i);
        }
    }
}
