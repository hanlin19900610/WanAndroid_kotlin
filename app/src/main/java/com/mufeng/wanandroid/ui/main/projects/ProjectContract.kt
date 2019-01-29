package com.mufeng.wanandroid.ui.main.projects

import com.mufeng.wanandroid.basic.IModel
import com.mufeng.wanandroid.basic.IPresenter
import com.mufeng.wanandroid.basic.IView
import com.mufeng.wanandroid.bean.HttpResult
import com.mufeng.wanandroid.bean.ProjectTreeBean
import io.reactivex.Observable

interface ProjectContract {

    interface View : IView {

        fun scrollToTop()

        fun setProjectTree(list: List<ProjectTreeBean>)

    }

    interface Presenter : IPresenter<View> {

        fun requestProjectTree()

    }

    interface Model : IModel {
        fun requestProjectTree(): Observable<HttpResult<List<ProjectTreeBean>>>
    }

}