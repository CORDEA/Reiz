package jp.cordea.reiz

import jp.cordea.reiz.model.Menu

class HomeListItemViewModel(menu: Menu) {

    val name = menu.name

    val priceText = menu.price.toString()
}