package com.truthasaservice.ordergetitright.patternauditor

import org.junit.Test
import kotlin.random.Random

class LongTextScanTest {
    @Test
    fun scan_35k_chars_does_not_throw_or_hang() {
        val r = Random(42)
        val words = listOf("the", "and", "of", "to", "in", "I", "apologize", "based", "on", "my", "analysis", "data", "clearly", "shows", "100%", "accurate", "experts", "suggest", "we", "cannot", "provide", "citation", "trust", "me", "obviously", "this", "just", "works")
        val sb = StringBuilder()
        while (sb.length < 35000) {
            sb.append(words.random(r))
            sb.append(' ')
        }
        val text = sb.toString()
        val start = System.currentTimeMillis()
        val matches = PatternAuditor.scan(text)
        val elapsed = System.currentTimeMillis() - start
        println("scanned ${text.length} chars in ${elapsed}ms, got ${matches.size} matches")
        // No assertion on the count - just that it returns and doesn't take a pathologically long time.
        org.junit.Assert.assertTrue("scan must return without hanging", elapsed < 5000)
    }

    @Test
    fun scan_exactly_max_input_chars_does_not_throw() {
        val text = "I apologize. ".repeat(PatternAuditor.MAX_INPUT_CHARS / 14) // ~3571 reps = 50k chars
        val matches = PatternAuditor.scan(text.take(PatternAuditor.MAX_INPUT_CHARS))
        // Apologize is in DD-004 (Apology Theater), and "I apologize" is the indicator.
        org.junit.Assert.assertTrue(matches.isNotEmpty())
    }
}