package com.mufeng.wanandroid.ui.main.wechat.list

import com.mufeng.wanandroid.ui.common.CommonPresenter
import com.mufeng.wanandroid.utils.ext.ss


class ArticleListPresenter : CommonPresenter<ArticleListContract.Model, ArticleListContract.View>(), ArticleListContract.Presenter {
    override fun createModel(): ArticleListContract.Model? = ArticleListModel()

    override fun requestArticleList(page: Int, cid: Int) {
        mModel?.requestArticleList(page, cid)?.ss(mModel, mView) {
            mView?.setArticleList(it.data)
        }
    }


}