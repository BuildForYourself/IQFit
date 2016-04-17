package com.buildforyourself.iqfit.data

import android.R
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.graphics.drawable.BitmapDrawable
import com.buildforyourself.iqfit.IQFitApplication
import com.buildforyourself.iqfit.calc.Formula
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
        IQFitApplication.instance.applicationContext.database.use {
            insert("User",
                    "age" to user.age,
                    "height" to user.height,
                    "weight" to user.weight,
                    "fatPercent" to user.fatPercent,
                    "activityType" to user.activityType.typeId)
        }
    }

    private val foods = mutableListOf<Food>()

    override fun loadUser(): User? {
        var result : User? = null
        IQFitApplication.instance.applicationContext.database.use {
            select("User", "age", "height", "weight", "fatPercent", "activityType").exec {
                if(this.count>0) {
                    this.moveToLast()
                    var  activityType = Formula.ActivityTypes.OFFICE_PLANKTON
                    when(this.getInt(4))
                    {
                        2 -> activityType = Formula.ActivityTypes.SPORTY
                        3 -> activityType = Formula.ActivityTypes.MORE_SPORTY
                        4 -> activityType = Formula.ActivityTypes.ATHLETE
                        5 -> activityType = Formula.ActivityTypes.HULK
                    }
                    result = User(
                            age = this.getInt(0),
                            height = this.getInt(1),
                            weight = this.getDouble(2),
                            fatPercent = this.getDouble(3),
                            activityType = activityType)
                }
            }
        }
        return result
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
        /*
        db.createTable("FoodCategory", true,
                "id" to INTEGER + PRIMARY_KEY + UNIQUE + AUTOINCREMENT,
                "name" to)
        db.createTable("Food", true,
                "id" to INTEGER + PRIMARY_KEY + UNIQUE + AUTOINCREMENT)

        db.createTable("QuantityComponent", true,
                "id" to INTEGER + PRIMARY_KEY + UNIQUE + AUTOINCREMENT)
        db.createTable("CalorieComponent", true,
                "id" to INTEGER + PRIMARY_KEY + UNIQUE + AUTOINCREMENT)
                */
        db.createTable("User", true,
                "id" to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                "age" to INTEGER,
                "height" to INTEGER,
                "weight" to REAL,
                "fatPercent" to REAL,
                "activityType" to INTEGER)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
    }
}