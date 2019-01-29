package com.mufeng.wanandroid.ui.main

import com.mufeng.wanandroid.basic.BaseModel
import com.mufeng.wanandroid.bean.HttpResult
import com.mufeng.wanandroid.net.RetrofitHelper
import io.reactivex.Observable

class MainModel : BaseModel(), MainContract.Model {
    override fun logout(): Observable<HttpResult<Any>> {
        return RetrofitHelper.service.logout()
    }
}