# Building Maigo from Source

## Prerequisites

- JDK 21 or newer
- Android SDK with `ANDROID_HOME` set

## Debug build

```bash
./gradlew assembleDebug
```

The APK is written to `app/build/outputs/apk/debug/`.

## Release build

Configure signing credentials via environment variables (`MAIGO_KEYSTORE_PATH`, `MAIGO_KEYSTORE_PASSWORD`, `MAIGO_KEY_ALIAS`, `MAIGO_KEY_PASSWORD`) or a gitignored `keystore.properties` file in the repository root, then run:

```bash
./gradlew assembleRelease
```

Release builds enable R8 full mode, resource shrinking, and disable Gradle dependency metadata blocks for reproducible F-Droid-friendly builds.

## Running checks

```bash
./gradlew ktlintCheck detekt lintDebug
./gradlew testDebugUnitTest
```
