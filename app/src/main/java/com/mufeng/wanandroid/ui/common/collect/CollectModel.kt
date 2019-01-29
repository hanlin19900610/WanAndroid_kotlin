package com.mufeng.wanandroid.ui.common.collect

import com.mufeng.wanandroid.basic.BaseModel
import com.mufeng.wanandroid.bean.CollectionArticle
import com.mufeng.wanandroid.bean.CollectionResponseBody
import com.mufeng.wanandroid.bean.HttpResult
import com.mufeng.wanandroid.net.RetrofitHelper
import io.reactivex.Observable

class CollectModel  : BaseModel(), CollectContract.Model {

    override fun getCollectList(page: Int): Observable<HttpResult<CollectionResponseBody<CollectionArticle>>> {
        return RetrofitHelper.service.getCollectList(page)
    }

    override fun removeCollectArticle(id: Int, originId: Int): Observable<HttpResult<Any>> {
        return RetrofitHelper.service.removeCollectArticle(id, originId)
    }

}