package io.github.takecare.cv.experience

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.takecare.cv.R
import io.github.takecare.network.ImageLoader
import kotlinx.android.synthetic.main.item_experience.view.*
import java.text.SimpleDateFormat
import java.util.*

class ExperienceAdapter(
    private val imageLoader: ImageLoader
) : RecyclerView.Adapter<ExperienceViewHolder>() {

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
        viewHolder.bind(data[position], imageLoader)
    }

    fun update(items: List<ExperienceItemViewModel>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }
}

class ExperienceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: ExperienceItemViewModel, imageLoader: ImageLoader) {
        itemView.title.text = item.name
        itemView.role.text = item.role

        val fromDate = item.from.readable()
        val toDate = item.to?.readable() ?: "Present" // FIXME @RUI
        itemView.date.text = itemView.context.resources.getString(R.string.date_format, fromDate, toDate)

        item.logoUrl?.let { imageLoader.loadAsCircle(it, itemView.logo) }
    }

    private fun Date.readable(): String {
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return simpleDateFormat.format(this)
    }
}
