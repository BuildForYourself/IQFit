package com.buildforyourself.iqfit.data

import android.graphics.drawable.BitmapDrawable
import com.buildforyourself.iqfit.model.Food
import com.buildforyourself.iqfit.model.FoodCategory
import com.buildforyourself.iqfit.model.FoodComponent
import java.util.*

class FakeDataProvider : IDataProvider {
    override fun loadFood(): List<Food> {

        val foodCategories = loadFoodCategories();

        var food = listOf (
                Food (1, foodCategories[0], listOf(), Date(), 10, 15),
                Food (2, foodCategories[1], listOf(), Date(), 10, 15),
                Food (3, foodCategories[2], listOf(), Date(), 10, 15),
                Food (4, foodCategories[3], listOf(), Date(), 10, 15),
                Food (5, foodCategories[4], listOf(), Date(), 10, 15),
                Food (6, foodCategories[5], listOf(), Date(), 10, 15),
                Food (7, foodCategories[6], listOf(), Date(), 10, 15)
        )

        return food;
    }

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