package io.tickr.data.network

import io.reactivex.Single
import io.tickr.data.model.Symbol
import retrofit2.http.GET

interface ReferenceDataService {
    @GET("ref-data/symbols")
    fun getSymbols(): Single<List<Symbol>>
}