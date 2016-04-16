package com.buildforyourself.iqfit.data

import com.buildforyourself.iqfit.model.FoodCategory

/**
 * Created by Ilya on 16.04.2016.
 */

interface IDataProvider {
    fun loadFoodCategories() : List<FoodCategory>
}