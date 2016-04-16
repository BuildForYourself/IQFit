package com.buildforyourself.iqfit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import com.buildforyourself.iqfit.data.FakeDataProvider
import com.buildforyourself.iqfit.data.IDataProvider
import org.jetbrains.anko.*

class FoodComponentsActivity  : AppCompatActivity() {
    var dataProvider : IDataProvider = FakeDataProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val categoryId = intent.extras.getInt("categoryId")
        val category = dataProvider.loadFoodCategories().find { it.id == categoryId } ?: return

        scrollView() {
            verticalLayout() {
                var i: Int = 0
                while (i < category.components.count()) {
                    linearLayout() {
                        orientation = LinearLayout.HORIZONTAL
                        weightSum = 2f
                        for (j in 0..1) {
                            if (i == category.components.count())
                                break

                            val component = category.components[i]
                            toggleButton() {
                                textOff = component.name
                                textOn = component.name
                                text = component.name
                                textSize = 14f
                                onClick {
                                    component.isSelected = !component.isSelected
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