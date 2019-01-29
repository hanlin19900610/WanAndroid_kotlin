package com.mufeng.wanandroid.ui.common.setting

import android.os.Bundle
import android.view.View
import com.mufeng.wanandroid.R
import com.mufeng.wanandroid.basic.BaseFragment

class SettingFragment : BaseFragment() {
    companion object {

        fun getInstance(bundle: Bundle): SettingFragment {
            val fragment = SettingFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override val attachLayoutRes: Int
        get() = R.layout.fragment_setting


    override fun initView(view: View) {
    }

    override fun lazyLoad() {
    }
}