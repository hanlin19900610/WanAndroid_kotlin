package com.mufeng.wanandroid.ui.account.register

import com.mufeng.wanandroid.basic.IModel
import com.mufeng.wanandroid.basic.IPresenter
import com.mufeng.wanandroid.basic.IView
import com.mufeng.wanandroid.bean.HttpResult
import com.mufeng.wanandroid.bean.LoginData
import io.reactivex.Observable

interface RegisterContract {

    interface View : IView {

        fun registerSuccess(data: LoginData)

        fun registerFail()
    }

    interface Presenter : IPresenter<View> {

        fun registerWanAndroid(username: String, password: String, repassword: String)

    }

    interface Model : IModel {
        fun registerWanAndroid(username: String, password: String, repassword: String): Observable<HttpResult<LoginData>>
    }

}