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

Configure signing credentials via environment variables (`MAIGO_KEYSTORE_PATH`, `MAIGO_KEYSTORE_PASSWORD`, `MAIGO_KEY_ALIAS`, `MAIGO_KEY_PASSWORD`) or a gitignored `keystore.properties` file in the repository root (`storeFile`, `storePassword`, `keyAlias`, `keyPassword`), then run:

```bash
./gradlew assembleRelease
```

If no signing credentials are provided, `assembleRelease` still runs R8 full mode and produces an unsigned APK.

Release builds enable R8 full mode, resource shrinking, and disable Gradle dependency metadata blocks for reproducible F-Droid-friendly builds.

## Continuous integration & releases

GitHub Actions workflows live in `.github/workflows/`:

- `ci.yml` runs `ktlintCheck`, `detekt`, `lintDebug`, `testDebugUnitTest`, `assembleDebug`, and `assembleRelease` on pushes and pull requests to `master`.
- `release.yml` runs on `v*` tags, verifies the tag matches `versionName` in `app/build.gradle.kts`, builds a signed release APK, verifies its signature with `apksigner`, generates release notes from code commits (`feat`, `fix`, `refactor`, `perf`) since the previous tag, and publishes the APK and its `.sha256` checksum to a GitHub Release.

Configure these repository secrets in GitHub for `release.yml`:

- `MAIGO_KEYSTORE_BASE64` — base64-encoded release keystore (`base64 -w 0 release.jks`)
- `MAIGO_KEYSTORE_PASSWORD`
- `MAIGO_KEY_ALIAS`
- `MAIGO_KEY_PASSWORD`

## Running checks

```bash
./gradlew ktlintCheck detekt lintDebug
./gradlew testDebugUnitTest
```
