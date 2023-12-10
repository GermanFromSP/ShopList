package com.example.shoplist.domain

interface ShopListRepository {
    fun getShopList():List<ShopItem>
    fun addShopItem(shopItem: ShopItem)
    fun removeShopItem(shopItem: ShopItem)
    fun getAtShopItem(id:Int): ShopItem
    fun editShopItem(shopItem: ShopItem)
}