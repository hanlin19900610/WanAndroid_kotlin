package com.mufeng.wanandroid.ui.common

import androidx.fragment.app.Fragment
import com.mufeng.wanandroid.R
import com.mufeng.wanandroid.basic.BaseSwipeBackActivity
import com.mufeng.wanandroid.constant.Constant
import com.mufeng.wanandroid.event.ColorEvent
import com.mufeng.wanandroid.ui.common.about.AboutFragment
import com.mufeng.wanandroid.ui.common.collect.CollectFragment
import com.mufeng.wanandroid.ui.common.search.SearchListFragment
import com.mufeng.wanandroid.ui.common.setting.SettingFragment
import com.mufeng.wanandroid.ui.common.todo.AddTodoFragment
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus

class CommonActivity : BaseSwipeBackActivity(){
    override val attachLayoutRes: Int
        get() = R.layout.activity_common

    override fun initData() {
    }

    override fun initView() {
        val extras = intent.extras
        val type = extras.getString(Constant.TYPE_KEY, "")
        toolbar.run {
            title = getString(R.string.app_name)
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        val fragment: Fragment? = when (type) {
            Constant.Type.COLLECT_TYPE_KEY -> {
                toolbar.title = getString(R.string.collect)
                CollectFragment.getInstance(extras)
            }
            Constant.Type.ABOUT_US_TYPE_KEY -> {
                toolbar.title = getString(R.string.about_us)
                AboutFragment.getInstance(extras)
            }
            Constant.Type.SETTING_TYPE_KEY -> {
                toolbar.title = getString(R.string.setting)
                SettingFragment.getInstance(extras)
            }
            Constant.Type.SEARCH_TYPE_KEY -> {
                toolbar.title = extras.getString(Constant.SEARCH_KEY, "")
                SearchListFragment.getInstance(extras)
            }
            Constant.Type.ADD_TODO_TYPE_KEY -> {
                toolbar.title = getString(R.string.add)
                AddTodoFragment.getInstance(extras)
            }
            Constant.Type.EDIT_TODO_TYPE_KEY -> {
                toolbar.title = getString(R.string.edit)
                AddTodoFragment.getInstance(extras)
            }
            Constant.Type.SEE_TODO_TYPE_KEY -> {
                toolbar.title = getString(R.string.see)
                AddTodoFragment.getInstance(extras)
            }
            else -> {
                null
            }
        }
        fragment ?: return
        supportFragmentManager.beginTransaction()
            .replace(R.id.common_frame_layout, fragment, Constant.Type.COLLECT_TYPE_KEY)
            .commit()

    }

    override fun start() {

    }

    override fun initColor() {
        super.initColor()
        EventBus.getDefault().post(ColorEvent(true, mThemeColor))
    }
}