package com.mufeng.wanandroid.ui.main.wechat.list

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.mufeng.wanandroid.App
import com.mufeng.wanandroid.R
import com.mufeng.wanandroid.basic.BaseMvpFragment
import com.mufeng.wanandroid.bean.Article
import com.mufeng.wanandroid.bean.ArticleResponseBody
import com.mufeng.wanandroid.constant.Constant
import com.mufeng.wanandroid.ui.account.login.LoginActivity
import com.mufeng.wanandroid.ui.content.ContentActivity
import com.mufeng.wanandroid.ui.main.knowledge.KnowledgeAdapter
import com.mufeng.wanandroid.ui.main.knowledge.KnowledgeContract
import com.mufeng.wanandroid.ui.main.knowledge.KnowledgeFragment
import com.mufeng.wanandroid.utils.NetWorkUtil
import com.mufeng.wanandroid.utils.ext.showSnackMsg
import com.mufeng.wanandroid.utils.ext.showToast
import com.mufeng.wanandroid.widget.SpaceItemDecoration
import kotlinx.android.synthetic.main.fragment_article_list.*

class ArticleListFragment : BaseMvpFragment<ArticleListContract.View, ArticleListContract.Presenter>(),
    ArticleListContract.View {

    companion object {

        fun getInstance(cid: Int): ArticleListFragment {
            val fragment = ArticleListFragment()
            val args = Bundle()
            args.putInt(Constant.CONTENT_CID_KEY, cid)
            fragment.arguments = args
            return fragment
        }
    }
    override val createPresenter: ArticleListContract.Presenter?
        get() = ArticleListPresenter()

    override val attachLayoutRes: Int
        get() = R.layout.fragment_article_list

    /**
     * cid
     */
    private var cid: Int = 0

    /**
     * datas
     */
    private val datas = mutableListOf<Article>()

    /**
     * is Refresh
     */
    private var isRefresh = true

    /**
     * RecyclerView Divider
     */
    private val recyclerViewItemDecoration by lazy {
        activity?.let {
            SpaceItemDecoration(it)
        }
    }

    /**
     * Article Adapter
     */
    private val articleListAdapter: ArticleListAdapter by lazy {
        ArticleListAdapter(activity, datas)
    }

    /**
     * LinearLayoutManager
     */
    private val linearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(activity)
    }

    override fun initView(view: View) {
        super.initView(view)
        mLayoutStatusView = multiple_status_view
        cid = arguments?.getInt(Constant.CONTENT_CID_KEY) ?: 0
        swipeRefreshLayout.run {
            setOnRefreshListener(onRefreshListener)
        }
        recyclerView.run {
            layoutManager = linearLayoutManager
            adapter = articleListAdapter
            itemAnimator = DefaultItemAnimator()
            recyclerViewItemDecoration?.let { addItemDecoration(it) }
        }

        articleListAdapter.run {
            setOnLoadMoreListener(onRequestLoadMoreListener, recyclerView)
            onItemClickListener = this@ArticleListFragment.onItemClickListener
            onItemChildClickListener = this@ArticleListFragment.onItemChildClickListener
            // setEmptyView(R.layout.fragment_empty_layout)
        }
    }

    override fun showLoading() {
        // swipeRefreshLayout.isRefreshing = isRefresh
    }

    override fun hideLoading() {
        swipeRefreshLayout?.isRefreshing = false
        if (isRefresh) {
            articleListAdapter.run {
                setEnableLoadMore(true)
            }
        }
    }

    override fun showError(errorMsg: String) {
        super.showError(errorMsg)
        mLayoutStatusView?.showError()
        articleListAdapter.run {
            if (isRefresh)
                setEnableLoadMore(true)
            else
                loadMoreFail()
        }
    }

    override fun lazyLoad() {
        mLayoutStatusView?.showLoading()
        mPresenter?.requestArticleList(0, cid)
    }

    override fun scrollToTop() {
        recyclerView.run {
            if (linearLayoutManager.findFirstVisibleItemPosition() > 20) {
                scrollToPosition(0)
            } else {
                smoothScrollToPosition(0)
            }
        }
    }

    override fun setArticleList(articles: ArticleResponseBody) {
        articles.datas.let {
            articleListAdapter.run {
                if (isRefresh) {
                    replaceData(it)
                } else {
                    addData(it)
                }
                val size = it.size
                if (size < articles.size) {
                    loadMoreEnd(isRefresh)
                } else {
                    loadMoreComplete()
                }
            }
        }
        if (articleListAdapter.data.isEmpty()) {
            mLayoutStatusView?.showEmpty()
        } else {
            mLayoutStatusView?.showContent()
        }
    }

    override fun showCollectSuccess(success: Boolean) {
        if (success) {
            showToast(getString(R.string.collect_success))
        }
    }

    override fun showCancelCollectSuccess(success: Boolean) {
        if (success) {
            showToast(getString(R.string.cancel_collect_success))
        }
    }

    /**
     * RefreshListener
     */
    private val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        isRefresh = true
        articleListAdapter.setEnableLoadMore(false)
        mPresenter?.requestArticleList(0, cid)
    }

    /**
     * LoadMoreListener
     */
    private val onRequestLoadMoreListener = BaseQuickAdapter.RequestLoadMoreListener {
        isRefresh = false
        swipeRefreshLayout.isRefreshing = false
        val page = articleListAdapter.data.size / 20
        mPresenter?.requestArticleList(page, cid)
    }

    /**
     * ItemClickListener
     */
    private val onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
        if (datas.size != 0) {
            val data = datas[position]
            Intent(activity, ContentActivity::class.java).run {
                putExtra(Constant.CONTENT_URL_KEY, data.link)
                putExtra(Constant.CONTENT_TITLE_KEY, data.title)
                putExtra(Constant.CONTENT_ID_KEY, data.id)
                startActivity(this)
            }
        }
    }

    /**
     * ItemChildClickListener
     */
    private val onItemChildClickListener =
        BaseQuickAdapter.OnItemChildClickListener { _, view, position ->
            if (datas.size != 0) {
                val data = datas[position]
                when (view.id) {
                    R.id.iv_like -> {
                        if (isLogin) {
                            if (!NetWorkUtil.isNetworkAvailable(App.context)) {
                                showSnackMsg(resources.getString(R.string.no_network))
                                return@OnItemChildClickListener
                            }
                            val collect = data.collect
                            data.collect = !collect
                            articleListAdapter.setData(position, data)
                            if (collect) {
                                mPresenter?.cancelCollectArticle(data.id)
                            } else {
                                mPresenter?.addCollectArticle(data.id)
                            }
                        } else {
                            Intent(activity, LoginActivity::class.java).run {
                                startActivity(this)
                            }
                            showToast(resources.getString(R.string.login_tint))
                        }
                    }
                }
            }
        }

}
