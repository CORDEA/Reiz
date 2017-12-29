package jp.cordea.reiz

import android.content.Context

class MenuListAdapter(context: Context) : BindingListAdapter<MenuListItemViewModel>(
        context,
        R.layout.list_item_menu,
        emptyList()
)
