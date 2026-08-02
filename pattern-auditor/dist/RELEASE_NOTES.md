# Pattern AI-uditor v0.1.0

First shippable build. Native Kotlin Android app, Jetpack Compose Material 3, no network, no telemetry, no analytics, no cloud.

## What it does

Pastes, types, or accepts Android Share (	ext/plain) text. Runs the configured 71-pattern lexical ruleset on it, locally. Surfaces every match: pattern ID, name, severity, the exact matching phrase, the indicator that fired, the rule explanation, and the ruleset version. Refuses to call any result a lie, truth, intent, or diagnosis.

## What it does NOT do

- It does not connect to the network. (No INTERNET permission in the merged manifest.)
- It does not invent matches, fabricate confidence, or infer meaning.
- It does not run a Python or JavaScript runtime.
- It does not phone home, log analytics, or store any data outside the on-device process.

## Build artefacts

| File | Size | SHA-256 |
|------|------|---------|
| Pattern-AI-uditor-v0.1.0.apk | 5917234 | $apkHash |
| Pattern-AI-uditor-v0.1.0.aab | 5650100 | $aabHash |

pk is for direct install. ab is for Google Play Console upload.

## Tests

8/8 PatternAuditorTest + 2/2 LongTextScanTest pass.

## Permissions

None. The manifest does not request INTERNET, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, CAMERA, RECORD_AUDIO, READ_CONTACTS, LOCATION, READ_PHONE_STATE, BLUETOOTH, or any account or notification permission. The only permission that ends up in the final APK is the auto-injected DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION, which androidx.core adds on SDK 33+ for unexported dynamic receivers.

## Operator

- Operator: Justin Barnett (TruthProject)
- Contact: truth.project.official@gmail.com
- ORCID: https://orcid.org/0009-0009-2936-6966
- Sibling project: https://github.com/truthprojectofficial-max/truthasaservice

## License

Apache 2.0. Copyright 2026 Justin Barnett.