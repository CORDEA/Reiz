package jp.cordea.reiz

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import jp.cordea.reiz.model.Menu

object MenuRepository {

    private val menuDao = ReizApplication.Database.menuDao()

    fun getMenus(): Single<List<Menu>> =
            Single
                    .fromCallable {
                        menuDao.getMenus()
                    }
                    .subscribeOn(Schedulers.io())

    fun addMenu(menu: Menu): Completable =
            Completable
                    .fromCallable {
                        menuDao.insertMenu(menu)
                    }
                    .subscribeOn(Schedulers.io())
}