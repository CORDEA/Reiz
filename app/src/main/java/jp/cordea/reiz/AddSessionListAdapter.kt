package jp.cordea.reiz

import android.content.Context

class AddSessionListAdapter(context: Context) : BindingListAdapter<AddSessionListItemViewModel>(
        context,
        R.layout.list_item_add_session,
        emptyList()
)
