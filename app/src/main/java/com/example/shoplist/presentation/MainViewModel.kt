package com.example.shoplist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoplist.data.ShopListRepositoryImpl
import com.example.shoplist.domain.EditShopItemUseCase
import com.example.shoplist.domain.GetShopListUseCase
import com.example.shoplist.domain.RemoveShopItemUseCase
import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.ShopListRepository

class MainViewModel: ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val removeShopListUseCase = RemoveShopItemUseCase(repository)
    private val editShopListUseCase = EditShopItemUseCase(repository)
    val shopList = getShopListUseCase.getShopList()



    fun removeShopItem(shopItem: ShopItem){
        removeShopListUseCase.removeShopItem(shopItem)
    }
    fun editShowItem(shopItem: ShopItem){
        val newShopItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopListUseCase.editShopItem(newShopItem)
    }
}