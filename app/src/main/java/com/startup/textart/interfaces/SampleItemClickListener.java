package com.startup.textart.interfaces;

import com.startup.textart.model.Sample;
import com.makeramen.roundedimageview.RoundedImageView;

public interface SampleItemClickListener {
    void onSampleItemClick(int i, Sample sample, RoundedImageView roundedImageView);
}
