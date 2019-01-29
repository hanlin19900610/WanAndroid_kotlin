package com.mufeng.wanandroid.ui.account.login

import com.mufeng.wanandroid.basic.BasePresenter
import com.mufeng.wanandroid.utils.ext.ss

class LoginPresenter : BasePresenter<LoginContract.Model, LoginContract.View>(), LoginContract.Presenter {

    override fun createModel(): LoginContract.Model? = LoginModel()

    override fun loginWanAndroid(username: String, password: String) {
        mModel?.loginWanAndroid(username, password)?.ss(mModel, mView) {
            mView?.loginSuccess(it.data)
        }
    }

}