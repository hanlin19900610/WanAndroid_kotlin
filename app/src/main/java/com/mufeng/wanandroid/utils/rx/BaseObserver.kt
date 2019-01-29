package com.mufeng.wanandroid.utils.rx


import com.mufeng.wanandroid.App
import com.mufeng.wanandroid.basic.IView
import com.mufeng.wanandroid.bean.BaseBean
import com.mufeng.wanandroid.net.exception.ErrorStatus
import com.mufeng.wanandroid.net.exception.ExceptionHandle
import com.mufeng.wanandroid.utils.NetWorkUtil
import io.reactivex.observers.ResourceObserver

/**
 * Created by chenxz on 2018/6/11.
 */
abstract class BaseObserver<T : BaseBean>(view: IView? = null) : ResourceObserver<T>() {

    private var mView = view

    abstract fun onSuccess(t: T)

    override fun onComplete() {
        mView?.hideLoading()
    }

    override fun onStart() {
        super.onStart()
        mView?.showLoading()
        if (!NetWorkUtil.isNetworkConnected(App.instance)) {
            mView?.showDefaultMsg("网络连接不可用,请检查网络设置!")
            onComplete()
        }
    }

    override fun onNext(t: T) {
        when {
            t.errorCode == ErrorStatus.SUCCESS -> onSuccess(t)
            t.errorCode == ErrorStatus.TOKEN_INVAILD -> {
                // Token 过期，重新登录
            }
            else -> mView?.showDefaultMsg(t.errorMsg)
        }
    }

    override fun onError(t: Throwable) {
        mView?.hideLoading()
        ExceptionHandle.handleException(t)
    }

}
