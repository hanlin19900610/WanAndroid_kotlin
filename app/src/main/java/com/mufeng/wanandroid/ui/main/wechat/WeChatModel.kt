package com.mufeng.wanandroid.ui.main.wechat

import com.mufeng.wanandroid.basic.BaseModel
import com.mufeng.wanandroid.bean.HttpResult
import com.mufeng.wanandroid.bean.WXChapterBean
import com.mufeng.wanandroid.net.RetrofitHelper
import io.reactivex.Observable

class WeChatModel : BaseModel(), WeChatContract.Model {

    override fun getWXChapters(): Observable<HttpResult<MutableList<WXChapterBean>>> {
        return RetrofitHelper.service.getWXChapters()
    }

}