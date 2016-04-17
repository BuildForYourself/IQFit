package com.buildforyourself.iqfit.data

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import com.buildforyourself.iqfit.IQFitApplication
import com.buildforyourself.iqfit.R
import com.buildforyourself.iqfit.model.*
import java.util.*

class FakeDataProvider() : IDataProvider {
    override fun saveFood(food: Food) {
        foods.add(food)
    }

    private val foods = mutableListOf<Food>()

    override fun saveDefaultComponents(foodCategory: FoodCategory) {
        throw UnsupportedOperationException()
    }

    override fun saveFoodCategory(foodCategory: FoodCategory) {
        throw UnsupportedOperationException()
    }

    override fun loadUser(): User? {
        throw UnsupportedOperationException()
    }

    override fun saveUser(user: User) {
        throw UnsupportedOperationException()
    }

    override fun loadFood(): List<Food> {

        /*
        val foodCategories = loadFoodCategories();

        var food = listOf (
                Food (1, foodCategories[0], listOf(), Date(), 10, 1),
                Food (2, foodCategories[1], listOf(), Date(), 10, 2),
                Food (3, foodCategories[2], listOf(), Date(), 10, 3),
                Food (4, foodCategories[3], listOf(), Date(), 10, 4),
                Food (5, foodCategories[4], listOf(), Date(), 10, 5),
                Food (6, foodCategories[5], listOf(), Date(), 10, 6),
                Food (7, foodCategories[6], listOf(), Date(), 10, 7),
                Food (8, foodCategories[0], listOf(), Date(), 10, 8),
                Food (9, foodCategories[1], listOf(), Date(), 10, 9),
                Food (10, foodCategories[2], listOf(), Date(), 10, 10),
                Food (11, foodCategories[3], listOf(), Date(), 10, 11),
                Food (12, foodCategories[4], listOf(), Date(), 10, 12),
                Food (13, foodCategories[5], listOf(), Date(), 10, 13),
                Food (14, foodCategories[6], listOf(), Date(), 10, 14)
        )
        */

        return foods;
    }

    override fun loadFoodCategories(): List<FoodCategory> {
        //        var foodCategories: MutableList<FoodCategory> = mutableListOf<FoodCategory>()
        //        for (i : Int in 1..12) {
        //            val foodCategory = FoodCategory ("Суп{i}", BitmapDrawable(), i,  true, listOf<FoodComponent>())
        //            foodCategories.add(foodCategory);
        //        }

        val context = IQFitApplication.instance.applicationContext
        var drawable1 = ContextCompat.getDrawable(context, R.drawable.fastfood);
        var drawable2 = ContextCompat.getDrawable(context, R.drawable.salad);

        var foodCategories = listOf (
                FoodCategory (1, "Супы", getIcon(R.drawable.soup), 1, true, getSoupComponents()),
                FoodCategory (2, "Салаты", getIcon(R.drawable.salad), 2, true, getSaladComponents()),
                FoodCategory (3, "Сладости", getIcon(R.drawable.bakery), 3, true, getSweetComponents()),
                FoodCategory (4, "Напитки", getIcon(R.drawable.drink), 4, true, getDrinksComponents()),
                FoodCategory (5, "Фрукты", getIcon(R.drawable.fruit), 5, true, listOf<FoodComponent>()),
                FoodCategory (7, "Мясо", getIcon(R.drawable.meat), 7, true, listOf<FoodComponent>()),
                FoodCategory (8, "Рыба", getIcon(R.drawable.fish), 8, true, listOf<FoodComponent>()),
                FoodCategory (9, "Гарниры", getIcon(R.drawable.seconddish), 9, true, listOf<FoodComponent>()),
                FoodCategory (10, "Снеки", getIcon(R.drawable.snack), 10, true, listOf<FoodComponent>()),
                FoodCategory (11, "Фастфуд", getIcon(R.drawable.fastfood), 11, true, listOf<FoodComponent>()),
                FoodCategory (12, "Молочное", getIcon(R.drawable.milk), 12, true, listOf<FoodComponent>())
        )

        return foodCategories;
    }

    private fun getSweetComponents(): List<FoodComponent> {
        return listOf(
                CalorieComponent(1, "Плитка Шоколада", BitmapDrawable(), "", calories = 500, operation = Operation.Sum),
                CalorieComponent(2, "Пастила, зефир, мармелад", BitmapDrawable(), "", calories = 65, operation = Operation.Sum),
                CalorieComponent(3, "Карамель, леденцы", BitmapDrawable(), "", calories = 57, operation = Operation.Sum),
                CalorieComponent(4, "Мороженое", BitmapDrawable(), "", calories = 168, operation = Operation.Sum),
                CalorieComponent(5, "Печенье, крекер", BitmapDrawable(), "", calories = 80, operation = Operation.Sum),
                CalorieComponent(6, "Шок. конфета", BitmapDrawable(), "", calories = 100, operation = Operation.Sum),
                CalorieComponent(7, "Пирожное", BitmapDrawable(), "", calories = 500, operation = Operation.Sum),
                QuantityComponent(8, "Половина порции", BitmapDrawable(), "", multiplier = 0.5),
                QuantityComponent(9, "Двойная порция", BitmapDrawable(), "", multiplier = 2.0),
                QuantityComponent(9, "Тройная порция", BitmapDrawable(), "", multiplier = 3.0)
        )
    }

    private fun getIcon(iconId: Int): Drawable {
        val context = IQFitApplication.instance.applicationContext
        var drawable = ContextCompat.getDrawable(context, iconId);
        return drawable;
    }

    private fun getSoupComponents(): List<FoodComponent> {
        return listOf(
                CalorieComponent(1, "Овощной", BitmapDrawable(), "", isDefault = true, calories = 80, operation = Operation.Avg),
                CalorieComponent(2, "Картофель", BitmapDrawable(), "", calories = 20, operation = Operation.Avg),
                CalorieComponent(3, "Горох, фасоль", BitmapDrawable(), "", calories = 20, operation = Operation.Avg),
                CalorieComponent(4, "Крем, сыр", BitmapDrawable(), "", calories = 20, operation = Operation.Avg),
                CalorieComponent(5, "Макароны, клецки", BitmapDrawable(), "", calories = 40, operation = Operation.Avg),
                CalorieComponent(6, "Мясо, рыба", BitmapDrawable(), "", calories = 50, operation = Operation.Avg),
                CalorieComponent(7, "Жирный бульон", BitmapDrawable(), "", calories = 60, operation = Operation.Avg),
                CalorieComponent(8, "Зажарка, сало", BitmapDrawable(), "", calories = 100, operation = Operation.Avg),
                CalorieComponent(9, "Сметана (ложка)", BitmapDrawable(), "", calories = 35, operation = Operation.Sum),
                CalorieComponent(10, "Майонез (ложка)", BitmapDrawable(), "", calories = 85, operation = Operation.Sum),
                CalorieComponent(11, "Хлеб черный (кусок)", BitmapDrawable(), "", calories = 80, operation = Operation.Sum),
                CalorieComponent(12, "Хлеб белый (кусок)", BitmapDrawable(), "", calories = 120, operation = Operation.Sum),
                QuantityComponent(13, "Половина порции", BitmapDrawable(), "", multiplier = 0.5),
                QuantityComponent(14, "Двойная порция", BitmapDrawable(), "", multiplier = 2.0)
        )
    }

    private fun getSaladComponents(): List<FoodComponent> {
        return listOf(
                CalorieComponent(1, "Огурцы, помидоры, зелень", BitmapDrawable(), "", calories = 25, operation = Operation.Avg),
                CalorieComponent(2, "Капуста, морковь, свекла", BitmapDrawable(), "", isDefault = true, calories = 40, operation = Operation.Avg),
                CalorieComponent(3, "Картофель", BitmapDrawable(), "", calories = 80, operation = Operation.Avg),
                CalorieComponent(4, "Рыба", BitmapDrawable(), "", calories = 70, operation = Operation.Avg),
                CalorieComponent(5, "Мясо", BitmapDrawable(), "", calories = 100, operation = Operation.Avg),
                CalorieComponent(6, "Сыр, яйцо", BitmapDrawable(), "", calories = 50, operation = Operation.Avg),
                CalorieComponent(7, "Фасоль", BitmapDrawable(), "", calories = 100, operation = Operation.Avg),
                CalorieComponent(8, "Масло (ложка)", BitmapDrawable(), "", calories = 153, operation = Operation.Sum),
                CalorieComponent(9, "Сметана (ложка)", BitmapDrawable(), "", calories = 40, operation = Operation.Sum),
                CalorieComponent(10, "Майонез/соус (ложка)", BitmapDrawable(), "", calories = 85, operation = Operation.Sum),
                QuantityComponent(11, "Половина порции", BitmapDrawable(), "", multiplier = 0.5),
                QuantityComponent(12, "Двойная порция", BitmapDrawable(), "", multiplier = 2.0)
        )
    }

    private fun getDrinksComponents(): List<FoodComponent> {
        val context = IQFitApplication.instance.applicationContext
        var drawable = ContextCompat.getDrawable(context, R.drawable.fastfood);

        return listOf(
                CalorieComponent(1, "Кофе", drawable, "", isDefault = true, calories = 10),
                CalorieComponent(2, "Чай", BitmapDrawable(), "", calories = 6),
                CalorieComponent(3, "Молоко/сливки", BitmapDrawable(), "", isDefault = true, calories = 15),
                CalorieComponent(4, "Сахар/мёд", BitmapDrawable(), "", calories = 37),
                CalorieComponent(5, "Фруктовый Сок", BitmapDrawable(), "", calories = 100),
                CalorieComponent(6, "Овощной сок", BitmapDrawable(), "", calories = 40),
                CalorieComponent(7, "Газировка", BitmapDrawable(), "", calories = 80),
                CalorieComponent(8, "Пиво", BitmapDrawable(), "", calories = 215),
                CalorieComponent(9, "Вино", BitmapDrawable(), "", calories = 113),
                CalorieComponent(10, "Крепкий алкоголь", BitmapDrawable(), "", false, false, false, 114, Operation.Sum)
        )
    }
}