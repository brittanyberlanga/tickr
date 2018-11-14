package io.tickr

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.tickr.data.network.ReferenceDataService
import io.tickr.data.network.ServiceProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.rx2.await
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val symbolsAdapter: SymbolsAdapter = SymbolsAdapter()
    private val lifecycleScope = LifecycleScope()

    init {
        lifecycle.addObserver(lifecycleScope)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        symbolsRecyclerView.adapter = symbolsAdapter

        val referenceDataService = ServiceProvider.createService(ReferenceDataService::class.java)

        lifecycleScope.launch {
            try {
                val symbols = withContext(Dispatchers.IO) {
                    referenceDataService.getSymbols().await()
                }
                symbolsAdapter.symbolsList = symbols
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
