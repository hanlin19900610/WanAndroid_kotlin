package com.mufeng.wanandroid.ui.main

import com.mufeng.wanandroid.basic.BasePresenter
import com.mufeng.wanandroid.utils.ext.ss

class MainPresenter : BasePresenter<MainContract.Model, MainContract.View>(), MainContract.Presenter {
    override fun createModel(): MainContract.Model? = MainModel()

    override fun logout() {
        mModel?.logout()?.ss(mModel, mView) {
            mView?.showLogoutSuccess(success = true)
        }
    }
}