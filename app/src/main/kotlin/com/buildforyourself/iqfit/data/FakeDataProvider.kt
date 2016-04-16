package com.buildforyourself.iqfit.data

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import com.buildforyourself.iqfit.model.FoodCategory
import com.buildforyourself.iqfit.model.FoodComponent

class FakeDataProvider : IDataProvider {
    override fun loadFoodCategories() : List<FoodCategory> {
//        var foodCategories: MutableList<FoodCategory> = mutableListOf<FoodCategory>()
//        for (i : Int in 1..12) {
//            val foodCategory = FoodCategory ("Суп{i}", BitmapDrawable(), i,  true, listOf<FoodComponent>())
//            foodCategories.add(foodCategory);
//        }

        var foodCategories = listOf (
                FoodCategory (1, "Супы", BitmapDrawable(), 1,  true, getSoupComponents()),
                FoodCategory (2, "Салаты", BitmapDrawable(), 2,  true, listOf<FoodComponent>()),
                FoodCategory (3, "Сладости", BitmapDrawable(), 3,  true, listOf<FoodComponent>()),
                FoodCategory (4, "Напитки", BitmapDrawable(), 4,  true, listOf<FoodComponent>()),
                FoodCategory (5, "Фрукты", BitmapDrawable(), 5,  true, listOf<FoodComponent>()),
                FoodCategory (6, "Мясо", BitmapDrawable(), 6,  true, listOf<FoodComponent>()),
                FoodCategory (7, "Сыры", BitmapDrawable(), 7,  true, listOf<FoodComponent>())
        )

        return foodCategories;
    }

    private fun getSoupComponents() : List<FoodComponent>{
        return listOf(
                FoodComponent(1, "Овощной", BitmapDrawable(), "", isDefault = true)
        )
    }
}