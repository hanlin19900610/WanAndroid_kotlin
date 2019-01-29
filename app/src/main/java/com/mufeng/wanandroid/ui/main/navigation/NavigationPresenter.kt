package com.mufeng.wanandroid.ui.main.navigation

import com.mufeng.wanandroid.basic.BasePresenter
import com.mufeng.wanandroid.utils.ext.ss

class NavigationPresenter : BasePresenter<NavigationContract.Model, NavigationContract.View>(), NavigationContract.Presenter{

    override fun createModel(): NavigationContract.Model? = NavigationModel()

    override fun requestNavigationList() {
        mModel?.requestNavigationList()?.ss(mModel, mView) {
            mView?.setNavigationData(it.data)
        }
    }
}