package com.anilax.customdesign.interfaces;

import com.anilax.customdesign.model.Sample;
import com.makeramen.roundedimageview.RoundedImageView;

public interface SampleItemClickListener {
    void onSampleItemClick(int i, Sample sample, RoundedImageView roundedImageView);
}
