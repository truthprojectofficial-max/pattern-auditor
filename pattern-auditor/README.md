# Pattern AI-uditor

Local-only Android text scanner. Native Kotlin, Jetpack Compose Material 3,
no internet permission, no telemetry, no cloud.

The app loads a fixed ruleset of 71 deception patterns
(DD-001..DD-071), exactly as specified in the canonical
OrderGetItRight ontology. At startup it asserts the ruleset count is
exactly 71. If it isn't, the app stops with a visible configuration
error and never silently falls back to a smaller ruleset.

## What it does

- Pastes, types, or accepts Android Share (`text/plain`) text.
- Runs the configured 71-pattern lexical ruleset on it, locally.
- Surfaces every match: pattern ID, name, severity, the exact
  matching phrase, the indicator that fired, the rule explanation,
  and the ruleset version.
- Refuses to call any result a lie, truth, intent, or diagnosis.

## What it does NOT do

- It does not connect to the network. (No `INTERNET` permission in
  the merged manifest.)
- It does not invent matches, fabricate confidence, or infer meaning.
- It does not claim the text was a lie, that the speaker was deceitful,
  or that a person is guilty of anything. Patterns are observable
  matches against a configured ruleset, nothing more.
- It does not run a Python or JavaScript runtime.
- It does not phone home, log analytics, or store any data outside
  the on-device process.

## Versioning

`app/build.gradle.kts` carries `versionName` and `versionCode`. Bump
`versionCode` every time you install over an existing one so adb
actually replaces it.

## Build (local)

Required once:
- JDK 17 (Temurin recommended)
- Android SDK with `platform-tools`, `platforms;android-34`,
  `build-tools;34.0.0`
- `ANDROID_HOME` env var pointing at the SDK
- A `key.properties` file in the project root for release signing
  (see `app/build.gradle.kts`). Gitignored.

Build commands (from the project root):

```
gradlew.bat :app:testDebugUnitTest          # 8 unit tests
gradlew.bat :app:assembleDebug              # unsigned debug APK
gradlew.bat :app:assembleRelease            # signed release APK
gradlew.bat :app:bundleRelease              # signed release AAB (Play)
```

Outputs:
- `app\build\outputs\apk\debug\app-debug.apk`
- `app\build\outputs\apk\release\app-release.apk`
- `app\build\outputs\bundle\release\app-release.aab`

## Install on a connected device

```
adb install -r app\build\outputs\apk\debug\app-debug.apk
adb shell am start -n com.truthasaservice.ordergetitright.patternauditor/.MainActivity
```

Or share `text/plain` from any other app (Chrome, Gmail, a reader,
etc) and select "Pattern AI-uditor" as the target.

## Tests

8 unit tests, all pass:

- `ruleCount_isExactly71`
- `ruleCount_idsAreContiguousDD001ThroughDD071`
- `assertCount_doesNotThrow_whenCountIs71`
- `assertCount_throws_whenCountIsNot71`
- `sourceVersionLabel_isPresentOnEveryMatch`
- `scan_emptyText_returnsEmpty`
- `scan_unknownText_returnsEmpty`
- `neverClaimsLieTruthIntentDiagnosis`

## Permissions

None. The manifest does not request `INTERNET`, `READ_EXTERNAL_STORAGE`,
`WRITE_EXTERNAL_STORAGE`, `CAMERA`, `RECORD_AUDIO`, `READ_CONTACTS`,
`LOCATION`, `READ_PHONE_STATE`, `BLUETOOTH`, or any account or
notification permission. The only permission that ends up in the
final APK is the auto-injected
`com.truthasaservice.ordergetitright.patternauditor.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION`,
which androidx.core adds on SDK 33+ for unexported dynamic receivers.

## How the share path works

1. Another app fires `Intent.ACTION_SEND` with `type=text/plain` and
   the text in `Intent.EXTRA_TEXT`.
2. Android's chooser appears; "Pattern AI-uditor" is one of the
   targets (see `AndroidManifest.xml`).
3. Our `MainActivity.onCreate` (cold start) or
   `onNewIntent` (warm) receives the text.
4. The screen shows "Received via Android Share" and the loaded text.
5. The local scan starts automatically. No user tap required.
6. The result card replaces the "Scanning locally..." state when the
   scan completes.

## Hard limits

- `MAX_INPUT_CHARS = 50_000`. If a shared text exceeds this, the app
  shows a visible refusal and does NOT silently truncate.
- 71 patterns, hard-coded. The ruleset version string
  `DD-001..DD-071 (71 patterns)` is shown on every result.

## License

Personal, operator-owned. No external accounts, no network calls.

## Developer

- Operator: Justin Barnett (Juzzy Chance)
- Organisation: TruthProject
- Contact: truth.project.official@gmail.com
- ORCID: https://orcid.org/0009-0009-2936-6966
- Timezone: Australia, UTC +09:30
- Sibling project: https://github.com/truthprojectofficial-max/truthasaservice
