package com.mufeng.wanandroid.ui.common.collect

import com.mufeng.wanandroid.basic.IModel
import com.mufeng.wanandroid.basic.IPresenter
import com.mufeng.wanandroid.basic.IView
import com.mufeng.wanandroid.bean.CollectionArticle
import com.mufeng.wanandroid.bean.CollectionResponseBody
import com.mufeng.wanandroid.bean.HttpResult
import io.reactivex.Observable

interface CollectContract {

    interface View : IView {

        fun setCollectList(articles: CollectionResponseBody<CollectionArticle>)

        fun showRemoveCollectSuccess(success: Boolean)

        fun scrollToTop()

    }

    interface Presenter : IPresenter<View> {

        fun getCollectList(page: Int)

        fun removeCollectArticle(id: Int, originId: Int)

    }

    interface Model : IModel {

        fun getCollectList(page: Int): Observable<HttpResult<CollectionResponseBody<CollectionArticle>>>

        fun removeCollectArticle(id: Int, originId: Int): Observable<HttpResult<Any>>

    }

}