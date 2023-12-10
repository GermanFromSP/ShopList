package com.example.shoplist.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {
    fun getShopList():LiveData<List<ShopItem>>
    fun addShopItem(shopItem: ShopItem)
    fun removeShopItem(shopItem: ShopItem)
    fun getAtShopItem(id:Int): ShopItem
    fun editShopItem(shopItem: ShopItem)
}