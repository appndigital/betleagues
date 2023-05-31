package com.appndigital.betleagues.core

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

abstract class FlowUseCase<ParamsT, ResultT> constructor() {
    suspend fun execute(params: ParamsT): Result<Flow<ResultT>> {
        return try {
            val result = withContext(Dispatchers.Default) { run(params) }
            Result.success(result)
        } catch (cancellationException: CancellationException) {
            throw cancellationException
        } catch (throwable: Throwable) {
            Result.failure(throwable)
        }
    }

    internal abstract suspend fun run(params: ParamsT): Flow<ResultT>
}
