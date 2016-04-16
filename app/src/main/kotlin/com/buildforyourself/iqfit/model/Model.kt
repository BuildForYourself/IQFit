package com.buildforyourself.iqfit.model

import android.graphics.drawable.Drawable
import kotlin.collections.*
import java.util.Date

class Food (
        val foodCategory: FoodCategory,
        val foodComponents: List<FoodComponent>,
        val dateTime: Date,
        val calories: Int) {
}

class FoodCategory (
        val name: String,
        val icon: Drawable,
        var priority: Int,
        var isActive: Boolean,
        var components: List<FoodComponent>) {
}

open class FoodComponent (
        val name: String,
        val icon: Drawable,
        val group: String,
        val isSingle: Boolean,
        var isDefault: Boolean,
        var isSelected: Boolean) {
}

class CalorieComponent (
        name: String,
        icon: Drawable,
        group: String,
        isSingle: Boolean,
        isDefault: Boolean,
        isSelected: Boolean,
        val calories: Int,
        val operation: Operation)
: FoodComponent(name, icon, group, isSingle, isDefault, isSelected) {
}

class QuantityComponent (
        name: String,
        icon: Drawable,
        group: String,
        isSingle: Boolean,
        isDefault: Boolean,
        isSelected: Boolean,
        val multiplier: Double)
: FoodComponent(name, icon, group, isSingle, isDefault, isSelected) {
}

enum class Operation {
    Sum,
    Avg
}

