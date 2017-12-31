package jp.cordea.reiz

import android.content.Context

class AddSessionMenuListAdapter(context: Context) : BindingListAdapter<AddSessionMenuListItemViewModel>(
        context,
        R.layout.list_item_add_session_menu,
        emptyList()
)
