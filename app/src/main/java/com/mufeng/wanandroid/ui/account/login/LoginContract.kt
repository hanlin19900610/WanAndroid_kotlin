package com.mufeng.wanandroid.ui.account.login

import com.mufeng.wanandroid.basic.IModel
import com.mufeng.wanandroid.basic.IPresenter
import com.mufeng.wanandroid.basic.IView
import com.mufeng.wanandroid.bean.HttpResult
import com.mufeng.wanandroid.bean.LoginData
import io.reactivex.Observable

interface LoginContract {

    interface View : IView {

        fun loginSuccess(data: LoginData)

        fun loginFail()

    }

    interface Presenter : IPresenter<View> {

        fun loginWanAndroid(username: String, password: String)

    }

    interface Model : IModel {

        fun loginWanAndroid(username: String, password: String): Observable<HttpResult<LoginData>>

    }

}