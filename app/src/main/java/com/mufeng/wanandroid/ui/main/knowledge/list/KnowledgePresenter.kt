package com.mufeng.wanandroid.ui.main.knowledge.list

import com.mufeng.wanandroid.ui.common.CommonPresenter
import com.mufeng.wanandroid.utils.ext.ss

class KnowledgePresenter : CommonPresenter<KnowledgeContract.Model, KnowledgeContract.View>(), KnowledgeContract.Presenter {

    override fun createModel(): KnowledgeContract.Model? = KnowledgeModel()

    override fun requestKnowledgeList(page: Int, cid: Int) {
        mModel?.requestKnowledgeList(page, cid)?.ss(mModel, mView) {
            mView?.setKnowledgeList(it.data)
        }
    }

}