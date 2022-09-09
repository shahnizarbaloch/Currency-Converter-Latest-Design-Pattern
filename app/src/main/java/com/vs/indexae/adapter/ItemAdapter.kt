package com.vs.indexae.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.TextView
import android.content.Context
import androidx.cardview.widget.CardView
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.vs.indexae.R
import com.vs.indexae.model.ServerRates

class ItemAdapter(
    private val context: Context, private val rateList: MutableList<ServerRates>,
    private val onMyOwnClickListener: OnMyOwnClickListener
) : RecyclerView.Adapter<ItemAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        @SuppressLint("InflateParams") val view = inflater.inflate(R.layout.design_item, null, false)
        return MyViewHolder(view, onMyOwnClickListener)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val obj = rateList[position]
        holder.tvType.text = obj.type+" : "
        holder.tvAmount.text = obj.amount.toString()
    }

    override fun getItemCount(): Int {
        return rateList.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class MyViewHolder(itemView: View, onMyOwnClickListener: OnMyOwnClickListener) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var tvType: TextView
        var tvAmount: TextView
        private var cardView: CardView
        private var onMyOwnClickListener: OnMyOwnClickListener
        override fun onClick(view: View) {
            onMyOwnClickListener.onMyOwnClick(adapterPosition)
        }

        init {
            tvType = itemView.findViewById(R.id.tv_type)
            tvAmount = itemView.findViewById(R.id.tv_euro_amount)
            cardView = itemView.findViewById(R.id.card_view)
            this.onMyOwnClickListener = onMyOwnClickListener
            itemView.setOnClickListener(this)
        }
    }

    interface OnMyOwnClickListener {
        fun onMyOwnClick(position: Int)
    }
}