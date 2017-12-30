package jp.cordea.reiz

import jp.cordea.reiz.model.Menu

class MenuListItemViewModel(
        menu: Menu
) {

    val title: String = menu.name
    val priceText: String = menu.price.toString()
}