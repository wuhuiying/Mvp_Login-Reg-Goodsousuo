package com.jingdong.wode.twoweekexam.presenter;

import android.content.Context;

import com.jingdong.wode.twoweekexam.Api.Api;
import com.jingdong.wode.twoweekexam.model.LoginModel;
import com.jingdong.wode.twoweekexam.view.ILoginView;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/12/10,0010.
 * 成功的接口的实现类
 */

public class LoginPresenter implements ILoginPresenter{
    private ILoginView iLoginView;
    private LoginModel loginModel;

    //一个构造方法
    public LoginPresenter(ILoginView iLoginView){//参数是要连接的view层的接口
        this.iLoginView = iLoginView;
        //在构造方法中new出要连接的model层类
        loginModel = new LoginModel(this);
    }
    public void getData(String url,String mobile,String password){
        //把从view中接收到的值传给model
        loginModel.getData(url,mobile,password);
    }
    @Override
    public void onSuccess(JSONObject jsonObject) {
        //把成功的数据传给view
        iLoginView.onSuccess(jsonObject);
    }

    @Override
    public void onError() {

    }
}
