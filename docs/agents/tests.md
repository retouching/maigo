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

## Commands
```bash
./gradlew ktlintCheck detekt lintDebug
./gradlew testDebugUnitTest
./gradlew connectedDebugAndroidTest
```
