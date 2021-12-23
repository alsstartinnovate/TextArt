package com.anilax.customdesign.adapter.sample;

import com.anilax.textart.R;
import com.anilax.customdesign.model.Sample;
import java.util.ArrayList;
import java.util.List;

public class GenDataBackGround {
    public static List<Sample> colorList() {
        List<Sample> arrayList = new ArrayList<>();
        arrayList.add(new Sample(R.drawable.color02));
        arrayList.add(new Sample(R.drawable.color04));
        arrayList.add(new Sample(R.drawable.color05));
        arrayList.add(new Sample(R.drawable.color09));
        arrayList.add(new Sample(R.drawable.color10));
        arrayList.add(new Sample(R.drawable.color11));
        arrayList.add(new Sample(R.drawable.color12));
        arrayList.add(new Sample(R.drawable.color13));
        arrayList.add(new Sample(R.drawable.color16));
        arrayList.add(new Sample(R.drawable.color18));
        arrayList.add(new Sample(R.drawable.color20));
        arrayList.add(new Sample(R.drawable.color21));
        arrayList.add(new Sample(R.drawable.color22));
        arrayList.add(new Sample(R.drawable.color25));
        arrayList.add(new Sample(R.drawable.color26));
        arrayList.add(new Sample(R.drawable.color27));
        arrayList.add(new Sample(R.drawable.color29));
        arrayList.add(new Sample(R.drawable.color30));
        arrayList.add(new Sample(R.drawable.color31));
        arrayList.add(new Sample(R.drawable.color32));
        arrayList.add(new Sample(R.drawable.color33));
        arrayList.add(new Sample(R.drawable.color34));
        arrayList.add(new Sample(R.drawable.color35));
        return arrayList;
    }

    public static List<Sample> nativeList() {
        List<Sample> arrayList = new ArrayList<>();
        arrayList.add(new Sample(R.drawable.nature1));
        arrayList.add(new Sample(R.drawable.nature2));
        arrayList.add(new Sample(R.drawable.nature3));
        arrayList.add(new Sample(R.drawable.nature4));
        arrayList.add(new Sample(R.drawable.nature5));
        arrayList.add(new Sample(R.drawable.nature6));
        arrayList.add(new Sample(R.drawable.nature7));
        arrayList.add(new Sample(R.drawable.nature8));
        arrayList.add(new Sample(R.drawable.nature9));
        arrayList.add(new Sample(R.drawable.nature10));
        arrayList.add(new Sample(R.drawable.nature11));
        return arrayList;
    }

    public static List<Sample> flowersList() {
        List<Sample> arrayList = new ArrayList<>();
        arrayList.add(new Sample(R.drawable.flower1));
        arrayList.add(new Sample(R.drawable.flower2));
        arrayList.add(new Sample(R.drawable.flower3));
        arrayList.add(new Sample(R.drawable.flower4));
        arrayList.add(new Sample(R.drawable.flower5));
        arrayList.add(new Sample(R.drawable.flower6));
        arrayList.add(new Sample(R.drawable.flower7));
        arrayList.add(new Sample(R.drawable.flower8));
        arrayList.add(new Sample(R.drawable.flower9));
        arrayList.add(new Sample(R.drawable.flower10));
        arrayList.add(new Sample(R.drawable.flower11));
        arrayList.add(new Sample(R.drawable.flower12));
        return arrayList;
    }

    public static List<Sample> nightList() {
        ArrayList<Sample> arrayList = new ArrayList<>();
        arrayList.add(new Sample(R.drawable.night1));
        arrayList.add(new Sample(R.drawable.night3));
        arrayList.add(new Sample(R.drawable.night5));
        arrayList.add(new Sample(R.drawable.night7));
        arrayList.add(new Sample(R.drawable.night8));
        arrayList.add(new Sample(R.drawable.night9));
        arrayList.add(new Sample(R.drawable.main));
        return arrayList;
    }

    public static List<String> suggest() {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("Love");
        arrayList.add("Cool");
        arrayList.add("Nature");
        arrayList.add("Birthday");
        arrayList.add("Dog");
        arrayList.add("Cat");
        arrayList.add("Rose");
        arrayList.add("Night Sky");
        arrayList.add("Ocean");
        return arrayList;
    }
}
