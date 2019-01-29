package com.mufeng.wanandroid.ui.todo

import com.mufeng.wanandroid.basic.IModel
import com.mufeng.wanandroid.basic.IPresenter
import com.mufeng.wanandroid.basic.IView
import com.mufeng.wanandroid.bean.AllTodoResponseBody
import com.mufeng.wanandroid.bean.HttpResult
import com.mufeng.wanandroid.bean.TodoResponseBody
import io.reactivex.Observable

interface TodoContract {

    interface View : IView {

        fun showNoTodoList(todoResponseBody: TodoResponseBody)

        fun showDeleteSuccess(success: Boolean)

        fun showUpdateSuccess(success: Boolean)

    }

    interface Presenter : IPresenter<View> {

        fun getAllTodoList(type: Int)

        fun getNoTodoList(page: Int, type: Int)

        fun getDoneList(page: Int, type: Int)

        fun deleteTodoById(id: Int)

        fun updateTodoById(id: Int, status: Int)

    }

    interface Model : IModel {

        fun getTodoList(type: Int): Observable<HttpResult<AllTodoResponseBody>>

        fun getNoTodoList(page: Int, type: Int): Observable<HttpResult<TodoResponseBody>>

        fun getDoneList(page: Int, type: Int): Observable<HttpResult<TodoResponseBody>>

        fun deleteTodoById(id: Int): Observable<HttpResult<Any>>

        fun updateTodoById(id: Int, status: Int): Observable<HttpResult<Any>>

    }

}