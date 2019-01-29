package com.mufeng.wanandroid.ui.main.projects

import com.mufeng.wanandroid.basic.BaseModel
import com.mufeng.wanandroid.bean.HttpResult
import com.mufeng.wanandroid.bean.ProjectTreeBean
import com.mufeng.wanandroid.net.RetrofitHelper
import io.reactivex.Observable

class ProjectModel : BaseModel(), ProjectContract.Model {
    override fun requestProjectTree(): Observable<HttpResult<List<ProjectTreeBean>>> {
        return RetrofitHelper.service.getProjectTree()
    }
}