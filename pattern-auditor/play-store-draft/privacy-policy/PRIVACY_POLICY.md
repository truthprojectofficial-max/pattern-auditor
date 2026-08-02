# Pattern AI-uditor - Privacy Policy

**Effective 2026-08-02.** Operator: Justin Barnett (TruthProject). Contact: truth.project.official@gmail.com

## Summary in one line

The app does not collect, transmit, store, log, or analyse any data outside your device. The text you scan is processed in memory and discarded.

## What the app does

Pattern AI-uditor is a local Android text scanner. You paste or share text into it, the app matches that text against a fixed set of 71 lexical patterns (DD-001 through DD-071) that are compiled into the APK, and the app shows you which patterns matched. That is the whole product.

## What data the app collects

None.

The app does not have the `android.permission.INTERNET` permission. It cannot make network requests. The Android system enforces this. There is no analytics SDK, no crash reporter, no telemetry, no Firebase, no Google Play Services dependency, and no third-party library that calls home.

## What data the app stores

The text you type or paste, the patterns that match, and the rule explanations are kept in volatile memory (RAM) only. The app does not write to the device's internal storage, external storage, shared preferences, or any database. When the app is closed, the process terminates and the memory is released to the operating system. The next launch starts with an empty text field and no results.

The app does not save a history of past scans, no clipboard, no screenshots, no logs of what you scanned.

## What the app shares

Nothing. The app does not upload, sync, copy, broadcast, or otherwise transmit anything to any other app, server, or service. The Android Share intent is *incoming only*: the app can receive text shared from another app into its input field, but it never shares anything out.

## Permissions

The merged manifest of the installed APK contains exactly one permission, which Android auto-injects and which the app does not request or use:

- `com.truthasaservice.ordergetitright.patternauditor.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION` &mdash; added automatically by AndroidX Core on Android 13+ for unexported dynamic receivers. The app has no other permissions. In particular it does not request `INTERNET`, `CAMERA`, `RECORD_AUDIO`, `READ_CONTACTS`, `LOCATION`, `READ_PHONE_STATE`, `BLUETOOTH`, `READ_EXTERNAL_STORAGE`, or `WRITE_EXTERNAL_STORAGE`.

## Children

The app is not directed at children under 13. The app does not collect any data from any user, so the question does not apply, but the operator does not knowingly solicit use by children.

## Changes to this policy

If the operator changes anything about data collection, this page is updated. The effective date at the top of the page is the authoritative version. Material changes are announced in the GitHub release notes.

## Open source

The full source code of the app, including every line of code that handles text, intents, and rendering, is public at https://github.com/truthprojectofficial-max/pattern-auditor. You can verify every claim in this policy by reading the source.

## Contact

- Operator: Justin Barnett (TruthProject)
- Email: truth.project.official@gmail.com
- ORCID: 0009-0009-2936-6966
- Sibling project: truthasaservice

## License

Pattern AI-uditor source code: Apache License 2.0. Copyright 2026 Justin Barnett.