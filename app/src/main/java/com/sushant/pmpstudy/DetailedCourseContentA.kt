package com.sushant.pmpstudy

data class LessonDeepDive(
    val whyItMatters: String,
    val method: String,
    val workedExample: String,
    val practice: String,
    val pitfalls: String,
    val partnerConversation: String
)

val deepDivesA = mapOf(
"1-0" to LessonDeepDive(
"""Compensation decisions create recurring cost and employee expectations. A one-time decision for one employee can become a reference point for an entire team, so experienced compensation professionals must think beyond the immediate request. The job is to balance attraction, retention, performance, internal equity, external competitiveness and affordability.

A strong Compensation Partner separates facts from assumptions. “The employee will leave” is an assumption until there is evidence. “The employee is at 82% of midpoint, 14% below market median, has two strong performance years and holds a scarce skill” is evidence. Senior-level work begins when you can turn an emotional request into a structured decision.""",
"""Use a six-part decision frame:
1. Define the business problem: retention, promotion, market correction, internal equity, performance recognition or hiring.
2. Confirm job facts: level, role scope, location and career track.
3. Review internal position: salary range, compa-ratio, peer position and recent increases.
4. Review external position: market P50/P75, skill scarcity and hiring difficulty.
5. Test affordability and precedent: annualized cost, budget source and impact on peers.
6. State recommendation, risks and alternative options.

Always document what evidence changed the recommendation. This makes later audits and manager discussions much easier.""",
"""A Senior Data Engineer earns ₹18.2 lakh. Range is ₹16–₹24 lakh with ₹20 lakh midpoint. Market P50 is ₹21.5 lakh. Performance is “Exceeds” for two years. Three peers at the same level earn ₹19.5, ₹20.1 and ₹21.2 lakh. The manager asks for ₹25 lakh.

Compa-ratio = 18.2 / 20 = 0.91.
Market ratio = 18.2 / 21.5 = 0.85.

Evidence supports a correction, but ₹25 lakh would exceed the range maximum and move the employee above all peers. A more defensible recommendation might be a targeted correction into the ₹20–₹21 lakh area, depending on skill scarcity, retention evidence and company policy. The important point is not the exact number; it is that the recommendation is anchored to range, market, peers and policy.""",
"""Build a one-page “Compensation Decision Sheet” in Excel with these columns: Employee, Job, Grade, Salary, Range Min, Mid, Max, Compa-Ratio, Market P50, Market Ratio, Performance, Years in Role, Critical Skill, Peer Median, Requested Increase, Recommended Increase, Annual Cost and Rationale.

Create three sample cases: a retention request, a promotion and a market correction. Force yourself to write a one-sentence recommendation for each.""",
"""Common mistakes:
• Treating a manager request as the problem statement.
• Looking only at market data and ignoring internal peers.
• Giving a percentage first and looking for justification later.
• Ignoring recurring annual cost.
• Using “underpaid” when all you know is “below midpoint” or “below market reference.”
• Failing to document exceptions.""",
"""Useful language with a manager:
“I can see the retention concern. Before deciding the percentage, I want to separate the market issue from the internal-equity issue. The employee is below both midpoint and market, so some adjustment is supported. The requested amount would place them above the range and peers, so I recommend we solve the gap without creating a second problem.”"""
),
"1-1" to LessonDeepDive(
"""A compensation philosophy is the bridge between business strategy and thousands of individual pay decisions. Without it, every manager can argue from a different principle: one wants to pay at P75, another wants to pay everyone equally, and Finance wants minimum cost. The result becomes inconsistent and difficult to defend.

A useful philosophy is specific enough to guide decisions but flexible enough to handle talent segments. It should answer who the company competes with for talent, where it targets the market, how performance affects pay, how critical skills are treated, and how exceptions are governed.""",
"""Build a philosophy in layers:
1. Talent market: industry, company size, geography and job families used for comparison.
2. Market position: P50 for standard roles, perhaps higher for selected scarcity areas.
3. Pay mix: base salary, short-term incentive, long-term incentive and benefits.
4. Internal equity: how levels and ranges create consistency.
5. Performance: what is differentiated and how much.
6. Critical skills: when premiums are allowed and how often reviewed.
7. Governance: who approves deviations and what evidence is required.
8. Review cycle: how frequently ranges and policies are refreshed.

A philosophy should be testable. If the company says “P50 for core roles,” you should be able to measure actual employee and range positioning against P50.""",
"""Suppose an IT services company competes for most talent locally but loses cybersecurity employees to product companies. A sensible philosophy might target the local IT-services P50 for most roles, while using a separate premium or P65–P75 reference for designated cybersecurity roles.

That does not mean every cybersecurity employee gets P75. It means the company’s range design or premium policy recognizes a different talent market. Individual salary still depends on level, experience, performance and internal position.""",
"""Write a compensation philosophy for a 5,000-person technology company. Limit it to one page. Then create a second sheet called “How We Measure It” with five metrics such as average compa-ratio, percentage below range minimum, market ratio by job family, critical-skill attrition, and manager exception rate.

If a philosophy cannot be measured, it is too vague.""",
"""Common mistakes:
• Copying a generic philosophy that says only “competitive and fair.”
• Choosing P75 because it sounds attractive without costing it.
• Mixing market position with individual salary entitlement.
• Creating critical-skill rules with no expiry or review date.
• Having no definition of the relevant talent market.""",
"""Useful language with leadership:
“Our philosophy is not a promise that every employee earns the market median. It defines where we design pay opportunities. Individual position within those opportunities still reflects job level, experience, performance and internal equity.”"""
),
"1-2" to LessonDeepDive(
"""Internal equity and external competitiveness often pull in different directions. New-hire market pressure can push one salary upward while long-tenured employees remain lower. Acquisitions, location transfers and historical promotion practices can also create differences.

The senior analyst’s task is not to eliminate every difference. It is to identify differences that cannot be explained by legitimate factors and then decide whether the issue is individual, team-level or structural.""",
"""Use a peer-review sequence:
1. Define the peer group narrowly enough to be meaningful: same job family, similar level, comparable location and role scope.
2. Compare salary, compa-ratio and market ratio.
3. Add experience in role, performance, scarce skills and recent promotions.
4. Identify unexplained residual differences.
5. Check whether the pattern repeats across a team, manager, gender, location or hiring cohort.
6. Decide whether action is individual correction, range redesign, hiring-policy change or no action.

Avoid comparing averages across fundamentally different jobs or levels.""",
"""Four G7 Product Analysts have salaries of ₹17.0, ₹17.6, ₹20.8 and ₹21.1 lakh. The two lower-paid employees joined four years ago; the two higher-paid employees were hired during a market spike last year. Performance is comparable.

This may be hiring-driven compression rather than a performance issue. A useful response could combine targeted internal corrections with a review of new-hire offer governance. Correcting only one employee without fixing offer policy may allow the problem to reappear.""",
"""In Excel, create a peer analysis with Employee, Job, Grade, Location, Salary, Compa-Ratio, Years in Role, Performance and Hire Year. Add peer median salary using MEDIAN/FILTER if available, then calculate Salary / Peer Median.

Sort by job and grade. Identify five cases where the salary difference is large and write whether it is explainable, uncertain or likely needs action.""",
"""Common mistakes:
• Comparing employees only because they share a title.
• Treating all salary gaps as unfair.
• Ignoring hire date and promotion history.
• Correcting salaries without addressing the process that caused the issue.
• Using broad organization averages to assess individual equity.""",
"""Useful language with an HRBP:
“The gap is real, but we need to understand whether it is explained by job scope or employee factors. I’ll compare a tighter peer group and separate historical hiring effects from performance before we recommend a correction.”"""
),
"1-3" to LessonDeepDive(
"""Employees experience rewards as a package, while compensation teams often analyze components separately. A role with lower base salary may still be competitive if bonus and equity are materially higher. Conversely, a high base salary may not compensate for a weak total package in a market where equity is standard.

Understanding Total Rewards is especially important in technology because pay mix can change substantially by level: junior employees may be mostly cash, while senior leaders may have significant variable pay and long-term incentives.""",
"""Analyze Total Rewards in four layers:
1. Guaranteed cash: base salary and fixed allowances.
2. Variable cash: annual bonus, sales incentive and project incentives.
3. Long-term value: RSUs, stock options, deferred cash.
4. Benefits and experience: retirement contributions, insurance, paid leave, flexibility, learning and recognition.

When comparing offers or retention cases, normalize timing. A ₹6 lakh RSU grant vesting over three years is not the same as ₹6 lakh annual cash.""",
"""Employee A: ₹24 lakh base + 10% bonus + ₹1.5 lakh benefits = about ₹27.9 lakh target value.
Employee B: ₹21 lakh base + 15% bonus + ₹6 lakh RSUs vesting over three years + ₹1.5 lakh benefits.

Annualized equity value for B is roughly ₹2 lakh per year before considering share-price movement. Target annual value is roughly ₹27.65 lakh. The packages are closer than base salary alone suggests.

This does not mean benefits and equity can always replace base salary; employee preference, vesting risk and market practice matter.""",
"""Create a Total Rewards comparison sheet for three hypothetical employees. Include base, target bonus, annualized equity, employer retirement contribution, insurance value and other allowances. Calculate Total Target Cash and Estimated Total Rewards.

Then create a column called “Employee Perception Risk” and explain where two packages have similar economic value but may feel very different to employees.""",
"""Common mistakes:
• Adding a multi-year equity grant to one year of salary without annualizing.
• Comparing target bonus with actual bonus without labeling the difference.
• Treating benefits as cash equivalents in employee communication.
• Ignoring vesting conditions.
• Assuming every employee values each reward component equally.""",
"""Useful language in a retention discussion:
“The external offer is higher on base salary, but our total package includes bonus and equity that narrow the difference. We should compare annualized total value before deciding whether a base adjustment is necessary.”"""
),
"2-0" to LessonDeepDive(
"""Job architecture is the foundation beneath compensation. If jobs are inconsistently titled or leveled, market benchmarking becomes unreliable, salary ranges become noisy, and employees struggle to see career paths.

A mature architecture creates a stable hierarchy that can survive title changes. It describes what changes as work becomes larger: scope, complexity, autonomy, influence, depth of expertise and leadership impact.""",
"""A practical architecture workshop:
1. Group roles into job families and sub-families.
2. Define career tracks: individual contributor, people manager and specialist where needed.
3. Establish enterprise-wide level definitions before arguing about specific titles.
4. Map representative “anchor jobs” to each level.
5. Test adjacent levels for clear differences in scope and decision authority.
6. Map remaining jobs.
7. Review outliers with business leaders.
8. Create governance so new titles do not bypass the architecture.

Use job content, not incumbent capability, as the primary evidence.""",
"""A company has Senior Developer, Software Engineer III, Technology Specialist and Lead Developer. Interviews show all four independently deliver complex features, mentor peers, make design choices within a product area and have no enterprise-level architectural authority.

Despite title differences, the roles may belong at the same level. The company can preserve business-facing titles if desired while mapping them to one internal career level and pay grade.""",
"""Build a job architecture for Software Engineering:
L1 Associate Engineer
L2 Engineer
L3 Senior Engineer
L4 Lead/Staff Engineer
L5 Principal Engineer
L6 Distinguished Engineer

For each, define scope, problem complexity, autonomy, influence and typical decisions. Then write one sentence explaining why L4 is larger than L3 and why L5 is larger than L4.""",
"""Common mistakes:
• Building levels around current employee titles.
• Creating too many levels because managers want promotions.
• Using years of experience as the sole leveling criterion.
• Mixing performance with job size.
• Allowing business units to invent exceptions with no governance.""",
"""Useful language with a business leader:
“We can keep the title that works for your customers, but internally we need one consistent level definition. That lets us benchmark and pay similar work consistently across the company.”"""
),
"2-1" to LessonDeepDive(
"""Dual career tracks solve a common technology-company problem: expert contributors should be able to increase scope, influence and compensation without becoming people managers. If management is the only route upward, companies often promote strong engineers into roles they do not want or are not suited for.

The challenge is to create real equivalence rather than simply inventing prestigious IC titles. Each senior IC level must have meaningful enterprise impact.""",
"""Design parallel tracks by comparing dimensions rather than titles:
IC scope: systems, technical domains, architecture, standards, cross-team influence.
Manager scope: team size, organizational breadth, budget, talent accountability and business decisions.

Define approximate equivalence points, for example Principal Engineer may align with Senior Engineering Manager in grade, while Distinguished Engineer may align with Director. Exact equivalence varies by organization.

Do not force one-to-one title symmetry if the jobs are materially different.""",
"""A Principal Engineer has no direct reports but defines architecture used by six product teams, reviews critical technical decisions and mentors senior engineers. A Senior Engineering Manager leads 35 employees across three teams and owns delivery outcomes.

Both can plausibly occupy the same grade because their organizational impact is comparable even though the source of impact differs.""",
"""Create a two-column career map for Engineering from L1 to L7. On the left, describe IC scope. On the right, describe management scope. At each level, write the evidence that justifies equal or different grade treatment.

Then test one real or hypothetical employee who wants promotion but does not want management. Identify what larger IC scope would be required.""",
"""Common mistakes:
• Giving senior IC titles based only on tenure.
• Creating Principal roles with no cross-team impact.
• Paying managers more automatically because they have direct reports.
• Treating technical expertise as equivalent to enterprise influence without evidence.""",
"""Useful language with a technical leader:
“The next IC level is not a reward for being the best engineer on one team. It requires broader impact—standards, architecture or influence across multiple teams. That is how we preserve meaningful equivalence with management levels.”"""
),
"2-2" to LessonDeepDive(
"""Job evaluation provides a disciplined way to compare different types of work. It prevents “title inflation” and gives compensation teams a basis for grouping jobs into grades.

Different methodologies exist, but most evaluate similar ideas: knowledge required, complexity, scope, impact, decision authority, leadership, communication and risk. The exact scoring system matters less than consistent application.""",
"""Evaluate a job using evidence:
1. Purpose: why does the job exist?
2. Scope: what products, geographies, budgets or populations does it affect?
3. Decisions: what can the role decide independently?
4. Complexity: how ambiguous are problems?
5. Impact: local team, function, business unit or enterprise?
6. Knowledge: depth and breadth required.
7. Leadership: people, projects, expertise or governance.
8. Relationships: who must the role influence?

Then compare against anchor jobs already evaluated.""",
"""Compare HR Analyst and Global Compensation Lead.

HR Analyst: executes defined analyses, supports one country, decisions reviewed by manager.
Global Compensation Lead: designs global programs, influences pay for 20,000 employees, advises executives, owns governance and interprets ambiguous market issues.

Even without direct reports, the Global Lead has much larger scope, autonomy and impact, supporting a higher level.""",
"""Take five jobs from unrelated functions—Software Engineer, Finance Manager, HRBP, Sales Operations Lead and Cybersecurity Architect. Evaluate each on Scope, Complexity, Decision Authority, Organizational Impact and Leadership using a 1–5 scale.

Do not compare the final number blindly. Write the evidence behind each rating.""",
"""Common mistakes:
• Scoring the employee rather than the job.
• Giving extra weight to scarce market pay when evaluating job size.
• Letting title or reporting line determine the outcome.
• Using a points system without written evidence.
• Evaluating jobs in isolation without anchor comparisons.""",
"""Useful language during calibration:
“We are evaluating the role as it is designed, not the strength of the current incumbent. If the employee is operating beyond the job, that may indicate the job itself needs redesign or the employee may be ready for a larger role.”"""
),
"3-0" to LessonDeepDive(
"""Market benchmarking is one of the most technical judgment areas in compensation. A poor match can create a false market gap and lead to unnecessary recurring cost. A good match compares the substance of work—scope, level, responsibilities, required expertise and organizational impact.

Emerging roles make this harder because surveys may lag the market. In those cases, analysts may blend matches or triangulate across multiple sources rather than force a weak exact-title match.""",
"""Use a match-quality score:
A = strong match; responsibilities and level closely align.
B = reasonable match; some differences but core accountabilities align.
C = blended or proxy match; use cautiously.
D = no reliable external match; use internal relativities and other evidence.

Document:
• Internal role summary
• Survey code/title
• Match rationale
• Level alignment
• Geography/industry cut
• Match quality
• Any blend weights

Do not silently use a weak match.""",
"""Internal role: AI Solutions Architect. Survey options: Solutions Architect, AI Engineer, Enterprise Architect.

The role spends 50% designing client AI solution architecture, 30% guiding ML engineering choices and 20% supporting pre-sales. A blended benchmark might weight Solutions Architect 60% and AI Engineer 40%, provided levels align.

If the Enterprise Architect survey role owns enterprise-wide technology governance, it may be too large despite the attractive title match.""",
"""Create a benchmarking table for 15 technology jobs. Add columns: Match 1, Weight 1, Match 2, Weight 2, Level Match, Match Quality, Survey P50, Internal Average Salary, Market Ratio and Notes.

Flag all C/D quality matches for review before using them in range design.""",
"""Common mistakes:
• Matching by title only.
• Using a higher-paying survey role because managers prefer it.
• Ignoring survey level definitions.
• Mixing national and city data inconsistently.
• Using weak matches without documenting uncertainty.""",
"""Useful language with a manager:
“The external title looks similar, but the survey role has enterprise-wide authority that this job does not have. I’d rather use a slightly different title with a stronger scope match than a title match that overstates the market.”"""
),
"3-1" to LessonDeepDive(
"""Percentiles describe the market distribution; they do not tell you what your company should pay. Choosing a market percentile is a strategy decision involving talent scarcity, business model, affordability and pay mix.

P50 is the median, not an “average employee salary.” P75 means the reference is above 75% of survey observations, which usually increases range midpoints and payroll pressure.""",
"""Read market data in three stages:
1. Quality: sample size, survey methodology and match quality.
2. Position: P25/P50/P75 spread and company target.
3. Internal comparison: current salaries and range midpoints versus the selected reference.

Use Market Ratio = Salary / Market Reference for employee comparisons.
Use Range Midpoint / Market Reference for structure comparisons.

Separating employee position from structure position helps identify whether the issue is the person, the range, or both.""",
"""Market for Data Scientist: P25 ₹18 lakh, P50 ₹23 lakh, P75 ₹30 lakh.
Company range midpoint: ₹22 lakh.
Employee salary: ₹20 lakh.

Employee market ratio to P50 = 20/23 = 0.87.
Range midpoint market ratio = 22/23 = 0.96.

The employee is below market, but the structure itself is only 4% below the P50 reference. The larger issue may be individual positioning rather than range design.""",
"""In Excel, create 10 jobs with P25, P50, P75, current range midpoint and average employee salary. Calculate:
Midpoint / P50
Average Salary / P50
P75 / P50
P50 / P25

Identify where the market spread is unusually wide and discuss whether skill scarcity or survey heterogeneity might explain it.""",
"""Common mistakes:
• Treating P50 as a mandatory employee salary.
• Comparing one employee to P75 while company philosophy targets P50.
• Ignoring wide percentile spreads.
• Using percentiles from different peer groups in the same structure with no rationale.""",
"""Useful language with leadership:
“Moving from P50 to P75 is not just a different benchmark label. It changes the cost basis of the salary structure. We should reserve that positioning for segments where the talent strategy justifies it.”"""
),
"3-2" to LessonDeepDive(
"""Survey aging updates market data from the survey’s effective date to the organization’s chosen reference date. It is necessary because salary surveys are historical snapshots while pay decisions happen later.

The aging assumption should come from a consistent market movement source or company policy, not from whatever rate makes a case look stronger.""",
"""Simple linear aging:
Aged Value = Survey Value × (1 + Annual Movement × Months/12)

Compound aging:
Aged Value = Survey Value × (1 + Annual Movement)^(Months/12)

For short periods, the difference is usually small. The organization should choose one standard method.

Document survey effective date, structure effective date, annual movement assumption and months aged.""",
"""P50 = ₹20 lakh effective January 1.
Annual movement assumption = 8%.
Decision date = July 1, six months later.

Simple aging:
20 × (1 + 0.08 × 6/12)
= 20 × 1.04
= ₹20.8 lakh.

If the organization updates ranges every April, it may age all surveys to April 1 rather than each individual decision date. Consistency matters more than hyper-precision.""",
"""Create an aging calculator in Excel with Survey P50, Effective Date, Target Date and Annual Movement %. Calculate months difference and aged P50.

Test 4%, 8% and 12% annual movement assumptions and explain how sensitive a ₹25 lakh benchmark is after 9 months.""",
"""Common mistakes:
• Aging data twice because a vendor already provided aged values.
• Using different movement rates for similar roles without evidence.
• Aging to each employee’s review date when policy uses one common reference date.
• Confusing salary-budget increase with market movement.""",
"""Useful language with Finance:
“The survey is six months old, so we’ve applied the standard 8% annual market-movement assumption to the common April reference date. That gives a consistent basis across all jobs rather than adjusting case by case.”"""
),
"4-0" to LessonDeepDive(
"""Salary ranges create a controlled space for employee pay progression. The midpoint usually represents the intended market reference for a fully competent employee, while minimum and maximum define the lower and upper boundaries for that grade.

Range design is a structural decision. It must work across many employees and remain sensible when managers hire, promote and reward people.""",
"""Key formulas:
Range Spread = (Maximum - Minimum) / Minimum
Midpoint = usually market reference or designed grade value
Minimum = Midpoint / (1 + half-spread approximation) depending on structure method
Maximum = derived from chosen spread
Midpoint Progression = (Next Midpoint / Current Midpoint) - 1

Test adjacent grades for overlap. Some overlap is normal; excessive overlap can blur career progression.""",
"""Grade G6: ₹14 min, ₹17.5 mid, ₹21 max.
Spread = (21-14)/14 = 50%.

Grade G7: ₹17 min, ₹21.5 mid, ₹26 max.
Midpoint progression = 21.5/17.5 - 1 = 22.9%.

The ranges overlap from ₹17–₹21, which can be healthy: an experienced G6 employee may earn more than a new G7 employee. Promotion should reflect larger job scope, not automatic salary superiority over every person in the lower grade.""",
"""Design six grades with midpoint progression between 12% and 18%. Choose a range spread for each grade, then calculate min and max. Plot midpoints and check whether progression becomes too steep or too flat.

Add three sample employees per grade and test whether promotions create reasonable new salary positions.""",
"""Common mistakes:
• Assuming higher grade always means higher salary than every lower-grade employee.
• Setting ranges entirely around current employees instead of market/job structure.
• Using random midpoint progression by grade.
• Making ranges so narrow that normal career progression causes frequent promotions.""",
"""Useful language with managers:
“Range overlap is intentional. It allows an experienced employee in one grade to earn more than a newly promoted employee in the next grade while still preserving larger long-term opportunity for the higher-level job.”"""
),
"4-1" to LessonDeepDive(
"""Compa-ratio is one of the most frequently used salary-position measures. It tells you salary as a proportion of range midpoint. It is powerful because it standardizes employees across different salary ranges.

But it becomes dangerous when used as an entitlement rule. A 0.85 compa-ratio can be appropriate for someone new to role, while 1.10 can be appropriate for a deep expert.""",
"""Formula:
Compa-Ratio = Salary / Midpoint

Useful bands for analysis, not automatic policy:
Below 0.85: investigate position
0.85–0.95: lower range area
0.95–1.05: near midpoint
1.05–1.15: above midpoint
Above 1.15: investigate position

Always pair with performance, time in role, market ratio and peer position.""",
"""Employee A earns ₹18 lakh, midpoint ₹20 lakh → 0.90.
Employee B earns ₹27 lakh, midpoint ₹24 lakh → 1.125.

If A has just been promoted, 0.90 may be fine. If A has eight years in role and strong performance, it deserves review.
If B is a recognized technical expert in a critical skill, 1.125 may be appropriate. If B has recently moved into the role, investigate how the salary was set.""",
"""In Excel, calculate compa-ratio for 100 sample employees. Create bands using IFS. Build a PivotTable by Grade and Performance showing average compa-ratio.

Then filter “High Performance + Compa < 0.90” and write the additional evidence needed before recommending action.""",
"""Common mistakes:
• Calling <1.00 underpaid and >1.00 overpaid.
• Comparing compa-ratios from poorly designed ranges.
• Forgetting that midpoint moves when structures are updated.
• Using compa-ratio without checking external market position.""",
"""Useful language:
“0.88 tells us where the employee sits in the range. It does not tell us why they are there or what increase they should receive. We need performance, experience, market and peer context before deciding.”"""
),
"4-2" to LessonDeepDive(
"""Range penetration measures how far an employee has progressed from range minimum to range maximum. Unlike compa-ratio, it uses the full range boundaries rather than only midpoint.

It is especially useful when range widths differ across grades or when managers want to understand progression through the range.""",
"""Formula:
Range Penetration = (Salary - Minimum) / (Maximum - Minimum)

Interpret carefully:
0% = at minimum
50% = exactly halfway between minimum and maximum
100% = at maximum

If salary falls below minimum or above maximum, penetration can be below 0% or above 100%, which is a useful exception signal.""",
"""Range: ₹15–₹25 lakh. Salary ₹18 lakh.
Penetration = (18-15)/(25-15) = 30%.

If midpoint is ₹20 lakh, compa-ratio is 0.90. Penetration and compa-ratio describe related but different positions.

Another range with uneven design could produce different penetration even at the same compa-ratio, which is why understanding both metrics helps.""",
"""Create an Excel chart with Salary, Min, Mid, Max, Compa-Ratio and Range Penetration. Add conditional formatting for penetration below 0% and above 100%.

Select five employees with similar compa-ratios but different range penetrations and explain why the metrics differ.""",
"""Common mistakes:
• Assuming 50% penetration always equals 1.00 compa-ratio when range design is asymmetric.
• Capping values at 0% and 100%, which hides out-of-range exceptions.
• Using range penetration to determine performance or merit.""",
"""Useful language:
“Range penetration helps us see progression through the full salary opportunity. It is a positioning metric, not a performance score.”"""
),
"4-3" to LessonDeepDive(
"""Salary compression occurs when pay differences become too small between employees who would normally be expected to have meaningful differentiation—for example new hires versus experienced incumbents, or lower versus higher job levels.

Compression is often a symptom of external market movement, aggressive hiring, frozen internal budgets or inconsistent promotion practices. Treating every case as an individual adjustment can become expensive without fixing the root cause.""",
"""Diagnose compression systematically:
1. Define expected comparisons: same level by tenure, adjacent levels, new hires vs incumbents.
2. Calculate salary ratios or gaps.
3. Identify whether the pattern is concentrated in certain jobs, locations or hiring years.
4. Check market movement and new-hire offer practices.
5. Estimate correction cost.
6. Decide structural versus targeted action.

Track compression over time after intervention.""",
"""Senior Engineer: 7 years in role, ₹22 lakh.
New Senior Engineer: ₹23 lakh.
Lead Engineer: ₹24 lakh.

The experienced Senior is below a new hire, and the Lead premium over the new Senior is only 4.3%. If this pattern exists across many employees, the issue is likely structural.

Possible responses: targeted incumbent adjustments, stronger new-hire offer controls, revised ranges, or a combination.""",
"""Create a compression report:
Employee, Level, Hire Date, Salary, Peer Median, New-Hire Median, Next-Level Minimum and Salary Gap.

Flag cases where experienced incumbents are more than 5% below recent-hire median or where next-level salary gap is below 5%. Review patterns by function.""",
"""Common mistakes:
• Defining compression from one anecdote.
• Giving broad increases without fixing hiring practices.
• Expecting zero overlap between grades.
• Treating any new hire paid more than an incumbent as automatically wrong.""",
"""Useful language with leadership:
“This is not one retention case. The pattern is concentrated in employees hired before the market spike, so we should evaluate a cohort correction and tighten new-hire offer governance rather than solve cases one at a time.”"""
),
"5-0" to LessonDeepDive(
"""Compensation analysis depends on combining data from multiple sources: HRIS employee data, grade/range tables, market surveys, performance files and bonus targets. XLOOKUP reduces manual copy-paste and creates an auditable link between tables.

The real skill is not memorizing the function; it is designing clean keys, handling missing matches and validating outputs.""",
"""Recommended workflow:
1. Convert source ranges to Excel Tables.
2. Choose a stable key such as Grade Code, Survey Job Code or Employee ID.
3. Use XLOOKUP with an explicit “Not Found” return.
4. Count all Not Found results.
5. Investigate duplicates in the lookup table.
6. Spot-check several matches manually.
7. Lock/reference the appropriate columns.

Example:
=XLOOKUP([@Grade],Ranges[Grade],Ranges[Midpoint],"Not Found")""",
"""Employee table has Grade G7. Range table maps G7 to Min ₹18 lakh, Mid ₹22 lakh, Max ₹26 lakh.

Use separate XLOOKUP formulas to retrieve all three values. If one employee has Grade G07 and returns “Not Found,” do not replace it with zero. The mismatch is a data-quality problem that should be fixed at source or in a controlled transformation.""",
"""Build three tables: Employees, Ranges and Market. Use XLOOKUP to add range midpoint and market P50 to Employees.

Create a control cell:
=COUNTIF(Employees[Midpoint],"Not Found")
The workbook should not be considered complete until this is zero or every exception is documented.""",
"""Common mistakes:
• Returning blank or zero for missing matches.
• Looking up on job title when grade code is the real key.
• Ignoring duplicate keys.
• Hard-coding lookup ranges that break when rows are added.
• Pasting values over formulas before review.""",
"""Useful language:
“The model has three unmatched grade codes. I have not forced them into a default range because that would hide the data issue. They are isolated for HRIS correction before recommendations are finalized.”"""
),
"5-1" to LessonDeepDive(
"""SUMIFS, COUNTIFS and AVERAGEIFS turn employee-level data into management questions: How much payroll is in Engineering? How many high performers are below midpoint? What is average pay by location?

These functions are simple but become powerful when used with structured tables and control totals.""",
"""Examples:
Total payroll for Engineering:
=SUMIFS(Employees[Salary],Employees[Function],"Engineering")

Count G7 employees below 0.90 compa:
=COUNTIFS(Employees[Grade],"G7",Employees[Compa],"<0.9")

Average salary for Pune Engineering:
=AVERAGEIFS(Employees[Salary],Employees[Location],"Pune",Employees[Function],"Engineering")

Add a control: SUM of function payrolls should reconcile to total payroll.""",
"""Suppose total payroll is ₹120 crore. SUMIFS by function returns Engineering ₹55 crore, Sales ₹30 crore, Operations ₹20 crore and Corporate ₹15 crore. Total = ₹120 crore, so the segmentation reconciles.

If it totals ₹116 crore, investigate blank function codes or excluded categories before presenting the report.""",
"""Create a management summary using only SUMIFS/COUNTIFS/AVERAGEIFS:
• Headcount by grade
• Payroll by function
• Average compa by location
• Count of high performers below 0.90
• Count above range maximum
• Increase cost by business unit

Add a reconciliation check for headcount and payroll.""",
"""Common mistakes:
• Reporting segmented totals that do not reconcile.
• Mixing text and numeric grade codes.
• Hard-coding criteria into dozens of formulas instead of using reference cells.
• Averaging averages instead of calculating from underlying records.""",
"""Useful language:
“The exception count is 42, but 31 are concentrated in one job family. That tells us the problem is likely structural rather than evenly spread across the company.”"""
),
"5-2" to LessonDeepDive(
"""PivotTables help compensation professionals move quickly from row-level data to patterns. Conditional formatting helps users see exceptions. Together they are useful for exploratory analysis and manager reporting.

The danger is producing attractive dashboards with no control totals or business interpretation. Every chart should answer a compensation question.""",
"""Useful PivotTable designs:
Rows: Grade; Values: Headcount, Avg Salary, Avg Compa
Rows: Job Family; Columns: Performance; Values: Avg Increase %
Rows: Location; Values: Payroll, Avg Market Ratio
Rows: Manager; Values: Override Count and Override Cost

Add slicers for location, function and job family. Always verify the pivot source includes all rows.""",
"""A PivotTable shows average compa-ratio:
Engineering 0.96
Cybersecurity 0.86
Finance 1.01
HR 0.98

The result does not prove cybersecurity is underpaid. Drill down: perhaps the function recently hired many entry-level employees. Add level, tenure and market ratio before recommending action.""",
"""Build one PivotTable per question, not one giant pivot. Required:
1. Where are below-range employees?
2. Where is annual increase budget being spent?
3. Which managers request the most overrides?
4. Which critical-skill groups are below market?

Add one sentence below each pivot stating the insight.""",
"""Common mistakes:
• Showing averages with no headcount.
• Forgetting to refresh pivots after source updates.
• Using color scales that exaggerate tiny differences.
• Presenting charts without an action or interpretation.
• Ignoring outliers hidden by averages.""",
"""Useful language in a review meeting:
“The dashboard highlights cybersecurity, but the drill-down shows the issue is concentrated in G6–G7 employees rather than the whole function. That narrows the intervention and cost.”"""
),
"5-3" to LessonDeepDive(
"""Power Query is a process-control tool as much as a data tool. Compensation cycles repeat every year, and manual cleaning creates inconsistent results, hidden steps and audit risk.

A refreshable query records transformations so the team can replace source files and rerun the same logic.""",
"""A robust Power Query pipeline:
1. Import HRIS employee file.
2. Standardize column names and data types.
3. Trim/clean IDs and text.
4. Remove obvious duplicates using documented keys.
5. Merge range table by Grade.
6. Merge market table by Survey Job Code.
7. Merge performance data by Employee ID.
8. Create validation flags for missing matches.
9. Load final clean table to Excel Data Model or worksheet.
10. Reconcile headcount and payroll to source.""",
"""HRIS exports salary as text with commas, Finance has Employee ID with leading zeros, and market data has inconsistent grade labels.

Instead of correcting rows manually, Power Query can convert data types, pad Employee ID, map grade labels through a reference table and merge datasets. The transformation becomes repeatable and visible in Applied Steps.""",
"""Create a mock Power Query process from three CSVs: Employees, Ranges, Performance.

Required controls:
• Source row count
• Duplicate Employee ID count
• Missing Grade match count
• Missing Performance match count
• Final row count
• Final payroll versus source payroll

Write what should happen if any control fails.""",
"""Common mistakes:
• Removing duplicates without knowing why duplicates exist.
• Replacing nulls with zero indiscriminately.
• Editing query output manually.
• Not documenting source locations.
• Refreshing just before leadership review without validation checks.""",
"""Useful language with your team:
“The goal is not to automate bad data. The query will stop and flag missing matches so we can fix source issues before compensation recommendations are produced.”"""
)
)

fun deepDiveFor(week: Int, lessonIndex: Int): LessonDeepDive? =
    (deepDivesA + deepDivesB)["$week-$lessonIndex"]
