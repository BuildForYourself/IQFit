package com.buildforyourself.iqfit.calc

abstract class Formula(
        open var weight: Double,
        open var activityType: Formula.ActivityTypes
) {
    enum class ActivityTypes(val typeId : Int) {
        /**
         * Usual healthy person with sitting way of life.
         */
        OFFICE_PLANKTON(1),
        /**
         * Physical activity 3-5 times per week.
         */
        SPORTY(2),
        /**
         * Active everyday work, weight trainings (i.e. Athletes) less then 15 hours per week.
         */
        MORE_SPORTY(3),
        /**
         * Serious weight trainings 15-20 hours per week.
         */
        ATHLETE(4),
        /**
         * Weight extreme trainings.
         */
        HULK(5)
    }

    internal abstract fun calculate():Double

}