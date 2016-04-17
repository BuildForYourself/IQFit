package com.buildforyourself.iqfit

import android.app.*
import android.content.Context
import kotlin.properties.Delegates

/**
 * Created by Ilya on 16.04.2016.
 */

class IQFitApplication : Application {
    constructor() : super() {
        instance = this
    }

    companion object {
        var instance: Context by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
    }
}