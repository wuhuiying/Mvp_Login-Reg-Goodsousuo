package com.jingdong.wode.twoweekexam.presenter;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/12/10,0010.
 * 成功的接口P层的接口
 */

public interface ILoginPresenter {
    void onSuccess(JSONObject jsonObject);
    void onError();
}
