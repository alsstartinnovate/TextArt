package com.commonutility;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.anilax.textart.R;

import es.dmoral.toasty.Toasty;

public class ShowCustomSnackBar {
    private Context con;

    public static final int ToastyError = 0;
    public static final int ToastySuccess = 1;
    public static final int ToastyInfo = 2;
    public static final int ToastyWarning = 3;
    public static final int ToastyNormal = 4;
    public static final int ToastyNormalWithIcon = 5;

    public ShowCustomSnackBar(Context context) {
        this.con = context;
    }

    public static void showCustomToast(final Context con, final String toastString, final int ToastType) {
        ((Activity) con).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (ToastType) {
                    case ToastyError:
                        Toasty.error(con, toastString, Toast.LENGTH_LONG, true).show();
                        break;
                    case ToastySuccess:
                        Toasty.success(con, toastString, Toast.LENGTH_LONG, true).show();
                        break;
                    case ToastyInfo:
                        Toasty.info(con, toastString, Toast.LENGTH_LONG, true).show();
                        break;
                    case ToastyWarning:
                        Toasty.warning(con, toastString, Toast.LENGTH_LONG, true).show();
                        break;
                    case ToastyNormal:
                        Toasty.normal(con, toastString, Toast.LENGTH_LONG).show();
                        break;
                    case ToastyNormalWithIcon:
                        Drawable icon = con.getResources().getDrawable(R.drawable.ic_launcher_background);
                        Toasty.normal(con, toastString, icon).show();
                        break;
                    default:
                        break;
                }
                if (GlobalVariables.ISTESTING) {
                    System.out.println(toastString + "..........toastString__print......");
                }
            }
        });
    }
}
