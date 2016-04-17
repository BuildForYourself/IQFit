package com.buildforyourself.iqfit

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import com.buildforyourself.iqfit.calc.FoodCalculator
import com.buildforyourself.iqfit.data.DataProviderFactory
import com.buildforyourself.iqfit.model.FoodCategory
import com.buildforyourself.iqfit.util.floatingActionButton
import org.jetbrains.anko.*

class FoodComponentsActivity() : AppCompatActivity() {
    val dataProvider = DataProviderFactory.instance.dataProvider
    private var currentCategory: FoodCategory? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val categoryId = intent.extras.getInt("categoryId")
        val category = dataProvider.loadFoodCategories().find { it.id == categoryId } ?: return

        currentCategory = category


            frameLayout {
                setTheme(R.style.AppTheme)
                verticalLayout {
                    //setSupportActionBar(toolbar() {
                    //    backgroundColor = Color.parseColor(getString(R.color.colorPrimary))
                    //    title = getString(R.string.food_components_title)
                    //})
                    scrollView {
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
                                            //height = dip(60)
                                            setCompoundDrawablesWithIntrinsicBounds(null, component.icon, null, null)
                                            textOff = component.name
                                            textOn = component.name
                                            text = component.name
                                            textSize = 14f
                                            isChecked = component.isDefault
                                            component.isSelected = component.isDefault
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
                floatingActionButton {
                    background = ColorDrawable(Color.parseColor("#14ae21"))
                    setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_menu_send))
                    setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#14ae21")))
                    imageTintList = ColorStateList.valueOf(-1)
                    onClick {
                        addFoodAndExit(category)
                    }

                }.lparams {
                    margin = dip(16)

                    gravity = Gravity.END or Gravity.BOTTOM
                }
            }

    }

    fun addFoodAndExit(category: FoodCategory)
    {
        val components = category.components.filter { it.isSelected }
        var calculator = FoodCalculator()
        val food = calculator.calculateFood(category, components)
        dataProvider.saveFood(food)
        finish()
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