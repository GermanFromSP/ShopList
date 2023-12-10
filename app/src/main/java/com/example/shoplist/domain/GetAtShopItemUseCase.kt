package com.example.shoplist.domain

class GetAtShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun getAtShopItem(id:Int): ShopItem{
        return shopListRepository.getAtShopItem(id)
    }
}