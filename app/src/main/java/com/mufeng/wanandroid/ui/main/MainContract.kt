package com.mufeng.wanandroid.ui.main

import com.mufeng.wanandroid.basic.IModel
import com.mufeng.wanandroid.basic.IPresenter
import com.mufeng.wanandroid.basic.IView
import com.mufeng.wanandroid.bean.HttpResult
import io.reactivex.Observable

interface MainContract {

    interface View : IView {
        fun showLogoutSuccess(success: Boolean)
    }

    interface Presenter : IPresenter<View> {

        fun logout()

    }

    interface Model : IModel {

        fun logout(): Observable<HttpResult<Any>>

    }

}