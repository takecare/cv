package io.github.takecare.cv.experience

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.takecare.cv.R
import kotlinx.android.synthetic.main.fragment_experience.*
import kotlinx.android.synthetic.main.item_experience.view.*
import javax.inject.Inject

class ExperienceFragment : Fragment(), ExperienceView {

    companion object {
        fun newInstance() = ExperienceFragment()
    }

    @Inject
    lateinit var presenter: ExperiencePresenter
    private val adapter = ExperienceAdapter()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        injectDependencies()
    }

    private fun injectDependencies() {
        DaggerExperienceComponent.builder()
                .build()
                .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_experience, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerview.layoutManager = LinearLayoutManager(activity)
        recyclerview.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        presenter.startPresenting(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.stopPresenting()
    }

    override fun show(experienceViewModel: ExperienceViewModel) {
        adapter.update(experienceViewModel.items)
    }

    override fun showError(throwable: Throwable) {
        // TODO: showError not implemented
    }
}

class ExperienceAdapter : RecyclerView.Adapter<ExperienceViewHolder>() {

    private val data: MutableList<ExperienceItemViewModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): ExperienceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_cover, parent, false)
        return ExperienceViewHolder(view)
    }

    override fun getItemViewType(position: Int) = 0

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(viewHolder: ExperienceViewHolder, position: Int) {
        viewHolder.bind(data[position])
    }

    fun update(items: List<ExperienceItemViewModel>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }
}

class ExperienceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: ExperienceItemViewModel) {
        itemView.textview.text = item.name
    }
}
