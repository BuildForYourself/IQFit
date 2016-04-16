package com.buildforyourself.iqfit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.LinearLayout
import com.buildforyourself.iqfit.data.DataProviderFactory
import com.buildforyourself.iqfit.model.Food
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import java.util.*

class FoodCategoriesActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataProvider = DataProviderFactory.instance.dataProvider

        val categories = dataProvider.loadFoodCategories()

        verticalLayout(){
            toolbar(){
                title = getString(R.string.food_category_title)
            }.lparams {
                width = matchParent
                height = R.attr.actionBarSize
                gravity = Gravity.TOP
            }

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
                                    text = category.name
                                    textSize = 14f
                                    onClick {
                                        if (category.components.count() > 0)
                                            startActivity<FoodComponentsActivity>("categoryId" to category.id)
                                    }
                                    onLongClick {
                                        val defaultComponents = category.getDefaultComponents()
                                        val food = Food(0, category, defaultComponents, Date(), 0, 0)
                                        dataProvider.saveFood(food)
                                        toast("Значение по умолчанию добавлено")
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
