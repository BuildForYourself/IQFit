package com.buildforyourself.iqfit.data

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import com.buildforyourself.iqfit.model.FoodCategory
import com.buildforyourself.iqfit.model.FoodComponent

/**
 * Created by Ilya on 16.04.2016.
 */

class FakeDataProvider : IDataProvider {
    override fun loadFoodCategories() : List<FoodCategory> {
//        var foodCategories: MutableList<FoodCategory> = mutableListOf<FoodCategory>()
//        for (i : Int in 1..12) {
//            val foodCategory = FoodCategory ("Суп{i}", BitmapDrawable(), i,  true, listOf<FoodComponent>())
//            foodCategories.add(foodCategory);
//        }

        var foodCategories = listOf (
                FoodCategory ("Супы", BitmapDrawable(), 1,  true, listOf<FoodComponent>()),
                FoodCategory ("Салаты", BitmapDrawable(), 2,  true, listOf<FoodComponent>()),
                FoodCategory ("Сладости", BitmapDrawable(), 3,  true, listOf<FoodComponent>()),
                FoodCategory ("Напитки", BitmapDrawable(), 4,  true, listOf<FoodComponent>()),
                FoodCategory ("Фрукты", BitmapDrawable(), 5,  true, listOf<FoodComponent>()),
                FoodCategory ("Мясо", BitmapDrawable(), 6,  true, listOf<FoodComponent>()),
                FoodCategory ("Сыры", BitmapDrawable(), 7,  true, listOf<FoodComponent>())
        )

        return foodCategories;
    }
}