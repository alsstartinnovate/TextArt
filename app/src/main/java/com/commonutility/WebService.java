package com.commonutility;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.anilax.textart.R;
import com.retrofit.ApiClient;
import com.retrofit.ApiInterface;

import java.util.LinkedList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebService {
    private Context context;
    private String postUrl;
    private WebServiceListener listener;
    private CustomeProgressDialog customeProgressDialog;
    private LinkedList<String> lstUploadData;
    private boolean isShowText = false;
    private boolean isDialogShow = true;
    private String bodyString;
    private CheckInternet checkNetwork;
    private NoInternetScreen errorScreen;

//    //Remove it
//    public WebService(Context context, String postUrl, LinkedList<String> lstUploadData, WebServiceListener listener) {
//        this.context = context;
//        this.postUrl = postUrl;
//        this.lstUploadData = lstUploadData;
//        this.listener = listener;
//        this.checkNetwork = new CheckInternet(context);
//    }
//
//    //Remove it
//    public WebService(Context context, String postUrl, LinkedList<String> lstUploadData, WebServiceListener listener,
//                      boolean isDialogShow) {
//        this.context = context;
//        this.postUrl = postUrl;
//        this.lstUploadData = lstUploadData;
//        this.listener = listener;
//        this.isDialogShow = isDialogShow;
//        this.checkNetwork = new CheckInternet(context);
//    }

    public WebService(Context context, String postUrl, LinkedList<String> lstUploadData, WebServiceListener listener,
                      View root, Activity act) {
        this.context = context;
        this.postUrl = postUrl;
        this.lstUploadData = lstUploadData;
        this.listener = listener;
        this.checkNetwork = new CheckInternet(context);
        this.errorScreen = new NoInternetScreen(context, root, act);
    }

    public WebService(Context context, String postUrl, LinkedList<String> lstUploadData, WebServiceListener listener,
                      boolean isDialogShow, View root, Activity act) {
        this.context = context;
        this.postUrl = postUrl;
        this.lstUploadData = lstUploadData;
        this.listener = listener;
        this.isDialogShow = isDialogShow;
        this.checkNetwork = new CheckInternet(context);
        this.errorScreen = new NoInternetScreen(context, root, act);
    }

    public void LoadDataRetrofit(Call<String> call) {
        if (GlobalVariables.ISTESTING) {
            for (int i = 0; i < lstUploadData.size(); i++) {
                System.out.println(lstUploadData.get(i) + "........." + i + "..........");
            }
        }

        if (isDialogShow) {
            customeProgressDialog = new CustomeProgressDialog(context, R.layout.lay_customprogessdialog);
            TextView textView = (TextView) customeProgressDialog.findViewById(R.id.loader_showtext);
            if (isShowText) {
                textView.setVisibility(View.VISIBLE);
                textView.setText(getDialogText(postUrl));
            } else {
                textView.setVisibility(View.GONE);
            }

            customeProgressDialog.setCancelable(false);
            customeProgressDialog.show();
        }

        if (checkNetwork.isConnectingToInternet()) {
            errorScreen.hideError();
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    try {
                        System.out.println(response.toString() + "......webservice response..........");
                        int code = response.code();
//                    if (response.isSuccessful()) {
                        if (code == 200 || code == 300) {
                            bodyString = response.body();

                            if (null != customeProgressDialog && customeProgressDialog.isShowing()) {
                                customeProgressDialog.dismiss();
                            }

//                        if (GlobalVariables.ISTESTING) {
//                            try {
//                                GlobalData.SaveStringInFile(context, bodyString, postUrl.split("/")[((postUrl.split("/")).length) - 1]);
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
                        } else {
                            bodyString = response.message();
                            if (null != customeProgressDialog && customeProgressDialog.isShowing()) {
                                customeProgressDialog.dismiss();
                            }
                            listener.onWebServiceError(bodyString, postUrl);
                            return;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    listener.onWebServiceActionComplete(bodyString, postUrl);
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    if (null != customeProgressDialog && customeProgressDialog.isShowing()) {
                        customeProgressDialog.dismiss();
                    }
                    Log.e(FragmentTAG.APPNAME, t.toString());
                }
            });
        } else {
            if (null != customeProgressDialog && customeProgressDialog.isShowing()) {
                customeProgressDialog.dismiss();
            }
            listener.onWebServiceError("Internet not available", postUrl);
            errorScreen.showError();
        }
    }

    public Call<String> callReturn() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        if (postUrl.equalsIgnoreCase(ApiInterface.SENDSMS)) {
            apiService = ApiClient.getSmsClient().create(ApiInterface.class);
        }

        switch (postUrl) {
            case ApiInterface.UPDATEFCM:
                return apiService.UpdateFCM(lstUploadData.get(0), lstUploadData.get(1));
            case ApiInterface.CHECKPHONE:
                return apiService.CheckPhone(lstUploadData.get(0));
            case ApiInterface.REGISTER:
                return apiService.Register(lstUploadData.get(0), lstUploadData.get(1),
                        lstUploadData.get(2), lstUploadData.get(3),
                        lstUploadData.get(4));
            default:
                new ShowCustomToast(context).showToast("Please declare url in webservice file", context);
                break;
        }
        return null;
    }

    public String getDialogText(String strPostUrl) {
        for (int i = 0; i < (ApiInterface.strUrlName).length; i++) {
            if ((ApiInterface.strUrlName)[i].equals(strPostUrl)) {
                return (ApiInterface.strUrlText)[i];
            }
        }
        return "";
    }
}
