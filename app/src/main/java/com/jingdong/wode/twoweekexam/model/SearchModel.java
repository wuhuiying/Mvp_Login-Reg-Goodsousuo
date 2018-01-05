package com.jingdong.wode.twoweekexam.model;

import android.util.Log;

import com.google.gson.Gson;
import com.jingdong.wode.twoweekexam.bean.SearchBean;
import com.jingdong.wode.twoweekexam.presenter.ISearchPresenter;

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

public class SearchModel {

    private ISearchPresenter iSearchPresenter;

    public SearchModel(ISearchPresenter iSearchPresenter){
        this.iSearchPresenter = iSearchPresenter;
    }
    public void getData(String url,String keyWords){
        Map<String, String> map = new HashMap<>();
        map.put("keywords",keyWords);
        map.put("page","1");
        map.put("source","android");
        OkHttp3Utils.doPost(url, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String string = response.body().string();
                    if (string != null){
                        Gson gson = new Gson();
                        SearchBean searchBean = gson.fromJson(string, SearchBean.class);
                        iSearchPresenter.onSuccess(searchBean);
                    }
                }
            }
        });
    }
}
