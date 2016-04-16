package com.buildforyourself.iqfit.data

import com.buildforyourself.iqfit.model.*

/**
 * Created by Ilya on 16.04.2016.
 */

interface IDataProvider {
    fun loadFoodCategories() : List<FoodCategory>
    fun saveFoodCategory(foodCategory: FoodCategory)

    fun loadFood() : List<Food>
    fun saveFood(food: Food)

    fun saveDefaultComponents(foodCategory: FoodCategory)

    fun loadUser() : User
    fun saveUser(user : User)
}