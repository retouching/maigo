# Architecture

## Gradle Module Graph

```text
:app
 ├── :feature:{home,search,library,player,history,radio,settings}
 │    ├── :core:designsystem ── :core:model
 │    ├── :core:data
 │    ├── :core:common ── :core:model
 │    └── :core:media (player/radio/home only)
 └── :core:*
      ├── :core:data ── :core:database, :core:network, :core:security, :core:media, :core:common, :core:model
      ├── :core:media ── :core:network, :core:common, :core:model
      ├── :core:network ── :core:security, :core:common, :core:model
      ├── :core:database ── :core:security, :core:common, :core:model
      ├── :core:security ── :core:common
      └── :core:testing (testImplementation / androidTestImplementation only)
```

## Convention Plugins (`build-logic/`)
- `maigo.jvm.library` — pure Kotlin JVM module (`allWarningsAsErrors`, JUnit 5).
- `maigo.android.library` — Android library (`minSdk 26`, `compileSdk 37`, Kotlin strict flags, Lint).
- `maigo.android.application` — `:app` configuration (R8 full mode, shrinking, reproducible metadata disabled).
- `maigo.android.compose` — Compose compiler plugin + Compose BOM.
- `maigo.android.hilt` — KSP + Hilt.
- `maigo.quality` — Detekt + ktlint wired to root configs.

## Multi-Backend Readiness
- `:core:network` defines a protocol-agnostic `MusicProvider` interface.
- V1 implements `OpenSubsonicProvider` (Navidrome, Gonic, Airsonic).
- Room keys synced entities by `(serverId, remoteId)` referencing `ServerEntity(id, type, baseUrl, ...)` so adding `JellyfinProvider` in V2 requires zero UI, database, or media rewrites.
