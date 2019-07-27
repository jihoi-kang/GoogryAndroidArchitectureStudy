package com.architecturestudy.data.upbit.source

import com.architecturestudy.data.upbit.UpbitTicker
import com.architecturestudy.data.upbit.source.local.UpbitLocalDataSource
import com.architecturestudy.data.upbit.source.remote.UpbitRemoteDataSource
import io.reactivex.disposables.Disposable

class UpbitRepository private constructor(
    private val upbitLocalDataSource: UpbitLocalDataSource?,
    private val upbitRemoteDataSource: UpbitRemoteDataSource
) : UpbitDataSource {

    override fun getMarketPrice(
        prefix: String,
        onSuccess: (List<UpbitTicker>) -> Unit,
        onFail: (Throwable) -> Unit
    ): Disposable = upbitRemoteDataSource.getMarketPrice(
        prefix,
        onSuccess,
        onFail
    )

    override fun saveTicker(upbitTicker: UpbitTicker): Disposable? =
        upbitLocalDataSource?.saveTicker(upbitTicker)

    override fun sort(
        sortType: String,
        isDesc: Boolean,
        onSuccess: (List<UpbitTicker>) -> Unit,
        onFail: (Throwable) -> Unit
    ): Disposable? = upbitLocalDataSource?.sort(
        sortType,
        isDesc,
        onSuccess,
        onFail
    )

    companion object {
        private var instance: UpbitRepository? = null

        operator fun invoke(
            upbitLocalDataSource: UpbitLocalDataSource?,
            upbitRemoteDataSource: UpbitRemoteDataSource
        ): UpbitRepository = instance ?: UpbitRepository(
            upbitLocalDataSource,
            upbitRemoteDataSource
        ).apply { instance = this }
    }
}