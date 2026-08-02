# C:\AIDERTESTBOX — Pattern AI-uditor workspace

Aider is removed. This sandbox is now a clean Android build root.

## What is here

```
android-sdk\                    # ANDROID_HOME (cmdline-tools, platform-tools,
                                # platforms;android-34, build-tools;34.0.0)
gradle-dist\                    # Standalone Gradle 8.7 distribution
pattern-auditor\                # The Kotlin/Compose Android project
Location Aidersbox start..txt   # The original task file
DD-055  Cloud Displacement ...  # Operator note
practice_prompt.txt             # Operator note
```

## What is NOT here anymore

Aider, Aider venvs, Aider configs, Aider chat history, the Aider
launcher scripts (`START_AIDER.cmd`, `run_aider.ps1`), the Aider
rules and build-instruction markdown files. Aider did not earn its
keep in this sandbox. It is gone.

## Where the shippable artifacts are

```
pattern-auditor\app\build\outputs\apk\debug\app-debug.apk
pattern-auditor\app\build\outputs\apk\release\app-release.apk   <-- signed, for friends
pattern-auditor\app\build\outputs\bundle\release\app-release.aab <-- for Play Store
```

## To rebuild

```
set JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-17.0.20.8-hotspot
set ANDROID_HOME=C:\AIDERTESTBOX\android-sdk
set PATH=%JAVA_HOME%\bin;%ANDROID_HOME%\cmdline-tools\latest\bin;%ANDROID_HOME%\platform-tools;%PATH%
cd C:\AIDERTESTBOX\pattern-auditor
gradlew.bat :app:testDebugUnitTest
gradlew.bat :app:assembleDebug
gradlew.bat :app:assembleRelease
gradlew.bat :app:bundleRelease
```

## To install on the connected Pixel 7a

```
adb install -r pattern-auditor\app\build\outputs\apk\release\app-release.apk
adb shell am start -n com.truthasaservice.ordergetitright.patternauditor/.MainActivity
```

Or share `text/plain` from any other app and pick "Pattern AI-uditor".
