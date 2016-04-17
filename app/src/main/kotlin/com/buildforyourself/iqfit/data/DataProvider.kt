package com.buildforyourself.iqfit.data

import android.R
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.graphics.drawable.BitmapDrawable
import com.buildforyourself.iqfit.model.Food
import com.buildforyourself.iqfit.model.FoodCategory
import com.buildforyourself.iqfit.model.FoodComponent
import com.buildforyourself.iqfit.model.User
import org.jetbrains.anko.db.*

/**
 * Created by Ilya on 16.04.2016.
 */

class DataProvider : IDataProvider {
    override fun saveUser(user: User) {

    }

    override fun loadUser(): User {
        throw UnsupportedOperationException()
    }

    override fun loadFood(): List<Food> {
        throw UnsupportedOperationException()
    }

    override fun saveFood(food: Food) {
        throw UnsupportedOperationException()
    }

    override fun loadFoodCategories(): List<FoodCategory> {
        throw Exception()
    }

    override fun saveFoodCategory(foodCategory: FoodCategory) {
        //foodCategory.save();
    }

    private fun getSoupComponents() : List<FoodComponent>{
        return listOf(
                FoodComponent(0, "Овощной", BitmapDrawable(), "", false, true, true)
        )
    }

    override fun saveDefaultComponents(foodCategory: FoodCategory) {

    }
}

// Access property for Context
val Context.database: DatabaseOpenHelper
    get() = DatabaseOpenHelper.getInstance(applicationContext)

class DatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "IQFit", null, 1) {

    companion object {
        private var instance: DatabaseOpenHelper? = null

        fun getInstance(ctx: Context): DatabaseOpenHelper {
            if (instance == null) {
                instance = DatabaseOpenHelper(ctx.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables (more info about that is below)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
    }
}