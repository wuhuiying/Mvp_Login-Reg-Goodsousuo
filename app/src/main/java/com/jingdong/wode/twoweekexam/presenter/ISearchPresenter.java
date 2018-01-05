package com.jingdong.wode.twoweekexam.presenter;

import com.jingdong.wode.twoweekexam.bean.SearchBean;

/**
 * Created by Administrator on 2017/12/10,0010.
 */

public interface ISearchPresenter {
    void onSuccess(SearchBean searchBean);
    void onError();
}
