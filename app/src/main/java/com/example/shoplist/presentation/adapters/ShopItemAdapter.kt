package com.example.shoplist.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.shoplist.R
import com.example.shoplist.databinding.ActivityMainBinding
import com.example.shoplist.databinding.ItemShopEnabledBinding
import com.example.shoplist.domain.ShopItem

class ShopItemAdapter() : Adapter<ShopItemAdapter.ShowItemViewHolder>() {

    var shopItemList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    private var count = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        Log.d("ADAPTER", "onCreateViewHolder ${++count}")
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShowItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShowItemViewHolder, position: Int) {
        val shopItem = shopItemList[position]
        with(holder) {
            tvName.text = shopItem.name
            tvCount.text = shopItem.count.toString()
        }
    }

    override fun getItemCount(): Int {
        return shopItemList.size
    }

    override fun getItemViewType(position: Int): Int {
        val shopItem = shopItemList[position]
        return if (shopItem.enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    class ShowItemViewHolder(view: View) :
        ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tvItemName)
        val tvCount = view.findViewById<TextView>(R.id.tvItemCount)
    }

    companion object {

        const val MAX_POOL_SIZE = 30
        const val VIEW_TYPE_ENABLED = 0
        const val VIEW_TYPE_DISABLED = 1
    }
}