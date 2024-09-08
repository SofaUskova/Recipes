package com.example.recipes.domain.repositories

import com.example.recipes.data.db.entities.MenuEntity
import com.example.recipes.data.db.entities.MenuWithRecipes

interface IMenuRepository {
    suspend fun insertMenu(menu: MenuEntity)
    suspend fun getListMenu(): List<MenuWithRecipes>?
}