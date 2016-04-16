package com.buildforyourself.iqfit.data;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Ilya on 16.04.2016.
 */
@Database(name = SuperDatabase.NAME, version = SuperDatabase.VERSION)
public class SuperDatabase {

    public static final String NAME = "SuperDatabase";
    public static final int VERSION = 4;
}