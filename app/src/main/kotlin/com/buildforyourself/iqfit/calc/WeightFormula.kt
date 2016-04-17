package com.buildforyourself.iqfit.calc

class WeightFormula(
        override var weight: Double,
        override var activityType: Formula.ActivityTypes
) : Formula(weight, activityType) {

    override fun calculate(): Double {
        return countCalories(weight, activityType)
    }

    fun countCalories(weight: Double, activityType: Formula.ActivityTypes): Double {

        when (activityType) {
            ActivityTypes.OFFICE_PLANKTON -> return 28.0 * weight
            ActivityTypes.SPORTY -> return 35.0 * weight
            ActivityTypes.MORE_SPORTY -> return 39.0 * weight
            ActivityTypes.ATHLETE -> return 45.0 * weight
            ActivityTypes.HULK -> return 50.0 * weight
        }
    }


}