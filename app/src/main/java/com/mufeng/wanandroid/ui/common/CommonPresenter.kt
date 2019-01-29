package com.mufeng.wanandroid.ui.common

import com.mufeng.wanandroid.basic.BasePresenter
import com.mufeng.wanandroid.utils.ext.ss

open class CommonPresenter<M: CommonContract.Model, V: CommonContract.View>
    : BasePresenter<M,V>(), CommonContract.Presenter<V> {
    override fun addCollectArticle(id: Int) {
        mModel?.addCollectArticle(id)?.ss(mModel, mView) {
            mView?.showCollectSuccess(true)
        }
    }

    override fun cancelCollectArticle(id: Int) {
        mModel?.cancelCollectArticle(id)?.ss(mModel, mView) {
            mView?.showCancelCollectSuccess(true)
        }
    }
}