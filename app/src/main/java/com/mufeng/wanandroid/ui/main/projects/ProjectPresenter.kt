package com.mufeng.wanandroid.ui.main.projects

import com.mufeng.wanandroid.basic.BasePresenter
import com.mufeng.wanandroid.utils.ext.ss

class ProjectPresenter : BasePresenter<ProjectContract.Model, ProjectContract.View>(), ProjectContract.Presenter  {

    override fun createModel(): ProjectContract.Model? = ProjectModel()

    override fun requestProjectTree() {
        mModel?.requestProjectTree()?.ss(mModel, mView) {
            mView?.setProjectTree(it.data)
        }
    }

}