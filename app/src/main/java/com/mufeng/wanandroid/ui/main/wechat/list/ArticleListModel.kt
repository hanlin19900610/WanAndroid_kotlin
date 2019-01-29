package com.mufeng.wanandroid.ui.main.wechat.list

import com.mufeng.wanandroid.bean.ArticleResponseBody
import com.mufeng.wanandroid.bean.HttpResult
import com.mufeng.wanandroid.net.RetrofitHelper
import com.mufeng.wanandroid.ui.common.CommonModel
import io.reactivex.Observable

class ArticleListModel : CommonModel(), ArticleListContract.Model {
    override fun requestArticleList(page: Int, cid: Int): Observable<HttpResult<ArticleResponseBody>> {
        return RetrofitHelper.service.getArticleListList(page, cid)
    }
}