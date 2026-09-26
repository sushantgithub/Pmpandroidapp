package com.sushant.pmpstudy

val deepDivesB = mapOf(
"6-0" to LessonDeepDive(
"""The annual salary-review budget is a recurring cost commitment. A 1% change in a large payroll can be material, so compensation professionals must understand both employee outcomes and financial impact.

The approved budget is usually a planning constraint, not a guarantee that every employee receives the same percentage. The analytical challenge is to distribute the budget according to the company’s compensation philosophy while controlling cost.""",
"""Build the cycle in this order:
1. Confirm eligible population and eligible salary basis.
2. Reconcile payroll with Finance.
3. Apply performance and salary-position guidelines.
4. Separate merit, promotion, market correction and other adjustments.
5. Calculate weighted-average increase, not simple average.
6. Compare cost with budget.
7. Recalibrate guidelines if necessary.
8. Add manager overrides with approval controls.
9. Reconcile final payroll and produce variance reporting.

Weighted increase = Total Increase Amount / Eligible Payroll.""",
"""Eligible payroll = ₹100 crore. Approved budget = 6.5%, so target increase cost is ₹6.5 crore.

A draft matrix produces ₹7.8 crore. The issue is not solved by reducing every employee by 1.3 percentage points. That could damage differentiation. Instead, model where the excess comes from: perhaps too many employees are classified Exceptional, promotion costs were included, or high compa employees are receiving generous guidelines.

Recalibration should preserve strategy while bringing weighted cost near budget.""",
"""Create a 200-employee salary-review model. Include Salary, Performance, Compa-Ratio, Guideline %, Increase Amount, Promotion %, Market Adjustment, Manager Override and Final Increase.

Create control cells:
Eligible Payroll
Merit Cost
Promotion Cost
Market Adjustment Cost
Total Cost
Weighted Increase %
Budget %
Variance ₹
Variance %""",
"""Common mistakes:
• Using simple average increase instead of payroll-weighted increase.
• Mixing promotion and merit cost.
• Forgetting ineligible employees in denominator logic.
• Publishing manager guidelines before modeling distribution.
• Treating budget as a target for each person instead of the population.""",
"""Useful language with Finance:
“The 6.5% budget is being managed on eligible payroll. Merit recommendations currently use 5.9%, promotions 0.4% and market corrections 0.2%, keeping total recurring cost within the approved 6.5% envelope.”"""
),
"6-1" to LessonDeepDive(
"""A merit matrix links performance and salary position to differentiated salary-increase guidance. Its purpose is to translate compensation philosophy into repeatable manager decisions.

A matrix should not become mechanical. It is a guideline calibrated to population distribution and budget. If performance ratings are inflated, the matrix cost will also inflate.""",
"""Design process:
1. Choose performance categories.
2. Choose salary-position bands, often compa-ratio.
3. Decide the intended differentiation between performance levels.
4. Decide how salary position affects opportunity.
5. Apply the matrix to actual employee population.
6. Calculate weighted cost.
7. Adjust cells until strategy and budget both work.
8. Test edge cases at range maximum or below minimum.
9. Define override rules.

Use employee-level simulation before launch.""",
"""Example matrix:
Exceptional: 9%, 8%, 6%, 4%
Exceeds: 7%, 6%, 5%, 3%
Meets: 5%, 4%, 3%, 2%
Needs Improvement: 0%, 0%, 0%, 0%

Columns represent compa bands <0.90, 0.90–1.00, 1.00–1.10, >1.10.

If 40% of employees are rated Exceptional, the matrix may exceed budget. That is a calibration issue involving both performance distribution and guideline design.""",
"""Create an Excel matrix using INDEX/MATCH or XLOOKUP logic. Feed 200 employees through it.

Then run three scenarios:
A. Normal performance distribution
B. Inflated high ratings
C. More employees above 1.10 compa

Compare weighted increase and explain why the same matrix costs differently.""",
"""Common mistakes:
• Setting matrix percentages before knowing rating distribution.
• Giving zero consideration to salary position.
• Allowing employees above range maximum to receive base increases automatically.
• Treating matrix output as guaranteed.
• Failing to document how overrides are handled.""",
"""Useful language with HRBPs:
“The matrix is designed to create stronger differentiation for high performers who are lower in range. It is guidance, not a promise. Exceptions need a business reason and will be reviewed against budget and internal equity.”"""
),
"6-2" to LessonDeepDive(
"""Manager overrides are where good compensation design can break down. Managers often have valid context that central data does not show, but unrestricted overrides can create favoritism, budget leakage and inconsistent outcomes.

The goal is not to eliminate manager judgment. It is to make exceptions visible, evidence-based and governable.""",
"""Create an override framework:
1. Capture original guideline.
2. Capture requested percentage.
3. Require reason code: retention, market, promotion, internal equity, performance nuance, other.
4. Require manager rationale above a threshold.
5. Calculate incremental cost versus guideline.
6. Set approval levels based on size/cost.
7. Track overrides by manager, function and demographic groups.
8. Review patterns after cycle.

Useful metric: Override Cost = Final Increase Amount - Guideline Increase Amount.""",
"""Manager A overrides 25 of 30 employees upward. Manager B overrides 2 of 30. Both stay within local budgets because Manager A offsets increases by reducing others.

Even if total cost is controlled, Manager A’s pattern may undermine company differentiation and fairness. Compensation should review who gained and lost, not just budget.""",
"""Add these columns to a cycle workbook:
Guideline %
Manager Proposed %
Difference %
Reason Code
Rationale
Incremental Cost
Approval Required?
Final Approved %

Create a PivotTable showing override count and cost by manager. Flag managers whose override rate exceeds 25%.""",
"""Common mistakes:
• Reviewing only total budget, not distribution.
• Allowing free-text reasons with no categories.
• Applying different approval standards by business unit.
• Ignoring downward overrides as well as upward ones.
• Failing to learn from override patterns after cycle.""",
"""Useful language with a manager:
“I’m not rejecting the exception automatically. I need the business reason and evidence because the request is materially outside the guideline. That lets us compare it consistently with other exceptions across the company.”"""
),
"7-0" to LessonDeepDive(
"""Promotion pay is a new-job decision, not merely a reward percentage. The employee is moving into a role with a different market value and salary range, so the new salary should be tested against both old and new positions.

Using one standard percentage for every promotion may leave lower-paid employees below the new minimum or push highly paid employees too far into the new range.""",
"""Promotion analysis:
1. Confirm the new job is genuinely larger.
2. Identify new grade and range.
3. Calculate current salary position in old range.
4. Apply policy guideline as a starting point.
5. Calculate resulting position in new range.
6. Compare with new-level peers and market.
7. Check minimum/maximum rules.
8. Review performance and readiness separately from job value.
9. Calculate annualized cost and effective date impact.""",
"""Employee moves G6 to G7.
Current salary: ₹18 lakh.
G7 range: ₹20–₹25–₹30 lakh.
Standard 10% increase gives ₹19.8 lakh, below range minimum.

A policy might require at least new range minimum, producing ₹20 lakh. But if comparable new-level peers earn ₹22–₹24 lakh and the employee is highly experienced, a higher position may be reasonable. The decision should not be “10% because policy says 10%” without checking the new range.""",
"""Build a promotion calculator with:
Current Grade
Current Salary
New Grade
New Min/Mid/Max
Policy %
Policy Salary
New Compa
Peer Median
Market P50
Recommended Salary
Promotion %
Annual Cost

Create five cases where the standard percentage gives a poor result.""",
"""Common mistakes:
• Treating promotion increase as a reward for past performance.
• Ignoring new range minimum.
• Giving every promotion the same percentage.
• Comparing promoted employee only with old peers.
• Failing to distinguish job promotion from title change.""",
"""Useful language with managers:
“The 10% guideline is a starting point. We also need the employee to land appropriately in the new G7 range. In this case, 10% leaves them below the minimum, so we should adjust the recommendation based on the new role.”"""
),
"7-1" to LessonDeepDive(
"""Retention adjustments are high-pressure decisions because they often happen quickly and involve fear of losing talent. That pressure can lead to automatic counteroffers.

A disciplined approach asks whether compensation is truly the issue, whether the employee is critical, whether the external offer is comparable, and whether matching it would damage internal equity.""",
"""Retention framework:
1. Confirm flight risk and timing.
2. Assess employee criticality and performance.
3. Understand reason for leaving.
4. Compare external offer components, not only base.
5. Review internal range, market and peers.
6. Estimate replacement cost and time.
7. Model retention options: base adjustment, retention bonus, role change, career action, no match.
8. Consider precedent and future sustainability.
9. Document decision and expiry where temporary elements are used.""",
"""Cybersecurity specialist:
Current base ₹28 lakh.
Market P50 ₹25 lakh.
Range max ₹32 lakh.
External base offer ₹36 lakh.
Performance Exceptional.
Critical skill Yes.

The employee is already above market median. Matching ₹36 lakh would exceed range max and may create peer inequity. Alternatives could include a smaller base adjustment plus time-bound retention bonus, accelerated career discussion or accepting the risk of departure. The right answer depends on criticality and total offer comparison.""",
"""Create a retention decision matrix with weighted factors:
Criticality 25%
Performance 15%
Market Gap 15%
Internal Equity 15%
Replacement Difficulty 15%
Reason for Leaving 10%
Offer Credibility 5%

Score five cases, but do not let the score make the decision automatically. Write a recommendation and risk statement.""",
"""Common mistakes:
• Matching every external offer.
• Comparing only base salary.
• Using a retention increase to solve a career problem.
• Ignoring peers.
• Creating permanent base cost for a temporary market spike.""",
"""Useful language with leadership:
“The external offer is materially higher, but the employee is already above our market reference and near range maximum. Matching would create a structural equity issue. I recommend a targeted retention package rather than permanently resetting base to the external offer.”"""
),
"7-2" to LessonDeepDive(
"""Variable pay helps organizations reward results without putting every reward into permanent base salary. Good incentive design aligns employee behavior with business outcomes while limiting unintended consequences.

A plan must define eligibility, target opportunity, performance measures, threshold, target, maximum payout and governance.""",
"""Core plan design:
1. Define plan purpose.
2. Choose eligible population.
3. Set target incentive by level/role.
4. Select measures employees can influence.
5. Define weights.
6. Set threshold, target and maximum performance.
7. Define payout curve.
8. Add caps, gates or risk controls.
9. Model cost under poor, target and exceptional scenarios.
10. Document treatment of joins, leaves, transfers and leave periods.""",
"""Base salary ₹30 lakh.
Target bonus 15%.
Target opportunity = ₹4.5 lakh.
Company factor = 110%.
Individual factor = 120%.

Payout = 30 × 15% × 110% × 120% = ₹5.94 lakh.

If plan maximum is 150% of target, calculated factor combination must be checked against the cap. Without modeling, a plan can create unexpectedly large payouts.""",
"""Build a bonus calculator with:
Base
Target %
Company Weight
Individual Weight
Company Result %
Individual Result %
Calculated Factor
Cap
Final Payout

Run scenarios at 50%, 100%, 120% and 150% achievement. Chart company cost.""",
"""Common mistakes:
• Choosing measures employees cannot influence.
• Creating cliffs where a tiny performance change causes huge payout change.
• Ignoring payout caps.
• Failing to model total cost.
• Changing rules after performance is known.""",
"""Useful language with plan owners:
“We should test the payout curve before launch. A plan that looks reasonable at target can become very expensive at maximum or produce unintended cliffs around threshold.”"""
),
"8-0" to LessonDeepDive(
"""Pay equity analysis asks whether pay differences are explainable by legitimate job- and employee-related factors. Raw averages can reveal a signal but are not sufficient to explain why a gap exists.

Senior compensation professionals need to distinguish descriptive analysis from statistical or legal conclusions. Formal equity reviews should involve appropriate legal and analytical expertise.""",
"""Practical descriptive sequence:
1. Validate data quality.
2. Segment by comparable job family, grade and geography.
3. Review raw average/median pay.
4. Add tenure, experience, performance and relevant skill factors.
5. Identify unexplained outliers within comparable groups.
6. Review hiring, promotion and increase history.
7. Escalate persistent unexplained patterns for deeper analysis.
8. Document actions and monitor after corrections.

Avoid over-broad comparison groups.""",
"""Overall average:
Group A ₹22 lakh
Group B ₹19 lakh

After controlling descriptively for grade, Group B has a larger share of junior-level employees. Within G7, medians are ₹21.0 and ₹20.8 lakh. Within G8, medians are ₹27.2 and ₹27.0 lakh.

The raw ₹3 lakh gap was largely driven by workforce mix. This does not end the analysis, but it changes the question.""",
"""Create an Excel file with Gender, Job Family, Grade, Location, Salary, Performance, Years in Role and Hire Year.

Compare:
Overall median pay
Median by grade
Median by job family + grade
Compa-ratio by group
Promotion rate by group
Average increase by group

Write which results are descriptive and which would require deeper statistical review.""",
"""Common mistakes:
• Calling a raw gap proof of discrimination.
• Using averages with very small groups.
• Comparing different jobs/levels.
• Ignoring workforce representation by grade.
• Correcting data before understanding root cause.""",
"""Useful language with leadership:
“The raw gap is a useful signal, but it is strongly affected by level mix. We should compare like-for-like groups and then investigate any differences that remain unexplained.”"""
),
"8-1" to LessonDeepDive(
"""Outliers deserve attention because they can reveal data errors, historical exceptions, market premiums or inequities. But an outlier is not automatically wrong.

A mature process creates a reason code for legitimate exceptions and makes undocumented outliers visible.""",
"""Outlier workflow:
1. Define statistical or policy threshold.
2. Verify source data.
3. Compare with appropriate peers.
4. Review history: hire, promotion, acquisition, transfer.
5. Check special arrangements.
6. Assign reason code if valid.
7. Escalate if unexplained.
8. Track correction status.

Useful thresholds: below range minimum, above maximum, ±20% from peer median, or extreme compa-ratio.""",
"""Ten G7 analysts earn ₹20–₹24 lakh. One earns ₹14 lakh and one ₹35 lakh.

₹14 lakh case: perhaps wrong grade mapping or legacy salary below range.
₹35 lakh case: perhaps red-circled salary after reorganization, expatriate arrangement or data error.

Both need investigation, but neither should be “fixed” until the reason is known.""",
"""Build an outlier report with:
Salary
Range Position
Peer Median
Variance to Peer Median %
Reason Code
Evidence
Action
Owner
Due Date

Create a PivotTable of outliers by reason code. This turns exceptions into a governance process rather than one-time analysis.""",
"""Common mistakes:
• Correcting outliers immediately.
• Deleting outliers from analysis because they distort averages.
• Having no documentation for valid exceptions.
• Looking only at high salaries and ignoring low salaries.""",
"""Useful language:
“This salary is an outlier, but the correct next step is validation. Once we understand whether it is a data issue, legacy protection or unexplained difference, we can choose the right action.”"""
),
"8-2" to LessonDeepDive(
"""Segmentation turns a large compensation dataset into actionable populations. Instead of asking “Who is underpaid?”, define business-relevant groups that warrant review.

The best flags combine multiple signals—performance, market position, range position and criticality—rather than one metric alone.""",
"""Example priority segments:
• High performer + compa <0.90
• Critical skill + market ratio <0.90
• Below range minimum
• Above range maximum
• Promotion candidate below new range minimum
• Experienced incumbent below recent-hire median
• Repeated low increases
• High override frequency

For each segment define: why it matters, evidence needed, possible action and owner.""",
"""Suppose 80 employees are below 0.90 compa. That sounds large.

After segmentation:
35 are recent promotions.
20 are new hires.
15 are high performers with 4+ years in role.
10 are data/mapping issues.

The actionable group may be the 15 experienced high performers plus the 10 data issues, not all 80.""",
"""Create a Review Flag formula that returns:
Data Issue
Below Range
Critical Skill Below Market
High Performer Low in Range
Recent Promotion
Normal

Build a summary by flag showing headcount and estimated correction cost.""",
"""Common mistakes:
• Treating every low compa employee as priority.
• Creating so many flags that everything becomes an exception.
• Not defining next action for each flag.
• Ignoring data-quality flags.""",
"""Useful language with an HRBP:
“The initial list has 80 people, but 55 have explainable positioning due to recent hire or promotion. The true priority group is 15 experienced high performers plus 10 records needing data correction.”"""
),
"9-0" to LessonDeepDive(
"""Critical-skill compensation is a targeted response to labor-market scarcity. The challenge is that skill scarcity changes faster than job architecture. If every hot skill permanently raises base salary, structures can become fragmented and expensive.

Organizations can use market positioning, skill premiums, retention bonuses or targeted incentives depending on whether scarcity is persistent or temporary.""",
"""Decision framework:
1. Confirm scarcity with external data and hiring evidence.
2. Define eligible skill precisely.
3. Decide whether scarcity is temporary or structural.
4. Choose mechanism: base range, fixed premium, variable premium, retention bonus.
5. Define amount and duration.
6. Set review/expiry date.
7. Prevent double-paying if market range already reflects the skill.
8. Track hiring, attrition and market movement.

Govern eligibility centrally.""",
"""AI Engineer P50 = ₹27 lakh.
General Software Engineer P50 = ₹20 lakh.
Same internal level.

If AI skill demand is expected to remain structurally higher, separate market ranges may be justified. If the shortage is temporary, a skill premium could preserve flexibility.

A ₹4 lakh annual premium reviewed every 12 months may be easier to remove than permanently raising base by ₹4 lakh, but employee communication must be clear.""",
"""Build a critical-skill register:
Skill
Jobs Eligible
Market Premium %
Hiring Difficulty
Attrition
Current Internal Gap
Mechanism
Premium Amount
Effective Date
Review Date
Owner

Create a stoplight review showing which premiums should continue, change or expire.""",
"""Common mistakes:
• Calling every difficult-to-hire role “critical.”
• No expiry date.
• Paying skill premium and already-premium market range simultaneously.
• Allowing manager-defined eligibility.
• Keeping premiums after market conditions normalize.""",
"""Useful language with leaders:
“We agree the skill is scarce. The remaining question is whether this is a permanent market difference or a temporary shortage. That determines whether we change base structure or use a reviewable premium.”"""
),
"9-1" to LessonDeepDive(
"""Geographic pay strategy determines whether and how location affects salary opportunities. Companies may use national ranges, city tiers, regional zones or local market ranges.

There is no universally correct model. The right model should match the company’s talent strategy, operating footprint and ability to administer consistently.""",
"""Design steps:
1. Define labor-market philosophy: where do we recruit from?
2. Group locations using reliable market data.
3. Set differentials relative to a reference location or national range.
4. Define rules for employee moves.
5. Define remote-worker treatment.
6. Define international transfers separately.
7. Review zones periodically.
8. Communicate whether pay changes when location changes.

Avoid one-off city exceptions.""",
"""Reference zone midpoint = ₹20 lakh.
Zone A differential +10% → ₹22 lakh.
Zone B 0% → ₹20 lakh.
Zone C -8% → ₹18.4 lakh.

If an employee moves from Zone A to C, policy must define whether salary changes immediately, gradually, or only future increases are affected. Without a rule, similar moves may be treated differently.""",
"""Create four geographic zones for a hypothetical India technology company. Assign Pune, Bengaluru, Hyderabad, Mumbai, Delhi NCR and three smaller cities.

Define:
Differential %
Move rule
Remote rule
New-hire rule
Review frequency

Then cost the same 100-person workforce under national versus zoned ranges.""",
"""Common mistakes:
• Using cost of living when strategy is based on cost of labor.
• Creating unique differentials for every city.
• No employee-move rule.
• Changing geography policy reactively after one case.
• Ignoring location data quality.""",
"""Useful language:
“Our geographic model is based on labor-market pay, not living expenses. We group locations into zones so similar markets are treated consistently and employee moves follow one published rule.”"""
),
"9-2" to LessonDeepDive(
"""Remote work exposes compensation philosophy because physical office location and talent market can diverge. Companies must decide whether pay follows employee residence, assigned work location, national market or role-specific talent market.

The key is consistency. A policy created after individual cases arise tends to accumulate exceptions.""",
"""Remote-pay policy should answer:
1. Which location determines salary range?
2. What happens on permanent move?
3. Are temporary moves treated differently?
4. How is cross-border remote work handled?
5. When are market zones reviewed?
6. Who approves exceptions?
7. How are employees notified of potential pay impact?
8. Does a move change bonus or benefits as well?

Separate tax/legal mobility rules from compensation philosophy.""",
"""Employee is hired in Bengaluru Zone A and later moves permanently to a Zone C city.

Possible policies:
A. Immediate range change and salary adjustment.
B. New range applies, but salary protected; future increases limited until aligned.
C. National pay—no range change.

Each approach has different cost, employee-relations and equity implications. The company should choose before cases occur.""",
"""Write a one-page remote-pay policy. Test it against five cases:
Permanent domestic move
Temporary 3-month move
Cross-border move
New remote hire
Employee moves to higher-cost zone

For each, state range used and salary action.""",
"""Common mistakes:
• No distinction between temporary and permanent location.
• Handling moves differently based on manager influence.
• Forgetting benefits/tax considerations exist alongside compensation.
• Using office location for some remote workers and residence for others without rationale.""",
"""Useful language with employees:
“Our policy uses your designated permanent work location to determine salary range. A permanent move may change the range that applies, and we review any salary impact under the same rule for all employees.”"""
),
"10-0" to LessonDeepDive(
"""The most valuable compensation professionals do more than calculate. They convert data into a recommendation that a business leader can act on.

A strong recommendation has five elements: fact, interpretation, risk, action and cost. Missing any one weakens the advice.""",
"""Recommendation structure:
1. FACT — What does the data show?
2. INTERPRETATION — Why does it matter?
3. RISK — What happens if we do nothing or do too much?
4. ACTION — What do you recommend?
5. COST — What is annualized financial impact?

Add alternatives when the decision is not obvious.""",
"""Weak:
“Compa-ratio is 0.82.”

Strong:
“Employee is at 82% of midpoint and 15% below P50. Performance has been strong for two years and two recent comparable hires are 10–12% higher. If unchanged, retention and internal-equity risk remain. I recommend moving salary to ₹20.5 lakh, costing ₹2.5 lakh annually, while preserving a gap to more experienced peers.”

The second version helps a leader decide.""",
"""Take five compensation findings from a sample workbook and write each in the five-part format.

Then compress each recommendation into:
• 30-second verbal version
• 3-line email version
• One-slide executive version

This builds senior stakeholder communication skill.""",
"""Common mistakes:
• Reporting numbers without recommendation.
• Giving recommendation with no cost.
• Using compensation jargon that leaders do not understand.
• Presenting only one option when trade-offs are real.
• Hiding uncertainty instead of stating it.""",
"""Useful language:
“The data supports action, but not the full amount requested. Here is the gap, the risk, the recommended correction, and the annual cost. I’ve also shown the alternative if leadership wants a stronger retention stance.”"""
),
"10-1" to LessonDeepDive(
"""Manager partnering requires curiosity and boundaries. Managers often come with a solution—“give 20%”—instead of the underlying problem. Compensation should diagnose the problem before debating the number.

The aim is to be a business partner, not a policy police officer and not an automatic approver.""",
"""Use five questions:
1. What outcome are you trying to achieve?
2. What has changed in the employee’s role or market?
3. What evidence suggests current pay is a problem?
4. How does this employee compare with peers?
5. What happens if we do not make the adjustment?

Then explain policy and data in plain language. Where you disagree, separate “I understand the problem” from “I recommend a different solution.”""",
"""Manager: “I need 20% for Priya.”

Compensation:
“What problem are we solving—promotion, retention or market position?”

Manager: “Retention; she says recruiters are calling.”

Now the analysis can focus on flight risk, market, peers and criticality rather than negotiating 20% immediately.""",
"""Practice three role plays:
1. Manager wants 25% because employee is “best on team.”
2. HRBP wants to match an external offer.
3. Business leader wants to promote an employee without larger job scope.

For each, write three diagnostic questions and a recommendation.""",
"""Common mistakes:
• Saying “policy says no” before understanding the request.
• Agreeing to percentages before analysis.
• Using too much technical terminology.
• Avoiding difficult conversations about internal equity.
• Failing to offer alternatives.""",
"""Useful language:
“I understand why you want to retain the employee. The data supports some action, but 20% creates a peer-equity problem. Let me show you two alternatives that address retention with less structural risk.”"""
),
"10-2" to LessonDeepDive(
"""Finance and executive leaders evaluate compensation through cost, risk and business outcomes. A technically correct analysis can fail if it does not explain financial impact.

Senior compensation professionals translate employee-level recommendations into payroll, forecast, budget and strategic talent implications.""",
"""Executive compensation proposal:
1. Business issue and population affected.
2. Evidence: market, internal position, attrition/hiring data.
3. Options: no action, targeted action, broad action.
4. Cost: annualized, current-year and future run-rate.
5. Risks: retention, equity, precedent, budget.
6. Recommendation.
7. Decision required.

Always distinguish current-year cash impact from full annualized run-rate.""",
"""A ₹2 crore annualized salary action effective October 1 does not cost ₹2 crore in the current calendar year if only three months remain. Current-year cost is roughly ₹0.5 crore, but next-year run-rate is ₹2 crore before further increases.

Finance needs both numbers to forecast correctly.""",
"""Build a proposal for 100 critical employees:
Current payroll
Recommended increase %
Annualized cost
Current-year cost based on effective date
Next-year run-rate
Attrition risk
Replacement cost estimate
Alternative options

Create a one-page executive summary.""",
"""Common mistakes:
• Showing only current-year cost.
• Forgetting recurring run-rate.
• Mixing one-time bonuses with base salary cost.
• Presenting detailed employee data to executives when a segmented summary is enough.
• Omitting a clear decision request.""",
"""Useful language with Finance:
“The October action costs ₹50 lakh this year but creates a ₹2 crore annualized run-rate next year. I’ve separated one-time retention payments from permanent salary cost so the forecast is clear.”"""
)
)

val advancedQuizQuestions = listOf(
QuizQuestion(1,"A manager asks for a 20% increase because an employee might resign. What should Compensation do first?",listOf("Approve 20%","Identify the underlying business problem and gather range, market and peer evidence","Reject it because 20% is too high","Ask Finance only"),1,"The percentage is a proposed solution. First diagnose the problem and gather evidence."),
QuizQuestion(1,"Which statement best describes a compensation philosophy?",listOf("A salary table","A set of principles defining talent market, market position, pay mix and governance","A legal contract","A bonus formula"),1,"A philosophy guides consistent decisions across the organization."),
QuizQuestion(1,"Two employees in the same job have different salaries. What does that prove?",listOf("Inequity","Nothing by itself; experience, performance, skills and history must be reviewed","The higher-paid employee is overpaid","The lower-paid employee must receive a correction"),1,"A difference is a signal to understand, not an automatic conclusion."),
QuizQuestion(1,"Why annualize equity when comparing Total Rewards?",listOf("To increase its value","To compare multi-year grants with annual compensation on a common basis","Because tax law requires it","To remove vesting risk"),1,"Annualization puts multi-year value on a comparable annual basis."),
QuizQuestion(2,"A highly successful employee is doing a G7 job. Should performance alone make the job G8?",listOf("Yes","No; job size and employee performance are separate","Only after one year","Only if salary is above midpoint"),1,"Job evaluation assesses the role, not the incumbent’s performance."),
QuizQuestion(2,"What is the strongest evidence that a Principal Engineer is a larger job than a Senior Engineer?",listOf("More years of service","Enterprise or cross-team technical scope and influence","Higher current salary","A more impressive title"),1,"Level should be supported by larger scope, complexity and impact."),
QuizQuestion(2,"Why are anchor jobs useful in job evaluation?",listOf("They eliminate market surveys","They provide consistent comparison points for evaluating other roles","They guarantee promotions","They replace job descriptions"),1,"Anchor jobs improve consistency across evaluations."),
QuizQuestion(2,"Which is a warning sign of title inflation?",listOf("Clear level definitions","Managers creating senior titles without larger scope","Dual career tracks","Documented job evaluation"),1,"Titles should reflect meaningful differences in job scope."),
QuizQuestion(3,"A survey title exactly matches an internal role but has much larger scope. What is the best action?",listOf("Use it because title matches","Reject or adjust the match because scope is more important than title","Use P25","Use it only for high performers"),1,"Market matching is based on job content and level."),
QuizQuestion(3,"What does a range midpoint at 96% of market P50 suggest?",listOf("Every employee is 4% underpaid","The structure midpoint is slightly below the chosen market reference","The survey is wrong","The range maximum is too low"),1,"Structure position and individual salary position must be analyzed separately."),
QuizQuestion(3,"What is the main risk of using a C-quality market match as if it were exact?",listOf("Too much Excel work","False precision and potentially wrong pay decisions","Lower bonus","Higher headcount"),1,"Weak matches should be documented and used cautiously."),
QuizQuestion(3,"Why should salary-budget increase not automatically be used as survey-aging rate?",listOf("They measure different things","Salary budgets are always lower","Survey aging is illegal","Budgets apply only to managers"),0,"Market movement and salary budget are related but distinct measures."),
QuizQuestion(4,"What is midpoint progression?",listOf("Employee increase over time","Percentage difference between adjacent grade midpoints","Range maximum divided by minimum","Market P75 minus P25"),1,"It measures structural movement between adjacent grades."),
QuizQuestion(4,"Why can salary ranges overlap?",listOf("Because the structure is broken","To allow experienced lower-grade employees to earn more than newer higher-grade employees while preserving career opportunity","Because surveys require it","To reduce payroll"),1,"Reasonable overlap supports progression and recognizes experience."),
QuizQuestion(4,"An employee has 110% range penetration. What does this signal?",listOf("The employee is 10% above range maximum","The employee is 10% above midpoint","The employee received 110% bonus","The formula is invalid"),0,"Penetration above 100% indicates salary above maximum."),
QuizQuestion(4,"Which pattern most strongly suggests compression?",listOf("High performer above midpoint","Recent hires consistently paid above experienced incumbents and close to next-level salaries","Wide range spread","Different locations have different ranges"),1,"Compression is about insufficient pay differentiation."),
QuizQuestion(5,"Why return 'Not Found' instead of zero in XLOOKUP?",listOf("It looks better","It preserves visibility of data-quality problems","Zero is not allowed in Excel","It makes formulas shorter"),1,"Errors should be visible so they can be corrected, not silently converted."),
QuizQuestion(5,"A function-level payroll summary does not reconcile to total payroll. What should you do?",listOf("Present it anyway","Investigate blanks, excluded categories or formula errors before reporting","Round the values","Delete unmatched employees"),1,"Control totals are fundamental to reliable compensation analysis."),
QuizQuestion(5,"What is a key benefit of Power Query for compensation cycles?",listOf("It replaces compensation judgment","It creates repeatable and auditable data transformation steps","It guarantees clean source data","It removes the need for validation"),1,"Repeatable transformations reduce manual risk but still require validation."),
QuizQuestion(5,"What should accompany a compensation dashboard chart?",listOf("More colors","A clear question or insight and relevant controls/headcount","Employee photos","No explanation"),1,"Dashboards should support decisions, not decoration."),
QuizQuestion(6,"Eligible payroll is ₹100 crore and increase cost is ₹6.2 crore. What is weighted increase?",listOf("6.2%","16.1%","₹106.2 crore","Cannot be calculated"),0,"Weighted increase equals total increase cost divided by eligible payroll."),
QuizQuestion(6,"Why can the same merit matrix cost differently in two years?",listOf("Excel changes formulas","Performance distribution and employee salary mix can differ","Midpoints never change","Managers cannot override"),1,"The population fed into the matrix changes total weighted cost."),
QuizQuestion(6,"What is Override Cost?",listOf("Final increase amount minus guideline increase amount","Total salary minus midpoint","Bonus minus salary","Range max minus min"),0,"It isolates the incremental cost created by overrides."),
QuizQuestion(6,"A manager reduces some employees to fund large increases for favorites while staying in budget. Is budget control alone sufficient?",listOf("Yes","No; distribution and consistency also require review","Only if Finance agrees","Only for executives"),1,"Fairness and policy consistency matter alongside cost."),
QuizQuestion(7,"A promotion guideline gives a salary below new range minimum. What should happen?",listOf("Use guideline blindly","Review new-range positioning and policy before finalizing","Cancel promotion","Lower new range"),1,"Promotion pay should be evaluated in the context of the new job and range."),
QuizQuestion(7,"Which is the strongest reason not to match every external offer?",listOf("External offers are always fake","Matching can create range, peer-equity and sustainability problems","Managers dislike it","It reduces bonus"),1,"Retention decisions must consider internal structure and long-term cost."),
QuizQuestion(7,"What is target bonus?",listOf("Guaranteed payout","The incentive opportunity at target performance","Maximum payout","Salary increase"),1,"Target bonus is the planned incentive opportunity at target results."),
QuizQuestion(7,"Why model threshold, target and maximum incentive scenarios?",listOf("To understand behavior and cost across performance outcomes","To avoid using Excel","To set salary ranges","To calculate compa-ratio"),0,"Scenario modeling tests affordability and unintended payout behavior."),
QuizQuestion(8,"A raw pay gap disappears after comparing within grade. What does that suggest?",listOf("The raw gap may have been driven by workforce mix","No further review is ever needed","The data is wrong","Everyone should receive the same salary"),0,"Level distribution can create raw differences that change under like-for-like comparison."),
QuizQuestion(8,"What is the correct first response to an extreme salary outlier?",listOf("Correct it immediately","Validate data and investigate history/context","Remove it from dataset","Give the same salary as peers"),1,"Outliers must be understood before action."),
QuizQuestion(8,"Why combine multiple signals in review flags?",listOf("To create longer formulas","To identify more meaningful priority populations","To avoid manager input","To guarantee fairness"),1,"Multiple signals help distinguish explainable low positioning from higher-risk cases."),
QuizQuestion(8,"Which group is usually more actionable?",listOf("All employees below 0.90 compa","Experienced high performers below 0.90 after excluding recent hires/promotions","All new hires","All employees above midpoint"),1,"Context narrows the priority population."),
QuizQuestion(9,"When is a skill premium preferable to changing the whole salary range?",listOf("When scarcity appears targeted and potentially temporary","Always","Never","When performance is low"),0,"Temporary targeted scarcity can be addressed without permanently resetting structure."),
QuizQuestion(9,"What should every temporary skill premium include?",listOf("An expiry/review date and clear eligibility","A promotion","P75 market target","Stock options"),0,"Without governance, temporary premiums can become permanent by default."),
QuizQuestion(9,"What is a major design question in geographic pay?",listOf("Which location or labor market determines the applicable range","Employee favorite city","Office furniture cost","Bonus rating"),0,"The policy must define the location basis for pay."),
QuizQuestion(9,"An employee permanently moves zones. What should determine treatment?",listOf("Manager preference","A published move rule applied consistently","Employee tenure only","The employee's previous salary only"),1,"Consistency requires pre-defined movement rules."),
QuizQuestion(10,"Which five elements make a strong recommendation?",listOf("Fact, interpretation, risk, action and cost","Title, tenure, age, location and bonus","Salary, tax, leave, manager and HR","Market P25 through P90"),0,"Senior advice connects evidence to action and financial impact."),
QuizQuestion(10,"Why should current-year cost and annualized run-rate both be shown?",listOf("Because an action effective midyear has different immediate and future recurring cost","Because Finance wants more numbers","Because bonus is annual","Because salary ranges expire"),0,"Effective date changes current-year cost while annualized cost affects future budgets."),
QuizQuestion(10,"A manager opens with 'I need 20%.' What is the best first response?",listOf("Agree","Ask what problem the increase is intended to solve","Reject","Offer 10%"),1,"Diagnose the problem before negotiating the percentage."),
QuizQuestion(10,"What makes an executive compensation summary useful?",listOf("Every employee row","Business issue, evidence, options, cost, risk, recommendation and decision required","Only market charts","Only policy wording"),1,"Executives need decision-ready information, not raw detail.")
)

val allQuizQuestions: List<QuizQuestion> = quizQuestions + advancedQuizQuestions
