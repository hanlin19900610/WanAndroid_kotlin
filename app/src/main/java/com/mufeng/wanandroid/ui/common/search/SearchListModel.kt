package com.mufeng.wanandroid.ui.common.search

import com.mufeng.wanandroid.bean.ArticleResponseBody
import com.mufeng.wanandroid.bean.HttpResult
import com.mufeng.wanandroid.net.RetrofitHelper
import com.mufeng.wanandroid.ui.common.CommonModel
import io.reactivex.Observable

class SearchListModel : CommonModel(), SearchListContract.Model {

    override fun queryBySearchKey(page: Int, key: String): Observable<HttpResult<ArticleResponseBody>> {
        return RetrofitHelper.service.queryBySearchKey(page, key)
    }

}