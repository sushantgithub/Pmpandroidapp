package com.sushant.pmpstudy.data

object FormulaCatalog {
    val all: List<Formula> = listOf(
        Formula("Communication channels", "n(n-1)/2", "Channels for n people including the PM"),
        Formula("PERT estimate", "(O + 4M + P) / 6", "Weighted average: Optimistic, Most likely, Pessimistic"),
        Formula("PERT std deviation", "(P - O) / 6", "Uncertainty range of the estimate"),
        Formula("SPI", "EV / PV", "Schedule Performance Index — >1 ahead, <1 behind"),
        Formula("CPI", "EV / AC", "Cost Performance Index — >1 under budget, <1 over"),
        Formula("CV", "EV - AC", "Cost Variance — positive means under budget"),
        Formula("SV", "EV - PV", "Schedule Variance — positive means ahead of schedule"),
        Formula("EAC (typical)", "BAC / CPI", "Estimate at Completion when current trend continues"),
        Formula("EAC (re-estimate)", "AC + ETC", "When original estimate is no longer valid"),
        Formula("ETC", "EAC - AC", "Estimate to Complete remaining work"),
        Formula("VAC", "BAC - EAC", "Variance at Completion — positive means under budget at end"),
        Formula("TCPI", "(BAC - EV) / (BAC - AC)", "Performance needed on remaining work to hit BAC"),
        Formula("Present Value", "FV / (1+r)^n", "Discount future value to today"),
        Formula("ROI", "(Gain - Cost) / Cost", "Return on Investment as a ratio"),
        Formula("Payback Period", "Investment / Annual Cash Flow", "Years to recover the investment"),
        Formula("Float / Slack", "LS - ES or LF - EF", "Schedule flexibility without delaying the project"),
        Formula("Free Float", "ES(successor) - EF(current)", "Delay allowed without affecting successor"),
    )
}