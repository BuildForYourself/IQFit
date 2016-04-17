package com.buildforyourself.iqfit.util

import com.buildforyourself.iqfit.IQFitApplication
import com.buildforyourself.iqfit.calc.Formula
import com.buildforyourself.iqfit.data.DataProvider
import com.buildforyourself.iqfit.data.database
import com.buildforyourself.iqfit.model.User
import org.jetbrains.anko.*

/**
 * Created by Ilya on 16.04.2016.
 */

class DbGenerator {
    fun fill() {
        val dataProvider = DataProvider()
        createUser(dataProvider)
    }

    private fun createUser(dataProvider: DataProvider) {
        var user = User(50, 180, 80.5, 30.0, Formula.ActivityTypes.HULK)
        dataProvider.saveUser(user)

        val u = dataProvider.loadUser()
    }

    fun clear() {
        val context = IQFitApplication.instance.applicationContext
    }
}