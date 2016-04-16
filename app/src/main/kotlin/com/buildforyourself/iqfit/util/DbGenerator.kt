package com.buildforyourself.iqfit.util

import Formula
import com.buildforyourself.iqfit.data.DataProvider
import com.buildforyourself.iqfit.model.SuperProduct
import com.buildforyourself.iqfit.model.User

/**
 * Created by Ilya on 16.04.2016.
 */

class DbGenerator {
    fun fill() {
        val dataProvider = DataProvider()
//        val foodCategory = FoodCategory (1, "Супы", BitmapDrawable(), 1, true,
//                listOf(FoodComponent(1, "Овощной", BitmapDrawable(), "", isDefault = true)))
//        dataProvider.saveFoodCategory(foodCategory);
//        var user = User(33, 180, 1.0, 2.0, Formula.ActivityTypos.ATHLETE)
//        dataProvider.saveUser(user)
        var superProduct = SuperProduct()
        superProduct.name = "Test"
        superProduct.randomNumber = 777
        superProduct.save()
    }

    fun clear() {
//        val dataProvider = DataProvider()
//        dataProvider.deleteFoodCategories();
    }
}