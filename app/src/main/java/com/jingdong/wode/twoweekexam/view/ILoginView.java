package com.jingdong.wode.twoweekexam.view;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/12/10,0010.
 * 成功的view层接口
 */

public interface ILoginView {
    void onSuccess(JSONObject jsonObject);//返回值和P层的接口返回值相同   方法名可以不一样
    void onError();
}
