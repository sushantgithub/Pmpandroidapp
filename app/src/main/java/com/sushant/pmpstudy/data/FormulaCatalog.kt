package com.sushant.pmpstudy.data

object FormulaCatalog {
    val all: List<Formula> = listOf(
        Formula("Earned Value", "EV = % complete × BAC", "Budgeted value of work actually finished. Anchor for every other EVM number."),
        Formula("Planned Value", "PV = planned % complete × BAC", "What the baseline said you should have earned by now."),
        Formula("Schedule Variance", "SV = EV − PV", "Negative = behind; positive = ahead; zero = on the time baseline."),
        Formula("Cost Variance", "CV = EV − AC", "Negative = over budget for work performed."),
        Formula("SPI", "SPI = EV / PV", "< 1 behind; > 1 ahead; 1.0 on pace."),
        Formula("CPI", "CPI = EV / AC", "< 1 over cost; > 1 under cost; 1.0 on budget."),
        Formula("EAC (typical)", "EAC = BAC / CPI", "Use when current cost efficiency is likely to continue."),
        Formula("EAC (remain at plan)", "EAC = AC + (BAC − EV)", "Use when remaining work should behave like the original estimate."),
        Formula("EAC (estimate broken)", "EAC = AC + bottom-up ETC", "Use when the original plan is no longer credible."),
        Formula("ETC", "ETC = EAC − AC", "How much more you expect to spend."),
        Formula("VAC", "VAC = BAC − EAC", "Positive = expect to finish under budget."),
        Formula("TCPI to BAC", "TCPI = (BAC − EV) / (BAC − AC)", "> 1 means remaining work must be more efficient than the past."),
        Formula("TCPI to EAC", "TCPI = (BAC − EV) / (EAC − AC)", "Efficiency needed to hit a new forecast."),
        Formula("PERT expected", "tE = (O + 4M + P) / 6", "Beta-weighted three-point duration or cost."),
        Formula("Triangular expected", "tE = (O + M + P) / 3", "Equal-weight three-point."),
        Formula("PERT std. deviation", "σ = (P − O) / 6", "Spread of a three-point estimate."),
        Formula("PERT variance", "σ² = ((P − O) / 6)²", "Add variances along a path when combining independent estimates."),
        Formula("Total float", "TF = LS − ES  or  LF − EF", "Slip allowed before the project finish moves."),
        Formula("Communication channels", "n(n − 1) / 2", "n is the number of people who communicate with each other."),
        Formula("EMV", "EMV = probability × monetary impact", "Used on decision trees and quantitative risk."),
        Formula("PTA (FPIF)", "PTA = ((ceiling − target price) / buyer share) + target cost", "Cost point where the seller absorbs all further overrun.")
    )
}
