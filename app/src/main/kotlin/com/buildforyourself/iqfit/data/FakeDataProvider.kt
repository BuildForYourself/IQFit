package com.buildforyourself.iqfit.data

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import com.buildforyourself.iqfit.model.FoodCategory
import com.buildforyourself.iqfit.model.FoodComponent

/**
 * Created by Ilya on 16.04.2016.
 */

class FakeDataProvider {
    fun loadFoodCategories() : List<FoodCategory> {

        var foodCategory = FoodCategory ("Суп", BitmapDrawable(), 1,  true, listOf<FoodComponent>())

        return listOf<FoodCategory> (foodCategory);
    }
}