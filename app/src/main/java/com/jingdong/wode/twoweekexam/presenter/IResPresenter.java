package com.jingdong.wode.twoweekexam.presenter;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/12/10,0010.
 */

public interface IResPresenter {
    void onSuccess(JSONObject jsonObject);
    void onError();
}
