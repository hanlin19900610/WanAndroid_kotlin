package com.mufeng.wanandroid.ui.main.knowledge

import com.mufeng.wanandroid.basic.BaseModel
import com.mufeng.wanandroid.bean.HttpResult
import com.mufeng.wanandroid.bean.KnowledgeTreeBody
import com.mufeng.wanandroid.net.RetrofitHelper
import io.reactivex.Observable

class KnowledgeModel : BaseModel(), KnowledgeContract.Model{
    override fun requestKnowledgeTree(): Observable<HttpResult<List<KnowledgeTreeBody>>> {
        return RetrofitHelper.service.getKnowledge()
    }
}