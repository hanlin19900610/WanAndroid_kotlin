package com.mufeng.wanandroid.ui.common.search

import com.mufeng.wanandroid.bean.ArticleResponseBody
import com.mufeng.wanandroid.bean.HttpResult
import com.mufeng.wanandroid.ui.common.CommonContract
import io.reactivex.Observable

interface SearchListContract {

    interface View : CommonContract.View {

        fun showArticles(articles: ArticleResponseBody)

        fun scrollToTop()

    }

    interface Presenter : CommonContract.Presenter<View> {

        fun queryBySearchKey(page: Int, key: String)

    }

    interface Model : CommonContract.Model {

        fun queryBySearchKey(page: Int, key: String): Observable<HttpResult<ArticleResponseBody>>

    }

}