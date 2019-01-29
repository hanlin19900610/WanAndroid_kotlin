package com.mufeng.wanandroid.ui.content

import android.content.Intent
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.*
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import com.just.agentweb.AgentWeb
import com.just.agentweb.NestedScrollAgentWebView
import com.mufeng.wanandroid.R
import com.mufeng.wanandroid.basic.BaseMvpSwipeBackActivity
import com.mufeng.wanandroid.constant.Constant
import com.mufeng.wanandroid.event.RefreshHomeEvent
import com.mufeng.wanandroid.ui.account.login.LoginActivity
import com.mufeng.wanandroid.utils.ext.getAgentWeb
import com.mufeng.wanandroid.utils.ext.showToast
import kotlinx.android.synthetic.main.activity_content.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus

class ContentActivity : BaseMvpSwipeBackActivity<ContentContract.View, ContentContract.Presenter>(), ContentContract.View {
    override val createPresenter: ContentContract.Presenter?
        get() = ContentPresenter()
    override val attachLayoutRes: Int
        get() = R.layout.activity_content

    private var agentWeb: AgentWeb? = null
    private lateinit var shareTitle: String
    private lateinit var shareUrl: String
    private var shareId: Int = 0
    private val mWebView: NestedScrollAgentWebView by lazy {
        NestedScrollAgentWebView(this)
    }



    override fun initData() {
    }

    override fun initView() {
        super.initView()
        toolbar.apply {
            title = ""//getString(R.string.loading)
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            //StatusBarUtil2.setPaddingSmart(this@ContentActivity, toolbar)
        }
        tv_title.apply {
            text = getString(R.string.loading)
            visibility = View.VISIBLE
            postDelayed({
                tv_title.isSelected = true
            }, 2000)
        }
        intent.extras?.let {
            shareId = it.getInt(Constant.CONTENT_ID_KEY, -1)
            shareTitle = it.getString(Constant.CONTENT_TITLE_KEY, "")
            shareUrl = it.getString(Constant.CONTENT_URL_KEY, "")
        }

        initWebView()

    }

    /**
     * 初始化 WebView
     */
    private fun initWebView() {
        val layoutParams = CoordinatorLayout.LayoutParams(-1, -1)
        layoutParams.behavior = AppBarLayout.ScrollingViewBehavior()

        agentWeb = shareUrl.getAgentWeb(this,
            cl_main,
            layoutParams,
            mWebView,
            webChromeClient,
            webViewClient)

        agentWeb?.webCreator?.webView?.let {
            it.settings.domStorageEnabled = true
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                it.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            }
        }
    }

    override fun start() {
    }

    override fun showCollectSuccess(success: Boolean) {
        if (success) {
            showToast(getString(R.string.collect_success))
            EventBus.getDefault().post(RefreshHomeEvent(true))
        }
    }

    override fun showCancelCollectSuccess(success: Boolean) {
        if (success) {
            showToast(getString(R.string.cancel_collect_success))
            EventBus.getDefault().post(RefreshHomeEvent(true))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_content, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_share -> {
                Intent().run {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT,
                        getString(
                            R.string.share_article_url,
                            getString(R.string.app_name),
                            shareTitle,
                            shareUrl
                        ))
                    type = Constant.CONTENT_SHARE_TYPE
                    startActivity(Intent.createChooser(this, getString(R.string.action_share)))
                }
                return true
            }
            R.id.action_like -> {
                if (isLogin) {
                    mPresenter?.addCollectArticle(shareId)
                } else {
                    Intent(this, LoginActivity::class.java).run {
                        startActivity(this)
                    }
                    showToast(resources.getString(R.string.login_tint))
                }
                return true
            }
            R.id.action_browser -> {
                Intent().run {
                    action = "android.intent.action.VIEW"
                    data = Uri.parse(shareUrl)
                    startActivity(this)
                }
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        agentWeb?.let {
            if (!it.back()) {
                super.onBackPressed()
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (agentWeb?.handleKeyEvent(keyCode, event)!!) {
            true
        } else {
            finish()
            super.onKeyDown(keyCode, event)
        }
    }

    override fun onResume() {
        agentWeb?.webLifeCycle?.onResume()
        super.onResume()
    }

    override fun onPause() {
        agentWeb?.webLifeCycle?.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        agentWeb?.webLifeCycle?.onDestroy()
        super.onDestroy()
    }

    /**
     * webViewClient
     */
    private val webViewClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            return super.shouldOverrideUrlLoading(view, request)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
        }

        override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
            // super.onReceivedSslError(view, handler, error)
            handler?.proceed()
        }
    }

    /**
     * webChromeClient
     */
    private val webChromeClient = object : WebChromeClient() {
        override fun onProgressChanged(view: WebView, newProgress: Int) {
        }

        override fun onReceivedTitle(view: WebView, title: String) {
            super.onReceivedTitle(view, title)
            title.let {
                // toolbar.title = it
                tv_title.text = it
            }
        }
    }

}