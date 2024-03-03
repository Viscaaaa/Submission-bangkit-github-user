package com.visca.subgithub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.visca.subgithub.data.ItemsItem
import com.visca.subgithub.database.FavEntity
import com.visca.subgithub.databinding.ActivityFavoriteUserBinding

class FavoriteUserActivity : AppCompatActivity() {

    private lateinit var rvUser: RecyclerView
    lateinit var activityFavoriteBinding: ActivityFavoriteUserBinding
    private lateinit var viewModel: FavViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityFavoriteBinding = ActivityFavoriteUserBinding.inflate(layoutInflater)
        rvUser = activityFavoriteBinding.rvUser

        viewModel = obtainViewModel(this@FavoriteUserActivity)

        viewModel.getAllFavorite().observe(this) { users: List<FavEntity> ->
            val items = arrayListOf<ItemsItem>()

            users.map {
                val item = ItemsItem(login = it.username, avatarUrl = it.avatarUrl)
                items.add(item)
            }

            val listUserAdapter = FavoriteUserAdapter(items)

            activityFavoriteBinding.rvUser.apply {
                layoutManager = LinearLayoutManager(this@FavoriteUserActivity)
                adapter = listUserAdapter
            }

            listUserAdapter.setOnItemClickCallback(object : FavoriteUserAdapter.OnItemClickCallback {
                override fun onItemClicked(data: ItemsItem) {
                    startActivity(
                        Intent(this@FavoriteUserActivity, DetailUser::class.java)
                            .putExtra(DetailUser.USERNAME,data.login)
                            .putExtra(DetailUser.USER_IMAGE,data.avatarUrl)
                    )
                }
            })
        }

        setContentView(activityFavoriteBinding.root)
    }
    private fun obtainViewModel(activity: AppCompatActivity): FavViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(FavViewModel::class.java)
    }
}