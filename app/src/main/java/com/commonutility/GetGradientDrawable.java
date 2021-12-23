package com.commonutility;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.View;

import com.anilax.textart.R;

public class GetGradientDrawable {
    //            GetGradientDrawable.setGradientDrawable(context, layHeadChange, new float[] {20, 20, 20, 20, 20, 20, 20, 20},
//                    2, R.color.round_corner_radio_head, R.color.white);
    public static void setGradientDrawable(Context context, View view, float[] cornerRadii, int strokeWidth, int strokeColor, int backColor) {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
//        shape.setCornerRadii(new float[] {8, 8, 8, 8, 0, 0, 0, 0});
        shape.setCornerRadii(cornerRadii);

//        int[][] states = new int[][]{
//                new int[]{android.R.attr.state_enabled}, // enabled
//                new int[]{-android.R.attr.state_enabled}, // disabled
//                new int[]{-android.R.attr.state_checked}, // unchecked
//                new int[]{android.R.attr.state_pressed}  // pressed
//        };
//
//        int[] colors = new int[]{
//                Color.BLACK,
//                Color.RED,
//                Color.GREEN,
//                Color.BLUE
//        };
//        ColorStateList myStateList = new ColorStateList(states, colors);
//        shape.setColor(myStateList);

        shape.setColor(context.getResources().getColor(R.color.green));

        shape.setStroke(strokeWidth, strokeColor);
        view.setBackground(shape);
    }
}
