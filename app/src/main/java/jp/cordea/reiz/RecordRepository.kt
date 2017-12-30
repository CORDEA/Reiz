package jp.cordea.reiz

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import jp.cordea.reiz.model.Record
import java.util.*

object RecordRepository {

    private val recordDao = ReizApplication.Database.recordDao()

    fun getCurrentRecord(): Single<Optional<Record>> =
            Single
                    .fromCallable {
                        Optional.ofNullable(recordDao.getCurrentRecord())
                    }
                    .subscribeOn(Schedulers.io())
}