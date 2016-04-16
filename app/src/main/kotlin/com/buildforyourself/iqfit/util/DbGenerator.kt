package com.buildforyourself.iqfit.util

import com.buildforyourself.iqfit.data.DataProvider
import com.buildforyourself.iqfit.model.*
import android.graphics.drawable.BitmapDrawable

/**
 * Created by Ilya on 16.04.2016.
 */

class DbGenerator {
    fun fill() {
        val dataProvider = DataProvider()
//        val foodCategory = FoodCategory (1, "Супы", BitmapDrawable(), 1, true,
//                listOf(FoodComponent(1, "Овощной", BitmapDrawable(), "", isDefault = true)))
//        dataProvider.saveFoodCategory(foodCategory);
        var user = User(33, 180)
        dataProvider.saveUser(user)
    }

    fun clear() {
//        val dataProvider = DataProvider()
//        dataProvider.deleteFoodCategories();
    }
}