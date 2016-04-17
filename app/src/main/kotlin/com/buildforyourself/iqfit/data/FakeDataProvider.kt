package com.buildforyourself.iqfit.data

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import com.buildforyourself.iqfit.IQFitApplication
import com.buildforyourself.iqfit.R
import com.buildforyourself.iqfit.calc.FoodCalculator
import com.buildforyourself.iqfit.calc.Formula
import com.buildforyourself.iqfit.model.*
import java.util.*

class FakeDataProvider() : IDataProvider {
    override fun saveFood(food: Food) {
        foods.add(food)
    }

    private val foods = mutableListOf<Food>()
    private val user = User(30, 185, 86.0, 20.0, Formula.ActivityTypes.SPORTY)

    override fun saveDefaultComponents(foodCategory: FoodCategory) {
        throw UnsupportedOperationException()
    }

    override fun saveFoodCategory(foodCategory: FoodCategory) {
        throw UnsupportedOperationException()
    }

    override fun loadUser(): User? {
        return user
    }

    override fun saveUser(user: User) {
        throw UnsupportedOperationException()
    }

    override fun loadFood(): List<Food> {
        val foodCategories = loadFoodCategories();

        var foodList = mutableListOf<Food>()
        for(i: Int in 1..1000)
        {
            val categoryId = Random().nextInt(11);
            val category = foodCategories[categoryId];
            val defaultComponents = category.getDefaultComponents()
            val calculator = FoodCalculator()
            val food = calculator.calculateFood(category, defaultComponents)
            foodList.add(food)
        }

        return foodList;
    }

    override fun loadFoodCategories(): List<FoodCategory> {
        var foodCategories = listOf (
                FoodCategory (1, "Суп", getIcon(R.drawable.soup), 1, true, getSoupComponents()),
                FoodCategory (2, "Салат", getIcon(R.drawable.salad), 2, true, getSaladComponents()),
                FoodCategory (3, "Сладкое", getIcon(R.drawable.bakery), 3, true, getSweetComponents()),
                FoodCategory (4, "Напиток", getIcon(R.drawable.drink), 4, true, getDrinksComponents()),
                FoodCategory (5, "Фрукт", getIcon(R.drawable.apple), 5, true, getFruitComponents()),
                FoodCategory (6, "Мясо", getIcon(R.drawable.meat), 7, true, getMeatComponents()),
                FoodCategory (7, "Рыба", getIcon(R.drawable.fish), 8, true, getFishComponents()),
                FoodCategory (8, "Гарнир", getIcon(R.drawable.seconddish), 9, true, getSecondCourseComponents()),
                FoodCategory (9, "Снек", getIcon(R.drawable.nut), 10, true, getSnackComponents()),
                FoodCategory (10, "Фастфуд", getIcon(R.drawable.fastfood), 11, true, getFastFoodComponents()),
                FoodCategory (11, "Молочный продукт", getIcon(R.drawable.milk), 12, true, getMilkComponents())
        )

        return foodCategories;
    }

    private fun getFastFoodComponents(): List<FoodComponent> {
        return listOf(
                CalorieComponent(1, "Чизбургер", getIcon(R.drawable.hamburger), "", calories = 304, operation = Operation.Sum),
                CalorieComponent(2, "Биг Тейсти", BitmapDrawable(), "", isDefault = true, calories = 842, operation = Operation.Sum),
                CalorieComponent(3, "Филе-о-фиш", BitmapDrawable(), "", calories = 333, operation = Operation.Sum),
                CalorieComponent(5, "Биф ролл", BitmapDrawable(), "", calories = 555, operation = Operation.Sum),
                CalorieComponent(6, "Картофель фри", BitmapDrawable(), "", calories = 239, operation = Operation.Sum),
                CalorieComponent(6, "Картофель по деревенски", BitmapDrawable(), "", calories = 108, operation = Operation.Sum),
                CalorieComponent(6, "Шаверма", getIcon(R.drawable.shaverma), "", calories = 108, operation = Operation.Sum),
                QuantityComponent(7, "Половина порции", getIcon(R.drawable.half), "", multiplier = 0.5),
                QuantityComponent(7, "Средняя порции", BitmapDrawable(), "", multiplier = 1.4),
                QuantityComponent(8, "Двойная порция", getIcon(R.drawable.double_portion), "", multiplier = 2.0)
        )
    }

    private fun getMilkComponents(): List<FoodComponent> {
        return listOf(
                CalorieComponent(1, "Плавленый сыр", BitmapDrawable(), "", calories = 65, operation = Operation.Avg),
                CalorieComponent(2, "Нежирный сыр", BitmapDrawable(), "", isDefault = true, calories = 75, operation = Operation.Avg),
                CalorieComponent(3, "Сыр", getIcon(R.drawable.cheese), "", calories = 144, operation = Operation.Avg),
                CalorieComponent(4, "Творог нежирный", BitmapDrawable(), "", calories = 101, operation = Operation.Avg),
                CalorieComponent(5, "Творог полужирный", BitmapDrawable(), "", calories = 101, operation = Operation.Avg),
                CalorieComponent(6, "Творог жирный", BitmapDrawable(), "", calories = 108, operation = Operation.Avg),
                CalorieComponent(6, "Сырок творожный", BitmapDrawable(), "", calories = 108, operation = Operation.Sum),
                CalorieComponent(4, "Сахар/мёд/варенье", BitmapDrawable(), "", calories = 37, operation = Operation.Sum),
                CalorieComponent(6, "Сметана (ложка)", BitmapDrawable(), "", calories = 35, operation = Operation.Sum),
                QuantityComponent(7, "Половина порции", getIcon(R.drawable.half), "", multiplier = 0.5),
                QuantityComponent(8, "Двойная порция", getIcon(R.drawable.double_portion), "", multiplier = 2.0)
        )
    }

    private fun getSnackComponents(): List<FoodComponent> {
        return listOf(
                CalorieComponent(1, "Цукаты", BitmapDrawable(), "", isDefault = true, calories = 525, operation = Operation.Avg),
                CalorieComponent(2, "Сухофрукты", BitmapDrawable(), "", calories = 330, operation = Operation.Avg),
                QuantityComponent(3, "Сушёная рыба, морепродукты", BitmapDrawable(), "", multiplier = 2.0),
                QuantityComponent(4, "Изюм", BitmapDrawable(), "", multiplier = 0.5),
                CalorieComponent(5, "Финики", BitmapDrawable(), "", calories = 200, operation = Operation.Avg),
                CalorieComponent(6, "Чипсы", BitmapDrawable(), "", calories = 35, operation = Operation.Sum),
                CalorieComponent(7, "Фисташки", BitmapDrawable(), "", calories = 85, operation = Operation.Sum),
                CalorieComponent(8, "Орехи", BitmapDrawable(), "", calories = 80, operation = Operation.Sum),
                QuantityComponent(10, "Средняя", BitmapDrawable(), "", multiplier = 2.0),
                QuantityComponent(11, "Большая", BitmapDrawable(), "", multiplier = 3.0)
        )
    }

    private fun getFishComponents(): List<FoodComponent> {
        return listOf(
                CalorieComponent(1, "Рыба", BitmapDrawable(), "", isDefault = true, calories = 525, operation = Operation.Avg),
                CalorieComponent(2, "Икра", BitmapDrawable(), "", calories = 330, operation = Operation.Avg),
                QuantityComponent(3, "Консервированный, Засоленный", BitmapDrawable(), "", multiplier = 2.0),
                QuantityComponent(4, "Варёный", BitmapDrawable(), "", multiplier = 0.5),
                CalorieComponent(5, "Жареный", BitmapDrawable(), "", calories = 200, operation = Operation.Avg),
                CalorieComponent(6, "Сметана (ложка)", BitmapDrawable(), "", calories = 35, operation = Operation.Sum),
                CalorieComponent(7, "Майонез (ложка)", BitmapDrawable(), "", calories = 85, operation = Operation.Sum),
                CalorieComponent(8, "Хлеб черный (кусок)", BitmapDrawable(), "", calories = 80, operation = Operation.Sum),
                CalorieComponent(9, "Хлеб белый (кусок)", BitmapDrawable(), "", calories = 120, operation = Operation.Sum),
                QuantityComponent(10, "Половина порции", getIcon(R.drawable.half), "", multiplier = 0.5),
                QuantityComponent(11, "Двойная порция", getIcon(R.drawable.double_portion), "", multiplier = 2.0)
        )
    }

    private fun getMeatComponents(): List<FoodComponent> {
        return listOf(
                CalorieComponent(1, "Свинина", BitmapDrawable(), "", isDefault = true, calories = 525, operation = Operation.Avg),
                CalorieComponent(2, "Птица", BitmapDrawable(), "", calories = 180, operation = Operation.Avg),
                CalorieComponent(3, "Говядина", BitmapDrawable(), "", calories = 330, operation = Operation.Avg),
                CalorieComponent(4, "Субпродукты", BitmapDrawable(), "", calories = 210, operation = Operation.Avg),
                CalorieComponent(5, "Паштет", BitmapDrawable(), "", calories = 450, operation = Operation.Avg),
                CalorieComponent(6, "Колбаса Варёная, ломтик", BitmapDrawable(), "", calories = 40, operation = Operation.Sum),
                CalorieComponent(15, "Колбаса Копчёная, ломтик", BitmapDrawable(), "", calories = 80, operation = Operation.Sum),
                QuantityComponent(7, "Варёный", BitmapDrawable(), "", multiplier = 0.5),
                CalorieComponent(8, "Жареный", BitmapDrawable(), "", calories = 200, operation = Operation.Avg),
                CalorieComponent(9, "Сметана (ложка)", BitmapDrawable(), "", calories = 35, operation = Operation.Sum),
                CalorieComponent(10, "Майонез (ложка)", BitmapDrawable(), "", calories = 85, operation = Operation.Sum),
                CalorieComponent(11, "Хлеб черный (кусок)", BitmapDrawable(), "", calories = 80, operation = Operation.Sum),
                CalorieComponent(12, "Хлеб белый (кусок)", BitmapDrawable(), "", calories = 120, operation = Operation.Sum),
                QuantityComponent(13, "Половина порции", getIcon(R.drawable.half), "", multiplier = 0.5),
                QuantityComponent(14, "Двойная порция", getIcon(R.drawable.double_portion), "", multiplier = 2.0)
        )
    }

    private fun getFruitComponents(): List<FoodComponent> {
        return listOf(
                CalorieComponent(1, "Цитрусовые", getIcon(R.drawable.citrus), "", calories = 65, operation = Operation.Sum),
                CalorieComponent(2, "Яблоко", getIcon(R.drawable.apple), "", isDefault = true, calories = 75, operation = Operation.Sum),
                CalorieComponent(3, "Банан", getIcon(R.drawable.banana), "", calories = 144, operation = Operation.Sum),
                CalorieComponent(4, "Виноград", getIcon(R.drawable.grape), "", calories = 101, operation = Operation.Sum),
                CalorieComponent(5, "Хурма", BitmapDrawable(), "", calories = 101, operation = Operation.Sum),
                CalorieComponent(6, "Гранат", getIcon(R.drawable.granade), "", calories = 108, operation = Operation.Sum),
                QuantityComponent(7, "Половина порции", getIcon(R.drawable.half), "", multiplier = 0.5),
                QuantityComponent(8, "Двойная порция", getIcon(R.drawable.double_portion), "", multiplier = 2.0)
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
                CalorieComponent(4, "Крем, сыр", BitmapDrawable(), "", calories = 20, operation = Operation.Sum),
                CalorieComponent(5, "Макароны, клецки", BitmapDrawable(), "", calories = 40, operation = Operation.Avg),
                CalorieComponent(6, "Мясо, рыба", BitmapDrawable(), "", calories = 50, operation = Operation.Avg),
                CalorieComponent(7, "Жирный бульон", BitmapDrawable(), "", calories = 60, operation = Operation.Avg),
                CalorieComponent(8, "Зажарка, сало", BitmapDrawable(), "", calories = 100, operation = Operation.Avg),
                CalorieComponent(9, "Сметана (ложка)", BitmapDrawable(), "", calories = 35, operation = Operation.Sum),
                CalorieComponent(10, "Майонез (ложка)", BitmapDrawable(), "", calories = 85, operation = Operation.Sum),
                CalorieComponent(11, "Хлеб черный (кусок)", BitmapDrawable(), "", calories = 80, operation = Operation.Sum),
                CalorieComponent(12, "Хлеб белый (кусок)", BitmapDrawable(), "", calories = 120, operation = Operation.Sum),
                QuantityComponent(13, "Половина порции", getIcon(R.drawable.half), "", multiplier = 0.5),
                QuantityComponent(14, "Двойная порция", getIcon(R.drawable.double_portion), "", multiplier = 2.0)
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
                QuantityComponent(11, "Половина порции", getIcon(R.drawable.half), "", multiplier = 0.5),
                QuantityComponent(12, "Двойная порция", getIcon(R.drawable.double_portion), "", multiplier = 2.0)
        )
    }

    private fun getSweetComponents(): List<FoodComponent> {
        return listOf(
                CalorieComponent(1, "Плитка Шоколада", BitmapDrawable(), "", calories = 500, operation = Operation.Sum),
                CalorieComponent(2, "Пастила, зефир, мармелад", BitmapDrawable(), "", calories = 65, operation = Operation.Sum),
                CalorieComponent(3, "Карамель, леденцы", BitmapDrawable(), "", calories = 57, operation = Operation.Sum),
                CalorieComponent(4, "Мороженое", BitmapDrawable(), "", calories = 168, operation = Operation.Sum),
                CalorieComponent(5, "Печенье, крекер", BitmapDrawable(), "", calories = 80, operation = Operation.Sum),
                CalorieComponent(6, "Шок. конфета", BitmapDrawable(), "", isDefault = true, calories = 100, operation = Operation.Sum),
                CalorieComponent(7, "Пирожное", BitmapDrawable(), "", calories = 500, operation = Operation.Sum),
                QuantityComponent(8, "Половина порции", getIcon(R.drawable.half), "", multiplier = 0.5),
                QuantityComponent(9, "Двойная порция", getIcon(R.drawable.double_portion), "", multiplier = 2.0),
                QuantityComponent(9, "Тройная порция", BitmapDrawable(), "", multiplier = 3.0)
        )
    }

    private fun getSecondCourseComponents(): List<FoodComponent> {
        return listOf(
                CalorieComponent(1, "Цветная капуста", BitmapDrawable(), "", calories = 80, operation = Operation.Avg),
                CalorieComponent(2, "Фасоль стручковая", BitmapDrawable(), "", calories = 90, operation = Operation.Avg),
                CalorieComponent(3, "Овощи", BitmapDrawable(), "", calories = 100, operation = Operation.Avg),
                CalorieComponent(4, "Картофель", BitmapDrawable(), "", calories = 150, operation = Operation.Avg),
                CalorieComponent(5, "Каша, Макароны, Пюре", BitmapDrawable(), "", isDefault = true, calories = 230, operation = Operation.Avg),
                CalorieComponent(6, "Каша на молоке", BitmapDrawable(), "", calories = 330, operation = Operation.Avg),
                CalorieComponent(7, "Блины", BitmapDrawable(), "", calories = 500, operation = Operation.Avg),
                CalorieComponent(7, "Бобы", BitmapDrawable(), "", calories = 700, operation = Operation.Avg),
                CalorieComponent(7, "Жареное", BitmapDrawable(), "", calories = 200, operation = Operation.Sum),
                CalorieComponent(7, "Фри", BitmapDrawable(), "", calories = 500, operation = Operation.Sum),
                QuantityComponent(8, "Половина порции", getIcon(R.drawable.half), "", multiplier = 0.5),
                QuantityComponent(9, "Двойная порция", getIcon(R.drawable.double_portion), "", multiplier = 2.0),
                QuantityComponent(9, "Тройная порция", BitmapDrawable(), "", multiplier = 3.0)
        )
    }

    private fun getDrinksComponents(): List<FoodComponent> {

        return listOf(
                CalorieComponent(1, "Кофе", BitmapDrawable(),  "", isDefault = true, calories = 10, operation = Operation.Sum),
                CalorieComponent(2, "Чай", BitmapDrawable(), "", calories = 6, operation = Operation.Sum),
                CalorieComponent(3, "Молоко/сливки", BitmapDrawable(), "", isDefault = true, calories = 15, operation = Operation.Sum),
                CalorieComponent(4, "Сахар/мёд", BitmapDrawable(), "", calories = 37, operation = Operation.Sum),
                CalorieComponent(5, "Фруктовый Сок", BitmapDrawable(), "", calories = 100, operation = Operation.Sum),
                CalorieComponent(6, "Овощной сок", BitmapDrawable(), "", calories = 40, operation = Operation.Sum),
                CalorieComponent(7, "Газировка", getIcon(R.drawable.soda), "", calories = 80, operation = Operation.Sum),
                CalorieComponent(8, "Пиво", getIcon(R.drawable.beer), "", calories = 215, operation = Operation.Sum),
                CalorieComponent(9, "Вино", getIcon(R.drawable.wine), "", calories = 113, operation = Operation.Sum),
                CalorieComponent(10, "Крепкий алкоголь", BitmapDrawable(), "", calories = 114, operation = Operation.Sum),
                QuantityComponent(11, "Половина порции", getIcon(R.drawable.half), "", multiplier = 0.5),
                QuantityComponent(12, "Двойная порция", getIcon(R.drawable.double_portion), "", multiplier = 2.0)
        )
    }
}