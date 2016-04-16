package com.buildforyourself.iqfit.calc

class WeightFormula(
        override var weight: Double,
        override var activityType: Formula.ActivityTypos
) : Formula(weight, activityType) {

    override fun calculate(): Double {
        return countCalories(weight, activityType)
    }

    fun countCalories(weight: Double, activityType: Formula.ActivityTypos): Double {

        when (activityType) {
            ActivityTypos.OFFICE_PLANKTON -> return 28.0 * weight
            ActivityTypos.SPORTY -> return 35.0 * weight
            ActivityTypos.MORE_SPORTY -> return 39.0 * weight
            ActivityTypos.ATHLETE -> return 45.0 * weight
            ActivityTypos.HULK -> return 50.0 * weight
        }
    }


}