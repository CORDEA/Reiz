package jp.cordea.reiz

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import jp.cordea.reiz.databinding.ListItemHomeBinding

class HomeListAdapter(
        private val context: Context,
        private val items: MutableList<HomeListItemViewModel> = mutableListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var isEnableItems: Boolean = false
        set(value) {
            field = value
            items.forEach { it.isEnabled = value }
        }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder =
            ListItemHomeBinding.inflate(
                    LayoutInflater.from(context),
                    parent,
                    false
            ).let {
                ViewHolder(it)
            }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as? ViewHolder)?.run {
            binding.vm = items[position]
        }
    }

    fun refreshItems(items: List<HomeListItemViewModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun removeItem(item: HomeListItemViewModel) {
        val index = items.indexOf(item)
        items.remove(item)
        notifyItemRemoved(index)
    }

    private class ViewHolder(val binding: ListItemHomeBinding)
        : RecyclerView.ViewHolder(binding.root)
}
