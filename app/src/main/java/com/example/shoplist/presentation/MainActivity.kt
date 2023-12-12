package com.example.shoplist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
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
        setupOnSwiped()

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            shopItemAdapter.submitList(it)
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
        setupLongClickListener()
        setupClickListener()
    }

    private fun setupClickListener() {
        shopItemAdapter.onShopItemClickListener = {
            Log.d("ZZZZZ", it.toString())
        }
    }

    private fun setupLongClickListener() {
        shopItemAdapter.onShopItemLongClickListener = {
            viewModel.editShowItem(it)
        }
    }

    private fun setupOnSwiped() {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.removeShopItem(shopItemAdapter.currentList[viewHolder.adapterPosition])
            }
        }).attachToRecyclerView(binding.rvShopItem)
    }


}


