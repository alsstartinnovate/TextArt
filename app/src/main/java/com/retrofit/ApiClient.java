package com.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {
    public static String PRE_URL = "http://tgi.startupinnovative.in/tgiadmin/api/";

    public static final String PRE_URL_MSG = "http://api.msg91.com/api/";
    public static final String MSG_URL = "sendhttp.php";
    public static final String SENDERID = "ksinfot";
    public static final String ROUTE = "4";
    public static final String AUTHKEY = "258377A7uVRSPmst35c4b691c";
    public static final String COUNTRYCODE = "91";

    public static Retrofit getClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PRE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }

    public static Retrofit getSmsClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PRE_URL_MSG)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }
}
