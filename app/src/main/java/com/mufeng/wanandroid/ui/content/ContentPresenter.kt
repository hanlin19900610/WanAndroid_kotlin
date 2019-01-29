package com.mufeng.wanandroid.ui.content

import com.mufeng.wanandroid.ui.common.CommonPresenter

class ContentPresenter : CommonPresenter<ContentContract.Model, ContentContract.View>(), ContentContract.Presenter {

    override fun createModel(): ContentContract.Model? = ContentModel()

}