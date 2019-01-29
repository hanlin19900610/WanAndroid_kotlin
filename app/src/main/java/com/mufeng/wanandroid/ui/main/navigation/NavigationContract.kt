package com.mufeng.wanandroid.ui.main.navigation

import com.mufeng.wanandroid.basic.IModel
import com.mufeng.wanandroid.basic.IPresenter
import com.mufeng.wanandroid.basic.IView
import com.mufeng.wanandroid.bean.HttpResult
import com.mufeng.wanandroid.bean.NavigationBean
import io.reactivex.Observable

interface NavigationContract {

    interface View : IView {
        fun setNavigationData(list: List<NavigationBean>)
    }

    interface Presenter : IPresenter<View> {
        fun requestNavigationList()
    }

    interface Model : IModel {
        fun requestNavigationList(): Observable<HttpResult<List<NavigationBean>>>
    }

}