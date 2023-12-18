package com.example.shoplist.presentation.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.shoplist.R
import com.example.shoplist.domain.ShopItem
import com.example.shoplist.presentation.ShopItemDiffCallback
import com.example.shoplist.presentation.ShowItemViewHolder

class ShopItemAdapter() : ListAdapter<ShopItem, ShowItemViewHolder>(ShopItemDiffCallback()) {

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }

        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShowItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShowItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        with(holder) {
            if (!shopItem.enabled){
                tvName.text = shopItem.name
                tvName.paintFlags = tvName.paintFlags or  Paint.STRIKE_THRU_TEXT_FLAG
            }else{
                tvName.text = shopItem.name
            }

            tvCount.text = shopItem.count.toString()
            itemView.setOnLongClickListener {
                onShopItemLongClickListener?.invoke(shopItem)
                true
            }
            itemView.setOnClickListener {
                onShopItemClickListener?.invoke(shopItem)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val shopItem = getItem(position)
        return if (shopItem.enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    companion object {

        const val MAX_POOL_SIZE = 30
        const val VIEW_TYPE_ENABLED = 0
        const val VIEW_TYPE_DISABLED = 1
    }
}