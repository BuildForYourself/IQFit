package com.buildforyourself.iqfit.calc

import com.buildforyourself.iqfit.data.DataProviderFactory
import com.buildforyourself.iqfit.model.*
import java.util.*

class FoodCalculator()
{
    fun calculateFood(category: FoodCategory, foodComponents: List<FoodComponent>): Food
    {
        var groups = mutableListOf<String>()

        for (foodComponent in foodComponents)
        {
            if (!groups.contains(foodComponent.group))
                groups.add(foodComponent.group)
        }

        var result = 0

        for (group in groups)
        {
            var groupMultiplier = 0.0;
            var avgCount = 0
            var avgSum = 0;
            var calories = 0;
            for(foodComponent in foodComponents)
            {
                if (foodComponent.group != group)
                    continue
                if (foodComponent is QuantityComponent)
                {
                    groupMultiplier += foodComponent.multiplier
                    continue
                }
                if (foodComponent is CalorieComponent)
                {
                    if (foodComponent.operation == Operation.Sum)
                    {
                        calories += foodComponent.calories
                        continue
                    }
                    if (foodComponent.operation == Operation.Avg)
                    {
                        avgCount += 1
                        avgSum += foodComponent.calories
                    }
                }
            }
            if(groupMultiplier - 0.001 < 0)
                groupMultiplier=1.0

            if (avgSum == 0 || avgCount == 0)
                result += (calories * groupMultiplier).toInt()
            else
                result += (((avgSum / avgCount) + calories) * groupMultiplier).toInt()
        }

        val dataProvider = DataProviderFactory.instance.dataProvider
        val user = dataProvider.loadUser()
        var caloriesNorm = 2000
        if (user != null)
            caloriesNorm = user.caloriesNorm

        var percent = ((result.toDouble() / caloriesNorm.toDouble()) * 100.0).toInt()

        return Food(0, category, foodComponents, Date(), result, percent)
    }
}
