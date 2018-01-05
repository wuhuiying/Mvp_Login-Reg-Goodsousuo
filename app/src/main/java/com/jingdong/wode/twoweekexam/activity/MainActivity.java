package com.jingdong.wode.twoweekexam.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jingdong.wode.twoweekexam.Api.Api;
import com.jingdong.wode.twoweekexam.R;
import com.jingdong.wode.twoweekexam.presenter.LoginPresenter;
import com.jingdong.wode.twoweekexam.view.ILoginView;

import org.json.JSONException;
import org.json.JSONObject;

//登录界面实现登录的view
public class MainActivity extends AppCompatActivity implements ILoginView{

    private EditText phone;
    private EditText pwd;
    private LoginPresenter presenter;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String password;
    private String mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
        findView();
        //new 出 p层的连接的中间者
        presenter = new LoginPresenter(this);
        //创建 存储的对象
        sharedPreferences = getSharedPreferences("mobile",MODE_PRIVATE);
        boolean islogin = sharedPreferences.getBoolean("islogin", false);
        if (islogin){
            Intent intent = new Intent(MainActivity.this,SearchActivity.class);
            startActivity(intent);
        }
    }
    //扎控件
    private void findView() {
        phone = findViewById(R.id.edit_phone);
        pwd = findViewById(R.id.edit_pwd);
    }

    //注册
    public void btn_res(View view) {
        Intent intent = new Intent(MainActivity.this,ResActivity.class);
        startActivity(intent);
    }
    //登录
    public void btn_login(View view) {
        mobile = phone.getText().toString();
        password = pwd.getText().toString();
        //点击登录后把接口和值传给中间者
        presenter.getData(Api.LOGIN_URL,mobile,password);
    }

    @Override
    public void onSuccess(final JSONObject jsonObject) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    //是第几次登录的
                    String code = jsonObject.getString("code");
                    if("0".equals(code)){
                        //登录成功后，将数据存入SharedPreferences
                        editor = sharedPreferences.edit();
                        editor.putString("mobile",mobile);
                        editor.putString("password",password);
                        editor.putBoolean("islogin",true);
                        editor.commit();
                        Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this,jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this,jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
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
