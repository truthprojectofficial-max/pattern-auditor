/*
 * Operator: Justin Barnett (TruthProject)
 * Contact:   truth.project.official@gmail.com
 * Sibling:   github.com/truthprojectofficial-max/truthasaservice
 * ORCID:     0009-0009-2936-6966
 *
 * This file is part of pattern-auditor, the native Android client for the
 * DD-001..DD-071 deception-ontology. Local-only. No network. No telemetry.
 */
package com.truthasaservice.ordergetitright.patternauditor

enum class Severity { LOW, MEDIUM, HIGH, CRITICAL }

/**
 * One configured deception pattern (rule).
 *
 * Indicators are matched case-insensitively as substrings against the
 * scanned text. No regex, no network, no LLM. Same lexical ruleset the
 * canonical Python module uses, ported verbatim.
 */
data class DeceptionPattern(
    val id: String,
    val name: String,
    val category: String,
    val description: String,
    val indicators: List<String>,
    val severity: Severity,
    val threshold: Float,
)

/**
 * One observable rule match. We surface the ID, name, the exact matching
 * indicator, the pattern description, and the ruleset version. We do NOT
 * claim the text is a lie, the person intended deception, or that the
 * result is a diagnosis.
 */
data class PatternMatch(
    val patternId: String,
    val patternName: String,
    val severity: Severity,
    val matchedText: String,
    val ruleIndicator: String,
    val ruleExplanation: String,
    val rulesetVersion: String,
)
