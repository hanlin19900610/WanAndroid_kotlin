package com.mufeng.wanandroid.ui.content

import com.mufeng.wanandroid.ui.common.CommonContract

interface ContentContract {

    interface View : CommonContract.View {

    }

    interface Presenter : CommonContract.Presenter<View> {

    }

    interface Model : CommonContract.Model {

    }

}