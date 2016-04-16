package com.buildforyourself.iqfit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import com.buildforyourself.iqfit.data.FakeDataProvider
import com.buildforyourself.iqfit.data.IDataProvider
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar

class FoodCategoriesActivity : AppCompatActivity() {
    var dataProvider : IDataProvider = FakeDataProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val categories = dataProvider.loadFoodCategories()

        verticalLayout(){
            setSupportActionBar(toolbar {
                backgroundColor = R.attr.colorPrimary
                popupTheme = R.style.AppTheme_PopupOverlay
            }.lparams {
                width=matchParent
                height= R.attr.actionBarSize
            })

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

                                val c = categories[i]
                                button() {
                                    setCompoundDrawablesWithIntrinsicBounds(null, c.icon, null, null)
                                    text = c.name
                                    textSize = 14f
                                    onClick {
                                        if (c.components != null && c.components.count() > 0)
                                            startActivity<FoodComponentsActivity>("categoryId" to 0)
                                    }
                                    onLongClick {
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
