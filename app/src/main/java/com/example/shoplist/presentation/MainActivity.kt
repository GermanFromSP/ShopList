package com.example.shoplist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.shoplist.R
import com.example.shoplist.domain.ShopItem

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val removedShopItem = ShopItem("Name 2", 2, true, 2)
        val editableShowItem = ShopItem("Name 3", 3, true, 3)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this){
            Log.d("MainActivityTEST", it.toString())
        }

        viewModel.removeShopItem(removedShopItem)
        viewModel.editShowItem(editableShowItem)

    }
}