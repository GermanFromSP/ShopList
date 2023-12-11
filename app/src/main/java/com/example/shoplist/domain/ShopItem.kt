package com.example.shoplist.domain

data class ShopItem(
    var name: String,
    val count: Int,
    var enabled:Boolean,
    var id: Int = NOT_DEFINED_ID
){
    companion object{
        const val NOT_DEFINED_ID = -1
    }
}