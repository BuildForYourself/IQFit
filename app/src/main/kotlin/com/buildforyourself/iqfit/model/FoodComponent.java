package com.buildforyourself.iqfit.model;

import android.graphics.drawable.Drawable;

import com.buildforyourself.iqfit.calc.Formula;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by Ilya on 17.04.2016.
 */
@Table(databaseName = IQFitDatabase.NAME)
public class FoodComponent extends BaseModel {
    @Column
    @PrimaryKey(autoincrement = true)
    public int id;

    @Column
    public String name;

    @Column
    public Drawable icon;

    @Column
    public String group;

    @Column
    public boolean isSingle;

    @Column
    public boolean isDefault;

    @Column
    public boolean isSelected;

    public FoodComponent() {
        super();
    }

    public FoodComponent(String name, Drawable icon, String group, boolean isSingle, boolean isDefault, boolean isSelected) {
        super();
        this.id = 0;
        this.name = name;
        this.icon = icon;
        this.group = group;
        this.isSingle = isSingle;
        this.isDefault = isDefault;
        this.isSelected = isSelected;
    }
}