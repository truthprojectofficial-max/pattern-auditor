package com.truthasaservice.ordergetitright.patternauditor

/**
 * The audit engine. Pure Kotlin, deterministic, local.
 *
 *   - assertCount() MUST be called at app start. If the loaded ruleset
 *     is not exactly 71 patterns, the app must stop. We never silently
 *     fall back to a smaller / older ruleset.
 *   - scan(text) returns every configured pattern whose indicator is
 *     present as a case-insensitive substring. A single text can match
 *     more than one pattern.
 *   - The engine never calls a result a lie, truth, intent, or diagnosis.
 *     It reports observable rule matches.
 */
object PatternAuditor {

    val rulesetVersion: String = PatternAuditorRuleset.ONTOLOGY_VERSION

    /**
     * Hard fail-closed. Run this at app start. Throws IllegalStateException
     * if the loaded ruleset is not exactly 71 patterns.
     */
    fun assertCount() {
        PatternAuditorRuleset.assertCount()
    }

    fun ruleCount(): Int = PatternAuditorRuleset.patterns.size

    /**
     * Scan a piece of text against the configured ruleset.
     * Returns the list of observable matches, in patterns-list order.
     */
    fun scan(text: String): List<PatternMatch> {
        val safe = text ?: ""
        if (safe.isEmpty()) return emptyList()
        val lower = safe.lowercase()
        val out = ArrayList<PatternMatch>()
        for (p in PatternAuditorRuleset.patterns) {
            for (indicator in p.indicators) {
                val idx = lower.indexOf(indicator.lowercase())
                if (idx >= 0) {
                    val matched = safe.substring(idx, idx + indicator.length)
                    out.add(
                        PatternMatch(
                            patternId = p.id,
                            patternName = p.name,
                            severity = p.severity,
                            matchedText = matched,
                            ruleIndicator = indicator,
                            ruleExplanation = p.description,
                            rulesetVersion = rulesetVersion,
                        )
                    )
                    break // one match per pattern is enough
                }
            }
        }
        return out
    }

    /**
     * Maximum allowed input length (chars). The handover requires a
     * visible refusal on overflow -- we never silently truncate.
     */
    const val MAX_INPUT_CHARS: Int = 50_000
}
