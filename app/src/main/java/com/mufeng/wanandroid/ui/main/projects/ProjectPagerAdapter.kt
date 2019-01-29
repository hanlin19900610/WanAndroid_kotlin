package com.mufeng.wanandroid.ui.main.projects

import android.text.Html
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.mufeng.wanandroid.bean.ProjectTreeBean
import com.mufeng.wanandroid.ui.main.projects.list.ProjectListFragment

class ProjectPagerAdapter (private val list: MutableList<ProjectTreeBean>, fm: FragmentManager) : FragmentStatePagerAdapter(fm)  {

    private val fragments = mutableListOf<Fragment>()

    init {
        fragments.clear()
        list.forEach {
            fragments.add(ProjectListFragment.getInstance(it.id))
        }
    }

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = list.size

    override fun getPageTitle(position: Int): CharSequence? = Html.fromHtml(list[position].name)

    override fun getItemPosition(`object`: Any): Int = PagerAdapter.POSITION_NONE

}