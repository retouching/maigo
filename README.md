<p align="center">
  <img alt="maigo" src="docs/assets/icon.svg" width="160">
</p>

<h1 align="center">maigo</h1>

<p align="center">
  Fast, private, offline-first Navidrome client for Android.
</p>

<p align="center">
  <img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-2.4-7F52FF">
  <img alt="Android 8.0+" src="https://img.shields.io/badge/Android-8.0%2B-3DDC84">
  <img alt="License Apache-2.0" src="https://img.shields.io/badge/license-Apache--2.0-blue">
</p>

`maigo` connects to any Navidrome or OpenSubsonic server and keeps your music library locally in an encrypted database so browsing, search, and playback work with or without a network connection. It ships with zero telemetry, zero tracking, and no Google Play Services dependency.

## Build

Prerequisites: Android SDK (`ANDROID_HOME` configured) and JDK 21+ (Gradle auto-provisions JDK 21 if needed).

```bash
./gradlew assembleDebug
./gradlew assembleRelease
```

## Commands

| Command | Purpose |
|---|---|
| `./gradlew assembleDebug` | Build debug APK |
| `./gradlew assembleRelease` | Build minified release APK (R8 full mode) |
| `./gradlew ktlintCheck` | Check Kotlin code formatting |
| `./gradlew detekt` | Run static analysis rules |
| `./gradlew lintDebug` | Run Android Lint checks |
| `./gradlew testDebugUnitTest` | Run JVM unit, integration, and architecture tests |
| `./gradlew connectedDebugAndroidTest` | Run instrumented UI and E2E tests on an AVD or device |

## Documentation

- [Usage](docs/usage.md): server connection, Online/Offline toggle, downloads vs cache, and private listening mode.
- [Building from source](docs/building.md): SDK setup, debug/release builds, and signing.
- [Privacy](docs/privacy.md): zero-telemetry guarantees, encrypted local storage, and permissions.
