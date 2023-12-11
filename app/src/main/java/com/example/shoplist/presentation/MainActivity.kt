package com.example.shoplist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import com.example.shoplist.R
import com.example.shoplist.databinding.ActivityMainBinding
import com.example.shoplist.databinding.ItemShopEnabledBinding
import com.example.shoplist.domain.ShopItem
import com.example.shoplist.presentation.adapters.ShopItemAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var shopItemAdapter: ShopItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            shopItemAdapter.shopItemList = it
        }
    }

    private fun setupRecyclerView() {
        shopItemAdapter = ShopItemAdapter()
        with(binding) {

            with(rvShopItem) {
                adapter = shopItemAdapter
                recycledViewPool.setMaxRecycledViews(
                    ShopItemAdapter.VIEW_TYPE_ENABLED,
                    ShopItemAdapter.MAX_POOL_SIZE
                )
                recycledViewPool.setMaxRecycledViews(
                    ShopItemAdapter.VIEW_TYPE_DISABLED,
                    ShopItemAdapter.MAX_POOL_SIZE
                )
            }
        }


    }


}