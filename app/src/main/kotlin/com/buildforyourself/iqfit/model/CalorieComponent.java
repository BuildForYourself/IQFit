package com.buildforyourself.iqfit.model;

import android.graphics.drawable.Drawable;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.builder.Condition;

/**
 * Created by Ilya on 17.04.2016.
 */
@Table(databaseName = IQFitDatabase.NAME)
public class CalorieComponent extends FoodComponent {
    @Column
    public int calories;

    @Column
    public Operation operation;

    public CalorieComponent() {
        super();
    }

    public CalorieComponent(String name, Drawable icon, String group, boolean isSingle,
                            boolean isDefault, boolean isSelected,
                            int calories, Operation operation) {
        super(name, icon, group, isSingle, isDefault, isSelected);
        this.calories = calories;
        this.operation = operation;
    }
}
