package com.buildforyourself.iqfit.model

import android.graphics.drawable.Drawable
import java.util.*

class User (
        val age : Int,
        val height : Int)

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

