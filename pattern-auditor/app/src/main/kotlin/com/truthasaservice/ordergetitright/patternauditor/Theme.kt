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

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Pattern AI-uditor - locked theme (truthprojectofficial-max/pattern-auditor 2026-08-02).
// The operator named four colours and asked for the app to keep them on every device:
//   - purple       (primary accent, "Pattern detected" headline, indicator text)
//   - washed white (off-white field and panel surface)
//   - black       (the dark theme base + on-light text)
//   - red         (errors and the match accent in light theme)
//
// The theme is locked to dark. The app does not follow the device's Material You
// dynamic colour. This is deliberate.

private val TruthProjectDark = darkColorScheme(
    primary          = Color(0xFFB388FF),  // richer purple, more saturated than M3 default
    onPrimary        = Color(0xFF1A0E40),
    primaryContainer = Color(0xFF4A2B8F),
    onPrimaryContainer = Color(0xFFEADDFF),

    secondary        = Color(0xFFCCC2DC),
    onSecondary      = Color(0xFF332D41),

    background       = Color(0xFF0F0E13),  // black, very slight purple tint
    onBackground     = Color(0xFFFFFFFF),  // white text on near-black

    surface          = Color(0xFF0F0E13),  // black
    onSurface        = Color(0xFFFFFFFF),  // white
    surfaceVariant   = Color(0xFF1F1E26),  // lifted panel
    onSurfaceVariant = Color(0xFFCAC4D0),

    outline          = Color(0xFFB388FF),  // purple outline reads as a design feature
    outlineVariant   = Color(0xFF49454F),

    error            = Color(0xFFFF6E6E),  // red, tuned for dark backgrounds
    onError          = Color(0xFF410002),
    errorContainer   = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),
)

val MatchAccent = Color(0xFFB388FF)  // purple "Pattern detected" headline and indicator text

@Composable
fun PatternAuditorTheme(
    // Locked to dark theme. The app is operator-only; the brand identity
    // does not follow the device's Material You dynamic colour. This is
    // deliberate and consistent across every device.
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = TruthProjectDark,
        content = content,
    )
}