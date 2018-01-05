package com.jingdong.wode.twoweekexam.model;

import com.jingdong.wode.twoweekexam.presenter.IResPresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import xp.code.okhttp3.utils.OkHttp3Utils;

/**
 * Created by Administrator on 2017/12/10,0010.
 */

public class ResModel {
    private IResPresenter iResPresenter;
    public ResModel(IResPresenter iResPresenter){
        this.iResPresenter = iResPresenter;
    }
    public void getData(String url,String mobile,String password){
        Map<String,String> map = new HashMap<>();
        map.put("mobile",mobile);
        map.put("password",password);
        OkHttp3Utils.doPost(url, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String string = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(string);
                        iResPresenter.onSuccess(jsonObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
