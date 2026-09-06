package com.sushant.pmpstudy.data

data class Chapter(
    val id: String,
    val title: String,
    val subtitle: String,
    val sections: List<Section>
)

data class Section(
    val heading: String,
    val body: String
)

data class Formula(
    val name: String,
    val expression: String,
    val meaning: String
)

data class QuizQuestion(
    val id: String,
    val prompt: String,
    val choices: List<String>,
    val correctIndex: Int,
    val explanation: String
)

object StudyRepository {
    val chapters: List<Chapter> = listOf(
        Chapter(
            id = "exam",
            title = "Exam snapshot",
            subtitle = "Format, domains, and how to study",
            sections = listOf(
                Section(
                    "What you are walking into",
                    "The PMP exam currently uses 180 questions in 230 minutes, with two optional 10-minute breaks. Some items are unscored pretest questions. Expect a mix of multiple-choice, multiple-response, matching, hotspot, and limited fill-in items. Roughly half of the scored content is predictive (plan-driven) and half is agile or hybrid."
                ),
                Section(
                    "Three domains (Exam Content Outline)",
                    "People (~42%): how you lead, coach, resolve conflict, and keep stakeholders engaged.\n\nProcess (~50%): how work is planned, delivered, measured, and closed across predictive, agile, and hybrid approaches.\n\nBusiness Environment (~8%): why the project exists, compliance, benefits, and how the organization and market constrain choices.\n\nThe exam is task-based. Memorizing process names is not enough; you must pick the next professional action in a messy situation."
                ),
                Section(
                    "How this app is meant to be used",
                    "Read a chapter, then do the quiz. Use the formula lab until SPI, CPI, and EAC are automatic. This material is original study commentary aligned to publicly documented PMI exam structure and standard earned-value math. It is not PMI courseware, not an exam dump, and not a copy of any commercial note set."
                )
            )
        ),
        Chapter(
            id = "mindset",
            title = "Decision mindset",
            subtitle = "How PMP items usually want you to think",
            sections = listOf(
                Section(
                    "Default professional posture",
                    "Serve the team and the organization, not your ego. Face problems early. Prefer facilitation over command unless safety, law, or a clear emergency requires a directive call. Document agreements. Keep the sponsor informed about impacts to value, not just activity."
                ),
                Section(
                    "A reliable sequence when a question is messy",
                    "1. Clarify the real problem and who is affected.\n2. Check what the team already agreed (charter, working agreements, Definition of Done, change process).\n3. Involve the people who own the work or the decision.\n4. Analyze options with data (risk, cost, schedule, quality, benefits).\n5. Decide at the lowest appropriate level; escalate only what they cannot resolve.\n6. Communicate the outcome and update artifacts.\n\nAnswers that skip straight to the sponsor, ignore the team, or hide bad news are usually wrong."
                ),
                Section(
                    "Agile-leaning vs predictive-leaning stems",
                    "If the team is iterating, look for servant leadership, backlog conversations, impediment removal, and empirical inspection (reviews, retrospectives). If the work is plan-driven with baselines, look for integrated change control, variance analysis, and formal stakeholder communication. Hybrid items mix both: keep enough ceremony to control what must be controlled, and enough flexibility to learn."
                )
            )
        ),
        Chapter(
            id = "people",
            title = "People domain",
            subtitle = "Leadership, conflict, and stakeholder engagement",
            sections = listOf(
                Section(
                    "Lead the team",
                    "Set a clear purpose, then give the team room to choose how. Match leadership style to the situation: coaching when skills are growing, supporting when motivation is the issue, directing when there is imminent harm. Celebrate outcomes, not heroics. Diversity of thought is a risk-reduction tool, not a slogan."
                ),
                Section(
                    "Conflict",
                    "Most project conflict is about scarce time, unclear roles, or competing stakeholder goals. Start with collaborative problem-solving. Smooth or compromise when the issue is small and speed matters. Use formal escalation when ethics, safety, or contractual rights are at stake. Never let conflict fester in a virtual channel with no live conversation."
                ),
                Section(
                    "Performance and impediments",
                    "Agree on how performance will be seen (team goals first, individual goals second). Remove blockers quickly: wait time, unclear decisions, missing skills, hostile stakeholders. Mentoring and pairing beat public blame. Psychological safety is a quality practice; people hide defects when they fear punishment."
                ),
                Section(
                    "Stakeholders and virtual teams",
                    "Map influence and interest, then tailor communication. High-power, high-interest people need partnership, not status-only email. For distributed teams, make working hours, response expectations, and decision rights explicit. Ground rules belong to the team; the project manager facilitates the conversation and then holds the line."
                )
            )
        ),
        Chapter(
            id = "process",
            title = "Process domain",
            subtitle = "Delivery, control, and closing",
            sections = listOf(
                Section(
                    "Start with why, then plan just enough",
                    "Confirm the business case and charter before detailed planning. Collect requirements from the people who will use or fund the result. Define scope as a product backlog, WBS, or both. Build a schedule and budget that match the delivery approach. Quality is planned in (acceptance criteria, Definition of Done), not inspected in at the end."
                ),
                Section(
                    "Execute and still keep control",
                    "Lead daily work, manage communications, and keep risk, issue, and change logs alive. In predictive work, compare actuals to baselines (earned value, critical path). In agile work, inspect increment quality and throughput each iteration. Changes go through the agreed path: product owner for backlog tradeoffs, change control for baselined documents."
                ),
                Section(
                    "Risk, procurement, and knowledge",
                    "Identify risks early, then choose avoid, mitigate, transfer, or accept. Watch secondary risk after you respond. Procurement needs clear statements of work and a fair seller process. Before closeout, transfer knowledge, confirm acceptance, release resources, and capture lessons while the team is still together."
                )
            )
        ),
        Chapter(
            id = "business",
            title = "Business environment",
            subtitle = "Benefits, compliance, and change outside the team",
            sections = listOf(
                Section(
                    "Projects exist to create benefits",
                    "Outputs are not benefits. A deployed system is an output; reduced cycle time or new revenue is a benefit. Track benefit owners, assumptions, and when value is supposed to appear. If the business case dies, recommend canceling rather than protecting sunk cost."
                ),
                Section(
                    "Compliance and external change",
                    "Legal, security, safety, and contractual constraints are not optional backlog items. Build them into Definition of Done. Scan the environment: regulation, market, mergers, new executives. Organizational change management (training, communication, sponsorship) is part of delivery when users must work differently."
                )
            )
        ),
        Chapter(
            id = "predictive",
            title = "Predictive delivery",
            subtitle = "Process groups and knowledge areas",
            sections = listOf(
                Section(
                    "Five process groups",
                    "Initiating: authorize the project or phase, identify stakeholders, set a high-level vision.\n\nPlanning: progressively elaborate scope, schedule, cost, quality, resources, communications, risk, procurement, and stakeholder engagement.\n\nExecuting: coordinate people and work to produce deliverables.\n\nMonitoring and controlling: measure, compare, recommend corrective action, and run change control.\n\nClosing: confirm completion, archive, release, and celebrate or debrief honestly."
                ),
                Section(
                    "Ten knowledge areas (study map)",
                    "Integration, Scope, Schedule, Cost, Quality, Resources, Communications, Risk, Procurement, Stakeholder. Integration is the glue: charter, project management plan, directed work, knowledge, monitoring, change control, and close. When a question names a baseline, think integration plus the matching knowledge area."
                ),
                Section(
                    "Critical path and float",
                    "The critical path is the longest path through the network; it has the least total float (often zero). Total float is how long an activity can slip without delaying the project finish. Free float is slip that does not delay the next activity. Crashing adds resources (usually cost). Fast-tracking overlaps phases (usually risk)."
                )
            )
        ),
        Chapter(
            id = "agile",
            title = "Agile and hybrid",
            subtitle = "Scrum, Kanban, and mixing with plans",
            sections = listOf(
                Section(
                    "Values that show up on the exam",
                    "Individuals and interactions, working product, customer collaboration, and responding to change. That does not mean 'no documentation' or 'no dates.' It means optimize for learning and customer value, and keep artifacts lean. Servant leaders remove impediments and grow the team’s ability to decide."
                ),
                Section(
                    "Scrum in exam language",
                    "Product owner: value, backlog order, acceptance. Scrum master: process health, coaching, impediments. Developers: how to deliver a done increment. Events: sprint planning, daily scrum, sprint review, retrospective. Artifacts: product backlog, sprint backlog, increment, plus a Definition of Done. Timeboxes protect focus; scope inside a sprint is a conversation, not a silent slip."
                ),
                Section(
                    "Kanban and hybrid",
                    "Kanban visualizes flow, limits work in progress, and manages cycle time. Use it when work is continuous rather than sprint-shaped. Hybrid is common: a predictive charter and budget with iterative delivery inside, or agile product work with a contractual milestone at the edge. Choose based on uncertainty of requirements and of technology, not fashion."
                )
            )
        )
    )

    val formulas: List<Formula> = listOf(
        Formula("Planned Value (PV)", "PV = planned % complete × budget at completion (BAC)", "What the plan said you should have earned by now."),
        Formula("Earned Value (EV)", "EV = actual % complete × BAC", "The budgeted value of work actually finished."),
        Formula("Schedule Variance (SV)", "SV = EV − PV", "Negative means behind the plan in earned value terms."),
        Formula("Cost Variance (CV)", "CV = EV − AC", "Negative means over budget for the work performed."),
        Formula("Schedule Performance Index (SPI)", "SPI = EV / PV", "1.0 on pace; below 1.0 behind; above 1.0 ahead."),
        Formula("Cost Performance Index (CPI)", "CPI = EV / AC", "1.0 on budget; below 1.0 over cost."),
        Formula("Estimate at Completion (typical)", "EAC = BAC / CPI", "Forecast total cost if current cost efficiency continues."),
        Formula("EAC (future at planned rate)", "EAC = AC + (BAC − EV)", "Remaining work behaves like the original estimate."),
        Formula("Estimate to Complete", "ETC = EAC − AC", "How much more you expect to spend."),
        Formula("Variance at Completion", "VAC = BAC − EAC", "Expected over/under at the end."),
        Formula("TCPI (to hit BAC)", "TCPI = (BAC − EV) / (BAC − AC)", "Efficiency the remaining work needs."),
        Formula("Communication channels", "channels = n(n − 1) / 2", "n is the number of people who talk to each other."),
        Formula("PERT expected duration", "tE = (O + 4M + P) / 6", "Optimistic, most likely, pessimistic."),
        Formula("PERT standard deviation", "σ = (P − O) / 6", "Use for probabilistic schedule talk, not false precision."),
        Formula("Total float", "TF = LS − ES  (or LF − EF)", "Slack before the project finish moves.")
    )

    val questions: List<QuizQuestion> = listOf(
        QuizQuestion(
            "q1",
            "A developer reports that a key stakeholder keeps adding work in chat and skipping the agreed backlog meeting. What should the project manager do first?",
            listOf(
                "Tell the stakeholder to stop contacting the team",
                "Facilitate a conversation to reaffirm how work is requested and ordered",
                "Ask the sponsor to replace the stakeholder",
                "Ignore the extras until the next release"
            ),
            1,
            "Start by repairing the working agreement with the people involved. Escalation or replacement is later, not first."
        ),
        QuizQuestion(
            "q2",
            "EV is \$80,000, PV is \$100,000, AC is \$90,000. Which statement is true?",
            listOf(
                "Ahead of schedule and under budget",
                "Behind schedule and under budget",
                "Behind schedule and over budget",
                "On schedule and over budget"
            ),
            2,
            "SV = 80k − 100k = −20k (behind). CV = 80k − 90k = −10k (over budget)."
        ),
        QuizQuestion(
            "q3",
            "A hybrid project has a fixed regulatory milestone in six months and an uncertain user interface. Which approach fits?",
            listOf(
                "Ignore the regulation until the UI is loved",
                "Plan the compliance work to the date; iterate the UI with users before that gate",
                "Force a full waterfall for every feature",
                "Drop the milestone because agile teams do not commit"
            ),
            1,
            "Constraints that are legally real stay predictive. Uncertain product design stays empirical."
        ),
        QuizQuestion(
            "q4",
            "Two senior engineers argue in stand-up and the rest of the team goes quiet. Best next action?",
            listOf(
                "Declare a winner so work can continue",
                "Move the conflict to a private facilitated discussion and restore safety for the group",
                "Document both as poor performers",
                "Cancel stand-ups for a week"
            ),
            1,
            "Protect the team environment, then resolve the content of the conflict with the two people."
        ),
        QuizQuestion(
            "q5",
            "A team of 6 people will add 2 more next week. How many communication channels exist after the change?",
            listOf("15", "21", "28", "36"),
            2,
            "n = 8. Channels = 8×7/2 = 28."
        ),
        QuizQuestion(
            "q6",
            "The business case no longer holds after a market shift. What should the project manager recommend?",
            listOf(
                "Finish the original scope to protect sunk cost",
                "Revisit benefits with the sponsor and consider canceling or pivoting",
                "Hide the news until the next steering meeting in three months",
                "Add overtime to deliver faster"
            ),
            1,
            "Projects exist for benefits. When the case dies, the professional move is an honest reset."
        ),
        QuizQuestion(
            "q7",
            "CPI is 0.8 and SPI is 1.1. The story in one sentence?",
            listOf(
                "Slow and cheap",
                "Fast and cheap",
                "Ahead of plan but spending more than the value earned",
                "Behind plan but under budget"
            ),
            2,
            "SPI > 1 ahead of earned-value schedule; CPI < 1 over cost."
        ),
        QuizQuestion(
            "q8",
            "Who is primarily accountable for ordering the product backlog on a Scrum team?",
            listOf("Project manager", "Scrum master", "Product owner", "Sponsor"),
            2,
            "The product owner is accountable for backlog order and value. The PM (if present in a hybrid org) supports, not replaces, that accountability."
        ),
        QuizQuestion(
            "q9",
            "An activity has ES=4, EF=9, LS=7, LF=12. Total float is:",
            listOf("2", "3", "5", "8"),
            1,
            "TF = LS − ES = 7 − 4 = 3 (same as LF − EF = 12 − 9)."
        ),
        QuizQuestion(
            "q10",
            "A tester finds a defect that would be expensive in production. The team is about to demo. What should happen?",
            listOf(
                "Hide it so the demo looks clean",
                "Surface it, discuss impact with the product owner, and be honest in the review",
                "Blame the developer in the demo",
                "Ship anyway because velocity matters more"
            ),
            1,
            "Transparency and quality beat theatrical demos. The PO decides tradeoffs with facts."
        ),
        QuizQuestion(
            "q11",
            "BAC = 200, CPI = 0.8. Using the typical EAC formula, EAC is:",
            listOf("160", "200", "250", "280"),
            2,
            "EAC = BAC / CPI = 200 / 0.8 = 250."
        ),
        QuizQuestion(
            "q12",
            "A new regulation lands mid-project. First professional move?",
            listOf(
                "Tell the team to ignore it until closeout",
                "Assess impact on scope, cost, schedule, and risk, then follow change/compliance process",
                "Immediately add 20 extra features",
                "Resign from the project"
            ),
            1,
            "Analyze, then use the agreed change and compliance path. Do not silently absorb legal risk."
        ),
        QuizQuestion(
            "q13",
            "Which action best removes an impediment?",
            listOf(
                "Adding the blocker to a slide and hoping executives notice",
                "Working with the team to name the blocker, then using influence to clear it this week",
                "Asking everyone to work weekends",
                "Rewriting the charter"
            ),
            1,
            "Impediment removal is active and near-term, not ceremonial."
        ),
        QuizQuestion(
            "q14",
            "Optimistic 4 days, most likely 6, pessimistic 16. PERT expected duration?",
            listOf("6", "7", "8", "10"),
            1,
            "tE = (4 + 4×6 + 16) / 6 = 44/6 ≈ 7.33, which rounds to 7 in typical exam items that ask for the nearest day."
        ),
        QuizQuestion(
            "q15",
            "When should you fast-track instead of crash?",
            listOf(
                "When you can overlap work and the extra risk is acceptable, without buying more resources",
                "When you have unlimited budget and no risk appetite",
                "Only after the project is already closed",
                "Whenever SPI is above 1.0"
            ),
            0,
            "Fast-tracking overlaps activities (risk). Crashing adds resources (cost). Pick based on which constraint you can spend."
        )
    )
}
