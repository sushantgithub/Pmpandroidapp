package com.sushant.pmpstudy

data class CourseWeek(
    val number: Int,
    val title: String,
    val objective: String,
    val lessons: List<Lesson>,
    val caseStudy: String,
    val assignment: String,
    val knowledgeCheck: List<String>
)

data class Lesson(
    val title: String,
    val body: String
)

data class QuizQuestion(
    val week: Int,
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String
)

data class ExcelLab(
    val title: String,
    val skill: String,
    val formula: String,
    val exercise: String
)

val courseWeeks = listOf(
    CourseWeek(
        1,
        "Thinking Like a Compensation Partner",
        "Shift from processing pay decisions to making defensible recommendations that balance market, equity, performance, retention and affordability.",
        listOf(
            Lesson("1.1 What compensation is trying to achieve", """
Compensation is not simply about deciding how much an employee should be paid. A compensation professional balances external competitiveness, internal equity, performance differentiation, employee retention and organizational affordability.

Example: a software engineer earns ₹18 lakh, the market reference is ₹22 lakh, and the manager wants ₹25 lakh because the employee may leave. Before recommending ₹25 lakh, review the employee's grade, peer salaries, experience, performance, skill scarcity, salary range, replacement difficulty and budget.

The important habit is to convert a manager's request into a business question. Instead of asking only "Can we afford ₹25 lakh?", ask "What problem are we solving, what evidence supports the adjustment, what precedent will it create, and what will happen to peer equity?"
""".trimIndent()),
            Lesson("1.2 Compensation philosophy", """
A compensation philosophy states how the company wants to pay relative to the relevant talent market. A company may target P50 for most jobs and P65–P75 for selected critical skills.

If market data for a role is P25 ₹18 lakh, P50 ₹22 lakh and P75 ₹27 lakh, an employee new to the role may be appropriately positioned near ₹19 lakh, while an experienced high performer may sit around ₹24 lakh. The market reference is not an automatic entitlement.

A strong philosophy also defines how the company treats promotions, hot skills, geographic differences, performance, variable pay, and exceptions.
""".trimIndent()),
            Lesson("1.3 Internal equity and external competitiveness", """
Internal equity asks whether pay differences between comparable employees are explainable. External competitiveness asks whether the organization can attract and retain people in the relevant labor market.

Two Senior Software Engineers can reasonably have different salaries when experience, performance, critical skills or role scope differ. The objective is not identical pay. The objective is explainable and defensible pay.

When an unexplained difference appears, investigate before correcting it. Historical hiring conditions, prior promotions, location, acquired-company legacy pay and scarce-skill premiums can all create differences.
""".trimIndent()),
            Lesson("1.4 Total Rewards", """
Total Rewards goes beyond base salary. It can include fixed pay, annual bonus, sales incentives, long-term incentives or RSUs, retirement benefits, insurance, recognition, learning opportunities, flexibility and career progression.

Employee A may have ₹24 lakh base, ₹2 lakh bonus and no equity. Employee B may have ₹21 lakh base, ₹3 lakh bonus and ₹6 lakh equity. Looking only at base pay makes Employee A appear better paid, while total rewards tells a different story.

Senior compensation work therefore requires understanding both cash and non-cash rewards and how each supports attraction, retention and performance.
""".trimIndent())
        ),
        "Priya earns ₹17 lakh against a ₹21 lakh market median, has 3 years in role and exceeds expectations. Amit earns ₹21 lakh, meets expectations and has 6 years in role. Neha earns ₹25 lakh, exceeds expectations and has 8 years in role. Only ₹3 lakh of annual salary budget is available. Decide how you would allocate the budget and what additional data you need.",
        "Write a one-page compensation philosophy for a 4,000-employee IT company. State target market position, treatment of critical skills, promotion principles, performance differentiation and exception governance.",
        listOf(
            "Why is a market median not an automatic salary target for every employee?",
            "What evidence would you request before approving a retention increase?",
            "How is Total Rewards different from base salary?"
        )
    ),
    CourseWeek(
        2,
        "Job Architecture and Job Leveling",
        "Create consistent job families, career levels and evaluation logic so market benchmarking and pay structures are reliable.",
        listOf(
            Lesson("2.1 Why job architecture matters", """
Companies often accumulate inconsistent titles such as Senior Engineer, Software Engineer III, Technical Specialist and Lead Developer. Titles alone do not tell you job size.

Job architecture creates a common language: job family, sub-family, career track, level and grade. A Software Engineering family might progress from Associate Engineer to Engineer, Senior Engineer, Lead, Principal and Distinguished Engineer.

The architecture should describe differences in scope, complexity, autonomy, impact and required expertise. This makes benchmarking and internal comparisons much more consistent.
""".trimIndent()),
            Lesson("2.2 Individual contributor and management tracks", """
Technology companies should not force strong technical employees into people management simply to progress.

A parallel architecture might have an IC track of Engineer → Senior → Lead → Principal → Distinguished and a management track of Engineering Manager → Senior Manager → Director → VP.

A Principal Engineer may have compensation comparable to a manager or director because the organization values technical impact, not only headcount responsibility.
""".trimIndent()),
            Lesson("2.3 Job evaluation", """
Job evaluation determines relative job size. Common factors include knowledge, problem-solving, business impact, decision authority, leadership responsibility, scope, risk and technical complexity.

Do not confuse job evaluation with employee performance. A high-performing employee does not automatically make a job larger. Evaluate the job as designed, then separately evaluate the person within that job.

For example, a Global Compensation Lead may have no direct reports but still influence pay decisions for 20,000 employees. Organizational impact can be large without large team size.
""".trimIndent())
        ),
        "A business unit has six titles that appear to perform similar software work. Managers insist all titles are unique. Build a leveling workshop approach that focuses on actual scope and complexity rather than title prestige.",
        "Create five career levels each for Software Engineering, Data Science, Finance, HR and Cybersecurity. For every level, define scope, complexity, decision authority and typical impact.",
        listOf(
            "Why should job title not be the primary basis for job evaluation?",
            "What is the difference between evaluating a job and evaluating an employee?",
            "Why are dual IC and management tracks useful in technology companies?"
        )
    ),
    CourseWeek(
        3,
        "Market Benchmarking and Salary Surveys",
        "Match jobs to reliable market data, understand percentiles, age survey data and distinguish market signals from automatic pay actions.",
        listOf(
            Lesson("3.1 Matching jobs correctly", """
Good market matching is based on job content, not title. If an internal title is AI Solutions Architect but the survey contains Solutions Architect, Machine Learning Engineer, Enterprise Architect and AI Engineer, compare responsibilities, skills, scope, level and decision authority.

A poor match creates misleading salary data. Document the rationale for each benchmark match and use multiple survey cuts when appropriate.

Benchmark quality is especially important for hybrid and emerging technology roles where exact external matches may not exist.
""".trimIndent()),
            Lesson("3.2 Percentiles", """
P25 means 25% of reported observations are below that value and 75% are above it. P50 is the median. P75 means 75% are below and 25% above.

If market data is P25 ₹16 lakh, P50 ₹20 lakh, P75 ₹25 lakh and P90 ₹30 lakh, the organization chooses which market position supports its talent strategy.

Do not assume P75 is automatically better. Higher market positioning increases recurring payroll cost and may not be necessary for every role.
""".trimIndent()),
            Lesson("3.3 Aging survey data", """
Survey data is usually collected at a point in time. If the effective date is January 1 and the organization is making decisions on July 1, market movement may need to be reflected.

A simplified approach with 8% annual movement for six months is approximately 4%. A ₹20 lakh reference becomes about ₹20.8 lakh.

Real organizations may use compound aging, different movement assumptions by country or job family, and a defined common structure effective date.
""".trimIndent())
        ),
        "A survey provides a strong match for only 60% of your technology jobs. For the remaining jobs, decide when to use blended matches, internal relativities or additional surveys.",
        "Build a 20-job benchmarking table with internal title, survey match, match quality, P25, P50, P75, current average pay and market ratio.",
        listOf(
            "Why can two companies legitimately use different market percentiles?",
            "When would you reject a survey match even if the title looks similar?",
            "What is survey aging and why is it used?"
        )
    ),
    CourseWeek(
        4,
        "Salary Structures",
        "Design and interpret salary ranges, compa-ratio, range penetration, midpoint progression and compression.",
        listOf(
            Lesson("4.1 Minimum, midpoint and maximum", """
A salary range normally has a minimum, midpoint and maximum. The midpoint often reflects the organization's chosen market reference for a fully competent employee, while minimum and maximum create room for progression.

A range of ₹15 lakh minimum, ₹20 lakh midpoint and ₹25 lakh maximum does not mean every employee should eventually reach ₹25 lakh. Employees progress based on capability, performance, time in role, market movement and organization policy.

Range design should also support sensible differences between adjacent grades.
""".trimIndent()),
            Lesson("4.2 Compa-ratio", """
Compa-ratio = Current Salary ÷ Range Midpoint.

An employee earning ₹18 lakh in a range with ₹20 lakh midpoint has a compa-ratio of 0.90. An employee earning ₹22 lakh has 1.10.

Compa-ratio is a positioning indicator, not a verdict. A new employee may appropriately sit below 1.00. A long-tenured expert may appropriately sit above 1.00.
""".trimIndent()),
            Lesson("4.3 Range penetration", """
Range penetration = (Salary - Minimum) ÷ (Maximum - Minimum).

With salary ₹18 lakh, minimum ₹15 lakh and maximum ₹25 lakh, penetration is 30%.

Range penetration shows where the salary sits between minimum and maximum. It is useful when comparing employees across ranges with different widths.
""".trimIndent()),
            Lesson("4.4 Salary compression", """
Compression occurs when pay differences between employees or levels become too small. Example: experienced Senior Engineer ₹22 lakh, newly hired Senior Engineer ₹23 lakh, Lead Engineer ₹24 lakh.

Compression often appears when external market pay rises faster than internal pay or when new-hire offers are aggressive. Diagnose the pattern before applying broad corrections.
""".trimIndent())
        ),
        "You find that newly hired G7 employees are often paid above experienced G7 employees and close to G8 salaries. Decide whether the issue is isolated, market-driven or structural.",
        "Design eight salary grades. Calculate minimum, midpoint, maximum, range spread and midpoint progression. Then calculate compa-ratio and range penetration for 30 sample employees.",
        listOf(
            "What does a compa-ratio of 0.90 tell you, and what does it not tell you?",
            "How does range penetration differ from compa-ratio?",
            "What business conditions can create salary compression?"
        )
    ),
    CourseWeek(
        5,
        "Excel for Compensation Analytics",
        "Use Excel as a compensation analysis tool rather than only a reporting tool.",
        listOf(
            Lesson("5.1 XLOOKUP and structured data", """
Compensation files often combine employee data with range tables, market data and performance data. XLOOKUP is ideal for retrieving grade midpoint, market median, bonus target or location differential.

Example:
=XLOOKUP(A2,RangeTable[Grade],RangeTable[Midpoint],"Not Found")

Use clean tables and unique lookup keys. Avoid manual copy-paste because it creates audit and refresh problems.
""".trimIndent()),
            Lesson("5.2 SUMIFS, COUNTIFS and AVERAGEIFS", """
Use SUMIFS to calculate payroll for a segment, COUNTIFS to count exceptions, and AVERAGEIFS to compare average pay.

Examples:
=SUMIFS(SalaryRange,GradeRange,"G7")
=COUNTIFS(GradeRange,"G7",CompaRange,"<0.90")
=AVERAGEIFS(SalaryRange,LocationRange,"Pune",JobFamilyRange,"Engineering")

These functions make manager and leadership questions answerable quickly.
""".trimIndent()),
            Lesson("5.3 PivotTables and conditional formatting", """
PivotTables let you summarize headcount, payroll, average salary, average compa-ratio and increase percentages by grade, location or function.

Conditional formatting should highlight actionable patterns: below-range employees, above-range employees, high performers below midpoint, large increases or missing market matches.

The purpose is not decoration. It is faster anomaly detection.
""".trimIndent()),
            Lesson("5.4 Power Query mindset", """
Power Query is useful when recurring data comes from HRIS exports, market files and finance templates. Instead of manually cleaning files every cycle, build repeatable transformation steps.

Typical steps include renaming columns, changing data types, removing duplicates, merging tables, replacing blanks, and appending monthly files. A refreshable process improves consistency and auditability.
""".trimIndent())
        ),
        "HR sends employee data, Finance sends a payroll file and the survey vendor sends market data. All three use different identifiers and formats. Design a repeatable Excel/Power Query workflow.",
        "Create an employee compensation workbook with XLOOKUP, SUMIFS, COUNTIFS, PivotTables, conditional formatting, market ratio, compa-ratio and range penetration.",
        listOf(
            "When should XLOOKUP return an error flag rather than a blank?",
            "What compensation questions are PivotTables especially good at answering?",
            "Why is a refreshable Power Query process stronger than repeated manual cleanup?"
        )
    ),
    CourseWeek(
        6,
        "Annual Salary Review",
        "Design merit matrices, allocate salary budget, analyze manager overrides and control cycle cost.",
        listOf(
            Lesson("6.1 Merit budget", """
If annual payroll is ₹100 crore and the salary increase budget is 7%, the organization has approximately ₹7 crore of recurring annual increase capacity.

Managers may collectively request more than the budget. Compensation's role is to direct limited money toward the outcomes the company values while staying within governance.

Budget analysis should show recommended increase cost, promotion cost, market-adjustment cost, total new payroll and variance from budget.
""".trimIndent()),
            Lesson("6.2 Merit matrix", """
A merit matrix can combine performance and salary position. For example, an Exceptional performer below 0.90 compa-ratio may receive a larger guideline than a Meets Expectations employee already above 1.10.

The matrix is a guideline, not a substitute for judgment. It should be calibrated against budget and tested for unintended outcomes.

Always model the population before publishing manager guidelines.
""".trimIndent()),
            Lesson("6.3 Manager overrides", """
If a matrix suggests 6% and a manager requests 15%, ask what problem the extra amount solves. Promotion? Market correction? Retention? Internal equity? Critical skill?

Require evidence for overrides and track patterns by manager, function and reason. Otherwise exceptions can gradually replace the compensation policy.
""".trimIndent())
        ),
        "The merit matrix generates 7.8% average increases but Finance approved 6.5%. Decide how you would recalibrate without destroying performance differentiation.",
        "Model a 500-employee annual cycle. Build a merit matrix, recommendation formula, override field, budget dashboard and manager exception report.",
        listOf(
            "Why should a merit matrix be modeled before launch?",
            "What is the risk of uncontrolled manager overrides?",
            "Which costs should be separated in annual compensation-cycle reporting?"
        )
    ),
    CourseWeek(
        7,
        "Promotions, Retention and Variable Pay",
        "Evaluate promotion positioning, counter-offer requests and incentive payouts with internal and external context.",
        listOf(
            Lesson("7.1 Promotion increases", """
A promotion should be evaluated against the new job and new salary range, not only against the employee's old salary.

If a G6 employee earns ₹18 lakh and the G7 range starts at ₹20 lakh, a standard 10% increase produces ₹19.8 lakh, still below the new minimum. Compensation should review policy, new range positioning, peers and market before finalizing.

Avoid using one fixed promotion percentage for every situation.
""".trimIndent()),
            Lesson("7.2 Retention adjustments", """
A counter-offer request should trigger analysis, not automatic matching. Review criticality, performance, market position, peer equity, range maximum, external offer credibility, replacement cost and the employee's reason for leaving.

Salary may not solve poor management, limited career growth or role dissatisfaction. Retention increases also create precedent and may shift peer equity.
""".trimIndent()),
            Lesson("7.3 Variable pay", """
Variable pay connects reward with company, team or individual performance.

Example: ₹30 lakh base × 15% target bonus × 110% company factor × 120% individual factor = ₹5.94 lakh payout.

Plan design should define target opportunity, performance measures, threshold, target, maximum, eligibility and governance. Always test payout curves under multiple business scenarios.
""".trimIndent())
        ),
        "A cloud-security specialist earns ₹28 lakh, market median ₹25 lakh, is exceptional and has an external offer of ₹36 lakh. The manager wants to match. Prepare a recommendation that considers total rewards, range maximum and replacement risk.",
        "Analyze 20 promotions and 10 retention cases. Then build a bonus calculator with target, company factor, individual factor and payout caps.",
        listOf(
            "Why can a standard promotion percentage create a bad outcome?",
            "What evidence should support a retention exception?",
            "What controls are needed in variable-pay plan design?"
        )
    ),
    CourseWeek(
        8,
        "Pay Equity and Compensation Analytics",
        "Identify meaningful pay patterns and distinguish explainable differences from unexplained differences requiring investigation.",
        listOf(
            Lesson("8.1 Pay equity analysis", """
A raw average pay difference between groups does not by itself establish inequity. Compare employees at appropriate levels of similarity: job family, grade, role, location, experience, performance and other legitimate factors.

The goal is to identify unexplained differences that remain after relevant factors are considered.

For formal legal or statistical work, partner with qualified legal and analytics specialists. Compensation's role is to maintain clean data, sensible comparison groups and disciplined investigation.
""".trimIndent()),
            Lesson("8.2 Outliers", """
An outlier is a signal, not a conclusion. If most G7 employees earn ₹20–₹24 lakh and one earns ₹14 lakh, investigate. If another earns ₹35 lakh, investigate.

Possible reasons include recent acquisition, demotion protection, expatriate arrangements, scarce skills, incorrect grade mapping, data errors or historical exceptions.

Document the reason for valid exceptions.
""".trimIndent()),
            Lesson("8.3 High-value segments", """
Useful compensation segments include high performers below midpoint, employees below range minimum, employees above maximum, critical skills below market, new hires paid above experienced peers, and employees with repeated low increases.

The best analysis connects each segment with an action: monitor, correct, validate, escalate or document.
""".trimIndent())
        ),
        "Your dashboard shows one employee group has lower average pay. Design the next five analytical steps before drawing any conclusion.",
        "Create review flags for below range, above range, priority review, critical skill below market and potential compression. Summarize counts and payroll impact.",
        listOf(
            "Why is an outlier not automatically a compensation problem?",
            "What factors should be controlled before comparing average pay?",
            "What makes an analytics flag useful to a Compensation Partner?"
        )
    ),
    CourseWeek(
        9,
        "Critical Skills, Geographic Pay and Workforce Trends",
        "Build consistent responses to hot-skill premiums, location differences and remote-work compensation questions.",
        listOf(
            Lesson("9.1 Critical skills", """
AI/ML, cybersecurity, cloud, data engineering and specialized product skills can create market pressure. The organization may respond with different ranges, skill premiums, retention allowances, variable pay or targeted market positioning.

Permanent base-pay increases create recurring cost. Temporary premiums can be easier to remove when scarcity changes, but they require clear eligibility and governance.

Choose the mechanism deliberately rather than solving each case independently.
""".trimIndent()),
            Lesson("9.2 Geographic pay", """
Organizations may use national pay, location zones, city differentials or local market ranges. No single model fits every company.

The important principles are consistency, clear rationale, reliable market data and understandable employee communication.

A location policy should define what happens when employees move, work remotely, transfer internationally or change assigned work location.
""".trimIndent()),
            Lesson("9.3 Remote work", """
Remote work makes compensation policy visible. Questions include whether pay follows employee residence, assigned office, hiring market or role market.

Define the philosophy before individual cases arise. Otherwise repeated exceptions can create inequity and employee-relations risk.

Review the policy periodically because talent markets and remote-work practices change.
""".trimIndent())
        ),
        "A Bengaluru AI team is 20% below a fast-rising market, while other technology functions are near target. Decide whether to change the entire salary structure or use a targeted mechanism.",
        "Design a critical-skill premium framework and a four-zone geographic pay policy. Include eligibility, review frequency, movement rules and governance.",
        listOf(
            "When might a temporary skill premium be preferable to a permanent base increase?",
            "What should a geographic-pay policy say about employee moves?",
            "Why is consistency especially important in remote-work compensation?"
        )
    ),
    CourseWeek(
        10,
        "Becoming a Strategic Compensation Partner",
        "Turn analysis into clear recommendations for managers, HR leaders and Finance.",
        listOf(
            Lesson("10.1 From data to recommendation", """
A junior analyst may report: "The employee's compa-ratio is 0.82."

A Compensation Partner adds context and action: "The employee is at 82% of midpoint and approximately 15% below market. Performance has been consistently strong and two recently hired peers are paid 10–12% higher. I recommend an adjustment to ₹20.5 lakh, improving market position while preserving differentiation from more experienced peers."

A strong recommendation contains fact, interpretation, risk, action and cost.
""".trimIndent()),
            Lesson("10.2 Partnering with managers", """
When a manager says "I need 20% for my employee," first identify the business problem: promotion, retention, market correction, high performance or internal equity.

Use questions to convert emotion into evidence. Then explain the recommendation in business terms, not compensation jargon.

You may disagree with a manager while still showing that the request was understood and evaluated fairly.
""".trimIndent()),
            Lesson("10.3 Partnering with Finance and leadership", """
Finance focuses on total cost, recurring expense, budget variance, forecast and business return. Leadership focuses on talent risk, competitiveness, fairness, critical capabilities and affordability.

Translate compensation analysis into those outcomes. Avoid presenting a spreadsheet without a conclusion.

The most useful executive summary normally states key findings, top risks, cost of recommended actions, alternatives and decisions required.
""".trimIndent())
        ),
        "A business leader asks for ₹2 crore of off-cycle increases for a critical division. Prepare a five-slide storyline: business problem, evidence, employee segments, options/cost and recommendation.",
        "Complete the capstone: analyze a 500-employee organization with a 6.5% increase budget. Identify range issues, market issues, compression, critical-skill risk and promotion cases. Produce a dashboard and one-page leadership recommendation.",
        listOf(
            "What are the five elements of a strong compensation recommendation?",
            "How should you respond when a manager asks for a percentage before explaining the problem?",
            "What does Finance need to see in a compensation proposal?"
        )
    )
)

val excelLabs = listOf(
    ExcelLab("Compa-Ratio", "Salary positioning", "=CurrentSalary/RangeMidpoint", "Calculate compa-ratio for 30 employees and flag values below 0.85 and above 1.15."),
    ExcelLab("Range Penetration", "Position within range", "=(Salary-Minimum)/(Maximum-Minimum)", "Calculate penetration for each employee and compare it with compa-ratio."),
    ExcelLab("Market Ratio", "External positioning", "=CurrentSalary/MarketP50", "Identify employees below 0.90 market ratio, then add performance and experience before recommending action."),
    ExcelLab("XLOOKUP", "Join range data", "=XLOOKUP(Grade,RangeTable[Grade],RangeTable[Midpoint],\"Not Found\")", "Pull midpoint, minimum and maximum from a grade table. Investigate all Not Found records."),
    ExcelLab("COUNTIFS", "Exception counts", "=COUNTIFS(GradeRange,\"G7\",CompaRange,\"<0.90\")", "Count low-positioned employees by grade, location and job family."),
    ExcelLab("SUMIFS", "Budget analysis", "=SUMIFS(IncreaseAmount,FunctionRange,\"Engineering\")", "Calculate annual increase cost by function and compare with approved budget."),
    ExcelLab("Merit Increase", "Annual review", "=CurrentSalary*RecommendedIncreasePct", "Calculate increase amount, new salary and total budget impact for 500 employees."),
    ExcelLab("Bonus Payout", "Variable pay", "=BaseSalary*TargetBonus*CompanyFactor*IndividualFactor", "Model payouts at threshold, target and maximum performance."),
    ExcelLab("Review Flag", "Exception logic", "=IF(Salary<Min,\"Below Range\",IF(Salary>Max,\"Above Range\",\"Normal\"))", "Extend the formula to flag high performers below midpoint and critical skills below market."),
    ExcelLab("PivotTable", "Management reporting", "Rows: Grade | Values: Headcount, Avg Salary, Avg Compa, Payroll", "Add filters for location and function, then explain the three most important patterns.")
)

val quizQuestions = listOf(
    QuizQuestion(1, "What is the strongest reason not to automatically pay every employee at market P50?", listOf("P50 is always too expensive", "Market data is only one input among role, experience, performance and internal equity", "P50 applies only to managers", "Salary surveys cannot be used for IT roles"), 1, "Market position is a reference. Individual pay still depends on job, experience, performance, internal equity and policy."),
    QuizQuestion(1, "Which statement best describes Total Rewards?", listOf("Base salary only", "Salary plus statutory tax", "The broader combination of pay, incentives, benefits and other rewards", "Only cash received monthly"), 2, "Total Rewards includes fixed pay, variable pay, benefits, long-term incentives and other value elements."),
    QuizQuestion(2, "What should job leveling primarily evaluate?", listOf("Employee popularity", "Actual job scope, complexity and impact", "Job title length", "Manager preference"), 1, "Job architecture should be based on the work, not title prestige or employee performance."),
    QuizQuestion(2, "Why use dual IC and management tracks?", listOf("To reduce salaries", "To let technical experts progress without becoming people managers", "To remove job grades", "To avoid market benchmarking"), 1, "Parallel tracks recognize that technical impact can grow without direct-report responsibility."),
    QuizQuestion(3, "A survey title exactly matches your internal title. What should you do next?", listOf("Accept the match automatically", "Check responsibilities, scope, level and skills", "Use P90", "Ignore the survey"), 1, "Title matching alone is not enough; validate actual job content."),
    QuizQuestion(3, "Why might survey data be aged?", listOf("To make it older", "To estimate market movement from survey effective date to decision date", "To reduce sample size", "To convert salary to bonus"), 1, "Aging updates older market observations to a chosen effective date using an assumed movement rate."),
    QuizQuestion(4, "An employee earns ₹18 lakh and midpoint is ₹20 lakh. What is compa-ratio?", listOf("0.80", "0.90", "1.00", "1.11"), 1, "18 ÷ 20 = 0.90."),
    QuizQuestion(4, "What does a compa-ratio below 1.00 prove?", listOf("The employee is underpaid", "The employee must receive an increase", "Only that salary is below range midpoint", "The range is incorrect"), 2, "Compa-ratio is positioning information, not a conclusion about fairness or action."),
    QuizQuestion(5, "Which Excel function is best suited to retrieve a midpoint from a grade table?", listOf("XLOOKUP", "LEFT", "ROUND", "CONCAT"), 0, "XLOOKUP is designed to retrieve matching values from reference tables."),
    QuizQuestion(5, "Why use conditional formatting in compensation analysis?", listOf("To make the file colorful", "To highlight meaningful exceptions and patterns", "To replace formulas", "To secure payroll data"), 1, "Conditional formatting is useful when it helps analysts spot actionable anomalies."),
    QuizQuestion(6, "A merit matrix produces 7.8% but budget is 6.5%. What should Compensation do?", listOf("Ignore budget", "Give everyone the same 6.5%", "Recalibrate guidelines while preserving intended differentiation", "Cancel the cycle"), 2, "Model and recalibrate the matrix so guidelines align with budget without eliminating strategy."),
    QuizQuestion(6, "What is the first question when a manager requests a 15% override?", listOf("Who approved it?", "What business problem are we solving?", "Can the employee resign?", "What is the manager's grade?"), 1, "Understanding whether the request is promotion, market, retention or equity driven makes the analysis focused."),
    QuizQuestion(7, "Why can a fixed 10% promotion increase be problematic?", listOf("Promotions never need increases", "It may leave the employee poorly positioned in the new range", "10% is legally prohibited", "It always exceeds budget"), 1, "Promotion pay should be evaluated against the new job, range and peers."),
    QuizQuestion(7, "Which is NOT enough by itself to justify matching an external offer?", listOf("The manager asks", "Criticality and replacement risk", "Market and peer analysis", "Range and total-reward position"), 0, "A manager request alone is not evidence; retention decisions need broader context."),
    QuizQuestion(8, "A raw average pay difference between two groups means:", listOf("Discrimination is proven", "Nothing should be investigated", "Further comparable analysis is needed before drawing conclusions", "Everyone needs the same salary"), 2, "Compare relevant job, level, location, experience and other factors before interpreting a raw difference."),
    QuizQuestion(8, "What is the best interpretation of an outlier?", listOf("Automatically wrong", "A signal requiring investigation", "Always a data error", "Always a high performer"), 1, "Outliers can be valid or problematic; investigate and document the reason."),
    QuizQuestion(9, "When might a temporary skill premium be useful?", listOf("When scarcity may change and the company wants targeted temporary differentiation", "For every employee", "To replace job architecture", "When no market data exists"), 0, "A temporary premium can address time-bound scarcity without permanently raising base salary."),
    QuizQuestion(9, "What is most important in geographic pay policy?", listOf("A unique rule for each employee", "Consistency and a clear rationale", "Always paying the same globally", "Only cost of living"), 1, "Different philosophies can work, but the policy should be consistent, explainable and evidence-based."),
    QuizQuestion(10, "Which is the strongest compensation recommendation?", listOf("Compa-ratio is 0.82", "Give 20%", "The employee is below midpoint and market, strong performance and peer evidence support a costed adjustment", "Manager requested more"), 2, "A strong recommendation links facts, interpretation, risk, action and cost."),
    QuizQuestion(10, "What does Finance most need from a compensation proposal?", listOf("Only employee names", "Recurring cost, budget variance and forecast impact", "Only job titles", "Only market percentile"), 1, "Compensation decisions need to be translated into financial impact and budget implications.")
)
