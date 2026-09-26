package com.sushant.pmpstudy

data class ExcelLabDeepDive(
    val scenario: String,
    val steps: String,
    val sample: String,
    val validation: String,
    val challenge: String
)

val excelLabDeepDives = listOf(
ExcelLabDeepDive(
"""You are reviewing salary positioning for an annual compensation cycle. Management wants to know which employees are low, near, or high relative to their grade midpoint.""",
"""1. Put Employee ID, Current Salary and Range Midpoint in columns A:C.
2. In D2 enter =B2/C2.
3. Format D as Number with 2 decimals, not percentage, if your organization expresses compa-ratio as 0.90/1.00.
4. Copy the formula down.
5. Add a band in E2:
=IFS(D2<0.85,"Below 0.85",D2<0.95,"0.85-0.94",D2<=1.05,"Near Midpoint",D2<=1.15,"1.06-1.15",TRUE,"Above 1.15")
6. Build a PivotTable counting employees by Grade and Compa Band.
7. Add Performance as a filter.""",
"""Employee A: Salary ₹18,00,000; Midpoint ₹20,00,000 → 0.90
Employee B: Salary ₹22,00,000; Midpoint ₹20,00,000 → 1.10
Employee C: Salary ₹16,00,000; Midpoint ₹20,00,000 → 0.80""",
"""Control checks:
• Midpoint must never be zero.
• Count blanks/errors.
• Compare average compa by grade.
• Investigate, do not automatically correct, values below 0.85 or above 1.15.""",
"""Create 30 employees across G6–G8. Make at least five high performers below 0.90 and three new hires below 0.90. Explain why the same compa-ratio can lead to different decisions."""
),
ExcelLabDeepDive(
"""You need to show managers where employees sit from salary-range minimum to maximum. Range penetration is helpful because it uses the full salary range.""",
"""1. Columns: Salary, Minimum, Maximum.
2. Formula:
=(Salary-Minimum)/(Maximum-Minimum)
3. Format as Percentage.
4. Do not cap results at 0% or 100%; below-range and above-range values are useful exceptions.
5. Add conditional formatting:
<0% = exception
0–25% = lower range
25–75% = central range
75–100% = upper range
>100% = exception
6. Compare range penetration with compa-ratio side by side.""",
"""Salary ₹18,00,000
Minimum ₹15,00,000
Maximum ₹25,00,000

=(18-15)/(25-15)=30%

If midpoint is ₹20,00,000, compa-ratio is 0.90.""",
"""Check that Maximum > Minimum for every row. A zero denominator indicates bad range data. Filter values below 0% and above 100% before presenting results.""",
"""Find five employees with similar compa-ratios but different range penetration. Explain how range design creates the difference."""
),
ExcelLabDeepDive(
"""You want to understand each employee’s external market position. Market Ratio compares current salary with the chosen market reference.""",
"""1. Add Market P50 to each employee using XLOOKUP.
2. Formula:
=CurrentSalary/MarketP50
3. Add a Market Band using IFS.
4. Keep Market Ratio separate from Compa-Ratio.
5. Create a scatter or table showing Market Ratio vs Compa-Ratio.
6. Identify four combinations:
Low Compa + Low Market
Low Compa + Normal Market
Normal Compa + Low Market
High Compa + High Market
Each combination points to a different diagnostic question.""",
"""Salary ₹18,00,000
Range midpoint ₹19,00,000 → Compa 0.95
Market P50 ₹21,00,000 → Market Ratio 0.86

The employee is reasonably positioned to internal midpoint but the structure itself may be below market.""",
"""Calculate Range Midpoint / Market P50 by job. If employee market ratio is low but midpoint market ratio is close to 1.00, the issue may be individual positioning. If both are low, structure may need review.""",
"""Create 15 jobs with different midpoint and P50 relationships. Classify each issue as Individual, Structure, Both or No Immediate Issue."""
),
ExcelLabDeepDive(
"""Your employee file contains Grade Code, while salary ranges sit in a separate table. You need reliable automatic matching.""",
"""1. Convert both datasets to Excel Tables (Ctrl+T).
2. Name them Employees and Ranges.
3. In Employees[Midpoint]:
=XLOOKUP([@Grade],Ranges[Grade],Ranges[Midpoint],"Not Found")
4. Repeat for Minimum and Maximum.
5. Create a control cell:
=COUNTIF(Employees[Midpoint],"Not Found")
6. Investigate any unmatched grade.
7. Check duplicates:
=COUNTIF(Ranges[Grade],[@Grade])
A value above 1 means the lookup table key is not unique.
8. Spot-check at least 5 records manually.""",
"""Ranges:
G6 | 15,00,000 | 18,00,000 | 21,00,000
G7 | 18,00,000 | 22,00,000 | 26,00,000
G8 | 22,00,000 | 27,00,000 | 32,00,000

Employee with G7 should retrieve ₹22,00,000 midpoint.""",
"""Do not replace “Not Found” with 0. That hides data-quality issues. Final control count should be zero or every remaining mismatch should be documented.""",
"""Add a second lookup table for Market P50 by Survey Job Code. Build controls for both Grade and Market matches."""
),
ExcelLabDeepDive(
"""Compensation leadership asks: “How many G7 employees are below 0.90 compa? How many are high performers? How many are in Pune?” COUNTIFS answers population questions quickly.""",
"""1. Convert data to a Table.
2. Count G7 below 0.90:
=COUNTIFS(Employees[Grade],"G7",Employees[Compa],"<0.9")
3. Add location:
=COUNTIFS(Employees[Grade],"G7",Employees[Compa],"<0.9",Employees[Location],"Pune")
4. Add performance:
=COUNTIFS(Employees[Compa],"<0.9",Employees[Performance],"Exceeds")
5. Put criteria in cells instead of hard-coding when building a reusable report.
6. Reconcile subgroup counts to total headcount where appropriate.""",
"""Headcount 500
Below 0.90 = 82
Of those, High Performers = 19
Of those, recent promotions = 35

The 82-person headline becomes more useful after segmentation.""",
"""Create a check:
=ROWS(Employees[Employee ID])
Compare with sum of mutually exclusive segmentation counts. If they do not reconcile, your categories overlap or miss records.""",
"""Create five management questions and answer each with COUNTIFS. Then convert the most useful one into a dashboard KPI."""
),
ExcelLabDeepDive(
"""Finance wants the annual salary cost of proposed increases by function, grade and business unit. SUMIFS lets you calculate the cost without manually filtering.""",
"""1. Calculate Increase Amount:
=CurrentSalary*IncreasePct
2. Total Engineering increase:
=SUMIFS(Employees[IncreaseAmount],Employees[Function],"Engineering")
3. Total for G7 Engineering:
=SUMIFS(Employees[IncreaseAmount],Employees[Function],"Engineering",Employees[Grade],"G7")
4. Compare to budget:
=EngineeringIncrease/EngineeringEligiblePayroll
5. Reconcile all functions to company total.
6. Separate Merit, Promotion and Market Adjustment into different amount columns.""",
"""Engineering eligible payroll ₹50 crore.
Merit cost ₹2.8 crore.
Promotion cost ₹0.25 crore.
Market correction ₹0.15 crore.
Total ₹3.2 crore → 6.4% of eligible payroll.""",
"""Sum of business-unit increase costs must equal company total. If not, check blanks and inconsistent labels. Weighted increase must be Total Increase / Eligible Payroll, not average of individual percentages.""",
"""Build a summary table by Function with Eligible Payroll, Merit Cost, Promotion Cost, Market Cost, Total Cost and Weighted Increase %."""
),
ExcelLabDeepDive(
"""You are running the annual salary review. The workbook must calculate new salary correctly, separate increase types and show budget variance.""",
"""1. Current Salary in B.
2. Merit % in C:
Merit Amount = B2*C2
3. Promotion % in D:
Promotion Amount = B2*D2
4. Market Adjustment Amount in E.
5. Total Increase = Merit + Promotion + Market.
6. New Salary = Current Salary + Total Increase.
7. Final Increase % = Total Increase / Current Salary.
8. Budget Variance = Total Increase Amount - Approved Budget Amount.
9. Add range check against new range maximum.""",
"""Salary ₹20,00,000
Merit 5% = ₹1,00,000
Promotion 8% = ₹1,60,000
Market Adjustment ₹40,000
Total increase ₹3,00,000
New salary ₹23,00,000
Final increase 15%""",
"""Check:
• New Salary = Old Salary + Total Increase
• Total increase cost reconciles to budget dashboard
• No formulas use rounded displayed values
• Promotion effective date treatment matches company policy""",
"""Create a 100-employee cycle with a 6.5% total budget. Recalibrate merit guidelines until total recurring cost is within ±0.05 percentage points of budget."""
),
ExcelLabDeepDive(
"""The company’s bonus plan combines base salary, target opportunity, company performance and individual performance. You need a transparent payout calculator.""",
"""1. Base Salary.
2. Target Bonus %.
3. Target Bonus Amount = Base*Target%.
4. Company Factor as decimal.
5. Individual Factor as decimal.
6. Raw Payout = Target Bonus Amount*Company Factor*Individual Factor.
7. Add maximum cap:
=MIN(RawPayout,TargetBonusAmount*MaxPayoutFactor)
8. Add eligibility/proration if needed.
9. Model threshold, target and maximum scenarios.""",
"""Base ₹30,00,000
Target 15% → ₹4,50,000
Company factor 110%
Individual factor 120%
Raw payout = ₹5,94,000

If cap is 150% of target, max payout = ₹6,75,000, so ₹5,94,000 remains uncapped.""",
"""Test zeros, maximums and ineligible employees. Reconcile total payout to Finance accrual assumptions. Make sure factors are stored as percentages/decimals consistently.""",
"""Build a payout curve with Company Performance from 50% to 150%. Plot resulting payout for an employee with ₹4.5 lakh target incentive."""
),
ExcelLabDeepDive(
"""A large compensation file needs clear review flags so analysts focus on high-risk cases instead of scanning every row manually.""",
"""1. Start with mutually prioritized rules.
2. Example:
=IFS(
[@DataIssue]="Yes","Data Issue",
[@Salary]<[@Minimum],"Below Range",
[@Salary]>[@Maximum],"Above Range",
AND([@CriticalSkill]="Yes",[@MarketRatio]<0.9),"Critical Skill Below Market",
AND([@Performance]="Exceeds",[@Compa]<0.9),"High Performer Low in Range",
TRUE,"Normal")
3. Put data-quality rules first so bad data does not become a pay recommendation.
4. Summarize flags with COUNTIF/PivotTable.
5. Add Estimated Correction Cost only after review.""",
"""500 employees:
8 Data Issues
12 Below Range
7 Above Range
15 Critical Skill Below Market
22 High Performer Low in Range
436 Normal

The priority list is 64, not all 500.""",
"""Ensure each row gets one primary flag if your reporting assumes mutually exclusive categories. If you need multiple flags, use separate Boolean columns instead of one text category.""",
"""Add secondary flags for Recent Promotion, New Hire and Retention Risk. Compare primary and secondary flags to avoid overreacting to explainable low positioning."""
),
ExcelLabDeepDive(
"""A PivotTable dashboard should help management answer decisions quickly: where are salary risks, where is budget going, and which segments need attention?""",
"""Build separate pivots:
1. Headcount and Average Compa by Grade.
2. Payroll and Weighted Increase by Function.
3. Market Ratio by Job Family.
4. Review Flags by Business Unit.
5. Override Count/Cost by Manager.

Then create a Dashboard sheet:
• KPI cards: Headcount, Payroll, Avg Compa, Below Range, Above Range, Budget Used
• 3–4 charts only
• Slicers for Function, Location, Grade
• Last Refresh date
• Control status

Write one insight below each chart.""",
"""Example insight:
“Cybersecurity average market ratio is 0.88, but the gap is concentrated in G6–G7 employees hired before 2025. A targeted cohort review is more appropriate than a function-wide increase.”""",
"""Validate Dashboard totals against the source table after every refresh. Add a visible control box:
Headcount Reconciles? YES/NO
Payroll Reconciles? YES/NO
Missing Ranges = 0?
Missing Market Matches = 0?""",
"""Create an executive dashboard and a separate analyst dashboard. The executive version should fit on one screen and focus on decisions; the analyst version can contain drill-down detail."""
)
)

val capstoneGuide = """
CAPSTONE: 500-EMPLOYEE COMPENSATION REVIEW

Objective
Act as the Compensation Partner for a 500-employee technology company. The approved annual salary-increase budget is 6.5%. Leadership wants stronger retention of critical talent without creating uncontrolled recurring cost.

Part 1 — Build the employee dataset
Required columns:
Employee ID
Business Unit
Function
Job Family
Job Title
Grade
Location
Gender
Hire Date
Years in Role
Performance
Current Salary
Range Minimum
Range Midpoint
Range Maximum
Market P25
Market P50
Market P75
Critical Skill
Promotion Proposed
Target Bonus %

Part 2 — Add calculations
Compa-Ratio
Range Penetration
Market Ratio
Peer Median
Variance to Peer Median
Review Flag
Merit Guideline %
Promotion %
Market Adjustment
Manager Override
Final Increase %
Increase Amount
New Salary
New Compa-Ratio
Target Bonus Amount
Estimated Total Cash

Part 3 — Run controls
Headcount reconciles to 500
No duplicate Employee IDs
No missing grades
No missing range matches
No missing market match unless documented
Total payroll reconciles
Increase budget reconciles
All above-range and below-range cases reviewed

Part 4 — Analyze
Find:
1. Employees below range minimum.
2. Employees above range maximum.
3. High performers below 0.90 compa.
4. Critical skills below 0.90 market ratio.
5. Salary compression between recent hires and experienced incumbents.
6. Promotion cases landing below new range.
7. Manager overrides above guideline.
8. Possible pay-equity patterns requiring deeper review.
9. Job families where range midpoint is materially below market P50.
10. Business units using disproportionate budget.

Part 5 — Budget
Model a 6.5% total recurring increase budget.
Separate:
Merit
Promotion
Market Correction
Other
Show annualized cost and current-year cost.

Part 6 — Executive recommendation
Prepare one page with:
Top 3 findings
Top 3 risks
Recommended actions
Total annualized cost
Alternative option
Decision required

Completion standard
You are finished only when the workbook reconciles, every flagged population has a documented rationale, and the executive recommendation can be understood without opening the employee-level data.
""".trimIndent()
