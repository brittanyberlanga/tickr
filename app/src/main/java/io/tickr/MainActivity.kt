package io.tickr

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.tickr.data.network.ReferenceDataService
import io.tickr.data.network.ServiceProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val symbolsAdapter: SymbolsAdapter = SymbolsAdapter()
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        symbolsRecyclerView.adapter = symbolsAdapter

        val referenceDataService = ServiceProvider.createService(ReferenceDataService::class.java)

        referenceDataService.getSymbols()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ symbolsAdapter.symbolsList = it }, { it.printStackTrace() })
            .addTo(compositeDisposable)

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}
