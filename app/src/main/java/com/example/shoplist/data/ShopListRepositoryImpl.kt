package com.example.shoplist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.ShopListRepository
import java.lang.RuntimeException
import kotlin.random.Random

object ShopListRepositoryImpl:ShopListRepository {
    private val shopList = mutableListOf<ShopItem>()
    private var autoIncrementId = 0
    private val shopListLD = MutableLiveData<List<ShopItem>>()

    init {
        for (i in 0 until 10){
            addShopItem(ShopItem("Name $i", i, Random.nextBoolean()))
        }
    }

    override fun getShopList():LiveData<List<ShopItem>> {
        return shopListLD
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.NOT_DEFINED_ID){
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun removeShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun getAtShopItem(id: Int): ShopItem {
        return shopList.find {
            it.id == id
        } ?: throw RuntimeException("Element with id $id not found")
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getAtShopItem(shopItem.id)
        shopList.remove(oldElement)
        shopList.add(shopItem)
        updateList()
    }

    private fun  updateList(){
        shopListLD.value = shopList.toList()
    }
}