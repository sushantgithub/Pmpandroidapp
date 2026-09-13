package com.sushant.pmpstudy.domain

data class EarnedValueResult(
    val sv: Double,
    val cv: Double,
    val spi: Double,
    val cpi: Double,
    val eacTypical: Double,
    val etc: Double,
    val vac: Double,
    val tcpi: Double
)

object EarnedValueMath {
    fun compute(ev: Double, pv: Double, ac: Double, bac: Double): EarnedValueResult {
        require(pv != 0.0) { "PV must not be zero" }
        require(ac != 0.0) { "AC must not be zero" }
        val remainingBudget = bac - ac
        require(remainingBudget != 0.0) { "BAC − AC must not be zero" }
        val sv = ev - pv
        val cv = ev - ac
        val spi = ev / pv
        val cpi = ev / ac
        val eacTypical = bac / cpi
        val etc = eacTypical - ac
        val vac = bac - eacTypical
        val tcpi = (bac - ev) / remainingBudget
        return EarnedValueResult(sv, cv, spi, cpi, eacTypical, etc, vac, tcpi)
    }

    fun communicationChannels(people: Int): Int {
        require(people >= 1)
        return people * (people - 1) / 2
    }
}
