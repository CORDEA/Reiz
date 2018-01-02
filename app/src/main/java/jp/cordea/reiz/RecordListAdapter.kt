package jp.cordea.reiz

import android.content.Context

class RecordListAdapter(context: Context) : BindingListAdapter<RecordListItemViewModel>(
        context,
        R.layout.list_item_record
)

