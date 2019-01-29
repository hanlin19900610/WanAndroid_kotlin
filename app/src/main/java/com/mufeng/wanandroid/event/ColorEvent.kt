package com.mufeng.wanandroid.event

import com.mufeng.wanandroid.utils.SettingUtil

class ColorEvent(var isRefresh: Boolean, var color: Int = SettingUtil.getColor())