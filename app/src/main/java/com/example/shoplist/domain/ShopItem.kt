package com.example.shoplist.domain

data class ShopItem(
    val name: String,
    val count: Int,
    val enabled:Boolean,
    var id: Int = NOT_DEFINED_ID
){
    companion object{
        const val NOT_DEFINED_ID = -1
    }
}