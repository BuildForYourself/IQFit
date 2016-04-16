package com.buildforyourself.iqfit.model;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Ilya on 16.04.2016.
 */
@Database(name = IQFitDatabase.NAME, version = IQFitDatabase.VERSION)
public class IQFitDatabase {

    public static final String NAME = "IQFitDatabase";
    public static final int VERSION = 1;
}
