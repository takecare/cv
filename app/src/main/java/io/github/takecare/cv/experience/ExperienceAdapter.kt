package io.github.takecare.cv.experience

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.takecare.cv.R
import kotlinx.android.synthetic.main.item_experience.view.*

class ExperienceAdapter : RecyclerView.Adapter<ExperienceViewHolder>() {

    private val data: MutableList<ExperienceItemViewModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): ExperienceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_experience, parent, false)
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
