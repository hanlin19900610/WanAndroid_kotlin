package com.mufeng.wanandroid.ui.main.navigation

import com.mufeng.wanandroid.basic.BaseModel
import com.mufeng.wanandroid.bean.HttpResult
import com.mufeng.wanandroid.bean.NavigationBean
import com.mufeng.wanandroid.net.RetrofitHelper
import io.reactivex.Observable

class NavigationModel : BaseModel(), NavigationContract.Model  {
    override fun requestNavigationList(): Observable<HttpResult<List<NavigationBean>>> {
        return RetrofitHelper.service.getNavigationList()
    }
}