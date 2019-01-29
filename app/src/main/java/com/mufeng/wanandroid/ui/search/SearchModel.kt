package com.mufeng.wanandroid.ui.search

import com.mufeng.wanandroid.basic.BaseModel
import com.mufeng.wanandroid.bean.HotSearchBean
import com.mufeng.wanandroid.bean.HttpResult
import com.mufeng.wanandroid.net.RetrofitHelper
import io.reactivex.Observable

class SearchModel : BaseModel(), SearchContract.Model {

    override fun getHotSearchData(): Observable<HttpResult<MutableList<HotSearchBean>>> {
        return RetrofitHelper.service.getHotSearchData()
    }

}