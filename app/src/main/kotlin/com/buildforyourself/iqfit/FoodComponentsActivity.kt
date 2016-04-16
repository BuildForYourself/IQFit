package com.buildforyourself.iqfit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import com.buildforyourself.iqfit.data.DataProviderFactory
import com.buildforyourself.iqfit.model.FoodCategory
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar

class FoodComponentsActivity() : AppCompatActivity() {
    val dataProvider = DataProviderFactory.instance.dataProvider
    private var currentCategory: FoodCategory? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val categoryId = intent.extras.getInt("categoryId")
        val category = dataProvider.loadFoodCategories().find { it.id == categoryId } ?: return

        currentCategory = category

        verticalLayout() {
            setSupportActionBar(toolbar() {
                backgroundColor = R.color.colorPrimary
                title = getString(R.string.food_components_title)
            })
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
                                    isChecked = component.isDefault
                                    onClick {
                                        component.isSelected = !component.isSelected
                                        if (component.isSingle)
                                            category.selectSingleComponent(component)
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.component, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        val c = currentCategory
        if (id == R.id.action_settings && c != null) {
            dataProvider.saveDefaultComponents(c)
        }

        return super.onOptionsItemSelected(item)
    }
}