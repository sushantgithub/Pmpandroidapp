package com.sushant.pmpstudy.data

/**
 * Additional 2026-ECO-aligned scenario questions used to bring the unique
 * practice bank to a full 180 items. They deliberately emphasize judgment and
 * context rather than memorized "always/never" shortcuts.
 */
internal object ExamReadinessQuestions {
    val all: List<QuizQuestion> = listOf(
        QuizQuestion(
            "q172", "mindset",
            "A newly formed project has three executive stakeholders describing success in different ways. What should the project manager do FIRST?",
            listOf(
                "A. Ask the sponsor to choose the definition of success",
                "B. Facilitate alignment on a shared project vision and measurable outcomes",
                "C. Create separate success criteria for each executive",
                "D. Begin planning and resolve the disagreement after scope is baselined"
            ),
            1,
            "The July 2026 People domain begins with developing a common vision. When key stakeholders hold conflicting views of success, the PM should facilitate shared understanding and measurable outcomes before detailed planning locks in competing assumptions."
        ),
        QuizQuestion(
            "q173", "mindset",
            "Two senior specialists disagree strongly about an architecture decision. The disagreement is delaying work, but neither option creates an immediate safety or compliance risk. What is the BEST response?",
            listOf(
                "A. Select the option preferred by the more senior specialist",
                "B. Escalate immediately to the sponsor",
                "C. Analyze the source and context of the conflict and facilitate an agreed resolution strategy",
                "D. Record the conflict as an issue and allow the team to resolve it without facilitation"
            ),
            2,
            "The 2026 People task 'Manage conflicts' emphasizes identifying sources, analyzing context, and implementing an agreed resolution strategy. Escalation or unilateral direction may be appropriate in some contexts, but the scenario gives no reason to bypass collaborative resolution."
        ),
        QuizQuestion(
            "q174", "leadership",
            "A high-performing team is experienced with the technology and has repeatedly delivered without close supervision. The PM is deciding how to lead the next phase. What is the BEST approach?",
            listOf(
                "A. Increase detailed task-level direction to reduce risk",
                "B. Empower the team within clear objectives and boundaries",
                "C. Require all technical decisions to be approved by the PM",
                "D. Rotate leadership weekly so no one becomes too influential"
            ),
            1,
            "Leadership style should fit the team's capability and context. An experienced, high-performing team generally benefits from empowerment and clear boundaries rather than unnecessary control."
        ),
        QuizQuestion(
            "q175", "stakeholders",
            "A product launch is technically complete, but a regional operations leader says the rollout does not address a critical local workflow. What should the PM do NEXT?",
            listOf(
                "A. Explain that requirements are already approved and cannot be revisited",
                "B. Analyze the stakeholder's expectation and its effect on intended outcomes, then facilitate alignment",
                "C. Ask the sponsor to overrule the regional leader",
                "D. Add the requested workflow immediately to preserve the relationship"
            ),
            1,
            "The People domain separates engaging stakeholders, aligning expectations, and managing expectations. The PM should understand the expectation and outcome impact before choosing a response; neither immediate rejection nor ungoverned scope expansion is justified."
        ),
        QuizQuestion(
            "q176", "stakeholders",
            "A specialist who holds critical operational knowledge will leave the project in three weeks. Documentation is incomplete. What should the PM prioritize?",
            listOf(
                "A. Hiring a replacement before any knowledge-transfer activity",
                "B. Identifying critical knowledge and creating a structured transfer plan with the specialist and receiving team",
                "C. Asking the specialist to record every meeting for future reference",
                "D. Moving the specialist off current work immediately so they can write a complete manual"
            ),
            1,
            "The 2026 People task 'Help ensure knowledge transfer' focuses on identifying critical knowledge, gathering it, and fostering an environment for transfer. A structured plan balances continuity of current work with targeted knowledge capture."
        ),
        QuizQuestion(
            "q177", "integration",
            "A hybrid program has predictive hardware milestones, adaptive software delivery, and a fixed regulatory date. Teams maintain separate plans that frequently conflict. What should the PM do?",
            listOf(
                "A. Force every workstream to use the same lifecycle",
                "B. Develop and maintain an integrated plan that exposes dependencies while preserving fit-for-purpose delivery approaches",
                "C. Let each team optimize independently and reconcile dates at the end",
                "D. Use only the regulatory milestone as the integrated plan"
            ),
            1,
            "The 2026 Process domain calls for an integrated project management plan and an approach appropriate to project needs. Integration does not require forcing all components into the same lifecycle."
        ),
        QuizQuestion(
            "q178", "scope",
            "During planning, stakeholders propose 40 features but funding can support only half. What is the BEST basis for deciding what to deliver first?",
            listOf(
                "A. Choose the easiest features to maximize completed scope",
                "B. Prioritize work using value, stakeholder feedback, dependencies, and opportunities for incremental delivery",
                "C. Ask the sponsor to rank features without additional analysis",
                "D. Defer prioritization until execution begins"
            ),
            1,
            "The 2026 Process task 'Help ensure value-based delivery' emphasizes identifying value components, prioritizing using stakeholder feedback, and assessing incremental delivery opportunities."
        ),
        QuizQuestion(
            "q179", "procurement",
            "A critical supplier is meeting delivery dates but defect rates are rising and rework is increasing. What should the PM do NEXT?",
            listOf(
                "A. Terminate the contract immediately",
                "B. Evaluate supplier performance against the agreement and quality requirements, then determine the appropriate contractual response",
                "C. Accept the defects because schedule performance is good",
                "D. Transfer all inspection work to the supplier"
            ),
            1,
            "Procurement management includes evaluating vendor performance and verifying that agreement objectives are met. The appropriate response depends on the contract, quality requirements, and facts."
        ),
        QuizQuestion(
            "q180", "cost",
            "Forecasts show that a known high-impact risk could require significant additional funding if triggered. What should the PM ensure during financial planning?",
            listOf(
                "A. Exclude the risk because contingency would make the budget look larger",
                "B. Quantify appropriate risk and contingency financial allocations and define how reserves will be managed",
                "C. Add the full worst-case amount directly to every work package",
                "D. Wait until the risk occurs before discussing funding"
            ),
            1,
            "The 2026 Process task 'Plan and manage finance' explicitly includes quantifying risk and contingency allocations and managing reserves. The treatment should be proportionate to analyzed risk, not hidden or deferred."
        ),
        QuizQuestion(
            "q181", "quality",
            "A team meets all internal test criteria, but users repeatedly reject delivered increments because the criteria do not reflect actual usage needs. What should the PM address?",
            listOf(
                "A. Increase the number of internal inspections without changing criteria",
                "B. Revisit quality requirements with stakeholders and optimize the quality approach around deliverable needs and outcomes",
                "C. Ask users to accept the team's Definition of Done",
                "D. Reduce user involvement until final acceptance"
            ),
            1,
            "Quality planning starts with suitable quality requirements. More inspection against the wrong criteria will not solve the gap between internal conformance and stakeholder needs."
        ),
        QuizQuestion(
            "q182", "schedule",
            "A predictive project is two weeks behind its baseline, but the delayed activities have substantial float and no milestone has moved. What should the PM do FIRST?",
            listOf(
                "A. Crash the delayed activities immediately",
                "B. Evaluate schedule status, float, dependencies, and forecast impact before selecting corrective action",
                "C. Report that the entire project is two weeks late",
                "D. Rebaseline the schedule to remove the variance"
            ),
            1,
            "Schedule variance must be interpreted in context. Delay on activities with float may not affect the project completion date. Evaluate actual status and forecast impact before choosing a response."
        ),
        QuizQuestion(
            "q183", "business",
            "A project begins without clear decision rights. Routine issues repeatedly wait days for executive approval. What should the PM establish?",
            listOf(
                "A. A governance structure with decision rights, success metrics, escalation paths, and thresholds",
                "B. A rule that every decision must be made by the sponsor",
                "C. A larger issue log",
                "D. A daily executive steering meeting"
            ),
            0,
            "The 2026 Business Environment task 'Define and establish project governance' includes structure, rules, success metrics, and escalation paths/thresholds. Clear governance reduces unnecessary decision latency."
        ),
        QuizQuestion(
            "q184", "business",
            "A regulatory audit finds one project control is not meeting a mandatory requirement. The team can correct it within a week. What is the BEST response?",
            listOf(
                "A. Continue unchanged until the next scheduled audit",
                "B. Determine the required compliance action, implement it through applicable governance, and verify compliance",
                "C. Treat the finding only as a future risk",
                "D. Ask the auditor to lower the requirement because the project is nearly complete"
            ),
            1,
            "Compliance is a current requirement, not merely a future uncertainty. The PM should determine and execute the required action using the project's governance/change process and measure compliance afterward."
        ),
        QuizQuestion(
            "q185", "risk",
            "A delivery dependency that was previously recorded as a risk has now failed and is blocking the team. What should the PM do?",
            listOf(
                "A. Leave it only in the risk register because it originated as a risk",
                "B. Recognize that the risk has become an issue, assess impact, and coordinate an intervention with relevant stakeholders",
                "C. Close the risk and take no further action",
                "D. Escalate immediately without assessing impact"
            ),
            1,
            "The 2026 Business Environment task 'Remove impediments and manage issues' explicitly includes recognizing when a risk becomes an issue and collaborating on resolution."
        ),
        QuizQuestion(
            "q186", "external-env",
            "Halfway through a product project, a competitor launches a lower-cost alternative and customer demand shifts sharply. What should the PM do NEXT?",
            listOf(
                "A. Ignore the change because external market conditions are outside project scope",
                "B. Assess and prioritize the effect on project scope/backlog and intended value, then bring the analysis into the appropriate governance decision",
                "C. Cancel the project immediately",
                "D. Add competing features without stakeholder approval"
            ),
            1,
            "The 2026 Business Environment task 'Evaluate external business environment changes' includes surveying market changes and assessing/prioritizing their impact on project scope or backlog. The resulting decision should follow appropriate governance."
        )
    )
}
