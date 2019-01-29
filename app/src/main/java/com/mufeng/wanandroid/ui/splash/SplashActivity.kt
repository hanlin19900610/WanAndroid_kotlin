package com.mufeng.wanandroid.ui.splash

import android.content.Intent
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.mufeng.wanandroid.R
import com.mufeng.wanandroid.basic.BaseActivity
import com.mufeng.wanandroid.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity

class SplashActivity : BaseActivity() {
    override val attachLayoutRes: Int
        get() = R.layout.activity_splash

    private var alphaAnimation: AlphaAnimation? = null

    override fun useEventBus(): Boolean = false

    override fun enableNetworkTip(): Boolean = false

    override fun initData() {

    }

    override fun initView() {
        alphaAnimation = AlphaAnimation(0.3F, 1.0F)
        alphaAnimation?.run {
            duration = 2000
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(p0: Animation?) {
                }

                override fun onAnimationEnd(p0: Animation?) {
                    jumpToMain()
                }

                override fun onAnimationStart(p0: Animation?) {
                }
            })
        }
        layout_splash.startAnimation(alphaAnimation)
    }

    override fun start() {

    }

    fun jumpToMain() {
        startActivity<MainActivity>()
        finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}