package com.buildforyourself.iqfit.model;

import android.graphics.drawable.Drawable;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Table;

/**
 * Created by Ilya on 17.04.2016.
 */
@Table(databaseName = IQFitDatabase.NAME)
public class QuantityComponent extends FoodComponent {
    @Column
    public double multiplier;

    public QuantityComponent() {
        super();
    }

    public QuantityComponent(String name, Drawable icon, String group, boolean isSingle,
                            boolean isDefault, boolean isSelected,
                            double multiplier) {
        super(name, icon, group, isSingle, isDefault, isSelected);
        this.multiplier = multiplier;
    }
}
