package io.tickr

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.tickr.data.model.Symbol
import kotlinx.android.synthetic.main.symbol_item.view.*

class SymbolsAdapter : RecyclerView.Adapter<SymbolsAdapter.SymbolViewHolder>() {

    var symbolsList: List<Symbol> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SymbolViewHolder =
        SymbolViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.symbol_item, parent, false))

    override fun onBindViewHolder(viewHolder: SymbolViewHolder, position: Int) {
        symbolsList.getOrNull(position)?.let { viewHolder.bind(it) }
    }

    override fun getItemCount(): Int = symbolsList.size

    class SymbolViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val symbol: TextView = itemView.symbol
        val name: TextView = itemView.name
        fun bind(data: Symbol) {
            symbol.text = data.symbol
            name.text = data.name
        }
    }

}