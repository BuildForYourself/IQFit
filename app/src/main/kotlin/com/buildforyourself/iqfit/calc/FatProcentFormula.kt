package com.buildforyourself.iqfit.calc

class FatProcentFormula(
        override var weight: Double,
        override var activityType: Formula.ActivityTypes,
        var fatProcent: Double
) : Formula(weight, activityType) {

    override fun calculate(): Double {
        return countCalories(weight, activityType, fatProcent)
    }

    fun countCalories(weight: Double, activityType: ActivityTypes, fatProcent: Double): Double {
        /**
         * LBM - lean body mass
         */
        var LBM = (weight * (100 - fatProcent)) / 100
        /**
         * BMR - basic metabolic rate
         */
        var BMR = 370 + (21.6 * LBM)
        when (activityType) {
            ActivityTypes.OFFICE_PLANKTON -> return BMR * 1.2
            ActivityTypes.SPORTY -> return BMR * 1.4
            ActivityTypes.MORE_SPORTY -> return BMR * 1.6
            ActivityTypes.ATHLETE -> return BMR * 1.8
            ActivityTypes.HULK -> return BMR * 2
        }
    }
}
