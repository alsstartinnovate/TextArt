package com.commonutility;


import android.os.Environment;
import android.widget.TextView;

import java.io.File;

public class GlobalVariables {
    public static final String defaultAppPath = Environment.getExternalStorageDirectory().getAbsolutePath() +
            File.separator + "Textart/";

    public static final String CUSTOMFONTNAME = "font/customfont.ttf";

    public static final String CURRENCYSYMBOL = "₹ ";

    public static final boolean ISTESTING = true;
    public static final String TAGPOSTTEXT = ".............tagprint..............";

    public static String SELECTDATE = "";
    public static TextView TextViewDate = null;


}