package com.jingdong.wode.twoweekexam.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jingdong.wode.twoweekexam.Api.Api;
import com.jingdong.wode.twoweekexam.R;
import com.jingdong.wode.twoweekexam.adapter.SearchRecyclerAdapter;
import com.jingdong.wode.twoweekexam.adapter.SearchRecyclerGVAdapter;
import com.jingdong.wode.twoweekexam.bean.SearchBean;
import com.jingdong.wode.twoweekexam.presenter.SearchPresenter;
import com.jingdong.wode.twoweekexam.view.ISearchView;

import java.net.URLEncoder;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements ISearchView{

    private EditText sou;
    private RecyclerView recyclerView;
    private CheckBox checkBox;
    private SearchPresenter presenter;
    boolean flag = false;
    private List<SearchBean.DataBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //找控件
        findView();
        presenter = new SearchPresenter(this);
    }

    private void findView() {
        sou = findViewById(R.id.shu);
        recyclerView = findViewById(R.id.recycler_view);
        checkBox = findViewById(R.id.ck);
    }
    //搜索
    public void search(View view) {
        try {
            String name = sou.getText().toString();
            String keywords = URLEncoder.encode(name, "utf-8");
            presenter.getData(Api.SEARCH_URL,keywords);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSuccess(final SearchBean searchBean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                list = searchBean.getData();
                Toast.makeText(SearchActivity.this, searchBean.getMsg(), Toast.LENGTH_SHORT).show();
                //设置适配器
                setAdapter(list);

                checkBox.setChecked(flag);
                checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (flag) {
                            //设置管理器、适配器
                            setAdapter(list);
                            checkBox.setChecked(false);
                            flag = checkBox.isChecked();
                        }
                        else {
                            SearchRecyclerGVAdapter gvAdapter = new SearchRecyclerGVAdapter(SearchActivity.this, list);
                            recyclerView.setAdapter(gvAdapter);
                            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                            checkBox.setChecked(true);
                            flag = checkBox.isChecked();
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onError() {

    }

    public void setAdapter(List<SearchBean.DataBean> list) {
        SearchRecyclerAdapter adapter = new SearchRecyclerAdapter(SearchActivity.this,list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this, LinearLayout.VERTICAL,false));

    }
}
