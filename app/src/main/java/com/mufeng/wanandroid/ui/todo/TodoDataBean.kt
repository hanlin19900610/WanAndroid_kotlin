package com.mufeng.wanandroid.ui.todo

import com.chad.library.adapter.base.entity.SectionEntity
import com.mufeng.wanandroid.bean.TodoBean

class TodoDataBean : SectionEntity<TodoBean> {

    constructor(isHeader: Boolean, headerName: String) : super(isHeader, headerName)

    constructor(todoBean: TodoBean) : super(todoBean)

}