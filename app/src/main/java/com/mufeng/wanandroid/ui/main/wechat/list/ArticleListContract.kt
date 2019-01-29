package com.mufeng.wanandroid.ui.main.wechat.list

import com.mufeng.wanandroid.bean.ArticleResponseBody
import com.mufeng.wanandroid.bean.HttpResult
import com.mufeng.wanandroid.ui.common.CommonContract
import io.reactivex.Observable

interface ArticleListContract {

    interface View : CommonContract.View {

        fun scrollToTop()

        fun setArticleList(articles: ArticleResponseBody)

    }

    interface Presenter : CommonContract.Presenter<View> {

        fun requestArticleList(page: Int, cid: Int)

    }

    interface Model : CommonContract.Model {

        fun requestArticleList(page: Int, cid: Int): Observable<HttpResult<ArticleResponseBody>>

    }

}