package com.mufeng.wanandroid.ui.common.search

import com.mufeng.wanandroid.ui.common.CommonPresenter
import com.mufeng.wanandroid.utils.ext.ss

class SearchListPresenter
    : CommonPresenter<SearchListContract.Model, SearchListContract.View>(), SearchListContract.Presenter {

    override fun createModel(): SearchListContract.Model? = SearchListModel()

    override fun queryBySearchKey(page: Int, key: String) {
        mModel?.queryBySearchKey(page, key)?.ss(mModel, mView, page == 0) {
            mView?.showArticles(it.data)
        }
    }

}