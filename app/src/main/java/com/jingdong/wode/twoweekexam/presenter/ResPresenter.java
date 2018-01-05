package com.jingdong.wode.twoweekexam.presenter;

import com.jingdong.wode.twoweekexam.model.ResModel;
import com.jingdong.wode.twoweekexam.view.IResView;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/12/10,0010.
 */

public class ResPresenter implements IResPresenter {
    IResView iResView;
    private final ResModel resModel;

    public ResPresenter(IResView iResView){
        this.iResView = iResView;
        resModel = new ResModel(this);
    }
    public void getData(String url,String mobile,String password){
        resModel.getData(url,mobile,password);
    }
    @Override
    public void onSuccess(JSONObject jsonObject) {
        iResView.onSuccess(jsonObject);
    }

    @Override
    public void onError() {

    }
}
