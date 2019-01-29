package com.mufeng.wanandroid.ui.common

import com.mufeng.wanandroid.basic.IModel
import com.mufeng.wanandroid.basic.IPresenter
import com.mufeng.wanandroid.basic.IView
import com.mufeng.wanandroid.bean.HttpResult
import io.reactivex.Observable

interface CommonContract {

    interface View : IView {

        fun showCollectSuccess(success: Boolean)

        fun showCancelCollectSuccess(success: Boolean)
    }

    interface Presenter<in V : View> : IPresenter<V> {

        fun addCollectArticle(id: Int)

        fun cancelCollectArticle(id: Int)

    }

    interface Model : IModel {

        fun addCollectArticle(id: Int): Observable<HttpResult<Any>>

        fun cancelCollectArticle(id: Int): Observable<HttpResult<Any>>

    }

}