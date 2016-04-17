package com.buildforyourself.iqfit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import com.buildforyourself.iqfit.calc.FoodCalculator
import com.buildforyourself.iqfit.data.DataProviderFactory
import org.jetbrains.anko.*

class FoodCategoriesActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataProvider = DataProviderFactory.instance.dataProvider

        val categories = dataProvider.loadFoodCategories()

        verticalLayout() {
            setTheme(R.style.AppTheme)

            scrollView() {
                verticalLayout() {
                    var i: Int = 0
                    while (i < categories.count()) {
                        linearLayout() {
                            orientation = LinearLayout.HORIZONTAL
                            weightSum = 2f
                            for (j in 0..1) {
                                if (i == categories.count())
                                    break

                                val category = categories[i]
                                button() {
                                    setCompoundDrawablesWithIntrinsicBounds(null, category.icon, null, null)
                                    //setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.fastfood, 0, 0)

                                    text = category.name
                                    textSize = 14f
                                    onClick {
                                        if (category.components.count() > 0)
                                            startActivity<FoodComponentsActivity>("categoryId" to category.id)
                                    }
                                    onLongClick {
                                        val defaultComponents = category.getDefaultComponents()
                                        var calculator = FoodCalculator()
                                        var food = calculator.calculateFood(category, defaultComponents)
                                        dataProvider.saveFood(food)
                                        toast("Значение по умолчанию добавлено")
                                        finish()
                                        true
                                    }
                                }.lparams {
                                    weight = 1f
                                    width = 0
                                }
                                i++
                            }
                        }
                    }
                }
            }
        }
    }
}
