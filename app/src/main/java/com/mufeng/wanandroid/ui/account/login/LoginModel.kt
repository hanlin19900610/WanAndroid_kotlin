package com.mufeng.wanandroid.ui.account.login

import com.mufeng.wanandroid.basic.BaseModel
import com.mufeng.wanandroid.bean.HttpResult
import com.mufeng.wanandroid.bean.LoginData
import com.mufeng.wanandroid.net.RetrofitHelper
import io.reactivex.Observable

class LoginModel : BaseModel(), LoginContract.Model {

    override fun loginWanAndroid(username: String, password: String): Observable<HttpResult<LoginData>> {
        return RetrofitHelper.service.login(username, password)
    }

}