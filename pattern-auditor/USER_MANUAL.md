# Pattern AI-uditor — User Manual

One page. Read this before you tap anything.

## What you have

- An Android app called "Pattern AI-uditor".
- It runs entirely on your phone. It does not connect to the internet.
- It loads a fixed set of 71 patterns (DD-001..DD-071) and matches them
  against the text you give it.
- It tells you which patterns matched, what indicator fired, and shows
  the rule's explanation.

## How to install on a phone

### From a friend's APK file

1. Copy `app-release.apk` to the phone (USB, Drive, email, Telegram,
   anywhere).
2. On the phone, open the file from the file manager.
3. If the phone asks "Allow from this source", grant it once.
4. Tap Install.

### From the Play Store (when published)

1. Open Play Store, search "Pattern AI-uditor".
2. Tap Install.

## How to use it

### Type or paste

1. Open "Pattern AI-uditor".
2. Tap the text box.
3. Type or paste your text. Up to 50,000 characters.
4. Tap SCAN.
5. The result card lists every pattern that matched. If nothing
   matched, the card says "No configured pattern detected."

### Share from another app

1. In Chrome, Gmail, a reader, a notes app, anywhere: select text and
   choose Share.
2. Pick "Pattern AI-uditor" from the share sheet.
3. The app opens with the shared text already loaded. The scan starts
   automatically. You do not need to tap SCAN.

## What the result means

Each match is shown as a card with:

- the pattern ID and name (e.g. `DD-001 — Facade of Competence`)
- the exact phrase from your text that triggered the match
- the indicator (the keyword or phrase the pattern looks for)
- the explanation of the pattern
- the ruleset version

Below all the cards:

- `Ruleset: DD-001..DD-071 (71 patterns)`
- `Processed on this device`

## What the result does NOT mean

- It does not mean the text is a lie.
- It does not mean the writer is deceitful, guilty, or wrong.
- It does not measure truth, intent, or diagnosis.
- It does not upload anything, share anything, or phone home.
- "No configured pattern detected" does not mean the text is honest.
  It just means none of the 71 lexical indicators in the ruleset fired.

## Hard limits

- 50,000 character input cap. If you share something longer, the app
  shows a refusal. It does not silently cut your text.
- The ruleset is fixed at 71 patterns. If the build ever ships with a
  different count, the app refuses to start.

## Uninstall

Settings -> Apps -> Pattern AI-uditor -> Uninstall.
