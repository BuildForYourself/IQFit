package com.buildforyourself.iqfit.calc

abstract class Formula(
        open var weight: Double,
        open var activityType: Formula.ActivityTypos
) {
    enum class ActivityTypos {
        /**
         * Usual healthy person with sitting way of life.
         */
        OFFICE_PLANKTON,
        /**
         * Physical activity 3-5 times per week.
         */
        SPORTY,
        /**
         * Active everyday work, weight trainings (i.e. Athletes) less then 15 hours per week.
         */
        MORE_SPORTY,
        /**
         * Serious weight trainings 15-20 hours per week.
         */
        ATHLETE,
        /**
         * Weight extreme trainings.
         */
        HULK
    }

    internal abstract fun calculate():Double

}