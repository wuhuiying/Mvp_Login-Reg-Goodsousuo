package com.jingdong.wode.twoweekexam.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jingdong.wode.twoweekexam.Api.Api;
import com.jingdong.wode.twoweekexam.R;
import com.jingdong.wode.twoweekexam.presenter.ResPresenter;
import com.jingdong.wode.twoweekexam.view.IResView;

import org.json.JSONException;
import org.json.JSONObject;

public class ResActivity extends AppCompatActivity implements IResView{

    private EditText phone;
    private EditText pwd;
    private ResPresenter resPresenter;
    private String password;
    private String mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res);
        //找控件
        findView();
        resPresenter = new ResPresenter(this);
    }

    private void findView() {
        phone = findViewById(R.id.edit_shouji);
        pwd = findViewById(R.id.edit_mima);
    }


    public void btn_res(View view) {
        mobile = phone.getText().toString();
        password = pwd.getText().toString();
        resPresenter.getData(Api.REGIST_URL,mobile,password);
    }

    @Override
    public void onSuccess(final JSONObject jsonObject) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    String code = jsonObject.getString("code");
                    if("0".equals(code)){
                        Toast.makeText(ResActivity.this,jsonObject.getString("msg"),Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(ResActivity.this,jsonObject.getString("msg"),Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onError() {

    }
}
