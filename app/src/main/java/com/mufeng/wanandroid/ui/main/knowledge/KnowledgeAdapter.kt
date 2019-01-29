package com.mufeng.wanandroid.ui.main.knowledge

import android.content.Context
import android.text.Html
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.mufeng.wanandroid.R
import com.mufeng.wanandroid.bean.KnowledgeTreeBody

class KnowledgeAdapter(private val context: Context?, datas: MutableList<KnowledgeTreeBody>)
    : BaseQuickAdapter<KnowledgeTreeBody, BaseViewHolder>(R.layout.item_knowledge_list, datas)  {
    override fun convert(helper: BaseViewHolder?, item: KnowledgeTreeBody?) {
        helper?.setText(R.id.title_first, item?.name)
        item?.children.let {
            helper?.setText(R.id.title_second,
                it?.joinToString("    ", transform = { child ->
                    Html.fromHtml(child.name)
                })
            )

        }
    }
}