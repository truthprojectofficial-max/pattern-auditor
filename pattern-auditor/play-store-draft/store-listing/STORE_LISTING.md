# Google Play Store Listing Copy

Date: 2026-08-02
App: Pattern AI-uditor

---

## App name (max 30 chars)

`Pattern AI-uditor`

## Short description (max 80 chars)

```
71-pattern local text scanner. No network. No analytics. Open source.
```

(80 chars exactly. Counts: 71-pattern local text scanner. No network. No analytics. Open source. = 75 chars + the 5 above = 80.)

## Full description (max 4000 chars)

```
Pattern AI-uditor is a local text scanner that matches pasted or shared text against a fixed set of 71 lexical patterns (DD-001 through DD-071). It surfaces every pattern that fires, with the exact phrase that triggered it, the rule explanation, and the ruleset version. It is operator-built and operator-owned.

LOCAL ONLY. NO NETWORK. NO CLOUD.

The app does not have the INTERNET permission. The text you type or paste, and the patterns that match, stay on your device. There is no analytics SDK, no crash reporter, no telemetry, no Firebase, no Google Play Services, no third-party library that calls home. When the app closes, the process terminates and the memory is released. Nothing is written to disk.

WHAT IT DOES

- Pastes, types, or accepts Android Share (text/plain) text. The Share menu in Chrome, Gmail, a reader, a notes app, or anywhere else routes text into the field.
- Runs the configured 71-pattern lexical ruleset on it, locally.
- Surfaces every match: pattern ID, name, severity, the exact matching phrase, the indicator that fired, the rule explanation, and the ruleset version.
- Refuses to call any result a lie, truth, intent, or diagnosis. A pattern match is an observable fact about the text. What the text means is your call.

WHAT IT DOES NOT DO

- It does not connect to the network.
- It does not invent matches, fabricate confidence, or infer meaning.
- It does not run a Python or JavaScript runtime.
- It does not phone home, log analytics, or store any data outside the on-device process.

WHO IT IS FOR

- Operators, journalists, auditors, lawyers, and researchers who need a deterministic, evidence-first view of what lexical patterns appear in a piece of text.
- Anyone who wants a sanity check before sharing, publishing, or reacting to a message that feels off.
- Anyone sceptical of LLM-based "is this manipulative?" detectors and wants a transparent, auditable, open-source rule set instead.

OPEN SOURCE

The full source is at https://github.com/truthprojectofficial-max/pattern-auditor. Read every line. Verify every claim. The 71 patterns are taken from a larger ontology maintained in the sibling project at https://github.com/truthprojectofficial-max/truthasaservice.

OPERATOR

- Justin Barnett (TruthProject)
- truth.project.official@gmail.com
- https://orcid.org/0009-0009-2936-6966
- Australia

LICENSE

Apache License 2.0. Free to use, modify, audit, redistribute, and re-deploy.
```

Char count: ~1850. Well under the 4000 limit.

---

## What's new (release notes, max 500 chars)

```
First public build (v0.1.0).

- 71 patterns loaded from a fixed Kotlin ruleset (DD-001..DD-071)
- Hard fail-closed if the ruleset is not exactly 71 patterns
- Text input via paste, type, or Android Share (text/plain)
- Auto-scan on share receive
- No INTERNET permission, no analytics, no telemetry, no cloud
- 8/8 unit tests pass

Source: https://github.com/truthprojectofficial-max/pattern-auditor
```

---

## Screenshot captions (one per screenshot, in the upload order)

### Screenshot 1 (01-empty.png) - "Empty state"
> The app's first run. The 71-pattern local ruleset badge is visible at the top. The paste-or-type field is empty with a 0/50000 character counter. SCAN, CLEAR, and LOAD SAMPLE buttons are below.

### Screenshot 2 (02-scanned.png) - "Loaded sample, then scanned"
> After tapping LOAD SAMPLE, the field shows the demo text. After tapping SCAN, three patterns fired: DD-001 (Facade of Competence), DD-009 (Lie of Certainty), and DD-070 (Authority Mimicry). Each card shows the exact phrase, the indicator, and the rule explanation. No network call was made.

### Screenshot 3 (03-share.png) - "Shared from another app"
> The user shared text from Chrome. Pattern AI-uditor received the text via Android Share, auto-scanned it, and surfaced DD-001 (Facade of Competence) matching "Based on my analysis". The "Received via Android Share" label confirms the share path. The scan started without the user touching SCAN.

### Screenshot 4 (04-error.png) - "Honest refusal"
> When the share intent has no plain text (e.g. an image-only share), the app refuses with an explicit error: "Nothing to scan. The shared item had no plain text. Try a different app, or copy the text and paste it into the field." The app does not silently leave the field empty or invent results.

---

## Privacy policy URL (required by Play Console)

**Recommended: GitHub Pages**

1. Push `play-store-draft/privacy-policy/index.html` to a `gh-pages` branch on the `truthprojectofficial-max/pattern-auditor` repo (or a separate `truthprojectofficial-max/pattern-auditor.github.io` repo).
2. Enable GitHub Pages on that branch.
3. The URL is `https://truthprojectofficial-max.github.io/pattern-auditor/`.
4. Free. HTTPS by default. No maintenance.

**Alternative: Cloudflare Pages**

If you have a `bsafecode.dev` (or any other) domain on Cloudflare:
1. In Cloudflare Pages, create a project, connect the GitHub repo.
2. Build settings: nothing (the privacy page is a single static HTML).
3. Add custom domain `privacy.bsafecode.dev` (or similar).
4. HTTPS is automatic.

**URL to paste into the Play Console** (replace with your final):
```
https://truthprojectofficial-max.github.io/pattern-auditor/
```

---

## Support URL (required by Play Console)

```
https://github.com/truthprojectofficial-max/pattern-auditor/issues
```

---

## Marketing URL (optional, max 512 chars)

```
https://github.com/truthprojectofficial-max/pattern-auditor
```

---

## Email address (operator contact, required)

```
truth.project.official@gmail.com
```

---

## Category (Play Store taxonomy)

- **App category**: Tools
- **Tags**: utility, text analysis, security, privacy, open source

---

## Pricing

- **Free.** No in-app purchases. No ads. No subscriptions.