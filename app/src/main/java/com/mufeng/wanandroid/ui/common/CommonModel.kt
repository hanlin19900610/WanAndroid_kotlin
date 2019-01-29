package com.mufeng.wanandroid.ui.common

import com.mufeng.wanandroid.basic.BaseModel
import com.mufeng.wanandroid.bean.HttpResult
import com.mufeng.wanandroid.net.RetrofitHelper
import io.reactivex.Observable

open class CommonModel : BaseModel(), CommonContract.Model {
    override fun addCollectArticle(id: Int): Observable<HttpResult<Any>> {
        return RetrofitHelper.service.addCollectArticle(id)
    }

    override fun cancelCollectArticle(id: Int): Observable<HttpResult<Any>> {
        return RetrofitHelper.service.cancelCollectArticle(id)
    }
}