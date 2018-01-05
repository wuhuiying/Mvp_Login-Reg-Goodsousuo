package com.jingdong.wode.twoweekexam.presenter;

import com.jingdong.wode.twoweekexam.bean.SearchBean;
import com.jingdong.wode.twoweekexam.model.SearchModel;
import com.jingdong.wode.twoweekexam.view.ISearchView;

/**
 * Created by Administrator on 2017/12/10,0010.
 */

public class SearchPresenter implements ISearchPresenter {

    private final ISearchView iSearchView;
    private final SearchModel searchModel;

    public SearchPresenter(ISearchView iSearchView){
        this.iSearchView = iSearchView;
        searchModel = new SearchModel(this);
    }
    public void getData(String url,String keywords){
        searchModel.getData(url,keywords);
    }
    @Override
    public void onSuccess(SearchBean searchBean) {
        iSearchView.onSuccess(searchBean);
    }

    @Override
    public void onError() {

    }
}
