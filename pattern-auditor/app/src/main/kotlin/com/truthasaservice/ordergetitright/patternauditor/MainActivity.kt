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

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Hard fail-closed. The app stops with a visible crash if the
        // ruleset is not exactly 71 patterns.
        PatternAuditor.assertCount()
        enableEdgeToEdge()
        setContent { App() }
    }
}

private enum class ScreenState { Idle, Scanning, Done, TooLarge, Received }

@OptIn(ExperimentalMaterial3Api::class, androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
private fun App() {
    val sourceBadge = "71-pattern local ruleset"
    val version = remember { PatternAuditor.rulesetVersion }

    var text by remember { mutableStateOf("") }
    var state by remember { mutableStateOf(ScreenState.Idle) }
    var receivedByShare by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var matches by remember { mutableStateOf<List<PatternMatch>>(emptyList()) }
    val activity = androidx.compose.ui.platform.LocalContext.current as ComponentActivity

    // Honour the Android Share intent if we were launched from another app.
    androidx.compose.runtime.LaunchedEffect(Unit) {
        handleIntent(
            intent = activity.intent,
            setText = { newText ->
                text = newText
                receivedByShare = true
                // Auto-scan on share per handover: text arrives exactly as
                // shared, scanning starts immediately, no extra user tap.
                if (newText.isNotBlank() && newText.length <= PatternAuditor.MAX_INPUT_CHARS) {
                    matches = PatternAuditor.scan(newText)
                    state = ScreenState.Done
                } else {
                    state = ScreenState.Received
                }
            },
            setError = { msg -> errorMessage = msg },
        )
    }

    androidx.compose.runtime.DisposableEffect(activity) {
        val listener = androidx.core.util.Consumer<Intent> { i ->
            handleIntent(
                intent = i,
                setText = { newText ->
                    text = newText
                    receivedByShare = true
                    if (newText.isNotBlank() && newText.length <= PatternAuditor.MAX_INPUT_CHARS) {
                        matches = PatternAuditor.scan(newText)
                        state = ScreenState.Done
                    } else {
                        state = ScreenState.Received
                    }
                },
                setError = { msg -> errorMessage = msg },
            )
        }
        activity.addOnNewIntentListener(listener)
        onDispose { activity.removeOnNewIntentListener(listener) }
    }

    PatternAuditorTheme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Pattern AI-uditor") },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
                )
            }
        ) { inner ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(inner),
                color = MaterialTheme.colorScheme.background,
            ) {
                val keyboard = LocalSoftwareKeyboardController.current
                val bringIntoView = remember { BringIntoViewRequester() }
                val scope = rememberCoroutineScope()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .imePadding()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    // Source badge (handover requirement: visible on main screen)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = sourceBadge,
                            style = MaterialTheme.typography.labelMedium,
                            modifier = Modifier
                                .padding(horizontal = 8.dp, vertical = 4.dp),
                        )
                    }

                    if (receivedByShare) {
                        Text(
                            text = "Received via Android Share",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }

                    OutlinedTextField(
                        value = text,
                        onValueChange = {
                            text = it
                            if (it.length > PatternAuditor.MAX_INPUT_CHARS) {
                                state = ScreenState.TooLarge
                            } else if (state == ScreenState.TooLarge) {
                                state = ScreenState.Idle
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 220.dp, max = 320.dp)
                            .bringIntoViewRequester(bringIntoView)
                            .onFocusEvent { st ->
                                if (st.isFocused) {
                                    scope.launch { bringIntoView.bringIntoView() }
                                }
                            },
                        label = { Text("Paste or type text to scan") },
                        supportingText = {
                            Text(text.length.toString() + " / " + PatternAuditor.MAX_INPUT_CHARS + " chars")
                        },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(onDone = { keyboard?.hide() }),
                        enabled = state != ScreenState.Scanning,
                    )

                    Button(
                        onClick = {
                            try {
                                when {
                                    text.length > PatternAuditor.MAX_INPUT_CHARS -> {
                                        state = ScreenState.TooLarge
                                    }
                                    text.isBlank() -> {
                                        matches = emptyList()
                                        state = ScreenState.Done
                                    }
                                    else -> {
                                        state = ScreenState.Scanning
                                        // Local scan. No network. No LLM.
                                        matches = PatternAuditor.scan(text)
                                        state = ScreenState.Done
                                    }
                                }
                            } catch (e: Throwable) {
                                matches = emptyList()
                                errorMessage = "Scan failed: " + e.javaClass.simpleName + ": " + (e.message ?: "unknown")
                                state = ScreenState.Idle
                            }
                        },
                        enabled = state != ScreenState.Scanning
                            && text.length <= PatternAuditor.MAX_INPUT_CHARS,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text("SCAN")
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        OutlinedButton(
                            onClick = {
                                text = ""
                                matches = emptyList()
                                errorMessage = null
                                state = ScreenState.Idle
                            },
                            enabled = text.isNotEmpty(),
                            modifier = Modifier.weight(1f),
                        ) {
                            Text("CLEAR")
                        }
                        OutlinedButton(
                            onClick = {
                                text = demoText()
                                matches = emptyList()
                                errorMessage = null
                                state = ScreenState.Idle
                            },
                            modifier = Modifier.weight(1f),
                        ) {
                            Text("LOAD SAMPLE")
                        }
                    }

                    errorMessage?.let { msg ->
                        Text(msg, color = MaterialTheme.colorScheme.error)
                    }

                    if (state == ScreenState.Scanning) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            CircularProgressIndicator(modifier = Modifier.height(20.dp))
                            Spacer(modifier = Modifier.height(0.dp))
                            Text(
                                "  Scanning locally\u2026",
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                    }

                    if (state == ScreenState.TooLarge) {
                        Text(
                            "Input exceeds the limit of " + PatternAuditor.MAX_INPUT_CHARS + " characters. " +
                                "Tap CLEAR, trim the text, and paste again. The app never silently cuts the text.",
                            color = MaterialTheme.colorScheme.error,
                        )
                    }

                    if (state == ScreenState.Done) {
                        HorizontalDivider()
                        ResultArea(matches = matches, version = version)
                    }
                }
            }
        }
    }
}

@Composable
private fun ResultArea(matches: List<PatternMatch>, version: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        if (matches.isEmpty()) {
            Text("No configured pattern detected.", style = MaterialTheme.typography.titleMedium)
        } else {
            Text(
                "Pattern detected",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
            )
            matches.forEach { m ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(),
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            m.patternId + " \u2014 " + m.patternName,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.SemiBold,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            "Matched: \u201C" + m.matchedText + "\u201D",
                            style = MaterialTheme.typography.bodyMedium,
                            fontFamily = FontFamily.Monospace,
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            "Indicator: " + m.ruleIndicator,
                            style = MaterialTheme.typography.bodySmall,
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            m.ruleExplanation,
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                }
            }
        }
        HorizontalDivider()
        Text("Ruleset: " + version, style = MaterialTheme.typography.labelMedium)
        Text("Processed on this device", style = MaterialTheme.typography.labelMedium)
    }
}

private fun demoText(): String =
    "I apologize for the confusion. Based on my analysis the data clearly shows this is 100% accurate, as experts suggest, but we cannot provide the citation right now. Trust me, this is standard."

private fun handleIntent(
    intent: Intent?,
    setText: (String) -> Unit,
    setError: (String) -> Unit,
) {
    if (intent == null) return
    if (intent.action != Intent.ACTION_SEND) return
    if (intent.type != "text/plain") return
    // Chrome on Android sometimes puts the shareable text in EXTRA_SUBJECT (page title)
    // and leaves EXTRA_TEXT empty. Fall back to subject, then any other text-typed extra.
    var payload = intent.getStringExtra(Intent.EXTRA_TEXT) ?: ""
    if (payload.isBlank()) {
        payload = intent.getStringExtra(Intent.EXTRA_SUBJECT) ?: ""
    }
    if (payload.isBlank()) {
        val extras = intent.extras
        if (extras != null) {
            for (key in extras.keySet()) {
                if (key == Intent.EXTRA_TEXT || key == Intent.EXTRA_SUBJECT) continue
                val v = extras.get(key)?.toString() ?: continue
                if (v.length > payload.length) payload = v
            }
        }
    }
    if (payload.isBlank()) {
        setError("Nothing to scan. The shared item had no plain text. Try a different app, or copy the text and paste it into the field.")
        return
    }
    if (payload.length > PatternAuditor.MAX_INPUT_CHARS) {
        setError(
            "Shared text exceeds the limit of " + PatternAuditor.MAX_INPUT_CHARS + " characters. " +
                "Tap CLEAR, trim the text, and paste again. The app never silently cuts the text."
        )
        return
    }
    setText(payload)
}
