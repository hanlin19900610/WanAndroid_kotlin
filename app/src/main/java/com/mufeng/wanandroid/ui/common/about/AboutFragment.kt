package com.mufeng.wanandroid.ui.common.about

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import com.mufeng.wanandroid.R
import com.mufeng.wanandroid.basic.BaseFragment
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment : BaseFragment() {

    companion object {
        fun getInstance(bundle: Bundle): AboutFragment {
            val fragment = AboutFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override val attachLayoutRes: Int
        get() = R.layout.fragment_about

    override fun initView(view: View) {
        about_content.run {
            text = Html.fromHtml(getString(R.string.about_content))
            movementMethod = LinkMovementMethod.getInstance()
        }

        val versionStr = getString(R.string.app_name) + " V" + activity?.packageManager?.getPackageInfo(
            activity?.packageName,
            0
        )?.versionName
        about_version.text = versionStr
    }

    override fun lazyLoad() {
    }
}