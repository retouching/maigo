# AGENTS.md

Maigo (`party.maigo.music`, `minSdk 26`) is a fast, private, offline-first Navidrome / OpenSubsonic Android client built in native Kotlin with Jetpack Compose, Material 3, Hilt, encrypted Room (SQLCipher + Android Keystore), OkHttp + `kotlinx.serialization`, Coil 3, and Jetpack Media3. It ships with **zero telemetry, zero analytics, and zero Google Play Services**.

## Definition of Done

1. Every behavior change is covered by tests at the appropriate tier (Unit JVM, Integration Robolectric/Room/MockWebServer, and UI/E2E when UI journeys change).
2. Run and pass before committing:
   - `./gradlew ktlintCheck detekt lintDebug`
   - `./gradlew testDebugUnitTest`
   - `./gradlew assembleDebug` (plus `./gradlew assembleRelease` when touching ProGuard, DI, or serialization)
   - `./gradlew connectedDebugAndroidTest` when approved by the owner for AVD/device testing.
3. **Zero ignored failures**: never dismiss a failing test, lint, Detekt, or Konsist check as pre-existing or unrelated. Fix every failure and every minor issue encountered during tests or review.
4. Update human docs (`README.md`, `docs/*.md`) for user-visible changes and AI agent docs (`AGENTS.md`, `docs/agents/*.md`) for architectural changes in the same commit.
5. Never run `pre-commit run` by hand or pass `--no-verify`.

## Before You Change a Part

| Area | Read first |
|---|---|
| Gradle modules, DI, backend abstraction, R8 | [docs/agents/architecture.md](docs/agents/architecture.md) |
| Compose UI, design system, 120 Hz stability, Coil 3 | [docs/agents/ui-and-compose.md](docs/agents/ui-and-compose.md) |
| Encrypted Room, FTS, sync, Offline/Private modes | [docs/agents/data-and-offline.md](docs/agents/data-and-offline.md) |
| Media3 playback, downloads, cache, ReplayGain, EQ, Radio | [docs/agents/media.md](docs/agents/media.md) |
| 3-tier tests, Konsist checks, AVD gate | [docs/agents/tests.md](docs/agents/tests.md) |
| Local `sketch` / `plan` / `execute` workflow | [docs/agents/workflow.md](docs/agents/workflow.md) |

## Module Boundaries & Layout Rules

- `:core:model` — pure JVM domain models, enums, sealed errors. Depends on nothing else.
- `:core:common` — `Clock`, `DispatcherProvider`, `AppLogger`, `SecretRedactor`, `AppResult`. Depends on `:core:model` only.
- `:core:security` — Android Keystore `SecretStore` (credentials + 256-bit DB passphrase), `AndroidAppLogger` (sole production caller of `android.util.Log`).
- `:core:database` — SQLCipher-encrypted Room database, entities (`*Entity`), DAOs, FTS tables, exported schemas, explicit migrations.
- `:core:network` — OkHttp, `kotlinx.serialization`, `MusicProvider` interface + `OpenSubsonicProvider`, DTOs (`*Dto`).
- `:core:media` — Media3 (`ExoPlayer`, `MediaSessionService`, `DownloadManager`, `SimpleCache`, ReplayGain, EQ, live radio).
- `:core:data` — offline-first repositories (Room SSOT), incremental sync, offline outbox, DataStore preferences, Online/Offline and Private session states.
- `:core:designsystem` — Material 3 theme, typography, shared dumb Compose primitives, Coil 3 wrapper. Depends on `:core:model` only.
- `:core:testing` — test-only shared fakes, fixtures, `TestDispatcherRule`.
- `:feature:*` — `home`, `search`, `library`, `player`, `history`, `radio`, `settings`. Never depends on another `:feature:*`, `:core:database`, or `:core:network`.
- `:app` — `@HiltAndroidApp`, `MainActivity`, root `NavHost`, ProGuard/R8 rules.

## Kotlin, Compose, Performance & Privacy Rules

- Zero `!!` non-null assertions, zero `GlobalScope`, zero `runBlocking` in production code.
- Always inject `DispatcherProvider` and `Clock`; never hardcode `Dispatchers.IO` or `System.currentTimeMillis()`.
- Never call `android.util.Log` outside `:core:security`; always log via `AppLogger` (redacted via `SecretRedactor`).
- Zero telemetry, zero analytics, zero Google Play Services.
- Room database must always open via SQLCipher with Keystore-backed 256-bit passphrase; never use `fallbackToDestructiveMigration()` in production.
- Compose 120 Hz discipline: `@Immutable` UI state, `ImmutableList`, mandatory `key` and `contentType` on every lazy list/grid, zero disk I/O or bitmap/color computation during composition or scroll.

## Documents

- Human docs (`README.md`, `docs/{usage,building,privacy}.md`) are strictly for humans and **never** mention or link to `AGENTS.md`, `CLAUDE.md`, `docs/agents/`, `.agents/`, `.antigravity/`, `.claude/`, or `.codex/`.
- AI agent docs (`AGENTS.md`, `CLAUDE.md -> AGENTS.md`, `docs/agents/*.md`) are strictly for coding agents.

## Git

- Conventional Commits in English (`feat:`, `fix:`, `refactor:`, `test:`, `docs:`, `build:`, `chore:`), small focused commits on the current branch (`master`).
- Never add `Co-Authored-By` trailers. Never push without asking.
- Fold late review fixes with `git commit --fixup=<sha>` + `GIT_SEQUENCE_EDITOR=: git rebase -i --autosquash` before pushing.

## Working with the Owner

- Build only what is asked. Ask open design decisions one question at a time with options and a recommendation.
- Feature changes follow `sketch` → `plan` → `execute` with specs and plans stored in `~/.cache/maigo/scratchpad/{specs,plans}/` (never in the repository).
- **Mandatory AVD gate**: before executing any plan, explicitly check `adb devices` and `emulator -list-avds` and ask the owner whether integration/E2E tests (`connectedDebugAndroidTest`) should run on an AVD/device or stay on JVM/Robolectric (`testDebugUnitTest`).

## Hooks

`commit-msg`, `pre-commit`, and `pre-push` are managed by `.pre-commit-config.yaml`. Git runs them automatically; never run `pre-commit run` by hand or pass `--no-verify`.

## Commands

```bash
./gradlew ktlintCheck detekt lintDebug
./gradlew testDebugUnitTest
./gradlew assembleDebug
./gradlew assembleRelease
./gradlew connectedDebugAndroidTest
```
