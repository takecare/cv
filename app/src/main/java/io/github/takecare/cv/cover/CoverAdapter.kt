package io.github.takecare.cv.cover

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.takecare.cv.R
import kotlinx.android.synthetic.main.item_cover.view.*
import kotlinx.android.synthetic.main.item_knowledge.view.*
import kotlinx.android.synthetic.main.item_link.view.*

class CoverAdapter(
    private val linkOpener: LinkOpener
) : RecyclerView.Adapter<CoverViewHolder>() {

    private val data: MutableList<CoverItemViewModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): CoverViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (CoverViewType.values()[type]) {
            CoverViewType.LETTER -> {
                val view = inflater.inflate(R.layout.item_cover, parent, false)
                CoverViewHolder.LetterViewHolder(view)
            }
            CoverViewType.LINK -> {
                val view = inflater.inflate(R.layout.item_link, parent, false)
                CoverViewHolder.LinkViewHolder(view, linkOpener)
            }
            CoverViewType.KNOWLEDGE -> {
                val view = inflater.inflate(R.layout.item_knowledge, parent, false)
                CoverViewHolder.KnowledgeViewHolder(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (data[position]) {
            is CoverItemViewModel.Letter -> CoverViewType.LETTER
            is CoverItemViewModel.Link -> CoverViewType.LINK
            is CoverItemViewModel.Knowledge -> CoverViewType.KNOWLEDGE
        }.ordinal
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(viewHolder: CoverViewHolder, position: Int) {
        when (viewHolder) {
            is CoverViewHolder.LetterViewHolder -> viewHolder.bind(data[position] as CoverItemViewModel.Letter)
            is CoverViewHolder.LinkViewHolder -> viewHolder.bind(data[position] as CoverItemViewModel.Link)
            is CoverViewHolder.KnowledgeViewHolder -> viewHolder.bind(data[position] as CoverItemViewModel.Knowledge)
        }
    }

    fun update(items: List<CoverItemViewModel>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }
}

sealed class CoverViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    class LetterViewHolder(view: View) : CoverViewHolder(view) {

        fun bind(item: CoverItemViewModel.Letter) {
            itemView.cover.text = item.text
        }
    }

    class LinkViewHolder(view: View, private val linkOpener: LinkOpener) : CoverViewHolder(view) {

        fun bind(item: CoverItemViewModel.Link) {
            itemView.link_title.text = item.text
            itemView.setOnClickListener { linkOpener.openLink(item.url) }
        }
    }

    class KnowledgeViewHolder(view: View) : CoverViewHolder(view) {

        fun bind(item: CoverItemViewModel.Knowledge) {
            itemView.knowledge_title.text = item.title
            itemView.knowledge_content.text = item.text
        }
    }
}

enum class CoverViewType {
    LETTER,
    LINK,
    KNOWLEDGE
}
