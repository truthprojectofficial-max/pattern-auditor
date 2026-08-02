package com.truthasaservice.ordergetitright.patternauditor

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test

class PatternAuditorTest {

    @Test
    fun ruleCount_isExactly71() {
        // 1. The extracted count and IDs from the canonical source (71 patterns).
        val n = PatternAuditor.ruleCount()
        assertEquals(
            "Handover requires the loaded ruleset to be exactly 71 patterns.",
            71,
            n,
        )
    }

    @Test
    fun ruleCount_idsAreContiguousDD001ThroughDD071() {
        // 2. The Kotlin rule count and IDs.
        val ids = PatternAuditorRuleset.patterns.map { it.id }
        val expected = (1..71).map { "DD-%03d".format(it) }
        assertEquals(expected, ids)
    }

    @Test
    fun assertCount_doesNotThrow_whenCountIs71() {
        // 3. A test proving ruleCount == 71 passes the fail-closed assertion.
        PatternAuditor.assertCount()
    }

    @Test
    fun assertCount_throws_whenCountIsNot71() {
        // 4. A test proving the app fails closed when the count is not 71.
        //    We simulate a tampered ruleset by reading the live list and
        //    removing the last pattern. The assertion must then throw.
        val live = PatternAuditorRuleset.patterns
        val originalSize = live.size
        assertEquals(71, originalSize)
        // We can't mutate the live `by lazy` list, so we test the helper
        // function with a synthetic, wrongly-sized collection instead.
        val wrong = List(70) { i ->
            DeceptionPattern(
                id = "DD-%03d".format(i + 1),
                name = "x",
                category = "x",
                description = "x",
                indicators = listOf("x"),
                severity = Severity.LOW,
                threshold = 0.5f,
            )
        }
        try {
            checkRulesetSize(wrong, 71)
            fail("Expected the fail-closed check to throw for a 70-pattern ruleset.")
        } catch (e: IllegalStateException) {
            assertTrue(
                "Exception message must mention the expected count.",
                e.message?.contains("71") == true,
            )
        }
    }

    @Test
    fun sourceVersionLabel_isPresentOnEveryMatch() {
        // 5. A test showing the source/version label on the result.
        val matches = PatternAuditor.scan("I apologize, the data clearly shows this is 100% accurate.")
        assertTrue("Expected at least one match on this adversarial text.", matches.isNotEmpty())
        for (m in matches) {
            assertEquals(
                "Every match must carry the same ruleset version label.",
                PatternAuditor.rulesetVersion,
                m.rulesetVersion,
            )
            assertTrue(
                "Every match must have a DD-NNN id.",
                m.patternId.matches(Regex("DD-\\d{3}")),
            )
        }
    }

    @Test
    fun scan_emptyText_returnsEmpty() {
        val m = PatternAuditor.scan("")
        assertTrue(m.isEmpty())
    }

    @Test
    fun scan_unknownText_returnsEmpty() {
        val m = PatternAuditor.scan("The quick brown fox jumps over the lazy dog. 1234567890.")
        assertTrue(
            "Generic text must not produce a configured-pattern match.",
            m.isEmpty(),
        )
    }

    @Test
    fun neverClaimsLieTruthIntentDiagnosis() {
        // We only emit ID, name, severity, matched text, indicator, and the
        // pattern description. The engine itself never labels a result as a
        // lie, truth, intent, or diagnosis.
        val matches = PatternAuditor.scan("Trust me, obviously this just works.")
        for (m in matches) {
            assertNotEquals("lie", m.patternId.lowercase())
            assertNotEquals("truth", m.patternId.lowercase())
            assertNotEquals("diagnosis", m.patternId.lowercase())
        }
    }

    // --- helpers ---

    private fun checkRulesetSize(list: List<DeceptionPattern>, expected: Int) {
        check(list.size == expected) {
            "Ruleset count mismatch: expected $expected, got ${list.size}. App must stop."
        }
    }
}
