package com.buildforyourself.iqfit.util

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import com.buildforyourself.iqfit.IQFitApplication
import com.buildforyourself.iqfit.R
import com.buildforyourself.iqfit.calc.Formula
import com.buildforyourself.iqfit.data.DataProvider
import com.buildforyourself.iqfit.data.database
import com.buildforyourself.iqfit.model.*
import org.jetbrains.anko.*
import java.io.InputStream

/**
 * Created by Ilya on 16.04.2016.
 */

class DbGenerator {
    fun fill() {
        val dataProvider = DataProvider()
        createUser(dataProvider)

        createIcon()
    }

    private fun createUser(dataProvider: DataProvider) {
        var user = User(50, 180, 80.5, 30.0, Formula.ActivityTypes.HULK)
        dataProvider.saveUser(user)

        val u = dataProvider.loadUser()
    }

    fun createIcon() {
        val context = IQFitApplication.instance.applicationContext
        var drawable = ContextCompat.getDrawable(context, R.drawable.fastfood);
        var components = listOf(
                CalorieComponent(1, "Кофе", BitmapDrawable(), "", isDefault = true, calories = 10),
                CalorieComponent(2, "Чай", BitmapDrawable(), "", calories = 6),
                CalorieComponent(3, "Молоко/сливки", BitmapDrawable(), "", isDefault = true, calories = 15),
                CalorieComponent(4, "Сахар/мёд", BitmapDrawable(), "", calories = 37),
                CalorieComponent(5, "Фруктовый Сок", BitmapDrawable(), "", calories = 100),
                CalorieComponent(6, "Овощной сок", BitmapDrawable(), "", calories = 40),
                CalorieComponent(7, "Газировка", BitmapDrawable(), "", calories = 80),
                CalorieComponent(8, "Пиво", BitmapDrawable(), "", calories = 215),
                CalorieComponent(9, "Вино", BitmapDrawable(), "", calories = 113),
                CalorieComponent(10, "Крепкий алкоголь", BitmapDrawable(), "", false, false, false, 114, Operation.Sum))

        var category = FoodCategory(0, "Name", drawable, 1, true, components);
    }

    fun clear() {
        val context = IQFitApplication.instance.applicationContext
    }
}