package com.example.recipes.data.repositories

import com.example.recipes.data.db.dao.MenuDao
import com.example.recipes.data.db.entities.MenuEntity
import com.example.recipes.data.db.entities.MenuWithRecipes
import com.example.recipes.domain.repositories.IMenuRepository
import javax.inject.Inject

class MenuRepository @Inject constructor(
    private val dao: MenuDao
) : IMenuRepository {

    override suspend fun insertMenu(menu: MenuEntity) {
        dao.insertMenu(menu)
    }

    override suspend fun getListMenu(): List<MenuWithRecipes>? {
        return dao.getAllMenu()
    }
}