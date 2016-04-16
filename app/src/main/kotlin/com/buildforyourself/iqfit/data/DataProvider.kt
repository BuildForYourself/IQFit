package com.buildforyourself.iqfit.data

import android.graphics.drawable.BitmapDrawable
import com.buildforyourself.iqfit.model.Food
import com.buildforyourself.iqfit.model.FoodCategory
import com.buildforyourself.iqfit.model.FoodComponent
import com.buildforyourself.iqfit.model.User

/**
 * Created by Ilya on 16.04.2016.
 */

class DataProvider : IDataProvider {
    override fun loadFood(): List<Food> {
        throw UnsupportedOperationException()
    }

    override fun loadFoodCategories(): List<FoodCategory> {
        throw Exception()
    }

    override fun saveFoodCategory(foodCategory: FoodCategory) {
        //foodCategory.save();
    }

    private fun getSoupComponents() : List<FoodComponent>{
        return listOf(
                FoodComponent(1, "Овощной", BitmapDrawable(), "", isDefault = true)
        )
    }

    override fun saveUser(user: User) {
        //user.save();
    }

    override fun loadUser(): User {
        throw Exception()
    }
}