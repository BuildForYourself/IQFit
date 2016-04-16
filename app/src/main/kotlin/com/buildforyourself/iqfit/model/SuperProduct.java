package com.buildforyourself.iqfit.model;

import com.buildforyourself.iqfit.data.SuperDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by Ilya on 16.04.2016.
 */

@Table(name = SuperDatabase.NAME, database = SuperDatabase.class)
public class SuperProduct extends BaseModel {

    // All tables must have a least one primary key
    @Column
    @PrimaryKey
    public String name;

    // By default the column name is the field name
    @Column
    public int randomNumber;

    public SuperProduct() {}
}