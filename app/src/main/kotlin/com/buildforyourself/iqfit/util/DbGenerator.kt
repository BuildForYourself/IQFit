package com.buildforyourself.iqfit.util

import android.graphics.drawable.BitmapDrawable
import com.buildforyourself.iqfit.IQFitApplication
import com.buildforyourself.iqfit.calc.Formula
import com.buildforyourself.iqfit.data.DataProvider
import com.buildforyourself.iqfit.model.FoodCategory
import com.buildforyourself.iqfit.model.FoodComponent
import com.buildforyourself.iqfit.model.IQFitDatabase
import com.buildforyourself.iqfit.model.User
import com.raizlabs.android.dbflow.config.FlowManager
import com.raizlabs.android.dbflow.sql.language.Select
import org.jetbrains.anko.*

/**
 * Created by Ilya on 16.04.2016.
 */

class DbGenerator {
    fun fill() {
        createDatabase();

        val dataProvider = DataProvider()
        createUser(dataProvider)

//        var superProducts = Select().from<SuperProduct>(SuperProduct::class.java).queryList()
    }

    private fun createDatabase() {
//        val context = IQFitApplication.instance.getApplicationContext()
//        FlowManager.init(context)
    }

    private fun createUser(dataProvider: DataProvider) {
        var user = User(50, 180, 80.5, 30.0, Formula.ActivityTypos.HULK)
        dataProvider.saveUser(user)
    }

    fun clear() {
        val context = IQFitApplication.instance.getApplicationContext()
        val databaseName = getDatabaseName()
        val deleted = context.deleteDatabase(databaseName)
        context.toast("База удалена $deleted")
    }

    private fun getDatabaseName(): String {
        val databaseName = String.format("%s.db", IQFitDatabase.NAME)
        return databaseName
    }
}