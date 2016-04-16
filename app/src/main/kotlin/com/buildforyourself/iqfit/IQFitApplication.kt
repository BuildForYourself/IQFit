package com.buildforyourself.iqfit

import android.app.*
import com.raizlabs.android.dbflow.config.FlowManager

/**
 * Created by Ilya on 16.04.2016.
 */

class IQFitApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FlowManager.init(this)
    }
}