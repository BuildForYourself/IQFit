package com.buildforyourself.iqfit.util

import android.graphics.drawable.BitmapDrawable
import com.buildforyourself.iqfit.calc.Formula
import com.buildforyourself.iqfit.data.DataProvider
import com.buildforyourself.iqfit.model.FoodCategory
import com.buildforyourself.iqfit.model.FoodComponent
import com.buildforyourself.iqfit.model.User
import com.raizlabs.android.dbflow.sql.language.Select
import org.jetbrains.anko.*

/**
 * Created by Ilya on 16.04.2016.
 */

class DbGenerator {
    fun fill() {
        val dataProvider = DataProvider()
        var user = User()
        user.age = 50
        user.height = 180
        user.weight = 80.5
        user.fatPercent = 30.0
        user.activityType = Formula.ActivityTypos.HULK
        dataProvider.saveUser(user)

        user = dataProvider.loadUser()
        if (user.age > 5)
            user.age = 55;

//        var superProducts = Select().from<SuperProduct>(SuperProduct::class.java).queryList()

    }

    fun clear() {
//        val dataProvider = DataProvider()
//        dataProvider.deleteFoodCategories();
    }
}