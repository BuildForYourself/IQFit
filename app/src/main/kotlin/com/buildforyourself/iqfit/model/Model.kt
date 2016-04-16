package com.buildforyourself.iqfit.model

import android.graphics.drawable.Drawable
import com.buildforyourself.iqfit.data.IQFitDatabase
import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel
import java.util.*

@Table(database = IQFitDatabase::class, name = "IQFitDatabase")
class User (
        @Column
        val age : Int,
        @Column
        val height : Int,
        @Column
        val weight: Double,
        @Column
        val fatPercent: Double,
        @Column
        var activityType: Formula.ActivityTypos)
: BaseModel ()

class Food (
        val id : Int,
        val foodCategory: FoodCategory,
        val foodComponents: List<FoodComponent>,
        val dateTime: Date,
        val calories: Int,
        val percent: Int) {
}

class FoodCategory (
        val id : Int,
        val name: String,
        val icon: Drawable,
        var priority: Int,
        var isActive: Boolean,
        var components: List<FoodComponent>) {

    fun selectSingleComponent(foodComponent: FoodComponent){
        val sameGroupItems = components.filter { it.group.equals(foodComponent.group)
                && it.id!=foodComponent.id }
        for(i in sameGroupItems)
            i.isSelected = false
    }

    fun getDefaultComponents() : List<FoodComponent>{
        return components.filter { it.isDefault }
    }
}

open class FoodComponent (
        val id : Int,
        val name: String,
        val icon: Drawable,
        val group: String = "",
        val isSingle: Boolean = false,
        var isDefault: Boolean = false,
        var isSelected: Boolean = false) {
}

class CalorieComponent (
        id : Int,
        name: String,
        icon: Drawable,
        group: String,
        isSingle: Boolean = false,
        isDefault: Boolean = false,
        isSelected: Boolean = false,
        val calories: Int,
        val operation: Operation = Operation.Sum)
: FoodComponent(id, name, icon, group, isSingle, isDefault, isSelected) {
}

class QuantityComponent (
        id : Int,
        name: String,
        icon: Drawable,
        group: String,
        isSingle: Boolean = false,
        isDefault: Boolean = false,
        isSelected: Boolean = false,
        val multiplier: Double)
: FoodComponent(id, name, icon, group, isSingle, isDefault, isSelected) {
}

enum class Operation {
    Sum,
    Avg
}

