package com.mufeng.wanandroid.ui.main.projects.list

import com.mufeng.wanandroid.ui.common.CommonPresenter
import com.mufeng.wanandroid.utils.ext.ss

class ProjectListPresenter : CommonPresenter<ProjectListContract.Model, ProjectListContract.View>(),ProjectListContract.Presenter  {

    override fun createModel(): ProjectListContract.Model? = ProjectListModel()

    override fun requestProjectList(page: Int, cid: Int) {
        mModel?.requestProjectList(page, cid)?.ss(mModel, mView, page == 1) {
            mView?.setProjectList(it.data)
        }
    }


}