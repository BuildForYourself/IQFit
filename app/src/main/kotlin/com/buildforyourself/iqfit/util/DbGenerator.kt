package com.buildforyourself.iqfit.util

import com.buildforyourself.iqfit.IQFitApplication
import com.buildforyourself.iqfit.calc.Formula
import com.buildforyourself.iqfit.data.DataProvider
import com.buildforyourself.iqfit.model.User
import org.jetbrains.anko.*

/**
 * Created by Ilya on 16.04.2016.
 */

class DbGenerator {
    fun fill() {
        createDatabase();

        val dataProvider = DataProvider()
        createUser(dataProvider)
    }

    private fun createDatabase() {
    }

    private fun createUser(dataProvider: DataProvider) {
        var user = User(50, 180, 80.5, 30.0, Formula.ActivityTypos.HULK)
        dataProvider.saveUser(user)
    }

    fun clear() {
        val context = IQFitApplication.instance.getApplicationContext()
    }
}