package com.anilax.fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anilax.adapter.DashboardAdapter;
import com.anilax.model.DashboardModel;
import com.anilax.textart.R;
import com.commonutility.CheckInternet;
import com.commonutility.FontUtils;
import com.commonutility.GlobalData;
import com.commonutility.GlobalVariables;
import com.commonutility.ItemAnimation;
import com.commonutility.NoInternetScreen;
import com.commonutility.PreferenceConnector;
import com.commonutility.ShowCustomToast;
import com.commonutility.WebService;
import com.commonutility.WebServiceListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FragHomeDashBoard extends Fragment implements OnClickListener, WebServiceListener {
    private View aiView = null;
    private boolean mAlreadyLoaded = false;
    private List<DashboardModel> lstDashBoard = new ArrayList<>();

    public static String[] selectedItem = {"Prepaid", "Postpaid", "Dth", "Electricity", "M Transfer", "Bus",
            "Flight", "Hotel", "Landline", "Gas", "Water", "Datacard"};
    private int[] allDrawable = {R.drawable.prepaid, R.drawable.prepaid, R.drawable.dth, R.drawable.electricity, R.drawable.moneytransfer,
            R.drawable.bus, R.drawable.flight, R.drawable.hotel,
            R.drawable.landline, R.drawable.gas, R.drawable.water, R.drawable.pancard};

    private EditText[] edTexts = {};
    private String[] edTextsError = {};
    private int[] editTextsClickId = {};

    private View[] allViewWithClick = {};
    private int[] allViewWithClickId = {};

    public FragHomeDashBoard() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (aiView == null) {
            aiView = inflater.inflate(R.layout.frag_home, container, false);
        }
        return aiView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        super.onCreate(savedInstanceState);
        StartApp();
        if (savedInstanceState == null && !mAlreadyLoaded) {
            mAlreadyLoaded = true;
            aiView = getView();
        }

        OnClickCombineDeclare(allViewWithClick);
        EditTextDeclare(edTexts);
        resumeApp();
    }

    private int animation_type = ItemAnimation.RIGHT_LEFT;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:

                break;
            default:
                break;
        }
    }

    public void resumeApp() {
        for (int j = 0; j < selectedItem.length; j++) {
            lstDashBoard.add(new DashboardModel(selectedItem[j], allDrawable[j], false));
        }
        RecyclerView recyclerView = (RecyclerView) aiView.findViewById(R.id.rv_dashboard);

        GridLayoutManager layoutManager = new GridLayoutManager(svContext, 4);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        DashboardAdapter mAdapter = new DashboardAdapter(svContext, lstDashBoard, animation_type);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new DashboardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String obj, int position) {
                customToast.showCustomToast(svContext, position+"", customToast.ToastySuccess);
//                Intent svIntent = new Intent(svContext, ActivityRecharge.class);
//                svIntent.putExtra("selecteditem", position);
//                startActivity(svIntent);
//                getActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });
    }

    private void EditTextDeclare(EditText[] editTexts) {
        for (int j = 0; j < editTexts.length; j++) {
            editTexts[j] = aiView.findViewById(editTextsClickId[j]);
        }
    }

    private void OnClickCombineDeclare(View[] allViewWithClick) {
        for (int j = 0; j < allViewWithClick.length; j++) {
            allViewWithClick[j] = aiView.findViewById(allViewWithClickId[j]);
            allViewWithClick[j].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
//        btnBack = (Button) allViewWithClick[0];
    }


    private Context svContext;
    private ShowCustomToast customToast;
    private CheckInternet checkNetwork;
    private ViewGroup root;
    private NoInternetScreen errrorScreen;
    private void StartApp() {
        svContext = getActivity();
        customToast = new ShowCustomToast(svContext);
        checkNetwork = new CheckInternet(svContext);
        root = (ViewGroup) aiView.findViewById(R.id.mylayout);
        errrorScreen = new NoInternetScreen(svContext, root, getActivity());
        if (!GlobalVariables.CUSTOMFONTNAME.equals("")) {
            Typeface font = Typeface.createFromAsset(getActivity().getAssets(), GlobalVariables.CUSTOMFONTNAME);
            FontUtils.setFont(root, font);
        }
        if (PreferenceConnector.readBoolean(svContext, PreferenceConnector.ISDARKTHEME, false)) {
//            FontUtils.setThemeColor(root, svContext, true);
        } else {
//            FontUtils.setThemeColor(root, svContext, false);
        }

        GlobalData.SetLanguage(svContext);
//        if (checkNetwork.isConnectingToInternet()) {
//            errrorScreen.hideError();
//        } else {
//            errrorScreen.showInternetError();
//        }
    }

    public static void hideFragmentkeyboard(Context meraContext, View meraView) {
        final InputMethodManager imm = (InputMethodManager) meraContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(meraView.getWindowToken(), 0);
    }

    LinkedList<String> lstUploadData = new LinkedList<>();

    private void callWebService(String postUrl, LinkedList<String> lstUploadData) {
        WebService webService = new WebService(svContext, postUrl, lstUploadData, this, root, getActivity());
        webService.LoadDataRetrofit(webService.callReturn());
    }

    @Override
    public void onWebServiceActionComplete(String result, String url) {
        System.out.println(result + ".........jsonresponse....." + url);
//        if (url.contains(ApiInterface.LOGIN)) {
//
//        }
    }

    @Override
    public void onWebServiceError(String result, String url) {
        customToast.showToast(result, svContext);
    }
}