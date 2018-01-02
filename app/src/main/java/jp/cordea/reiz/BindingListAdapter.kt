package jp.cordea.reiz

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

open class BindingListAdapter<T>(
        private val context: Context,
        private val layout: Int,
        private var initItems: List<T> = emptyList()
) : BaseAdapter() {

    var items: List<T> = initItems
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItem(position: Int): T = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = items.size

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val binding: ViewDataBinding
        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(context), layout, parent, false)
            view = binding.root
        } else {
            view = convertView
            binding = DataBindingUtil.getBinding(view)
        }

        binding.setVariable(BR.vm, getItem(position))

        return view
    }
}
