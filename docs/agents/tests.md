# Tests & Verification

## Zero-Ignored-Failures Rule
Fix every failing test, Detekt, ktlint, Android Lint, or Konsist check — even if it looks pre-existing or unrelated — as well as minor issues surfaced during review.

## Mandatory AVD Gate Before Executing a Plan
Before executing any plan or running E2E verification:
1. Run `adb devices` and `emulator -list-avds`.
2. Explicitly ask the owner whether `./gradlew connectedDebugAndroidTest` should run on an AVD/device or stay on JVM/Robolectric (`./gradlew testDebugUnitTest`).
3. Record the decision in `.orchestration/<plan-name>/constraints.md` and report clearly what ran on JVM vs AVD.

## 3-Tier Pyramid
- **Tier 1 — Unit (`src/test/`, pure JVM)**: JUnit 5 + `kotlinx-coroutines-test` + Turbine + `:core:testing` fakes (`FakeClock`, `TestDispatcherRule`, `RecordingLogger`, `FakeSecretStore`, `FakeMusicProvider`).
- **Tier 2 — Integration (`src/test/`, JVM + Robolectric / MockWebServer)**: Room in-memory DAOs/FTS/migrations, OkHttp `MockWebServer` OpenSubsonic parsing & secret redaction, offline-first repositories, and **Konsist** architecture/layout tests.
- **Tier 3 — UI / E2E (`src/androidTest/`, AVD/Device)**: Compose UI Test + Hilt + local `MockWebServer`.

## Instrumented Test Setup
- Convention plugins add `androidx.test:runner` and `androidx.test.ext:junit` to every Android module's `androidTestImplementation`; `maigo.android.compose` adds `ui-test-junit4` (plus `ui-test-manifest` in debug). Never drop them: without the runner, every test APK crashes at startup.
- Use the v2 Compose test API (`androidx.compose.ui.test.junit4.v2.createAndroidComposeRule`); the v1 rule is deprecated and fails `-Werror`.
- `:app` keeps `MainActivitySmokeTest`, which launches `MainActivity` and checks the app name renders.
- A physical device works like an AVD: enable USB debugging, authorize the computer, and confirm it shows as `device` in `adb devices`. With several targets attached, pick one with `ANDROID_SERIAL=<serial>`.

## Commands
```bash
./gradlew ktlintCheck detekt lintDebug
./gradlew testDebugUnitTest
./gradlew connectedDebugAndroidTest
```
