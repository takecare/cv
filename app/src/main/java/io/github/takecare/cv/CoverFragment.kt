package io.github.takecare.cv

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.takecare.cv.cover.Cover
import kotlinx.android.synthetic.main.fragment_cover.*
import kotlinx.android.synthetic.main.item_cover.view.textview as cover_textview
import kotlinx.android.synthetic.main.item_knowledge.view.textview as knowledge_textview
import kotlinx.android.synthetic.main.item_link.view.textview as link_textview

class CoverFragment : Fragment() {

    companion object {
        fun newInstance() = CoverFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cover, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerview.layoutManager = LinearLayoutManager(activity)
        recyclerview.adapter = CoverAdapter().apply {
            update(listOf(
                    Cover.Letter(""),
                    Cover.Link("github", "https://www.google.pt", 0),
                    Cover.Link("personal webpage", "https://www.google.pt", 0),
                    Cover.Knowledge("knowledge 1"),
                    Cover.Knowledge("knowledge 2"),
                    Cover.Knowledge("knowledge 3")
            ))
            notifyDataSetChanged()
        }
    }
}

class CoverAdapter : RecyclerView.Adapter<CoverViewHolder>() {

    private val data: MutableList<Cover> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): CoverViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (CoverViewType.values()[type]) {
            CoverViewType.LETTER -> {
                val view = inflater.inflate(R.layout.item_cover, parent, false)
                CoverViewHolder.LetterViewHolder(view)
            }
            CoverViewType.LINK -> {
                val view = inflater.inflate(R.layout.item_link, parent, false)
                CoverViewHolder.LinkViewHolder(view)
            }
            CoverViewType.KNOWLEDGE -> {
                val view = inflater.inflate(R.layout.item_knowledge, parent, false)
                CoverViewHolder.KnowledgeViewHolder(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (data[position]) {
            is Cover.Letter -> CoverViewType.LETTER
            is Cover.Link -> CoverViewType.LINK
            is Cover.Knowledge -> CoverViewType.KNOWLEDGE
        }.ordinal
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(viewHolder: CoverViewHolder, position: Int) {
        when (viewHolder) {
            is CoverViewHolder.LetterViewHolder -> viewHolder.bind(data[position] as Cover.Letter)
            is CoverViewHolder.LinkViewHolder -> viewHolder.bind(data[position] as Cover.Link)
            is CoverViewHolder.KnowledgeViewHolder -> viewHolder.bind(data[position] as Cover.Knowledge)
        }
    }

    fun update(items: List<Cover>) {
        data.addAll(items)
    }
}

sealed class CoverViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    class LetterViewHolder(view: View) : CoverViewHolder(view) {

        fun bind(item: Cover.Letter) {
            itemView.cover_textview.text = item.text
        }
    }

    class LinkViewHolder(view: View) : CoverViewHolder(view) {

        fun bind(item: Cover.Link) {
            itemView.link_textview.text = item.text
        }
    }

    class KnowledgeViewHolder(view: View) : CoverViewHolder(view) {

        fun bind(item: Cover.Knowledge) {
            itemView.knowledge_textview.text = item.text
        }
    }
}

enum class CoverViewType {
    LETTER,
    LINK,
    KNOWLEDGE
}
