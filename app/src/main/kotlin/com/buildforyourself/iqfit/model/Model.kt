package com.buildforyourself.iqfit.model

import android.graphics.drawable.Drawable
import com.buildforyourself.iqfit.calc.Formula
import java.util.*

class User (
        val age : Int,
        val height : Int,
        val weight: Double,
        val fatPercent: Double,
        var activityType: Formula.ActivityTypos) {
}

class Food (
        val id : Int = 0,
        val foodCategory: FoodCategory,
        val foodComponents: List<FoodComponent>,
        val dateTime: Date,
        val calories: Int,
        val percent: Int) {
}

class FoodCategory (
        val id : Int = 0,
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
        val id : Int = 0,
        val name: String,
        val icon: Drawable,
        val group: String = "",
        val isSingle: Boolean = false,
        var isDefault: Boolean = false,
        var isSelected: Boolean = false) {
}

class CalorieComponent (
        id : Int = 0,
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
        id : Int = 0,
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

