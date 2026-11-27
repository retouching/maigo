<p>
  <img src="assets/icon.svg" alt="Maigo icon" width="72" />
</p>

# Maigo

Fast, private, offline-first Navidrome client for Android.

Maigo connects to any Navidrome or OpenSubsonic server and mirrors your music library locally so browsing, searching, and playback stay responsive with or without a network connection. It ships with zero telemetry, zero tracking, encrypted local storage, and no dependency on Google Play Services.

## Features

- Connects to any Navidrome / OpenSubsonic server
- Offline-first library browsing and instant local search across tracks, artists, and albums
- Explicit offline downloads (albums, playlists, tracks) separate from automatic streaming cache
- Quick Online / Offline mode switch and manual server refresh
- Private listening mode (disables Now Playing, scrobbling, and local history)
- Internet Radio playback from Navidrome
- Volume normalization (ReplayGain) and equalizer
- Local playlist folders, pinned items, and listening history
- Encrypted local database (SQLCipher + Android Keystore) and zero telemetry

## Requirements

- Android 8.0 (API 26) or newer
- A Navidrome or OpenSubsonic-compatible server

## Build from source

Prerequisites: JDK 21+ and Android SDK (`ANDROID_HOME` configured).

```bash
./gradlew assembleDebug
./gradlew assembleRelease
```

## Documentation

- [Usage](docs/usage.md)
- [Building from source](docs/building.md)
- [Privacy](docs/privacy.md)

## License

Licensed under the [Apache License 2.0](LICENSE).
