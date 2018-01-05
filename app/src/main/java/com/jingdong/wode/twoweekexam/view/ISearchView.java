package com.jingdong.wode.twoweekexam.view;

import com.jingdong.wode.twoweekexam.bean.SearchBean;

/**
 * Created by Administrator on 2017/12/10,0010.
 */

public interface ISearchView {
    void onSuccess(SearchBean searchBean);
    void onError();
}
