package com.buildforyourself.iqfit.util

import android.support.design.widget.FloatingActionButton
import android.view.ViewManager
import org.jetbrains.anko.custom.ankoView

fun ViewManager.floatingActionButton(init: FloatingActionButton.() -> Unit = {}) =
        ankoView({ FloatingActionButton(it) }, init)