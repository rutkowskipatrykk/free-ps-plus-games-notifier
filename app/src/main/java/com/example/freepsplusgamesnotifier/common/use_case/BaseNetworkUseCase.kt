package com.example.freepsplusgamesnotifier.common.use_case

import com.example.freepsplusgamesnotifier.common.Resource
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import retrofit2.Response
import timber.log.Timber
import java.net.HttpURLConnection

abstract class BaseNetworkUseCase<Dto, ReturnModel> {

    private var arguments: Array<Any> = arrayOf()

    protected abstract fun makeCall(arguments: Array<Any>): Response<Dto>?
    protected abstract fun handleData(data: Dto): ReturnModel

    operator fun invoke(vararg arguments: Any): Flowable<Resource<ReturnModel>> {
        this.arguments = arguments as Array<Any>
        return startCall()
    }

    private fun startCall(): Flowable<Resource<ReturnModel>> =
        Flowable.create(
            { emitter ->
                emitter.onNext(Resource.Loading())
                val response = makeCall(arguments)
                try {
                    if (response?.code() == HttpURLConnection.HTTP_OK) {
                        response.body()?.let {
                            emitter.onNext(Resource.Success(data = handleData(it)))
                        }
                    } else {
                        emitter.onNext(Resource.Error("Something went wrong :("))
                    }
                } catch (e: Exception) {
                    Timber.d(e.message)
                    emitter.onError(e)
                }

                emitter.onComplete()
            }, BackpressureStrategy.BUFFER
        )
}
