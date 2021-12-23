package com.retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    String SENDSMS = "sendhttp.php"; //ok
    String CHECKPHONE = "check_phone"; //ok
    String REGISTER = "register"; //ok
    String UPDATEFCM = "update_fcm"; //ok
    String EDITPROFILE = "edit_profile"; //ok
    String CHANGEPWD = "change_password"; //ok
    String LOADSLIDERADS = "banner_list";
    String SUBMITCOMPALINT = "submit_feedback";
    String GETNOTIFICATION = "getnotification";

    String[] strUrlName = {CHECKPHONE, SENDSMS, REGISTER, UPDATEFCM, EDITPROFILE,
            CHANGEPWD, LOADSLIDERADS, SUBMITCOMPALINT};
    String[] strUrlText = {"...", "....", "...", "......", ".....", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
            "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};

    @FormUrlEncoded
    @POST(CHECKPHONE)
    Call<String> CheckPhone(@Field("phone") String phone);

    @FormUrlEncoded
    @POST(SENDSMS)
    Call<String> SendSms(@Field("sender") String sender,
                         @Field("route") String route,
                         @Field("authkey") String authkey,
                         @Field("country") String country,
                         @Field("mobiles") String mobiles,
                         @Field("message") String message);

    @FormUrlEncoded
    @POST(REGISTER)
    Call<String> Register(@Field("first_name") String fname,
                          @Field("last_name") String lname,
                          @Field("email") String email,
                          @Field("phone") String phone,
                          @Field("password") String password);

    @FormUrlEncoded
    @POST(UPDATEFCM)
    Call<String> UpdateFCM(@Field("id") String id,
                           @Field("fcm_id") String fcmid);

    @FormUrlEncoded
    @POST(EDITPROFILE)
    Call<String> EditProfile(@Field("id") String id,
                             @Field("first_name") String fname,
                             @Field("last_name") String lname,
                             @Field("email") String email,
                             @Field("phone") String phone,
                             @Field("image") String image,
                             @Field("fcm_id") String fcmid);

    @FormUrlEncoded
    @POST(CHANGEPWD)
    Call<String> ChanePwd(@Field("id") String id,
                          @Field("old_pass") String oldpwd,
                          @Field("new_pass") String newpwd);

    @FormUrlEncoded
    @POST(LOADSLIDERADS)
    Call<String> LoadSliderAds(@Field("id") String id);

    @FormUrlEncoded
    @POST(SUBMITCOMPALINT)
    Call<String> SubmitComplaint(@Field("user_id") String id,
                                 @Field("title") String title,
                                 @Field("description") String description,
                                 @Field("type") String type,
                                 @Field("image") String image);

    @FormUrlEncoded
    @POST(GETNOTIFICATION)
    Call<String> GetNotification(@Field("id") String id);
}
