package com.mufeng.wanandroid.ui.main.projects.list

import com.mufeng.wanandroid.bean.ArticleResponseBody
import com.mufeng.wanandroid.bean.HttpResult
import com.mufeng.wanandroid.net.RetrofitHelper
import com.mufeng.wanandroid.ui.common.CommonModel
import io.reactivex.Observable

class ProjectListModel: CommonModel(),ProjectListContract.Model {

    override fun requestProjectList(page: Int, cid: Int): Observable<HttpResult<ArticleResponseBody>> {
        return RetrofitHelper.service.getProjectList(page, cid)
    }

}