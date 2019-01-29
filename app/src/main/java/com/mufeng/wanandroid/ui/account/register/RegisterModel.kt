package com.mufeng.wanandroid.ui.account.register

import com.mufeng.wanandroid.basic.BaseModel
import com.mufeng.wanandroid.bean.HttpResult
import com.mufeng.wanandroid.bean.LoginData
import com.mufeng.wanandroid.net.RetrofitHelper
import io.reactivex.Observable

class RegisterModel : BaseModel(), RegisterContract.Model {

    override fun registerWanAndroid(username: String, password: String, repassword: String): Observable<HttpResult<LoginData>> {
        return RetrofitHelper.service.register(username, password, repassword)
    }

}