package com.mufeng.wanandroid.ui.main.home

import com.mufeng.wanandroid.bean.Article
import com.mufeng.wanandroid.bean.ArticleResponseBody
import com.mufeng.wanandroid.bean.Banner
import com.mufeng.wanandroid.bean.HttpResult
import com.mufeng.wanandroid.ui.common.CommonContract
import io.reactivex.Observable

interface HomeContract {

    interface View : CommonContract.View {

        fun scrollToTop()

        fun setBanner(banners: List<Banner>)

        fun setArticles(articles: ArticleResponseBody)

    }

    interface Presenter : CommonContract.Presenter<View> {

        fun requestBanner()

        fun requestHomeData()

        fun requestArticles(num: Int)

    }

    interface Model : CommonContract.Model {

        fun requestBanner(): Observable<HttpResult<List<Banner>>>

        fun requestTopArticles(): Observable<HttpResult<MutableList<Article>>>

        fun requestArticles(num: Int): Observable<HttpResult<ArticleResponseBody>>
    }

}