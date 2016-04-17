package com.buildforyourself.iqfit.data

import android.R
import android.graphics.drawable.BitmapDrawable
import com.buildforyourself.iqfit.model.Food
import com.buildforyourself.iqfit.model.FoodCategory
import com.buildforyourself.iqfit.model.FoodComponent
import com.buildforyourself.iqfit.model.User
import com.raizlabs.android.dbflow.sql.language.Select

/**
 * Created by Ilya on 16.04.2016.
 */

class DataProvider : IDataProvider {
    override fun saveUser(user: User) {
        user.save();
    }

    override fun loadUser(): User {
        var user = Select().from<User>(User::class.java).querySingle()
        return user
    }

    override fun loadFood(): List<Food> {
        throw UnsupportedOperationException()
    }

    override fun saveFood(food: Food) {
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
                FoodComponent("Овощной", BitmapDrawable(), "", false, true, true)
        )
    }

    override fun saveDefaultComponents(foodCategory: FoodCategory) {

    }
}