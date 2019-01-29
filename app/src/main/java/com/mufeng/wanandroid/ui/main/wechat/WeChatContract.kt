package com.mufeng.wanandroid.ui.main.wechat

import com.mufeng.wanandroid.basic.IModel
import com.mufeng.wanandroid.basic.IPresenter
import com.mufeng.wanandroid.basic.IView
import com.mufeng.wanandroid.bean.HttpResult
import com.mufeng.wanandroid.bean.WXChapterBean
import io.reactivex.Observable

interface WeChatContract {

    interface View : IView {

        fun scrollToTop()

        fun showWXChapters(chapters: MutableList<WXChapterBean>)

    }

    interface Presenter : IPresenter<View> {
        fun getWXChapters()
    }

    interface Model : IModel {
        fun getWXChapters(): Observable<HttpResult<MutableList<WXChapterBean>>>
    }

}