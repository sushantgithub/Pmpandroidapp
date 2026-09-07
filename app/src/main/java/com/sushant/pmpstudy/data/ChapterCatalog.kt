package com.sushant.pmpstudy.data

private fun s(heading: String, body: String, kind: SectionKind = SectionKind.BODY) =
    Section(heading, body, kind)

object ChapterCatalog {
    val all: List<Chapter> = listOf(
        Chapter(
            "overview",
            "Exam overview",
            "ECO domains, format, and which guide to use",
            "Start here",
            listOf(
                s(
                    "What the exam actually tests",
                    "The PMP exam follows the PMI Exam Content Outline (ECO), not a memorization contest of process IDs. You get 180 questions in 230 minutes, including a handful of unscored pretest items. Two optional 10-minute breaks are built in. Items include multiple choice, multiple response, matching, hotspot, limited fill-in, and drag-and-drop. Delivery is online-proctored or at a test center. PMI does not publish a raw passing score; aim for Target or Above Target in every domain."
                ),
                s(
                    "Domain mix (weightings)",
                    "People ~42%: lead, coach, resolve conflict, keep stakeholders engaged.\nProcess ~50%: plan, deliver, measure, and close work in predictive, agile, and hybrid settings.\nBusiness Environment ~8%: compliance, benefits, strategy, and external change.\nAcross those domains, expect roughly half the scored items to feel predictive and half agile or hybrid."
                ),
                s(
                    "PMBOK editions in one glance",
                    "PMBOK 6: 49 processes, 5 process groups, 10 knowledge areas, ITTO-heavy, predictive-leaning.\nPMBOK 7: 12 principles, 8 performance domains, outcomes and tailoring.\nPMBOK 8 (2025): 6 principles, 7 performance domains, about 40 non-prescriptive processes inside domains, 5 focus areas (the classic process groups renamed), plus AI, sustainability, and digital delivery themes. Knowledge areas are mapped into those domains rather than standing alone."
                ),
                s(
                    "How to use a guide on exam day",
                    "The exam is still ECO-based. Use current PMI publications as context, but answer as a professional who can pick the next useful action in a messy situation. Do not freeze if a stem uses PMBOK 6 vocabulary (WBS, CCB, ITTO) or PMBOK 8 vocabulary (performance domain, focus area). The behavior PMI wants is consistent: clarify, involve the right people, analyze impact, then act through the agreed process.",
                    SectionKind.WARN
                )
            )
        ),
        Chapter(
            "mindset",
            "PM mindset",
            "How to think before you pick an answer",
            "Start here",
            listOf(
                s(
                    "Default posture",
                    "Be proactive, serve the team, and protect value for the customer and organization. A large share of a project manager’s time is communication. Do not skip agreed process or documentation to look fast. Do not assign blame. Do not dump unsolved problems on the sponsor. Gather enough facts to decide, then decide at the lowest competent level. Escalate only what the team cannot resolve."
                ),
                s(
                    "A.R.T. sequence for messy stems",
                    "Assess: name the real problem, who is affected, and what data you still need.\nReview: check charter, plan, working agreements, backlog rules, risk/issue logs, and baselines.\nTake action: involve the people who own the work, update artifacts, and communicate the outcome.\nIf two answers look good, the one that analyzes or aligns stakeholders usually beats the one that immediately crashes, cuts scope, or emails the sponsor."
                ),
                s(
                    "Risk versus issue",
                    "Risk = uncertain future event (may, might, could). First: capture it in the risk register, then analyze and plan a response.\nIssue = already happening. First: issue log, then resolve, then capture lessons if it is closed.\nWhen a risk occurs, it becomes an issue. Do not mix the logs."
                ),
                s(
                    "Keyword habits",
                    "Do / do first / do next: assess impact, then the smallest professional action.\nHave done: planning, risk process, communications that should already exist.\nNever do: fire people as a first move, skip the CCB, hide defects, ignore safety or ethics.\nNew law or regulation: treat as a constraint/risk, assess compliance impact, then change control.\nSafety: stop work that is unsafe, then analyze.\nClosure order: formal acceptance → lessons learned / knowledge transfer → transition → release the team."
                ),
                s(
                    "Conflict preference",
                    "Collaborate / problem-solve is the default (win-win).\nCompromise when time is short and both can give a little.\nSmooth only as a temporary calm-down.\nForce only for safety, legal, or true emergency.\nWithdraw is almost never the exam’s best first move.\nTalk with people privately first when emotions are high, then bring parties together. Face-to-face (or live video) beats a spicy chat thread."
                )
            )
        ),
        Chapter(
            "question-types",
            "Question types and tactics",
            "How to read stems without falling for traps",
            "Start here",
            listOf(
                s(
                    "Item styles",
                    "Situational: read the last sentence and the choices first, then the story. Apply A.R.T.\nMultiple choice: eliminate extremes and blame.\nMultiple response: pick the set that is proactive and complete, not overlapping duplicates.\nFormula: compute calmly; mark and return if the arithmetic is messy.\nAbsolute words (always, never, all, none) are often traps unless the topic is safety, law, or ethics.\nDouble negatives: rewrite in plain English before you choose."
                ),
                s(
                    "Name the delivery approach first",
                    "Agile clues: iteration, increment, product owner, scrum master, sprint, retrospective, backlog, velocity, daily scrum, WIP.\nPredictive clues: baseline, CCB, WBS, network diagram, EV/SPI/CPI, SOW, RFP, phase gate.\nHybrid: a fixed constraint (date, regulation, contract) plus empirical product work. Honor the constraint; keep learning where uncertainty lives."
                ),
                s(
                    "Process-group verbs",
                    "Planning: plan, estimate, define, develop.\nExecuting: manage, conduct, implement, acquire.\nMonitor and control: monitor, control, validate, measure.\nClosing: finalize, close, complete, sign-off, transition.\nThese verbs hint at the process even when the question never names it."
                )
            )
        ),
        Chapter(
            "projects",
            "Projects, structures, and PMO",
            "What a project is and who has authority",
            "Predictive map",
            listOf(
                s(
                    "Project, program, portfolio",
                    "A project is temporary and unique: a product, service, or result with a start and an end.\nA program coordinates related projects for benefits you would miss if you ran them alone.\nA portfolio groups projects, programs, and operations against strategy.\nOperations are ongoing; projects change the business and then hand work back."
                ),
                s(
                    "Lifecycle language",
                    "Lifecycle: the phases from start to finish (predictive, incremental, iterative, agile, or hybrid).\nPhase: a group of work with exit criteria and often a deliverable.\nGate: go / no-go / modify before the next phase.\nProcess groups (or PMBOK 8 focus areas) are not the same thing as phases. You can plan and control inside every phase."
                ),
                s(
                    "Org structure and PM power",
                    "Functional / organic: PM has little authority; functional managers own people.\nWeak matrix: still low PM power.\nBalanced matrix: shared power; expect negotiation.\nStrong matrix: PM is usually full-time with moderate-to-high authority.\nProject-oriented / projectized: PM almost owns the resources.\nIf the question is about who assigns a person, name the structure first."
                ),
                s(
                    "PMO types",
                    "Supportive: templates, training, lessons — consultative, low control.\nControlling: requires frameworks and compliance — moderate control.\nDirective: assigns project managers and runs projects — high control."
                )
            )
        ),
        Chapter(
            "integration",
            "Integration management",
            "Charter, plan, change control, close",
            "Predictive map",
            listOf(
                s(
                    "Charter",
                    "The charter authorizes the project and the project manager to apply resources. The sponsor issues it (the PM may help draft). Typical contents: purpose, high-level scope, milestones, rough budget, key risks, and stakeholder list. Inputs often include the business case, benefits plan, agreements, EEFs, and OPAs. When you need high-level ‘why’ or authority, go to the charter."
                ),
                s(
                    "Project management plan",
                    "The plan integrates subsidiary plans and the scope, schedule, and cost baselines. Once baselines are approved, they change only through integrated change control. For day-to-day ‘how we work,’ use the plan. For ‘are we even supposed to do this project,’ use the charter and business case."
                ),
                s(
                    "Integrated change control",
                    "Every baseline change needs impact analysis and the agreed change path (usually a CCB). Seniority does not skip the process. Tiny changes still count. Sequence: request → impact (scope, time, cost, quality, risk, benefits) → decision → update plans and communicate. In agile, product backlog tradeoffs are owned by the product owner; contractual or baseline commitments still need the formal path.",
                    SectionKind.DANGER
                ),
                s(
                    "Knowledge and close",
                    "Manage project knowledge while people are still on the team. Close: confirm acceptance, transfer product and knowledge, archive, release resources, and close contracts. Do not disband the team before acceptance is real."
                )
            )
        ),
        Chapter(
            "scope",
            "Scope management",
            "Requirements, WBS, validation versus control",
            "Predictive map",
            listOf(
                s(
                    "Collect and define",
                    "Requirements come from stakeholders who use, fund, operate, or constrain the result. Tools include interviews, workshops, observation, prototyping, and document analysis. The requirements traceability matrix keeps each requirement tied to origin, design, test, and benefit. The scope statement describes product scope, project scope, deliverables, and exclusions."
                ),
                s(
                    "WBS",
                    "The work breakdown structure decomposes deliverables into work packages you can estimate and assign. The WBS dictionary explains each package. Scope baseline = scope statement + WBS + WBS dictionary. If someone asks ‘is this in the project,’ that baseline is the contract with the organization."
                ),
                s(
                    "Validate versus control",
                    "Validate scope: formal acceptance of deliverables with the customer or sponsor (inspection).\nControl scope: keep the project from silently growing; compare work to the baseline and route changes.\nGold plating (adding extras ‘to be nice’) is still out-of-scope work. In agile, acceptance is continuous against the Definition of Done and product owner feedback."
                )
            )
        ),
        Chapter(
            "schedule",
            "Schedule management",
            "Network, float, compression, estimates",
            "Predictive map",
            listOf(
                s(
                    "Build the model",
                    "Plan schedule management → define activities → sequence → estimate durations → develop schedule → control schedule. Dependencies: finish-to-start (most common), start-to-start, finish-to-finish, start-to-finish. Leads overlap; lags insert wait."
                ),
                s(
                    "Critical path and float",
                    "Critical path = longest path; it usually has zero total float. Delay on it delays the finish.\nTotal float = LS − ES (or LF − EF): slip without moving the project end.\nFree float: slip without delaying the immediate successor.\nNear-critical paths matter when you crash or fast-track."
                ),
                s(
                    "Compression",
                    "Crashing: add resources on the critical path — usually costs money.\nFast-tracking: overlap sequential work — usually adds risk, not direct cost.\nNeither is a first answer when you have not analyzed impact or spoken with the requestor about tradeoffs."
                ),
                s(
                    "Estimating",
                    "Analogous: similar past project, fast, coarse.\nParametric: unit rate × quantity.\nThree-point / PERT: (O + 4M + P) / 6; triangular is (O + M + P) / 3.\nStandard deviation ≈ (P − O) / 6.\nBottom-up: estimate packages and roll up — slowest and usually most accurate."
                )
            )
        ),
        Chapter(
            "cost",
            "Cost and earned value",
            "Reserves, EVM, forecasts",
            "Predictive map",
            listOf(
                s(
                    "Reserves",
                    "Contingency reserve: known-unknowns, owned by the project, inside the baseline.\nManagement reserve: unknown-unknowns, owned by management, outside the PM’s baseline until released.\nBAC is the approved budget for the work (typically including contingency, excluding management reserve)."
                ),
                s(
                    "EVM anchors",
                    "PV: what the plan said you should have earned by now.\nEV: budgeted value of work actually finished (often % complete × BAC for that work).\nAC: what you actually spent.\nSV = EV − PV. CV = EV − AC.\nSPI = EV / PV. CPI = EV / AC.\nNegative SV/CV or index below 1.0 is unfavorable."
                ),
                s(
                    "Forecasts",
                    "Typical EAC = BAC / CPI (current cost efficiency continues).\nRemaining work at plan = AC + (BAC − EV).\nIf the original estimate is broken, EAC = AC + a fresh bottom-up ETC.\nETC = EAC − AC. VAC = BAC − EAC.\nTCPI to BAC = (BAC − EV) / (BAC − AC). Above 1.0 means you must be more efficient than you have been."
                ),
                s(
                    "Estimate ranges",
                    "Rough order of magnitude early on is wide (classically about −25% to +75%). Definitive estimates later are much tighter (about −5% to +10%). The exam cares that you match estimate quality to how much you actually know.",
                    SectionKind.TIP
                )
            )
        ),
        Chapter(
            "quality",
            "Quality management",
            "Prevention, grades, and control tools",
            "Predictive map",
            listOf(
                s(
                    "Quality versus grade",
                    "Quality is meeting requirements. Grade is a category of features. Low grade can be fine; low quality is not. Prevention (design, standards, training) is cheaper than inspection after defects ship. Cost of quality: prevention and appraisal versus internal and external failure."
                ),
                s(
                    "Plan, manage, control",
                    "Plan quality: standards, metrics, Definition of Done, checklists.\nManage quality: process audits, quality assurance, continuous improvement (often cited as 85% of problems being process, not people).\nControl quality: inspect deliverables — control charts, Pareto, cause-and-effect, histograms, scatter diagrams."
                ),
                s(
                    "Control chart cues",
                    "A process is out of control if a point is outside control limits, or if you see non-random patterns such as seven consecutive points on one side of the mean (rule of seven). Specification limits are customer requirements; control limits describe process variation. Do not confuse them."
                )
            )
        ),
        Chapter(
            "resources",
            "Resources and teams",
            "Acquire, develop, Tuckman, motivation",
            "Predictive map",
            listOf(
                s(
                    "Plan and acquire",
                    "Estimate activity resources, then acquire people, equipment, and materials. RACI clarifies who is responsible, accountable, consulted, and informed — only one Accountable per activity. A resource calendar shows when people are actually available."
                ),
                s(
                    "Team development",
                    "Tuckman: forming, storming, norming, performing, adjourning. Storming is normal; facilitate it, do not punish it. Training, colocation or virtual working agreements, and recognition build performance. Maslow, Herzberg, McGregor (X/Y), and McClelland show up as ‘which theory’ items — know the labels, then still pick servant leadership in situational stems."
                ),
                s(
                    "Servant leadership",
                    "Remove impediments, coach, and grow decision-making in the team. Do not grab technical work as a first move unless safety requires it. Virtual teams need explicit hours, tools, and decision rights."
                )
            )
        ),
        Chapter(
            "communications",
            "Communications",
            "Channels, methods, 5 Cs",
            "Predictive map",
            listOf(
                s(
                    "Plan communications",
                    "Stakeholder needs drive format, frequency, and language. Push (reports, email), pull (intranet, repository), and interactive (meetings, calls) are all valid. Sensitive conflict: interactive and preferably face-to-face. Formal written is for contracts and baselines."
                ),
                s(
                    "Channels formula",
                    "Channels = n(n − 1) / 2. Adding people explodes complexity. If the team grows from 6 to 8, channels go from 15 to 28. The exam loves that arithmetic."
                ),
                s(
                    "5 Cs of written communication",
                    "Correct grammar and spelling, concise expression, clear purpose, coherent flow, and controlling flow of words and ideas. Plus: listen, confirm understanding, and manage meetings so they have an outcome."
                )
            )
        ),
        Chapter(
            "risk",
            "Risk management",
            "Identify, analyze, respond, watch",
            "Predictive map",
            listOf(
                s(
                    "Process flow",
                    "Plan risk management → identify → qualitative analysis (and quantitative when it pays) → plan responses → implement responses → monitor. Identification never stops. Every risk needs an owner."
                ),
                s(
                    "Responses for threats",
                    "Escalate (outside your authority), avoid, transfer, mitigate, accept (active with a contingency, or passive). Opportunities: escalate, exploit, share, enhance, accept. Residual risk remains after a response; secondary risk is born from the response itself."
                ),
                s(
                    "Qualitative versus quantitative",
                    "Qualitative: probability and impact matrix, urgency, watch list.\nQuantitative: EMV = probability × monetary impact, decision trees, simulation.\nA risk that ‘might’ happen next month is not an issue yet — register it and analyze."
                )
            )
        ),
        Chapter(
            "procurement",
            "Procurement",
            "Contract types, PTA, make or buy",
            "Predictive map",
            listOf(
                s(
                    "Make or buy and documents",
                    "Make-or-buy compares internal capacity versus seller cost, risk, and control. SOW or statement of work tells the seller what done looks like. RFI / RFQ / RFP gather information, quotes, or proposals. Conduct procurements selects the seller; control procurements manages the relationship."
                ),
                s(
                    "Contract types",
                    "FFP: seller holds most cost risk — use when scope is clear.\nCost-plus: buyer holds more cost risk — use when scope is unclear; add incentives or award fees to share pain/gain.\nT&M: hybrid, useful for staff augmentation; needs a not-to-exceed or strong oversight.\nPoint of total assumption (PTA) on FPIF: ((ceiling − target price) / buyer share) + target cost — the cost point where the seller bears all further overrun."
                ),
                s(
                    "Ethics in buying",
                    "Fair, transparent evaluation. Do not share one seller’s price with another. Close procurements with formal acceptance and file the records. A senior stakeholder still cannot verbally rewrite a contract."
                )
            )
        ),
        Chapter(
            "stakeholders",
            "Stakeholder engagement",
            "Identify, map, move them toward support",
            "Predictive map",
            listOf(
                s(
                    "Identify early and again",
                    "Stakeholders appear throughout. When new ones show up, update identification and the engagement plan — do not only glance at an old register. Power/interest and salience models help you tailor effort."
                ),
                s(
                    "Engagement levels",
                    "Unaware → resistant → neutral → supportive → leading. The plan states the current and desired level. Moving someone from resistant to supportive is work, not a status email. In agile, the product owner represents value; you still owe other stakeholders a communication path."
                )
            )
        ),
        Chapter(
            "pmbok8",
            "PMBOK 8 map",
            "Principles, domains, focus areas, AI, sustainability",
            "Current standard",
            listOf(
                s(
                    "Why the structure changed",
                    "PMBOK 8 blends process detail with domain thinking. Expect six principles, seven performance domains, focus areas that look like the five process groups, and processes described inside domains rather than as a 49-box grid. Quality often sits with governance. Communications often sits with stakeholders. Procurement often sits with resources and governance. Finance is a broader view of cost plus investment and benefits."
                ),
                s(
                    "Six principles (study labels)",
                    "1. Holistic / systems view — decisions have side effects.\n2. Focus on value — activity is not the same as benefit.\n3. Build quality into work — do not inspect it in at the end.\n4. Accountable leadership — integrity, trust, psychological safety; leadership can be shared.\n5. Sustainability — environmental, social, and economic effects.\n6. Enable change and tailoring — adapt the approach on purpose, not by accident."
                ),
                s(
                    "Seven performance domains",
                    "A practical mapping for the exam: Governance (integration + much of quality), Scope, Schedule, Finance, Stakeholders (plus communications), Resources (plus much of procurement), Risk. Each domain has concepts, interactions, and tailoring. You still close, still control change, still lead people — the labels moved."
                ),
                s(
                    "Focus areas and cadence",
                    "Focus areas: initiating, planning, executing, monitoring and controlling, closing. Delivery cadence can be single delivery, multiple deliveries, or periodic. Life cycles differ by how much you plan, when you plan, and who plans — not by whether planning exists."
                ),
                s(
                    "AI and sustainability on the exam",
                    "Treat AI as a tool: data quality, privacy, bias, and human accountability still belong to the project. Prompting and analysis can support communication and reporting; they do not replace stakeholder agreements. Sustainability is a value and risk topic — include it in design and in benefits, not as a poster at closeout.",
                    SectionKind.NOTE
                ),
                s(
                    "Tailoring in four moves",
                    "1. Understand the product, culture, and constraints.\n2. Select the starting approach (predictive, agile, hybrid).\n3. Adjust process, artifacts, and ceremonies.\n4. Inspect with retrospectives, issues, quality data, and stakeholder feedback, then adjust again."
                )
            )
        ),
        Chapter(
            "agile",
            "Agile practice",
            "Manifesto, when to use agile, Kanban, XP",
            "Agile",
            listOf(
                s(
                    "Agile is an umbrella",
                    "Agile is not one methodology. It is iterative and incremental product development under uncertainty. It fits unclear requirements, high change, research, and new technology. Stakeholder satisfaction with working increments is the point. The four values: individuals and interactions, working product, customer collaboration, responding to change — over processes, comprehensive docs, contract negotiation, and following a plan. The right-hand items still matter; they are not banned."
                ),
                s(
                    "Predictive versus agile choice",
                    "Stable, well-known requirements and a contractual design freeze → predictive.\nEvolving needs and a need to learn → agile.\nSpecialized silos and a single big handover → predictive.\nCross-functional team and frequent increments → agile.\nRisk managed up front versus risk burned down by delivering slices."
                ),
                s(
                    "Kanban",
                    "Visualize work, limit WIP, manage flow. Lead time is request-to-done. Cycle time is start-to-done. Throughput is items per time. There is no required sprint; flow is continuous. WIP limits expose bottlenecks instead of hiding them in a huge ‘in progress’ column."
                ),
                s(
                    "XP highlights",
                    "Test-driven development, pair programming, continuous integration, refactoring, simple design, small releases. If a question is about engineering practices that protect quality at speed, think XP."
                )
            )
        ),
        Chapter(
            "scrum",
            "Scrum",
            "Roles, events, artifacts, Definition of Done",
            "Agile",
            listOf(
                s(
                    "Empiricism",
                    "Scrum uses transparency, inspection, and adaptation. Values: commitment, courage, focus, openness, respect. Memory aid: 3 roles, 5 events, 3 artifacts."
                ),
                s(
                    "Roles",
                    "Product owner: value, ordered backlog, acceptance of the increment.\nScrum master: coach, facilitate, remove impediments, protect the empirical process.\nDevelopers: how to deliver a Done increment; self-managing, typically small and cross-functional.\nA project manager in a hybrid org supports this system; they do not silently become the product owner."
                ),
                s(
                    "Events",
                    "Sprint: 1–4 weeks, fixed timebox, a Done increment is the goal.\nSprint planning: why, what, how — sprint goal and sprint backlog.\nDaily scrum: developers inspect progress to the sprint goal (15 minutes).\nSprint review: inspect the increment with stakeholders.\nRetrospective: inspect the process and people system, then improve."
                ),
                s(
                    "Artifacts and Done",
                    "Product backlog, sprint backlog, increment. Definition of Done is a quality contract; undone work is not an increment. Changes inside a sprint are a conversation about the sprint goal, not silent extra scope from chat messages."
                )
            )
        ),
        Chapter(
            "people-domain",
            "People domain drills",
            "ECO People tasks as exam behaviors",
            "ECO domains",
            listOf(
                s(
                    "Lead and support",
                    "Set purpose, then match leadership style to skill and will. Coach when skill is growing. Support when motivation dropped. Direct when harm is imminent. Appraise team performance against shared goals first. Mentoring and pairing beat public shaming."
                ),
                s(
                    "Empower and unblock",
                    "Give the team the smallest authority that still lets them finish the work. Impediments are the scrum master’s and project manager’s job to clear this week, not to decorate a slide. Negotiate for people and environment. Virtual teams need explicit ground rules they helped write."
                ),
                s(
                    "Stakeholders and conflict",
                    "Engage continuously. When conflict hits, collaborate. Emotional intelligence is a delivery skill: name the feeling, then return to the work agreement. Never ‘win’ a meeting by humiliating someone who still has to build the product tomorrow."
                )
            )
        ),
        Chapter(
            "process-domain",
            "Process domain drills",
            "ECO Process tasks as exam behaviors",
            "ECO domains",
            listOf(
                s(
                    "Execute with a plan you can change",
                    "Plan enough to start, then keep the plan honest. Manage communications, risk, issues, schedule, budget, quality, and scope with the artifacts the approach requires. Predictive work uses baselines and CCB. Agile work uses backlog tradeoffs and Done. Hybrid uses both without mixing them randomly."
                ),
                s(
                    "Urgency without panic",
                    "Deliver value early when you can. Do not skip quality or safety to look busy. Knowledge transfer is a process task, not a leftover. Procurement and resources are process work too — sellers and specialists still need a SOW and a definition of done."
                )
            )
        ),
        Chapter(
            "business-domain",
            "Business environment drills",
            "Benefits, compliance, org change",
            "ECO domains",
            listOf(
                s(
                    "Benefits over sunk cost",
                    "Outputs are not benefits. Track who owns benefits and when they should appear. If the business case dies, recommend pause, pivot, or cancel. Finishing a useless project to ‘use the budget’ is not professional."
                ),
                s(
                    "Compliance and external change",
                    "Legal, security, safety, and contract constraints belong in Done and in the plan. A new regulation is a risk/change, not a team-chat rumor. Organizational change management (sponsors, training, communication) is part of delivery when users must work differently."
                )
            )
        ),
        Chapter(
            "lifecycles",
            "Iterative vs incremental",
            "How learning and delivery differ",
            "Agile deep dive",
            listOf(
                s(
                    "Four patterns",
                    "Predictive: plan, then build, then deliver once.\nIterative: refine a solution through repeated cycles; the product may not ship every cycle.\nIncremental: deliver slices of usable product; each slice adds function.\nAgile: iterative plus incremental — learn and ship value frequently."
                ),
                s(
                    "Exam use",
                    "If the pain is ‘we do not know the right design,’ iterate. If the pain is ‘users need something working now,’ increment. Most software questions want both. Hybrid: lock the regulatory wrapper, iterate the uncertain middle."
                )
            )
        ),
        Chapter(
            "agile-metrics",
            "Agile hierarchy and metrics",
            "Themes, epics, stories, velocity, flow",
            "Agile deep dive",
            listOf(
                s(
                    "Work hierarchy",
                    "Theme / initiative → epic → feature or user story → task. Stories are small enough to finish in a sprint. INVEST is a quality check: independent, negotiable, valuable, estimable, small, testable. Story points compare relative effort; they are not hours."
                ),
                s(
                    "Metrics that help",
                    "Velocity: completed story points per sprint — a forecast aid, not a weapon.\nBurndown / burnup: remaining or completed work versus time.\nCycle time and throughput: Kanban flow.\nEscaped defects: quality after release.\nDo not compare two teams’ velocity as a performance ranking."
                )
            )
        )
    )
}
