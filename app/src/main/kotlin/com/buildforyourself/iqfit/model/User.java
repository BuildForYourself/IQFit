package com.buildforyourself.iqfit.model;

import com.buildforyourself.iqfit.calc.Formula.ActivityTypos;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by Ilya on 17.04.2016.
 */
@Table(databaseName = IQFitDatabase.NAME)
public class User extends BaseModel {
    @Column
    @PrimaryKey(autoincrement = true)
    public int id;

    @Column
    public int age;

    @Column
    public int height;

    @Column
    public double weight;

    @Column
    public double fatPercent;

    @Column
    public ActivityTypos activityType;
}
