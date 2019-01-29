package com.mufeng.wanandroid.ui.todo

import com.mufeng.wanandroid.basic.BaseModel
import com.mufeng.wanandroid.bean.AllTodoResponseBody
import com.mufeng.wanandroid.bean.HttpResult
import com.mufeng.wanandroid.bean.TodoResponseBody
import com.mufeng.wanandroid.net.RetrofitHelper
import io.reactivex.Observable

class TodoModel : BaseModel(), TodoContract.Model {

    override fun getTodoList(type: Int): Observable<HttpResult<AllTodoResponseBody>> {
        return RetrofitHelper.service.getTodoList(type)
    }

    override fun getNoTodoList(page: Int, type: Int): Observable<HttpResult<TodoResponseBody>> {
        return RetrofitHelper.service.getNoTodoList(page, type)
    }

    override fun getDoneList(page: Int, type: Int): Observable<HttpResult<TodoResponseBody>> {
        return RetrofitHelper.service.getDoneList(page, type)
    }

    override fun deleteTodoById(id: Int): Observable<HttpResult<Any>> {
        return RetrofitHelper.service.deleteTodoById(id)
    }

    override fun updateTodoById(id: Int, status: Int): Observable<HttpResult<Any>> {
        return RetrofitHelper.service.updateTodoById(id, status)
    }

}