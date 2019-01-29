package com.mufeng.wanandroid.ui.main.home

import com.mufeng.wanandroid.bean.Article
import com.mufeng.wanandroid.bean.ArticleResponseBody
import com.mufeng.wanandroid.bean.Banner
import com.mufeng.wanandroid.bean.HttpResult
import com.mufeng.wanandroid.net.RetrofitHelper
import com.mufeng.wanandroid.ui.common.CommonModel
import io.reactivex.Observable

class HomeModel  : CommonModel(), HomeContract.Model {
    override fun requestBanner(): Observable<HttpResult<List<Banner>>> {
        return RetrofitHelper.service.getBanners()
    }

    override fun requestTopArticles(): Observable<HttpResult<MutableList<Article>>> {
        return RetrofitHelper.service.getTopArticles()
    }

    override fun requestArticles(num: Int): Observable<HttpResult<ArticleResponseBody>> {
        return RetrofitHelper.service.getArticles(num)
    }
}