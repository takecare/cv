package io.github.takecare.cv.cover

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.takecare.cv.R
import kotlinx.android.synthetic.main.fragment_cover.*
import javax.inject.Inject
import kotlinx.android.synthetic.main.item_cover.view.textview as cover_textview
import kotlinx.android.synthetic.main.item_knowledge.view.textview as knowledge_textview
import kotlinx.android.synthetic.main.item_link.view.textview as link_textview

class CoverFragment : Fragment(), CoverView {

    companion object {
        fun newInstance() = CoverFragment()
    }

    @Inject
    lateinit var presenter: CoverPresenter

    private val coverAdapter = CoverAdapter()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        injectDependencies()
    }

    private fun injectDependencies() {
        DaggerCoverComponent.builder()
                .build()
                .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cover, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerview.layoutManager = LinearLayoutManager(activity)
        recyclerview.adapter = coverAdapter
    }

    override fun onResume() {
        super.onResume()
        presenter.startPresenting(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.stopPresenting()
    }

    override fun show(coverViewModel: CoverViewModel) {
        coverAdapter.update(coverViewModel.items)
    }

    override fun showError(throwable: Throwable) {
        // TODO: showError not implemented
        Log.e("RUI", "error: $throwable")
    }
}

class CoverAdapter : RecyclerView.Adapter<CoverViewHolder>() { // TODO move to its own file

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
            itemView.cover_textview.text = item.text
        }
    }

    class LinkViewHolder(view: View) : CoverViewHolder(view) {

        fun bind(item: CoverItemViewModel.Link) {
            itemView.link_textview.text = item.text
        }
    }

    class KnowledgeViewHolder(view: View) : CoverViewHolder(view) {

        fun bind(item: CoverItemViewModel.Knowledge) {
            itemView.knowledge_textview.text = item.text
        }
    }
}

enum class CoverViewType {
    LETTER,
    LINK,
    KNOWLEDGE
}
